package com.van.util;

import com.alibaba.fastjson.JSONObject;




public class SendSMSValidate {
    public static boolean sendSms( String mobile,String param) {
        String sid="377ff0bf20c37ea7e4b25f2ed23c159e";
        String token="f364c943f9265ad1f86c8da5426cf290";
        String appid="2a89f5db3fba4bbd84fb8f69447ebf0d";
        String templateid="420243"; // 模板id


        boolean result = false;

        try {
            String url = "https://open.ucpaas.com/ol/sms/sendsms";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token", token);
            jsonObject.put("appid", appid);
            jsonObject.put("templateid", templateid);
            jsonObject.put("param", param);
            jsonObject.put("mobile", mobile);

            String body = jsonObject.toJSONString();

            System.out.println("body = " + body);

            result = HttpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
