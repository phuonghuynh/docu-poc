package estalea.ir.membersite.accounting.w9.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by phuonghqh on 4/28/15.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TinServiceUser {

  @XmlElement(name = "UserLogin", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private String userLogin;

  @XmlElement(name = "UserPassword", namespace = "http://www.TinCheck.com/WebServices/PVSService/")
  private String userPassword;

  public String getUserLogin() {
    return userLogin;
  }

  public void setUserLogin(String userLogin) {
    this.userLogin = userLogin;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public static class TinServiceUserBuilder {
    private TinServiceUser tinServiceUser;

    private TinServiceUserBuilder() {
      tinServiceUser = new TinServiceUser();
    }

    public TinServiceUserBuilder withUserLogin(String userLogin) {
      tinServiceUser.userLogin = userLogin;
      return this;
    }

    public TinServiceUserBuilder withUserPassword(String userPassword) {
      tinServiceUser.userPassword = userPassword;
      return this;
    }

    public static TinServiceUserBuilder tinServiceUser() {
      return new TinServiceUserBuilder();
    }

    public TinServiceUser build() {
      return tinServiceUser;
    }
  }
}
