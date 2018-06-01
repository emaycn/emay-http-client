package cn.emay.http.client.request.parser.impl;

import java.io.UnsupportedEncodingException;

import cn.emay.http.client.request.params.EmayHttpRequestParams;
import cn.emay.http.client.request.parser.EmayHttpRequestPraser;

/**
 * Http 请求解析器：byte[]
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestPraserBytes implements EmayHttpRequestPraser<byte[]> {

	/**
	 * 请求内容字符串
	 */
	private String contentString;

	@Override
	public String praseRqeuestContentToString(EmayHttpRequestParams<byte[]> httpParams) {
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
	public byte[] praseRqeuestContentToBytes(EmayHttpRequestParams<byte[]> httpParams) {
		return httpParams.getParams();
	}

	@Override
	public int praseRqeuestContentLength(EmayHttpRequestParams<byte[]> httpParams) {
		if (httpParams.getParams() != null) {
			return httpParams.getParams().length;
		} else {
			return 0;
		}
	}

}
