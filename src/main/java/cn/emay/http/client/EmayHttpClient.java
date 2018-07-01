package cn.emay.http.client;

import java.util.List;

import cn.emay.http.client.common.EmayHttpCookie;
import cn.emay.http.client.common.EmayHttpHeader;
import cn.emay.http.client.logic.EmayHttpLogic;
import cn.emay.http.client.request.data.EmayHttpRequestData;
import cn.emay.http.client.request.https.EmayHttpsCustomParams;
import cn.emay.http.client.response.EmayHttpResponse;

/**
 * EMAY http客户端
 * 
 * @author Frank
 *
 */
public class EmayHttpClient {
	
	/*post*/

	public static EmayHttpResponse post(String url, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, "POST", requestData);
	}

	public static EmayHttpResponse post(String url, String charSet, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, "POST", charSet, requestData);
	}

	public static EmayHttpResponse post(String url,  String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, "POST", charSet, headers, cookies, requestData);
	}

	public static EmayHttpResponse post(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData,
			int connectionTimeOut, int readTimeOut) {
		return EmayHttpLogic.getInstance().service(url, "POST", charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut);
	}

	public static EmayHttpResponse post(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData,
			int connectionTimeOut, int readTimeOut, EmayHttpsCustomParams customHttpsParams) {
		return EmayHttpLogic.getInstance().service(url, "POST", charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, customHttpsParams);
	}
	
	/*get*/
	
	public static EmayHttpResponse get(String url) {
		return EmayHttpLogic.getInstance().service(url, "GET");
	}

	public static EmayHttpResponse get(String url, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, "GET", requestData);
	}

	public static EmayHttpResponse get(String url, String charSet, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, "GET", charSet, requestData);
	}

	public static EmayHttpResponse get(String url,  String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, "GET", charSet, headers, cookies, requestData);
	}

	public static EmayHttpResponse get(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData,
			int connectionTimeOut, int readTimeOut) {
		return EmayHttpLogic.getInstance().service(url, "GET", charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut);
	}

	public static EmayHttpResponse get(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData,
			int connectionTimeOut, int readTimeOut, EmayHttpsCustomParams customHttpsParams) {
		return EmayHttpLogic.getInstance().service(url, "GET", charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, customHttpsParams);
	}
	
	/*service*/

	public static EmayHttpResponse service(String url, String method) {
		return EmayHttpLogic.getInstance().service(url, method);
	}

	public static EmayHttpResponse service(String url, String method, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, method, requestData);
	}

	public static EmayHttpResponse service(String url, String method, String charSet, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, method, charSet, requestData);
	}

	public static EmayHttpResponse service(String url, String method, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, method, charSet, headers, cookies, requestData);
	}

	public static EmayHttpResponse service(String url, String method, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData,
			int connectionTimeOut, int readTimeOut) {
		return EmayHttpLogic.getInstance().service(url, method, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut);
	}

	public static EmayHttpResponse service(String url, String method, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData,
			int connectionTimeOut, int readTimeOut, EmayHttpsCustomParams customHttpsParams) {
		return EmayHttpLogic.getInstance().service(url, method, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, customHttpsParams);
	}

}
