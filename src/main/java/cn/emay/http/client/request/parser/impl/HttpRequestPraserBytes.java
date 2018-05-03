package cn.emay.http.client.request.parser.impl;

import java.io.UnsupportedEncodingException;

import cn.emay.http.client.request.params.HttpRequestParams;
import cn.emay.http.client.request.parser.HttpRequestPraser;

/**
 * Http 请求解析器：byte[]
 * 
 * @author Frank
 *
 */
public class HttpRequestPraserBytes implements HttpRequestPraser<byte[]> {

	/**
	 * 请求内容字符串
	 */
	private String contentString;

	@Override
	public String praseRqeuestContentToString(HttpRequestParams<byte[]> httpParams) {
		if (contentString != null) {
			return contentString;
		}
		try {
			contentString = new String(httpParams.getParams(), httpParams.getCharSet());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return contentString;
	}

	@Override
	public byte[] praseRqeuestContentToBytes(HttpRequestParams<byte[]> httpParams) {
		return httpParams.getParams();
	}

	@Override
	public int praseRqeuestContentLength(HttpRequestParams<byte[]> httpParams) {
		if (httpParams.getParams() != null) {
			return httpParams.getParams().length;
		} else {
			return 0;
		}
	}

}
