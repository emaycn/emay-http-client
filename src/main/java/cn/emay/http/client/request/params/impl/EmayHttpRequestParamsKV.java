package cn.emay.http.client.request.params.impl;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import cn.emay.http.client.request.params.EmayHttpRequestParams;

/**
 * Http 请求解析器：Map<String, String>
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestParamsKV extends EmayHttpRequestParams<Map<String, String>> {
	

	/**
	 * 
	 * @param url
	 *            URL
	 */
	public EmayHttpRequestParamsKV(String url) {
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
	public EmayHttpRequestParamsKV(String url, String charSet, String method, Map<String, String> headers, Map<String, String> cookies, Map<String, String> params) {
		super(url, charSet, method, headers, cookies, params);
	}

	/**
	 * 请求内容byte数组
	 */
	private byte[] contentBytes;

	/**
	 * 请求内容字符串
	 */
	private String contentString;

	@Override
	public String paramsToString() {
		if (contentString != null) {
			return contentString;
		}
		Map<String, String> params = getParams();
		if (params == null || params.isEmpty()) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for (Entry<String, String> entry : params.entrySet()) {
			if (entry.getValue() != null) {
				buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
		}
		String param = buffer.toString();
		contentString = param.substring(0, param.length() - 1);
		return contentString;
	}

	@Override
	public byte[] paramsToBytes() {
		if (contentBytes != null) {
			return contentBytes;
		}
		String paramStr = paramsToString();
		if (paramStr == null) {
			return null;
		}
		try {
			contentBytes = paramStr.getBytes(getCharSet());
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
