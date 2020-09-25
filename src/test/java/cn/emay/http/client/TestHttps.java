package cn.emay.http.client;

import cn.emay.http.client.common.HttpHeader;
import cn.emay.http.client.https.HttpsCerParams;
import cn.emay.http.client.https.HttpsStoreParams;
import cn.emay.http.client.request.HttpRequestData;
import cn.emay.http.client.request.impl.HttpRequestDataMap;
import cn.emay.http.client.response.HttpResponse;
import cn.emay.http.client.response.parser.impl.HttpResponseParserString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Frank
 */
public class TestHttps {

    private static final Logger logger = LoggerFactory.getLogger(TestHttps.class);

    public static void main(String[] args) throws UnsupportedEncodingException {

        HttpClient.openDebug();

        String charSet = "UTF-8";

        String baseUrl = "https://sdx-st.com/hello.html";
        Map<String, String> urlParams = new HashMap<>(2);
        urlParams.put("key1", URLEncoder.encode("vv1&s=s", charSet));
        urlParams.put(URLEncoder.encode("key2&s=s", charSet), "vv2");
        String url = HttpClient.genGetUrl(baseUrl, urlParams);

        List<HttpHeader> headers = new ArrayList<>();
        headers.add(new HttpHeader("hello-header0", "world1"));
        headers.add(new HttpHeader("hello-header0", "world2"));
        headers.add(new HttpHeader("hello-header1", "world3&s=s"));
        List<HttpCookie> cookies = new ArrayList<>();
        cookies.add(new HttpCookie("hello-cookie0", "world1"));
        cookies.add(new HttpCookie("hello-cookie0", "world2"));
        cookies.add(new HttpCookie("hello-cookie1", "world3&s=s"));

        int connectionTimeOut = 10;
        int readTimeOut = 10;

        HttpRequestData requestData = new HttpRequestDataMap(urlParams);

        // 需要把证书导入密钥库，此处传入的是密钥库文件，而非证书
        HttpsStoreParams customHttpsParams = new HttpsStoreParams("123456", "C:\\Program Files\\apache-tomcat-7.0.64\\secuitry\\cacerts",
                "C:\\\\Program Files\\\\apache-tomcat-7.0.64\\\\secuitry\\\\cacerts", "JKS");

        HttpsCerParams cer = new HttpsCerParams("X.509", "C:\\\\Program Files\\\\apache-tomcat-7.0.64\\\\secuitry\\\\tomcat.cer");

        HttpResponse response;

        response = HttpClient.get(url, charSet, headers, cookies, connectionTimeOut, readTimeOut, customHttpsParams);
        parse(response, charSet);

        response = HttpClient.post(baseUrl, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, cer);
        parse(response, charSet);
    }

    private static void parse(HttpResponse response, String charSet) {

        logger.info("code:" + response.getResultCode().getCode());
        logger.info("httpCode:" + response.getHttpCode());
        logger.info("data:" + response.getData(new HttpResponseParserString(), charSet));

        for (HttpCookie cookie : response.getCookies()) {
            logger.info("cookie: name=" + cookie.getName() + " value=" + cookie.getValue());
        }

        for (HttpHeader header : response.getHeaders()) {
            logger.info("header: name=" + header.getName() + " value=" + header.getValue());
        }
    }

}
