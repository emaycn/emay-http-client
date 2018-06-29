package cn.emay.http.client.request.data.impl;

import cn.emay.http.client.request.data.EmayHttpRequestData;

/**
 * Http 请求解析器：byte[]
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestDataBytes implements EmayHttpRequestData {

	/**
	 * 数据
	 */
	private byte[] data;

	/**
	 * 
	 * @param data
	 *            数据
	 */
	public EmayHttpRequestDataBytes(byte[] data) {
		this.data = data;
	}

	public String toString(String charSet) {
		return null;
	}

	public byte[] toBytes(String charSet) {
		return data;
	}
}
