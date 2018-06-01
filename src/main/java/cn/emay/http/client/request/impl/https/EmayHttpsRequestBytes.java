package cn.emay.http.client.request.impl.https;

import cn.emay.http.client.request.EmayHttpRequest;
import cn.emay.http.client.request.params.EmayHttpRequestParams;
import cn.emay.http.client.request.params.EmayHttpsRequestParams;

/**
 * Https 请求实体<byte[]>
 * 
 * @author Frank
 *
 */
public class EmayHttpsRequestBytes extends EmayHttpRequest<byte[]> {

	/**
	 * 
	 * @param httpParams
	 *            http请求参数
	 * @param httpsParams
	 *            https参数
	 */
	public EmayHttpsRequestBytes(EmayHttpRequestParams<byte[]> httpParams, EmayHttpsRequestParams httpsParams) {
		super(httpParams, httpsParams);
	}

	/**
	 * 
	 * @param httpParams
	 *            http请求参数
	 */
	public EmayHttpsRequestBytes(EmayHttpRequestParams<byte[]> httpParams) {
		super(httpParams, null);
	}

}
