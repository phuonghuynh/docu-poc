package estalea.ir.membersite.accounting.w9.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import estalea.ir.membersite.accounting.w9.dto.DocusignInfo;
import estalea.ir.membersite.accounting.w9.dto.SocialReqResp;

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
