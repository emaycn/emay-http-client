package cn.emay.http.client;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.emay.http.client.common.EmayHttpCookie;
import cn.emay.http.client.common.EmayHttpHeader;
import cn.emay.http.client.common.EmayHttpMethod;
import cn.emay.http.client.logic.EmayHttpLogic;
import cn.emay.http.client.request.data.EmayHttpRequestData;
import cn.emay.http.client.request.https.EmayHttpsCustomParams;
import cn.emay.http.client.response.EmayHttpResponse;

/**
 * EMAY http客户端<br/>
 * <br/>
 * 1.不进行URLENCODE，需要自己处理<br/>
 * 2.链接、读数据超时时间默认均为30秒<br/>
 * 3.默认编码为UTF-8<br/>
 * 
 * @author Frank
 *
 */
public class EmayHttpClient {

	/* debug */

	/**
	 * 打开DEBUG
	 */
	public static void openDebug() {
		EmayHttpLogic.getInstance().openDebug();
	}

	/**
	 * 关闭DEBUG
	 */
	public static void closeDebug() {
		EmayHttpLogic.getInstance().closeDebug();
	}

	/* post */

	/**
	 * post请求
	 * 
	 * @param url
	 *            链接
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @return
	 */
	public static EmayHttpResponse post(String url, byte[] requestData) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, requestData);
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            链接
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @return
	 */
	public static EmayHttpResponse post(String url, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, requestData.toBytes("UTF-8"));
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            链接
	 * @param charSet
	 *            编码
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @return
	 */
	public static EmayHttpResponse post(String url, String charSet, byte[] requestData) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, charSet, requestData);
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            链接
	 * @param charSet
	 *            编码
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @return
	 */
	public static EmayHttpResponse post(String url, String charSet, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, charSet, requestData.toBytes(charSet));
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            链接
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @return
	 */
	public static EmayHttpResponse post(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, byte[] requestData) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, charSet, headers, cookies, requestData);
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            链接
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @return
	 */
	public static EmayHttpResponse post(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, charSet, headers, cookies, requestData.toBytes(charSet));
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            链接
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @return
	 */
	public static EmayHttpResponse post(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, byte[] requestData, int connectionTimeOut, int readTimeOut) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut);
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            链接
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @return
	 */
	public static EmayHttpResponse post(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData, int connectionTimeOut,
			int readTimeOut) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, charSet, headers, cookies, requestData.toBytes(charSet), connectionTimeOut, readTimeOut);
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            链接
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @param customHttpsParams
	 *            自定义Https证书相关参数
	 * @return
	 */
	public static EmayHttpResponse post(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, byte[] requestData, int connectionTimeOut, int readTimeOut,
			EmayHttpsCustomParams customHttpsParams) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, customHttpsParams);
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            链接
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @param customHttpsParams
	 *            自定义Https证书相关参数
	 * @return
	 */
	public static EmayHttpResponse post(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData, int connectionTimeOut, int readTimeOut,
			EmayHttpsCustomParams customHttpsParams) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.POST, charSet, headers, cookies, requestData.toBytes(charSet), connectionTimeOut, readTimeOut, customHttpsParams);
	}

	/* get */

	/**
	 * get请求
	 * 
	 * @param url
	 *            链接
	 * @return
	 */
	public static EmayHttpResponse get(String url) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.GET);
	}

	/**
	 * get请求
	 * 
	 * @param baseUrl
	 *            基础URL
	 * @param urlParams
	 *            URL参数
	 * @return
	 */
	public static EmayHttpResponse get(String baseUrl, Map<String, String> urlParams) {
		return EmayHttpLogic.getInstance().service(genGetUrl(baseUrl, urlParams), EmayHttpMethod.GET);
	}

	/**
	 * get请求
	 * 
	 * @param url
	 *            链接
	 * @param charSet
	 *            编码
	 * @return
	 */
	public static EmayHttpResponse get(String url, String charSet) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.GET, charSet, null);
	}

	/**
	 * get请求
	 * 
	 * @param url
	 *            链接
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @return
	 */
	public static EmayHttpResponse get(String url, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.GET, "UTF-8", headers, cookies, null);
	}

	/**
	 * get请求
	 * 
	 * @param baseUrl
	 *            基础URL
	 * @param urlParams
	 *            URL参数
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @return
	 */
	public static EmayHttpResponse get(String baseUrl, Map<String, String> urlParams, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies) {
		return EmayHttpLogic.getInstance().service(genGetUrl(baseUrl, urlParams), EmayHttpMethod.GET, charSet, headers, cookies, null);
	}

	/**
	 * get请求
	 * 
	 * @param url
	 *            链接
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @return
	 */
	public static EmayHttpResponse get(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.GET, charSet, headers, cookies, null);
	}

	/**
	 * get请求
	 * 
	 * @param baseUrl
	 *            基础URL
	 * @param urlParams
	 *            URL参数
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @return
	 */
	public static EmayHttpResponse get(String baseUrl, Map<String, String> urlParams, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, int connectionTimeOut,
			int readTimeOut) {
		return EmayHttpLogic.getInstance().service(genGetUrl(baseUrl, urlParams), EmayHttpMethod.GET, charSet, headers, cookies, null, connectionTimeOut, readTimeOut);
	}

	/**
	 * get请求
	 * 
	 * @param url
	 *            链接
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @return
	 */
	public static EmayHttpResponse get(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, int connectionTimeOut, int readTimeOut) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.GET, charSet, headers, cookies, null, connectionTimeOut, readTimeOut);
	}

	/**
	 * get请求
	 * 
	 * @param baseUrl
	 *            基础URL
	 * @param urlParams
	 *            URL参数
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @param customHttpsParams
	 *            自定义Https证书相关参数
	 * @return
	 */
	public static EmayHttpResponse get(String baseUrl, Map<String, String> urlParams, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, int connectionTimeOut,
			int readTimeOut, EmayHttpsCustomParams customHttpsParams) {
		return EmayHttpLogic.getInstance().service(genGetUrl(baseUrl, urlParams), EmayHttpMethod.GET, charSet, headers, cookies, null, connectionTimeOut, readTimeOut, customHttpsParams);
	}

	/**
	 * get请求
	 * 
	 * @param url
	 *            链接
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @param customHttpsParams
	 *            自定义Https证书相关参数
	 * @return
	 */
	public static EmayHttpResponse get(String url, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, int connectionTimeOut, int readTimeOut,
			EmayHttpsCustomParams customHttpsParams) {
		return EmayHttpLogic.getInstance().service(url, EmayHttpMethod.GET, charSet, headers, cookies, null, connectionTimeOut, readTimeOut, customHttpsParams);
	}

	/* service */

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @return
	 */
	public static EmayHttpResponse service(String url, EmayHttpMethod method) {
		return EmayHttpLogic.getInstance().service(url, method);
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @return
	 */
	public static EmayHttpResponse service(String url, EmayHttpMethod method, byte[] requestData) {
		return EmayHttpLogic.getInstance().service(url, method, requestData);
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @param charSet
	 *            编码
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @return
	 */
	public static EmayHttpResponse service(String url, EmayHttpMethod method, String charSet, byte[] requestData) {
		return EmayHttpLogic.getInstance().service(url, method, charSet, requestData);
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @return
	 */
	public static EmayHttpResponse service(String url, EmayHttpMethod method, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, byte[] requestData) {
		return EmayHttpLogic.getInstance().service(url, method, charSet, headers, cookies, requestData);
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @return
	 */
	public static EmayHttpResponse service(String url, EmayHttpMethod method, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, byte[] requestData, int connectionTimeOut,
			int readTimeOut) {
		return EmayHttpLogic.getInstance().service(url, method, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut);
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @param customHttpsParams
	 *            自定义Https证书相关参数
	 * @return
	 */
	public static EmayHttpResponse service(String url, EmayHttpMethod method, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, byte[] requestData, int connectionTimeOut,
			int readTimeOut, EmayHttpsCustomParams customHttpsParams) {
		return EmayHttpLogic.getInstance().service(url, method, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, customHttpsParams);
	}

	/* util */

	/**
	 * 将基础URL与URL参数拼接成完整URL<br/>
	 * 
	 * @param baseUrl
	 *            基础URL
	 * @param urlParams
	 *            URL参数
	 * @return
	 */
	public static String genGetUrl(String baseUrl, Map<String, String> urlParams) {
		if (urlParams == null || urlParams.isEmpty()) {
			return baseUrl;
		}
		StringBuffer buffer = new StringBuffer();
		for (Entry<String, String> entry : urlParams.entrySet()) {
			if (entry.getValue() != null) {
				String value = entry.getValue();
				buffer.append(entry.getKey()).append("=").append(value).append("&");
			}
		}
		String param = buffer.toString();
		String getprams = param.substring(0, param.length() - 1);
		if (getprams == null || !"".equalsIgnoreCase(getprams)) {
			return baseUrl;
		}
		if (baseUrl.indexOf("?") > 0) {
			return baseUrl + "&" + getprams;
		} else {
			return baseUrl + "?" + getprams;
		}
	}

}
