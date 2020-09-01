package com.hulunbuir.clam.parent.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Explain:HC短信发送工具类
 * </p >
 *
 * @author wangjunming
 * @since 2019-10-08
 */
public class HCSmsUtil {

    private static Logger logger = LoggerFactory.getLogger(HCSmsUtil.class);

    /**
     * 请求编码utf-8
     */
    public static String hcSmsUtf8Url = "http://120.25.62.251:8888/sms.aspx";

    /**
     * 请求编码gbk
     */
    public static String hcSmsGBKUrl = "http://120.25.62.251:8888/smsGBK.aspx";

    /**
     * 企业ID-验证码
     */
    public static String userId_hy = "708";

    /**
     * 企业ID-通知
     */
    public static String userId_tz = "709";

    /**
     * 发送用户账号-验证码
     */
    public static String account_hy = "zljf-hy";

    /**
     * 发送账号密码-验证码
     */
    public static String password_hy = "zljf-hy";

    /**
     * 发送用户账号-通知
     */
    public static String account_tz = "zljf-tz";

    /**
     * 发送账号密码-通知
     */
    public static String password_tz = "zljf-tz";

//    @Value("${hcSmsUtf8Url}")
    public void setHcSmsUtf8Url(String hcSmsUtf8Url) {
        HCSmsUtil.hcSmsUtf8Url = hcSmsUtf8Url;
    }

//    @Value("${hcSmsGBKUrl}")
    public void setHcSmsGBKUrl(String hcSmsGBKUrl) {
        HCSmsUtil.hcSmsGBKUrl = hcSmsGBKUrl;
    }

//    @Value("${userId_hy}")
    public void setUserId_hy(String userId_hy) {
        HCSmsUtil.userId_hy = userId_hy;
    }

//    @Value("${userId_tz}")
    public void setUserId_tz(String userId_tz) {
        HCSmsUtil.userId_tz = userId_tz;
    }

//    @Value("${account_hy}")
    public void setAccount_hy(String account_hy) {
        HCSmsUtil.account_hy = account_hy;
    }

//    @Value("${password_hy}")
    public void setPassword_hy(String password_hy) {
        HCSmsUtil.password_hy = password_hy;
    }

//    @Value("${account_tz}")
    public void setAccount_tz(String account_tz) {
        HCSmsUtil.account_tz = account_tz;
    }

//    @Value("${password_tz}")
    public void setPassword_tz(String password_tz) {
        HCSmsUtil.password_tz = password_tz;
    }

    /**
     * 成功
     */
    private static String success = "Success";

    /**
     * 失败
     */
    private static String faild = "Faild";


    /**
     * 及时发送
     *
     * @return {
     * status：成功返回Success 失败返回：Faild
     * message :{
     * ok	提交成功
     * 用户名或密码不能为空	提交的用户名或密码为空
     * 发送内容包含sql注入字符	包含sql注入字符
     * 用户名或密码错误	表示用户名或密码错误
     * 短信号码不能为空	提交的被叫号码为空
     * 短信内容不能为空	发送内容为空
     * 包含非法字符：	表示检查到不允许发送的非法字符
     * 对不起，您当前要发送的量大于您当前余额	当支付方式为预付费是，检查到账户余额不足
     * 其他错误	其他数据库操作方面的错误
     * }
     * remainpoint： 余额
     * taskID： 返回本次任务的序列ID
     * successCounts：-成功短信数：当成功后返回提交成功短信数
     * }
     * <p>
     * {"returnstatus":"Success","successCounts":1,"message":"ok","remainpoint":21,"taskID":314471}
     * <returnsms>
     * <returnstatus>Success</returnstatus>
     * <message>ok</message>
     * <remainpoint>21</remainpoint>
     * <taskID>314471</taskID>
     * <successCounts>1</successCounts></returnsms>
     */
    private static String postMessageHy(String mobile, String content, String type) {
        logger.info("手机号：{},内容：{},类型：{}", mobile, content, type);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("sendTime", ""));
        params.add(new BasicNameValuePair("extno", ""));
        params.add(new BasicNameValuePair("action", "send"));
        params.add(new BasicNameValuePair("mobile", mobile));
        params.add(new BasicNameValuePair("content", content));
        String result = postHttpClient(params, type);
        logger.info("发送结果：" + result);
        Map<String, String> resultMap = parseXml(result);
        result = JSON.toJSONString(resultMap);
        JSONObject jsonObjects = JSONObject.parseObject(result);
        logger.info("发送结果转换成为json" + jsonObjects);
        logger.info("HC--商户短信发送结果-->{}", jsonObjects);
        String returnstatus = (String) jsonObjects.get("returnstatus");
        String successCounts = (String) jsonObjects.get("successCounts");
        String message = (String) jsonObjects.get("message");
        String remainpoint = (String) jsonObjects.get("remainpoint");
        String taskID = (String) jsonObjects.get("taskID");
        return returnstatus;
    }

    public static boolean postMessage(String mobile, String content, String type) {
        boolean flag = false;
        String returnStatus = postMessageHy(mobile, content, type);
        if (success.equals(returnStatus)) {
            flag = true;
            logger.info("HC--商户短信发送成功！！");
        } else if (faild.equals(returnStatus)) {
            logger.info("HC--商户短信发送失败！！");
        } else {
            logger.error("HC--商户短信发送异常！！");
        }
        return flag;
    }

    public  void mainas(String[] args) {
        String mobile = "15321355715";
        String content = "【磁云金服】融资申请（融资单号）已经提交成功，请等待资金方审核。";
        boolean b = postMessage(mobile, content, account_tz);
        System.out.println(b);

    }

    /**
     * post请求
     *
     * @param params
     * @param type
     * @return
     */
    private static String postHttpClient(List<NameValuePair> params, String type) {
        String responseResult = "";
        // 1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 创建HttpPost对象
        HttpPost httpPost = new HttpPost(hcSmsUtf8Url);
        // 3. 设置POST请求传递参数
        params.add(new BasicNameValuePair("userId", type.equals(account_hy) ? userId_hy : userId_tz));
        params.add(new BasicNameValuePair("account", type.equals(account_hy) ? account_hy : account_tz));
        params.add(new BasicNameValuePair("password", type.equals(account_hy) ? password_hy : password_tz));
        try {
            //进行url编码
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
            httpPost.setEntity(entity);
        } catch (Exception e) {
            logger.error("进行url编码异常-->", e);
        }
        // 4. 执行请求并处理响应
        try {
            logger.info(httpPost.getURI().toString());
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseResult = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            logger.error("发送异常-->", e);
        } finally {
            //释放资源
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                logger.error("释放资源异常-->{}", e);
            }
        }
        return responseResult;
    }

    public static Map<String, String> parseXml(String result) {
        Map<String, String> map = new HashMap<String, String>();
        Document document = null;
        try {
            document = DocumentHelper.parseText(result);
        } catch (DocumentException e) {
            logger.error("解析字符串的xml失败!!!" + e);
        }
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        return map;
    }

}
