package cn.emay.http.client.request.params.impl;

import java.io.UnsupportedEncodingException;

import cn.emay.http.client.request.params.EmayHttpRequestParams;

/**
 * Http 请求解析器：String
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestParamsString extends EmayHttpRequestParams<String> {

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
