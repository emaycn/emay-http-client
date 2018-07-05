package cn.emay.http.client;

import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.emay.http.client.common.HttpHeader;
import cn.emay.http.client.https.HttpsStoreParams;
import cn.emay.http.client.request.HttpRequestData;
import cn.emay.http.client.request.impl.HttpRequestDataMap;
import cn.emay.http.client.response.HttpResponse;
import cn.emay.http.client.response.parser.impl.HttpResponseParserString;

public class TestHttp {

	public static void main(String[] args) throws UnsupportedEncodingException {

		String charSet = "UTF-8";

		String baseUrl = "127.0.0.1:8999/test1";
		Map<String, String> urlParams = new HashMap<>();
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

		HttpsStoreParams customHttpsParams = null;

		HttpClient.openDebug();

		HttpResponse response = null;

		response = HttpClient.get(url, charSet, headers, cookies, connectionTimeOut, readTimeOut, customHttpsParams);
		parse(response, charSet);

		response = HttpClient.get(baseUrl, urlParams, charSet, headers, cookies, connectionTimeOut, readTimeOut, customHttpsParams);
		parse(response, charSet);
		
		response = HttpClient.post(baseUrl, charSet, headers, cookies, requestData.toBytes(charSet), connectionTimeOut, readTimeOut, customHttpsParams);
		parse(response, charSet);

		response = HttpClient.post(baseUrl, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, customHttpsParams);
		parse(response, charSet);
	}

	private static void parse(HttpResponse response, String charSet) {

		System.out.println("code:" + response.getResultCode().getCode());
		System.out.println("httpcode:" + response.getHttpCode());
		System.out.println("data:" + response.getData(new HttpResponseParserString(), charSet));

		for (HttpCookie cookie : response.getCookies()) {
			System.out.println("cookie: name=" + cookie.getName() + " value=" + cookie.getValue());
		}

		for (HttpHeader header : response.getHeaders()) {
			System.out.println("header: name=" + header.getName() + " value=" + header.getValue());
		}
	}

}
