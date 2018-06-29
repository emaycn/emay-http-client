package cn.emay.http.client.request.params;

import java.util.List;
import java.util.Map;

import cn.emay.http.client.common.EmayHttpCookie;

/**
 * Http参数
 * 
 * @author Frank
 *
 * @param <T>
 *            传输数据类型
 */
public abstract class EmayHttpRequestParams {

	/**
	 * URL
	 */
	private String url;
	/**
	 * 编码
	 */
	private String charSet = "UTF-8";
	/**
	 * Http方法
	 */
	private String method = "GET";
	/**
	 * 头信息
	 */
	private Map<String, String> headers;
	/**
	 * cookie信息
	 */
	private List<EmayHttpCookie> cookies;

	/**
	 * 
	 * @param url
	 *            URL
	 */
	public EmayHttpRequestParams(String url) {
		this(url,null,null,null,null);
	}
	
	/**
	 * 
	 * @param url
	 *            URL
	 * @param charSet
	 *            编码
	 * @param method
	 *            Http方法
	 * @param params
	 *            传输数据
	 */
	public EmayHttpRequestParams(String url, String charSet, String method) {
		this(url,charSet,method,null,null);
	}

	/**
	 * 
	 * @param url
	 *            URL
	 * @param charSet
	 *            编码
	 * @param method
	 *            Http方法
	 * @param headers
	 *            头信息
	 * @param cookies
	 *            cookie信息
	 * @param params
	 *            传输数据
	 */
	public EmayHttpRequestParams(String url, String charSet, String method, Map<String, String> headers, List<EmayHttpCookie> cookies) {
		this.url = url;
		if(charSet != null) {
			this.charSet = charSet;
		}
		if(method != null) {
			this.method = method;
		}
		this.headers = headers;
		this.cookies = cookies;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public List<EmayHttpCookie> getCookies() {
		return cookies;
	}

	public void setCookies(List<EmayHttpCookie> cookies) {
		this.cookies = cookies;
	}

}
