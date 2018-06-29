package cn.emay.http.client.response.impl;

import java.io.ByteArrayOutputStream;

import cn.emay.http.client.response.EmayHttpResponse;

/**
 * 解析Byte[]响应的解析器
 * 
 * @author Frank
 *
 */
public class EmayHttpResponseBytes extends EmayHttpResponse<byte[]> {

	@Override
	public byte[] parseResult(ByteArrayOutputStream outputStream) {
		return outputStream == null ? null : outputStream.toByteArray();
	}

}
