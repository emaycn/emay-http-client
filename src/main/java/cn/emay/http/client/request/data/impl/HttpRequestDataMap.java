package cn.emay.http.client.request.data.impl;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import cn.emay.http.client.request.data.HttpRequestData;

/**
 * Http 请求解析器：Map<String, String>
 * 
 * @author Frank
 *
 */
public class HttpRequestDataMap implements HttpRequestData {

	/**
	 * 数据
	 */
	private Map<String, String> data;

	/**
	 * 请求内容byte数组
	 */
	private byte[] contentBytes;

	/**
	 * 
	 * @param data
	 *            数据
	 */
	public HttpRequestDataMap(Map<String, String> data) {
		this.data = data;
	}

	private String toString(String charSet) {
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
		return param.substring(0, param.length() - 1);
	}

	@Override
	public byte[] toBytes(String charSet) {
		if (contentBytes != null) {
			return contentBytes;
		}
		String paramStr = toString(charSet);
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
