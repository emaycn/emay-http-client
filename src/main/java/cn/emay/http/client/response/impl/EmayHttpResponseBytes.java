package cn.emay.http.client.response.impl;

import java.util.List;
import java.util.Map;

import cn.emay.http.client.common.EmayHttpResultCode;
import cn.emay.http.client.response.EmayHttpResponse;

/**
 * http 响应: byte[]
 * 
 * @author Frank
 *
 */
public class EmayHttpResponseBytes extends EmayHttpResponse<byte[]> {

	/**
	 * 
	 * @param resultCode
	 *            Http 结果代码
	 * @param httpCode
	 *            Http链接Code
	 * @param headers
	 *            Http响应头
	 * @param cookies
	 *            http响应Cookies
	 * @param charSet
	 *            http字符集
	 * @param result
	 *            http响应数据
	 */
	public EmayHttpResponseBytes(EmayHttpResultCode resultCode, int httpCode, Map<String, String> headers, List<String> cookies, String charSet, byte[] result) {
		super(resultCode, httpCode, headers, cookies, charSet, result);
	}

}
