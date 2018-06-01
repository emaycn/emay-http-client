package cn.emay.http.client.request.parser.impl;

import java.io.UnsupportedEncodingException;

import cn.emay.http.client.request.params.EmayHttpRequestParams;
import cn.emay.http.client.request.parser.EmayHttpRequestPraser;

/**
 * Http 请求解析器：String
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestPraserString implements EmayHttpRequestPraser<String> {

	/**
	 * 请求内容byte数组
	 */
	private byte[] contentBytes;

	@Override
	public String praseRqeuestContentToString(EmayHttpRequestParams<String> httpParams) {
		return httpParams.getParams();
	}

	@Override
	public byte[] praseRqeuestContentToBytes(EmayHttpRequestParams<String> httpParams) {
		if (contentBytes != null) {
			return contentBytes;
		}
		try {
			contentBytes = httpParams.getParams().getBytes(httpParams.getCharSet());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			contentBytes = null;
		}
		return contentBytes;
	}

	@Override
	public int praseRqeuestContentLength(EmayHttpRequestParams<String> httpParams) {
		praseRqeuestContentToBytes(httpParams);
		if (contentBytes != null) {
			return contentBytes.length;
		} else {
			return 0;
		}
	}

}
