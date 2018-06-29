package cn.emay.http.client.request.data.impl;

import java.io.UnsupportedEncodingException;

import cn.emay.http.client.request.data.EmayHttpRequestData;

/**
 * Http 请求解析器：String
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestDataString implements EmayHttpRequestData {

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
	public EmayHttpRequestDataString(String data) {
		this.data = data;
	}

	@Override
	public String toString(String charSet) {
		return data;
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
