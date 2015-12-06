package estalea.ir.membersite.accounting.w9.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import estalea.ir.membersite.accounting.w9.dto.DocusignInfo;
import estalea.ir.membersite.accounting.w9.dto.SocialReqResp;
import estalea.ir.membersite.accounting.w9.service.DocusignService;
import estalea.ir.membersite.accounting.w9.service.TinCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by phuonghqh on 4/4/15.
 */
@Controller
public class DocusignController {

  private static final Logger LOGGER = LoggerFactory.getLogger(DocusignController.class);

  @Resource
  private DocusignService docusignService;

  @Resource
  private TinCheckService tinCheckService;

  @ResponseBody
  @RequestMapping("/docusign/download/{envelopeId}")
  public void downloadCompletedEnvelope(@PathVariable String envelopeId, HttpServletResponse httpResponse) throws IOException, UnirestException {
    InputStream envelopeInputStream = docusignService.downloadCompletedEnvelop(envelopeId);
    ReadableByteChannel inputChannel = Channels.newChannel(envelopeInputStream);
    WritableByteChannel outputChannel = Channels.newChannel(httpResponse.getOutputStream());

    //example: 779b4559-ace4-49ba-a124-81c5ba167abc
    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);//in bytes
    long size = 0;
    while (inputChannel.read(buffer) != -1) {
      buffer.flip();
      size += outputChannel.write(buffer);
      buffer.clear();
    }
    LOGGER.debug("Download envelope {}, size = {}", envelopeId, size);
  }

  @RequestMapping("/docusign/complete/{envelopeId}")
  public void complete(@PathVariable String envelopeId, HttpServletResponse httpResponse) throws IOException, UnirestException {
    LOGGER.debug("Completed envelopId: {}", envelopeId);

    DocusignInfo docusignInfo = docusignService.extractSSN(envelopeId);

    String ssn = docusignInfo.getSsn();
    String name = docusignInfo.getName();

    if (tinCheckService.checkSSN(ssn, name)) {
      httpResponse.getWriter().write(String.format("SSN %s is correct for %s", ssn, name));
    }
    else {
      httpResponse.getWriter().write(String.format("SSN %s is NOT correct for %s", ssn, name));
    }
//fcffb3a5-593e-4d33-bf13-eaea5219fd04

//    JSONObject tabsJson = recipients.getObject().getJSONArray("signers").getJSONObject(0).getJSONObject("tabs");

//    JSONArray textTabs = tabsJson.getJSONArray("textTabs");
//    for (int i = 0; i < textTabs.length(); i++) {
//      JSONObject textJson = textTabs.getJSONObject(i);
//      String value = textJson.getString("value");
//      if ("Text 1".equalsIgnoreCase(textJson.getString("name")) && value != null) {
//        fName = value;
//      }
//
////      LOGGER.debug("TEXT name: {} , value: {}", textJson.getString("name"), textJson.getString("value"));
////      httpResponse.getWriter().write("\n" + "TEXT name: " + textJson.getString("name") + " , value: " + textJson.getString("value"));
//    }
//
//    JSONArray checkboxTabs = tabsJson.getJSONArray("checkboxTabs");
//    for (int i = 0; i < checkboxTabs.length(); i++) {
//      JSONObject checkbox = checkboxTabs.getJSONObject(i);
//      LOGGER.debug("CHECKBOX name: {} , value: {}", checkbox.getString("name"), checkbox.getString("selected"));
//      httpResponse.getWriter().write("\n" + "CHECKBOX name: " + checkbox.getString("name") + " , value: " + checkbox.getString("selected"));
//    }
  }

  @ResponseBody
  @RequestMapping("/docusign/createEnvelope")
  public SocialReqResp createEnvelope() throws IOException, UnirestException {
    return docusignService.createEnvelope();
  }

}
