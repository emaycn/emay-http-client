package cn.emay.http.client.response.parser.impl;

import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.util.List;

import cn.emay.http.client.common.HttpHeader;
import cn.emay.http.client.response.parser.HttpResponseParser;

/**
 * 解析响应为String的解析器
 * 
 * @author Frank
 *
 */
public class HttpResponseParserString implements HttpResponseParser<String> {

	@Override
	public String parseData(int httpCode, List<HttpHeader> headers, List<HttpCookie> cookies, String charSet, byte[] data) {
		if (data == null) {
			return null;
		}
		try {
			String charSet0 = charSet != null ? charSet : "UTF-8";
			return new String(data, charSet0);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
