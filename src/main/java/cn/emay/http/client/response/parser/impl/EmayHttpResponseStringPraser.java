package cn.emay.http.client.response.parser.impl;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import cn.emay.http.client.common.EmayHttpResultCode;
import cn.emay.http.client.response.impl.EmayHttpResponseString;
import cn.emay.http.client.response.parser.EmayHttpResponsePraser;

/**
 * 解析String响应的解析器
 * 
 * @author Frank
 *
 */
public class EmayHttpResponseStringPraser implements EmayHttpResponsePraser<EmayHttpResponseString>{

	@Override
	public EmayHttpResponseString prase(EmayHttpResultCode resultCode, int httpCode, Map<String, String> headers, List<String> cookies, String charSet, ByteArrayOutputStream outputStream) {
		String st = null;
		try {
			if(outputStream != null){
				byte[] resultBytes = outputStream.toByteArray();
				st = new String(resultBytes, charSet);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new EmayHttpResponseString(resultCode, httpCode, headers, cookies, charSet, st);
	}

	
}
