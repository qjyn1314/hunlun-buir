package com.hulunbuir.admin.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.HashMap;

/**
 * 2018/12/21
 * 创建人：Wjunming
 */
@WebService
public class WebServiceService {

    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:8009/Service/Function",new WebServiceService());
        System.out.println("Publish Success~");
    }

    @WebMethod
    public String getWetherByCityName(String cityName){
        HashMap<String,String> cityWether = new HashMap<String, String>();
        cityWether.put("北京","北京现在是零下十度");
        cityWether.put("南京","南京现在是零下十度");
        return cityWether.get(cityName);
    }
}
