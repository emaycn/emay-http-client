package cn.emay.http.client.request.params.impl;

import java.io.UnsupportedEncodingException;

import cn.emay.http.client.request.params.EmayHttpRequestParams;

/**
 * Http 请求解析器：byte[]
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestParamsBytes extends EmayHttpRequestParams<byte[]> {

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
