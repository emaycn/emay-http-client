package cn.emay.http.client;

import java.util.List;

import cn.emay.http.client.common.EmayHttpCookie;
import cn.emay.http.client.common.EmayHttpHeader;
import cn.emay.http.client.common.EmayHttpMethod;
import cn.emay.http.client.logic.EmayHttpLogic;
import cn.emay.http.client.request.data.EmayHttpRequestData;
import cn.emay.http.client.request.https.EmayHttpsCustomParams;
import cn.emay.http.client.response.EmayHttpResponse;

/**
 * EMAY http客户端<br/>
 * POST PUT等同<br/>
 * 大数据传输及响应未考虑<br/>
 * 
 * @author Frank
 *
 */
public class EmayHttpClient {

	/* debug */

	public static void openDebug() {
		 EmayHttpLogic.getInstance().openDebug();
	}

	/* post */

	public static EmayHttpResponse post(String url, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, requestData);
	}

	public static EmayHttpResponse post(String url, String charSet, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, charSet, requestData);
	}

	public static EmayHttpResponse post(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, charSet, headers, cookies, requestData);
	}

	public static EmayHttpResponse post(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData, int connectionTimeOut,
			int readTimeOut) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut);
	}

	public static EmayHttpResponse post(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData, int connectionTimeOut, int readTimeOut,
			EmayHttpsCustomParams customHttpsParams) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, customHttpsParams);
	}

	/* get */

	public static EmayHttpResponse get(String url) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.GET);
	}

	public static EmayHttpResponse get(String url, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.GET, requestData);
	}

	public static EmayHttpResponse get(String url, String charSet, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.GET, charSet, requestData);
	}

	public static EmayHttpResponse get(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.GET, charSet, headers, cookies, requestData);
	}

	public static EmayHttpResponse get(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData, int connectionTimeOut,
			int readTimeOut) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.GET, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut);
	}

	public static EmayHttpResponse get(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData, int connectionTimeOut, int readTimeOut,
			EmayHttpsCustomParams customHttpsParams) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.GET, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, customHttpsParams);
	}

	/* service */

	public static EmayHttpResponse service(String url, EmayHttpMethod method) {
		return EmayHttpLogic.getInstance().service(url, method);
	}

	public static EmayHttpResponse service(String url, EmayHttpMethod method, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, method, requestData);
	}

	public static EmayHttpResponse service(String url, EmayHttpMethod method, String charSet, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, method, charSet, requestData);
	}

	public static EmayHttpResponse service(String url, EmayHttpMethod method, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, method, charSet, headers, cookies, requestData);
	}

	public static EmayHttpResponse service(String url, EmayHttpMethod method, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData,
			int connectionTimeOut, int readTimeOut) {
		return EmayHttpLogic.getInstance().service(url, method, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut);
	}

	public static EmayHttpResponse service(String url, EmayHttpMethod method, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData,
			int connectionTimeOut, int readTimeOut, EmayHttpsCustomParams customHttpsParams) {
		return EmayHttpLogic.getInstance().service(url, method, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, customHttpsParams);
	}

}
