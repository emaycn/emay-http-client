package cn.emay.http.client.request.impl.https;

import java.util.Map;

import cn.emay.http.client.request.EmayHttpRequest;
import cn.emay.http.client.request.params.EmayHttpRequestParams;
import cn.emay.http.client.request.params.EmayHttpsRequestParams;

/**
 * Https 请求实体<Map<String, String>>
 * 
 * @author Frank
 *
 */
public class EmayHttpsRequestKV extends EmayHttpRequest<Map<String, String>> {

	/**
	 * 
	 * @param httpParams
	 *            http请求参数
	 * @param httpsParams
	 *            https参数
	 */
	public EmayHttpsRequestKV(EmayHttpRequestParams<Map<String, String>> httpParams, EmayHttpsRequestParams httpsParams) {
		super(httpParams, httpsParams);
	}

	/**
	 * 
	 * @param httpParams
	 *            http请求参数
	 * @param httpsParams
	 *            https参数
	 */
	public EmayHttpsRequestKV(EmayHttpRequestParams<Map<String, String>> httpParams) {
		super(httpParams, null);
	}

	
}
