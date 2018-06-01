package cn.emay.http.client.request.params.impl;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import cn.emay.http.client.request.params.EmayHttpRequestParams;

/**
 * Http 请求解析器：String
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestParamsString extends EmayHttpRequestParams<String> {

	/**
	 * 
	 * @param url
	 *            URL
	 */
	public EmayHttpRequestParamsString(String url) {
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
	public EmayHttpRequestParamsString(String url, String charSet, String method, Map<String, String> headers, Map<String, String> cookies, String params) {
		super(url, charSet, method, headers, cookies, params);
	}

	/**
	 * 请求内容byte数组
	 */
	private byte[] contentBytes;

	@Override
	public String paramsToString() {
		return getParams();
	}

	@Override
	public byte[] paramsToBytes() {
		if (getParams() == null) {
			return null;
		}
		if (contentBytes != null) {
			return contentBytes;
		}
		try {
			contentBytes = getParams().getBytes(getCharSet());
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
		return contentBytes;
	}

	@Override
	public int paramsLength() {
		paramsToBytes();
		if (contentBytes != null) {
			return contentBytes.length;
		} else {
			return 0;
		}
	}

}
