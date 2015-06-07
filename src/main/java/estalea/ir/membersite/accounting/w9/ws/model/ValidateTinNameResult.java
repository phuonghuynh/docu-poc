package estalea.ir.membersite.accounting.w9.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by phuonghqh on 4/28/15.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidateTinNameResult {

  @XmlElement(name = "TINNAME_CODE", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private Integer tinNameCode;

  @XmlElement(name = "TINNAME_DETAILS", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private String tinNameDetails;

  @XmlElement(name = "STATUS", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private ServiceStatusResult serviceStatusResult;

  @XmlElement(name = "DMF_CODE", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private Integer dmfCode;

  @XmlElement(name = "DMF_DETAILS", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private String dmfDetails;

  @XmlElement(name = "DMF_DATA", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private String dmfData;

  @XmlElement(name = "EIN_CODE", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private Integer einCode;

  @XmlElement(name = "EIN_DETAILS", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private String einDetails;

  @XmlElement(name = "EIN_DATA", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private String einData;

  @XmlElement(name = "GIIN_CODE", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private Integer giinCode;

  @XmlElement(name = "GIIN_DETAILS", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private String giinDetails;

  @XmlElement(name = "GIIN_DATA", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private String giinData;

  public Integer getTinNameCode() {
    return tinNameCode;
  }

  public void setTinNameCode(Integer tinNameCode) {
    this.tinNameCode = tinNameCode;
  }

  public String getTinNameDetails() {
    return tinNameDetails;
  }

  public void setTinNameDetails(String tinNameDetails) {
    this.tinNameDetails = tinNameDetails;
  }

  public ServiceStatusResult getServiceStatusResult() {
    return serviceStatusResult;
  }

  public void setServiceStatusResult(ServiceStatusResult serviceStatusResult) {
    this.serviceStatusResult = serviceStatusResult;
  }

  public Integer getDmfCode() {
    return dmfCode;
  }

  public void setDmfCode(Integer dmfCode) {
    this.dmfCode = dmfCode;
  }

  public String getDmfDetails() {
    return dmfDetails;
  }

  public void setDmfDetails(String dmfDetails) {
    this.dmfDetails = dmfDetails;
  }

  public String getDmfData() {
    return dmfData;
  }

  public void setDmfData(String dmfData) {
    this.dmfData = dmfData;
  }

  public Integer getEinCode() {
    return einCode;
  }

  public void setEinCode(Integer einCode) {
    this.einCode = einCode;
  }

  public String getEinDetails() {
    return einDetails;
  }

  public void setEinDetails(String einDetails) {
    this.einDetails = einDetails;
  }

  public String getEinData() {
    return einData;
  }

  public void setEinData(String einData) {
    this.einData = einData;
  }

  public Integer getGiinCode() {
    return giinCode;
  }

  public void setGiinCode(Integer giinCode) {
    this.giinCode = giinCode;
  }

  public String getGiinDetails() {
    return giinDetails;
  }

  public void setGiinDetails(String giinDetails) {
    this.giinDetails = giinDetails;
  }

  public String getGiinData() {
    return giinData;
  }

  public void setGiinData(String giinData) {
    this.giinData = giinData;
  }
}
