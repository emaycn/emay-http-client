package cn.emay.http.client.request.params.impl;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import cn.emay.http.client.request.params.EmayHttpRequestParams;

/**
 * Http 请求解析器：byte[]
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestParamsBytes extends EmayHttpRequestParams<byte[]> {

	/**
	 * 
	 * @param url
	 *            URL
	 */
	public EmayHttpRequestParamsBytes(String url) {
		super(url);
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
	public EmayHttpRequestParamsBytes(String url, String charSet, String method, Map<String, String> headers, Map<String, String> cookies,byte[] params) {
		super(url, charSet, method, headers, cookies, params);
	}
	
	/**
	 * 请求内容字符串
	 */
	private String contentString;

	public String paramsToString() {
		if (getParams() == null) {
			return null;
		}
		if (contentString != null) {
			return contentString;
		}
		try {
			contentString = new String(getParams(), getCharSet());
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
		return contentString;
	}

	public byte[] paramsToBytes() {
		return getParams();
	}

	public int paramsLength() {
		if (getParams() != null) {
			return getParams().length;
		} else {
			return 0;
		}
	}

}
