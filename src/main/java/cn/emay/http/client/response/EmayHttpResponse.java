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

	/**
	 * 是否成功的响应
	 * 
	 * @return
	 */
	public boolean isSuccess() {
		return EmayHttpResultCode.SUCCESS.equals(resultCode);
	}

	/**
	 * 获取自定义转换规则的数据
	 * 
	 * @param parser
	 *            数据转换器
	 * @param charSet
	 *            编码
	 * @return
	 */
	public <T> T getData(EmayHttpResponseParser<T> parser, String charSet) {
		return parser.parseData(httpCode, headers, cookies, charSet, data);
	}

	/**
	 * 获取头信息
	 * 
	 * @param name
	 *            头名称
	 * @return
	 */
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

	/**
	 * 获取单个头信息
	 * 
	 * @param name
	 *            头名称
	 * @return
	 */
	public EmayHttpHeader getHeaderSingle(String name) {
		List<EmayHttpHeader> headerlist = getHeader(name);
		if (headerlist == null || headerlist.isEmpty()) {
			return null;
		}
		return headerlist.get(0);
	}

	/**
	 * 获取Cookie
	 * 
	 * @param name
	 *            Cookie名称
	 * @return
	 */
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

	/**
	 * 获取单个Cookie
	 * 
	 * @param name
	 *            Cookie名称
	 * @return
	 */
	public EmayHttpCookie getCookieSingle(String name) {
		List<EmayHttpCookie> cookielist = getCookie(name);
		if (cookielist == null || cookielist.isEmpty()) {
			return null;
		}
		return cookielist.get(0);
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
