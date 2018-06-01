package cn.emay.http.client.request.impl.http;

import cn.emay.http.client.request.EmayHttpRequest;
import cn.emay.http.client.request.params.EmayHttpRequestParams;
import cn.emay.http.client.request.parser.impl.EmayHttpRequestPraserBytes;

/**
 * Http 请求实体<byte[]>
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestBytes extends EmayHttpRequest<byte[]> {

	/**
	 * 
	 * @param httpParams
	 *            请求参数
	 */
	public EmayHttpRequestBytes(EmayHttpRequestParams<byte[]> httpParams) {
		super(httpParams, new EmayHttpRequestPraserBytes());
	}

}
