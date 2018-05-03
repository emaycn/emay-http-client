package cn.emay.http.client.response.parser.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import cn.emay.http.client.common.HttpResultCode;
import cn.emay.http.client.response.impl.HttpResponseBytes;
import cn.emay.http.client.response.parser.HttpResponsePraser;

/**
 * 解析Byte[]响应的解析器
 * 
 * @author Frank
 *
 */
public class HttpResponseBytesPraser implements HttpResponsePraser<HttpResponseBytes> {

	@Override
	public HttpResponseBytes prase(HttpResultCode resultCode, int httpCode, Map<String, String> headers, List<String> cookies, String charSet, ByteArrayOutputStream outputStream) {
		return new HttpResponseBytes(resultCode, httpCode, headers, cookies, charSet, outputStream == null ? null : outputStream.toByteArray());
	}

}
