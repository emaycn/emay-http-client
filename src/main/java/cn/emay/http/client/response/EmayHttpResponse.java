package cn.emay.http.client.response;

import java.util.List;
import java.util.Map;

import cn.emay.http.client.common.EmayHttpResultCode;

/**
 * Http响应
 * 
 * @author Frank
 *
 */
public class EmayHttpResponse<T> {

	/**
	 * Http 结果代码
	 */
	private EmayHttpResultCode resultCode;

	/**
	 * Http链接Code
	 */
	private int httpCode;

	/**
	 * Http响应头
	 */
	private Map<String, String> headers;

	/**
	 * http响应Cookies
	 */
	private List<String> cookies;

	/**
	 * http字符集
	 */
	private String charSet;

	/**
	 * http响应数据
	 */
	private T result;

	/**
	 * 异常
	 */
	private Throwable throwable;

	public EmayHttpResponse() {

	}

	/**
	 * 
	 * @param resultCode
	 *            Http 结果代码
	 * @param httpCode
	 *            Http链接Code
	 * @param headers
	 *            Http响应头
	 * @param cookies
	 *            http响应Cookies
	 * @param charSet
	 *            http字符集
	 * @param result
	 *            http响应数据
	 * @param throwable
	 *            异常
	 */
	public EmayHttpResponse(EmayHttpResultCode resultCode, int httpCode, Map<String, String> headers, List<String> cookies, String charSet, T result, Throwable throwable) {
		this.resultCode = resultCode;
		this.httpCode = httpCode;
		this.headers = headers;
		this.cookies = cookies;
		this.charSet = charSet;
		this.result = result;
		this.throwable = throwable;
	}

	public EmayHttpResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(EmayHttpResultCode resultCode) {
		this.resultCode = resultCode;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public List<String> getCookies() {
		return cookies;
	}

	public void setCookies(List<String> cookies) {
		this.cookies = cookies;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

}
