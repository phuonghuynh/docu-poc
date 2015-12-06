package com.github.phuonghuynh.dto;

/**
 * Created by phuonghqh on 6/7/15.
 */
public class DocusignInfo {

  private String ssn;

  private String name;

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static class DocusignInfoBuilder {
    private DocusignInfo docusignInfo;

    private DocusignInfoBuilder() {
      docusignInfo = new DocusignInfo();
    }

    public DocusignInfoBuilder withSsn(String ssn) {
      docusignInfo.ssn = ssn;
      return this;
    }

    public DocusignInfoBuilder withName(String name) {
      docusignInfo.name = name;
      return this;
    }

    public static DocusignInfoBuilder docusignInfo() {
      return new DocusignInfoBuilder();
    }

    public DocusignInfo build() {
      return docusignInfo;
    }
  }
}
