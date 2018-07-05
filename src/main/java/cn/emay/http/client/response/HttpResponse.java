package cn.emay.http.client.response;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

import cn.emay.http.client.common.HttpHeader;
import cn.emay.http.client.common.HttpResultCode;
import cn.emay.http.client.response.parser.HttpResponseParser;

/**
 * Http响应
 * 
 * @author Frank
 *
 */
public class HttpResponse {

	/**
	 * Http 结果代码
	 */
	private HttpResultCode resultCode;

	/**
	 * Http链接Code
	 */
	private int httpCode;

	/**
	 * Http响应头
	 */
	private List<HttpHeader> headers;

	/**
	 * http响应Cookies
	 */
	private List<HttpCookie> cookies;

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
	public HttpResponse(HttpResultCode resultCode, int httpCode, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] data, Throwable throwable) {
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
		return HttpResultCode.SUCCESS.equals(resultCode);
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
	public <T> T getData(HttpResponseParser<T> parser, String charSet) {
		return parser.parseData(httpCode, headers, cookies, charSet, data);
	}

	/**
	 * 获取头信息
	 * 
	 * @param name
	 *            头名称
	 * @return
	 */
	public List<HttpHeader> getHeader(String name) {
		if (name == null) {
			return null;
		}
		if (headers == null || headers.isEmpty()) {
			return null;
		}
		List<HttpHeader> list = new ArrayList<HttpHeader>();
		for (HttpHeader header : headers) {
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
	public HttpHeader getHeaderSingle(String name) {
		List<HttpHeader> headerlist = getHeader(name);
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
	public List<HttpCookie> getCookie(String name) {
		if (name == null) {
			return null;
		}
		if (cookies == null || cookies.isEmpty()) {
			return null;
		}
		List<HttpCookie> list = new ArrayList<HttpCookie>();
		for (HttpCookie header : cookies) {
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
	public HttpCookie getCookieSingle(String name) {
		List<HttpCookie> cookielist = getCookie(name);
		if (cookielist == null || cookielist.isEmpty()) {
			return null;
		}
		return cookielist.get(0);
	}

	public HttpResultCode getResultCode() {
		return resultCode;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public List<HttpHeader> getHeaders() {
		return headers;
	}

	public List<HttpCookie> getCookies() {
		return cookies;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public byte[] getData() {
		return data;
	}

}
