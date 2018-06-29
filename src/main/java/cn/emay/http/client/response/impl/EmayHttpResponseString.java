package cn.emay.http.client.response.impl;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import cn.emay.http.client.response.EmayHttpResponse;

/**
 * 解析String响应的解析器
 * 
 * @author Frank
 *
 */
public class EmayHttpResponseString extends EmayHttpResponse<String> {

	@Override
	public String parseResult(ByteArrayOutputStream outputStream) {
		String st = null;
		try {
			if (outputStream != null) {
				byte[] resultBytes = outputStream.toByteArray();
				st = new String(resultBytes, super.getCharSet());
			}
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
		return st;
	}

}
