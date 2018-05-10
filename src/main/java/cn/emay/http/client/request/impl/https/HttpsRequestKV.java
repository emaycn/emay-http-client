package cn.emay.http.client.request.impl.https;

import java.util.Map;

import cn.emay.http.client.request.HttpRequest;
import cn.emay.http.client.request.params.HttpRequestParams;
import cn.emay.http.client.request.params.HttpsParams;
import cn.emay.http.client.request.parser.impl.HttpRequestPraserKV;

/**
 * Https 请求实体<Map<String, String>>
 * 
 * @author Frank
 *
 */
public class HttpsRequestKV extends HttpRequest<Map<String, String>> {

	/**
	 * 
	 * @param httpParams
	 *            http请求参数
	 * @param httpsParams
	 *            https参数
	 */
	public HttpsRequestKV(HttpRequestParams<Map<String, String>> httpParams, HttpsParams httpsParams) {
		super(httpParams, httpsParams, new HttpRequestPraserKV());
	}
	
	/**
	 * 
	 * @param httpParams
	 *            http请求参数
	 * @param httpsParams
	 *            https参数
	 */
	public HttpsRequestKV(HttpRequestParams<Map<String, String>> httpParams) {
		super(httpParams, null, new HttpRequestPraserKV());
	}

}
