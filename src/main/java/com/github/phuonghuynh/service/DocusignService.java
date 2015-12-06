package com.github.phuonghuynh.service;

import com.github.phuonghuynh.dto.DocusignInfo;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.github.phuonghuynh.dto.SocialReqResp;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by phuonghqh on 6/7/15.
 */
public interface DocusignService {
  DocusignInfo extractSSN(String envelopeId) throws IOException, UnirestException;

  SocialReqResp createEnvelope() throws IOException, UnirestException;

  InputStream downloadCompletedEnvelop(String envelopId) throws IOException, UnirestException;
}
