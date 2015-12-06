package estalea.ir.membersite.accounting.w9.ws;

import estalea.ir.membersite.accounting.w9.ws.model.TinServiceUser;
import estalea.ir.membersite.accounting.w9.ws.model.ServiceStatusResult;
import estalea.ir.membersite.accounting.w9.ws.model.TinName;
import estalea.ir.membersite.accounting.w9.ws.model.ValidateTinNameResult;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by phuonghqh on 4/28/15.
 */

@WebService(targetNamespace = "http://www.TinCheck.com/WebServices/PVSService/")
public interface TinWebService {

  @WebMethod(operationName = "ServiceStatus")
  @WebResult(name = "ServiceStatusResult", targetNamespace = "http://www.TinCheck.com/WebServices/PVSService/")
  ServiceStatusResult serviceStatus(
    @WebParam(name = "CurUser", targetNamespace = "http://www.TinCheck.com/WebServices/PVSService/")
    TinServiceUser input);

  @WebMethod(operationName = "ValidateTinName")
  @WebResult(name = "ValidateTinNameResult", targetNamespace = "http://www.TinCheck.com/WebServices/PVSService/")
  ValidateTinNameResult validateTinName(
    @WebParam(name = "TinName", targetNamespace = "http://www.TinCheck.com/WebServices/PVSService/")
    TinName tinName,
    @WebParam(name = "CurUser", targetNamespace = "http://www.TinCheck.com/WebServices/PVSService/")
    TinServiceUser input);



}
