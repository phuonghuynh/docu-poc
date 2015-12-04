package com.github.phuonghuynh.service;

import com.github.phuonghuynh.ws.TinWebService;
import com.github.phuonghuynh.ws.model.TinName;
import com.github.phuonghuynh.ws.model.TinServiceUser;
import com.github.phuonghuynh.ws.model.ValidateTinNameResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Created by phuonghqh on 4/29/15.
 */
@Service
public class TinCheckService {

  @Value("${tinCheckWs.user}")
  private String tinWsUser;

  @Value("${tinCheckWs.pwd}")
  private String tinWsPwd;

  @Resource
  private TinWebService tinWebService;

  public boolean checkSSN(String ssn, String name) {
    ValidateTinNameResult validateTinNameResult = tinWebService.validateTinName(
      TinName.TinNameBuilder.tinName().withTin(ssn).withName(name).build(),
      TinServiceUser.TinServiceUserBuilder.tinServiceUser().withUserLogin(tinWsUser).withUserPassword(tinWsPwd).build()
    );
    Integer tinNameCode = validateTinNameResult.getTinNameCode();
    return Arrays.asList(1, 6, 7, 8).contains(tinNameCode);
  }
}
