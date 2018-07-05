package cn.emay.http.client.request.impl;

import java.io.UnsupportedEncodingException;

import cn.emay.http.client.request.HttpRequestData;

/**
 * Http 请求解析器：String
 * 
 * @author Frank
 *
 */
public class HttpRequestDataString implements HttpRequestData {

	/**
	 * 数据
	 */
	private String data;

	/**
	 * 请求内容byte数组
	 */
	private byte[] contentBytes;

	/**
	 * 
	 * @param data
	 *            数据
	 */
	public HttpRequestDataString(String data) {
		this.data = data;
	}

	@Override
	public byte[] toBytes(String charSet) {
		if (contentBytes != null) {
			return contentBytes;
		}
		if (data == null) {
			return null;
		}
		try {
			contentBytes = data.getBytes(charSet);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
		return contentBytes;
	}

}
