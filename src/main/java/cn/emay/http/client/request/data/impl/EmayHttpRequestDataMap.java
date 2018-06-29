package cn.emay.http.client.request.data.impl;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import cn.emay.http.client.request.data.EmayHttpRequestData;

/**
 * Http 请求解析器：Map<String, String>
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestDataMap implements EmayHttpRequestData {

	/**
	 * 数据
	 */
	private Map<String, String> data;

	/**
	 * 请求内容byte数组
	 */
	private byte[] contentBytes;

	/**
	 * 请求内容字符串
	 */
	private String contentString;

	/**
	 * 
	 * @param data
	 *            数据
	 */
	public EmayHttpRequestDataMap(Map<String, String> data) {
		this.data = data;
	}

	@Override
	public String toString(String charSet) {
		if (contentString != null) {
			return contentString;
		}
		if (data == null || data.isEmpty()) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for (Entry<String, String> entry : data.entrySet()) {
			if (entry.getValue() != null) {
				buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
		}
		String param = buffer.toString();
		contentString = param.substring(0, param.length() - 1);
		return contentString;
	}

	@Override
	public byte[] toBytes(String charSet) {
		if (contentBytes != null) {
			return contentBytes;
		}
		String paramStr = toString();
		if (paramStr == null) {
			return null;
		}
		try {
			contentBytes = paramStr.getBytes(charSet);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
		return contentBytes;
	}

}
