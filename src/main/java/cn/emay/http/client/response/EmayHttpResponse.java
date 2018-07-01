package cn.emay.http.client.response;

import java.util.ArrayList;
import java.util.List;

import cn.emay.http.client.common.EmayHttpCookie;
import cn.emay.http.client.common.EmayHttpHeader;
import cn.emay.http.client.common.EmayHttpResultCode;
import cn.emay.http.client.response.parser.EmayHttpResponseParser;

/**
 * Http响应
 * 
 * @author Frank
 *
 */
public class EmayHttpResponse {

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
	private List<EmayHttpHeader> headers;

	/**
	 * http响应Cookies
	 */
	private List<EmayHttpCookie> cookies;

	/**
	 * http响应数据
	 */
	private byte[] data;

	/**
	 * 异常
	 */
	private Throwable throwable;

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
	 * @param data
	 *            http响应数据
	 * @param throwable
	 *            异常
	 */
	public EmayHttpResponse(EmayHttpResultCode resultCode, int httpCode, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, byte[] data, Throwable throwable) {
		this.resultCode = resultCode;
		this.httpCode = httpCode;
		this.headers = headers;
		this.cookies = cookies;
		this.data = data;
		this.throwable = throwable;
	}
	
	public boolean isSuccess() {
		return httpCode == 200 && EmayHttpResultCode.SUCCESS.equals(resultCode);
	}

	public <T> T getData(EmayHttpResponseParser<T> parser, String charSet) {
		return parser.parseData(httpCode, headers, cookies, charSet, data);
	}

	public List<EmayHttpHeader> getHeader(String name) {
		if (name == null) {
			return null;
		}
		if (headers == null || headers.isEmpty()) {
			return null;
		}
		List<EmayHttpHeader> list = new ArrayList<EmayHttpHeader>();
		for (EmayHttpHeader header : headers) {
			if (header.getName().equals(name)) {
				list.add(header);
			}
		}
		return list;
	}

	public List<EmayHttpCookie> getCookie(String name) {
		if (name == null) {
			return null;
		}
		if (cookies == null || cookies.isEmpty()) {
			return null;
		}
		List<EmayHttpCookie> list = new ArrayList<EmayHttpCookie>();
		for (EmayHttpCookie header : cookies) {
			if (header.getName().equals(name)) {
				list.add(header);
			}
		}
		return list;
	}

	public EmayHttpResultCode getResultCode() {
		return resultCode;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public List<EmayHttpHeader> getHeaders() {
		return headers;
	}

	public List<EmayHttpCookie> getCookies() {
		return cookies;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public byte[] getData() {
		return data;
	}

}
