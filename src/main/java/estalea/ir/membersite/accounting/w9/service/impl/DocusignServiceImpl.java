package estalea.ir.membersite.accounting.w9.service.impl;

import com.jayway.jsonpath.JsonPath;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import estalea.ir.membersite.accounting.w9.dto.DocusignInfo;
import estalea.ir.membersite.accounting.w9.dto.SocialReqResp;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by phuonghqh on 6/7/15.
 */
@Service
public class DocusignServiceImpl implements estalea.ir.membersite.accounting.w9.service.DocusignService {

  @Value("classpath:docusign-auth.json")
  private org.springframework.core.io.Resource docusignAuthRes;

  @Value("classpath:docusign-create-envelop.json")
  private org.springframework.core.io.Resource docusignCreateEnvelopRes;

  @Value("classpath:docusign-view.json")
  private org.springframework.core.io.Resource docusignViewRes;

  @Value("${docusign.templateName}")
  private String templateName;

  private static final String BASE_URL = "https://demo.docusign.net/restapi/v2/";

  private static final String BRANDS_URL = BASE_URL + "accounts/%s/brands";

  private static final String TEMPLATE_URL = BASE_URL + "accounts/%s/templates";

  private static final String ENVELOP_URL = BASE_URL + "accounts/%s/envelopes";

  private static final String ENVELOP_RECEIPT_URL = BASE_URL + "accounts/%s/envelopes/%s/recipients?include_tabs=true";

  private static final String EMBEDDED_VIEW_URL = BASE_URL + "accounts/%s/envelopes/%s/views/recipient";

  private static final Logger LOGGER = LoggerFactory.getLogger(DocusignServiceImpl.class);

  @Override
  public DocusignInfo extractSSN(String envelopeId) throws IOException, UnirestException {
    JsonNode loginInfo = Unirest.get(BASE_URL + "login_information")
      .header("X-DocuSign-Authentication", getLoginInfoJson())
      .asJson().getBody();
    String accountId = loginInfo.getObject().getJSONArray("loginAccounts").getJSONObject(0).getString("accountId");
    LOGGER.debug("Account id = {}", accountId);

    JsonNode recipients = Unirest.get(String.format(ENVELOP_RECEIPT_URL, accountId, envelopeId))
      .header("X-DocuSign-Authentication", getLoginInfoJson())
      .asJson().getBody();

    Object document = com.jayway.jsonpath.Configuration.defaultConfiguration().jsonProvider()
      .parse(recipients.toString());
    String name = JsonPath.read(document, "$.signers[0].tabs.textTabs[0].value");
    LOGGER.debug("Name = {}", name);

    String ssn = String.format("%s%s%s",
      ((net.minidev.json.JSONArray) JsonPath.read(document, "$.signers[0].tabs.textTabs[?(@.name=='Text 18')].value")).get(0),
      ((net.minidev.json.JSONArray) JsonPath.read(document, "$.signers[0].tabs.textTabs[?(@.name=='Text 19')].value")).get(0),
      ((net.minidev.json.JSONArray) JsonPath.read(document, "$.signers[0].tabs.textTabs[?(@.name=='Text 20')].value")).get(0));
    LOGGER.debug("SSN = {}", ssn);

    return DocusignInfo.DocusignInfoBuilder.docusignInfo().withName(name).withSsn(ssn).build();
  }

  @Override
  public SocialReqResp createEnvelope() throws IOException, UnirestException {
    JsonNode loginInfo = Unirest.get(BASE_URL + "login_information")
      .header("X-DocuSign-Authentication", getLoginInfoJson())
      .asJson().getBody();

    String accountId = loginInfo.getObject().getJSONArray("loginAccounts").getJSONObject(0).getString("accountId");
    LOGGER.debug("Account id = {}", accountId);

    String brandId = getRecipientBrandIdDefault(accountId);
    LOGGER.debug("Brand id = {}", brandId);

    String templateId = getTemplateId(accountId, templateName);
    LOGGER.debug("Template Name = {} , Template id = {}", templateName, templateId);

    if (templateId != null) {
      String envelopeId = createEnvelope(accountId, templateId, brandId);
      LOGGER.debug("EnvelopeId: {}", envelopeId);

      String viewUrl = createEnvelopeView(accountId, envelopeId);
      LOGGER.debug("viewUrl: {}", viewUrl);
      return SocialReqResp.SocialReqRespBuilder.socialReqResp().withWidgetUrl(viewUrl).build();
    }
    return null;
  }

  private String createEnvelopeView(String accountId, String envelopeId) throws IOException, UnirestException {
    JsonNode view = Unirest.post(String.format(EMBEDDED_VIEW_URL, accountId, envelopeId))
      .header("X-DocuSign-Authentication", getLoginInfoJson())
      .header("Content-Type", "application/json").header("accept", "application/json")
      .body(getViewJson(envelopeId)).asJson().getBody();
    return view.getObject().getString("url");
  }

  private String getTemplateId(String accountId, String templateName) throws IOException, UnirestException {
    JSONObject templateInfo = Unirest.get(String.format(TEMPLATE_URL, accountId))
      .header("X-DocuSign-Authentication", getLoginInfoJson())
      .asJson().getBody().getObject();

    JSONArray templates = templateInfo.getJSONArray("envelopeTemplates");
    for (int i = 0; i < templateInfo.getInt("resultSetSize"); i++) {
      JSONObject template = templates.getJSONObject(i);
      if (templateName.equals(template.getString("name"))) {
        return template.getString("templateId");
      }
    }
    return null;
  }

  private String getRecipientBrandIdDefault(String accountId) throws IOException, UnirestException {
    JsonNode brandInfo = Unirest.get(String.format(BRANDS_URL, accountId))
      .header("X-DocuSign-Authentication", getLoginInfoJson())
      .asJson().getBody();
    return brandInfo.getObject().getString("recipientBrandIdDefault");
  }

  private String createEnvelope(String accountId, String templateId, String brandId) throws IOException, UnirestException {
    String createEnvelope = FileUtils.readFileToString(docusignCreateEnvelopRes.getFile(), "UTF-8");//new String(Files.readAllBytes(Paths.get(docusignCreateEnvelopRes.getURI())));
    createEnvelope = String.format(createEnvelope, templateId, brandId);
    JsonNode envelope = Unirest.post(String.format(ENVELOP_URL, accountId))
      .header("X-DocuSign-Authentication", getLoginInfoJson())
      .header("Content-Type", "application/json").header("accept", "application/json")
      .body(createEnvelope).asJson().getBody();
    String envelopeId = envelope.getObject().getString("envelopeId");
    return envelopeId;
  }

  private String getViewJson(String envelopeId) throws IOException {
    String consoleView = FileUtils.readFileToString(docusignViewRes.getFile(), "UTF-8");//new String(Files.readAllBytes(Paths.get(docusignViewRes.getURI()))).replace("\n", "").replace("\r", "");
    return String.format(consoleView, envelopeId);
  }

  private String getLoginInfoJson() throws IOException {
    return FileUtils.readFileToString(docusignAuthRes.getFile(), "UTF-8").replace("\n", "").replace("\r", ""); //new String(Files.readAllBytes(Paths.get(docusignAuthRes.getURI())))
  }
}
