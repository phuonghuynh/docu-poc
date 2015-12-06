package com.github.phuonghuynh.dto;

/**
 * Created by phuonghqh on 12/11/14.
 */
public class SocialReqResp {

  private String token;
  private String clientId;
  private String code;
  private String redirectUri;
  private String widgetUrl;
  private String baseUrl;
  private String widgetId;

  public String getWidgetId() {
    return widgetId;
  }

  public void setWidgetId(String widgetId) {
    this.widgetId = widgetId;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public String getWidgetUrl() {
    return widgetUrl;
  }

  public void setWidgetUrl(String widgetUrl) {
    this.widgetUrl = widgetUrl;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  public void setRedirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public static class SocialReqRespBuilder {
    private SocialReqResp socialReqResp;

    private SocialReqRespBuilder() {
      socialReqResp = new SocialReqResp();
    }

    public SocialReqRespBuilder withToken(String token) {
      socialReqResp.token = token;
      return this;
    }

    public SocialReqRespBuilder withClientId(String clientId) {
      socialReqResp.clientId = clientId;
      return this;
    }

    public SocialReqRespBuilder withCode(String code) {
      socialReqResp.code = code;
      return this;
    }

    public SocialReqRespBuilder withRedirectUri(String redirectUri) {
      socialReqResp.redirectUri = redirectUri;
      return this;
    }

    public SocialReqRespBuilder withWidgetUrl(String widgetUrl) {
      socialReqResp.widgetUrl = widgetUrl;
      return this;
    }

    public SocialReqRespBuilder withBaseUrl(String baseUrl) {
      socialReqResp.baseUrl = baseUrl;
      return this;
    }

    public SocialReqRespBuilder withWidgetId(String widgetId) {
      socialReqResp.widgetId = widgetId;
      return this;
    }

    public static SocialReqRespBuilder socialReqResp() {
      return new SocialReqRespBuilder();
    }

    public SocialReqResp build() {
      return socialReqResp;
    }
  }
}
