package estalea.ir.membersite.accounting.w9.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by phuonghqh on 4/28/15.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceStatusResult {

  @XmlElement(name = "Status", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private String status;

  @XmlElement(name = "CallsRemaining", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private Integer callsRemaining;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getCallsRemaining() {
    return callsRemaining;
  }

  public void setCallsRemaining(Integer callsRemaining) {
    this.callsRemaining = callsRemaining;
  }
}
