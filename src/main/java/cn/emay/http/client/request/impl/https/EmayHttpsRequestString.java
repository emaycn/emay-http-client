package cn.emay.http.client.request.impl.https;

import cn.emay.http.client.request.EmayHttpRequest;
import cn.emay.http.client.request.params.EmayHttpRequestParams;
import cn.emay.http.client.request.params.EmayHttpsRequestParams;

/**
 * Https 请求实体<String>
 * 
 * @author Frank
 *
 */
public class EmayHttpsRequestString extends EmayHttpRequest<String> {

	/**
	 * 
	 * @param httpParams
	 *            http请求参数
	 * @param httpsParams
	 *            https参数
	 */
	public EmayHttpsRequestString(EmayHttpRequestParams<String> httpParams, EmayHttpsRequestParams httpsParams) {
		super(httpParams, httpsParams);
	}

	/**
	 * 
	 * @param httpParams
	 *            http请求参数
	 */
	public EmayHttpsRequestString(EmayHttpRequestParams<String> httpParams) {
		super(httpParams, null);
	}

}
