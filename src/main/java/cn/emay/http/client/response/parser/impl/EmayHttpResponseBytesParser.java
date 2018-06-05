package cn.emay.http.client.response.parser.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import cn.emay.http.client.common.EmayHttpResultCode;
import cn.emay.http.client.response.impl.EmayHttpResponseBytes;
import cn.emay.http.client.response.parser.EmayHttpResponseParser;

/**
 * 解析Byte[]响应的解析器
 * 
 * @author Frank
 *
 */
public class EmayHttpResponseBytesParser implements EmayHttpResponseParser<EmayHttpResponseBytes> {

	@Override
	public EmayHttpResponseBytes parse(EmayHttpResultCode resultCode, int httpCode, Map<String, String> headers, List<String> cookies, String charSet, ByteArrayOutputStream outputStream,
			Throwable throwable) {
		return new EmayHttpResponseBytes(resultCode, httpCode, headers, cookies, charSet, outputStream == null ? null : outputStream.toByteArray(), throwable);
	}

}
