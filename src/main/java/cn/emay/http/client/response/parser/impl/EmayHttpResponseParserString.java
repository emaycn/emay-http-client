package cn.emay.http.client.response.parser.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cn.emay.http.client.common.EmayHttpCookie;
import cn.emay.http.client.common.EmayHttpHeader;
import cn.emay.http.client.response.parser.EmayHttpResponseParser;

/**
 * 解析String响应的解析器
 * 
 * @author Frank
 *
 */
public class EmayHttpResponseParserString implements EmayHttpResponseParser<String> {

	@Override
	public String parseData(int httpCode, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, String charSet, byte[] data) {
		try {
			return new String(data, charSet);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

}