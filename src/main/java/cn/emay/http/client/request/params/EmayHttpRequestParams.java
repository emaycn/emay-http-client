package cn.emay.http.client.request.params;

import java.util.Map;

/**
 * Http参数
 * 
 * @author Frank
 *
 * @param <T>
 *            传输数据类型
 */
public abstract class EmayHttpRequestParams<T> {

	private String url;// URL
	private String charSet = "UTF-8";// 编码
	private String method = "GET";// Http方法
	private Map<String, String> headers;// 头信息
	private Map<String, String> cookies;// cookie信息
	private T params;// 传输数据

	/**
	 * 
	 * @param url
	 *            URL
	 */
	public EmayHttpRequestParams(String url) {
		this.url = url;
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
	public EmayHttpRequestParams(String url, String charSet, String method, Map<String, String> headers, Map<String, String> cookies, T params) {
		this.url = url;
		this.charSet = charSet;
		this.method = method;
		this.headers = headers;
		this.cookies = cookies;
		this.params = params;
	}

	/**
	 * 将请求参数转换为String<br/>
	 * 主要用于get方法传输
	 * 
	 * @param httpParams
	 *            请求参数
	 * @return
	 */
	public abstract String paramsToString();

	/**
	 * 将请求参数转换为byte[]<br/>
	 * 主要用于post方法传输
	 * 
	 * @param httpParams
	 *            请求参数
	 * @return
	 */
	public abstract byte[] paramsToBytes();

	/**
	 * 获取请求参数大小<br/>
	 * 主要用于post方法传输
	 * 
	 * @param httpParams
	 *            请求参数
	 * @return
	 */
	public abstract int paramsLength();

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

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public T getParams() {
		return params;
	}

	public void setParams(T params) {
		this.params = params;
	}

}
