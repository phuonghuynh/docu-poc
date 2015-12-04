package com.github.phuonghuynh.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by phuonghqh on 4/28/15.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TinName {

  @XmlElement(name = "TIN", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private String tin;

  @XmlElement(name = "LName", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private String name;

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static class TinNameBuilder {
    private TinName tinName;

    private TinNameBuilder() {
      tinName = new TinName();
    }

    public TinNameBuilder withTin(String tin) {
      tinName.tin = tin;
      return this;
    }

    public TinNameBuilder withName(String name) {
      tinName.name = name;
      return this;
    }

    public static TinNameBuilder tinName() {
      return new TinNameBuilder();
    }

    public TinName build() {
      return tinName;
    }
  }
}
