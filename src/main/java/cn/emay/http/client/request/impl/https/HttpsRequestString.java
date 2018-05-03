package cn.emay.http.client.request.impl.https;

import cn.emay.http.client.request.HttpRequest;
import cn.emay.http.client.request.params.HttpRequestParams;
import cn.emay.http.client.request.params.HttpsParams;
import cn.emay.http.client.request.parser.impl.HttpRequestPraserString;

/**
 * Https 请求实体<String>
 * 
 * @author Frank
 *
 */
public class HttpsRequestString extends HttpRequest<String> {

	/**
	 * 
	 * @param httpParams
	 *            http请求参数
	 * @param httpsParams
	 *            https参数
	 */
	public HttpsRequestString(HttpRequestParams<String> httpParams, HttpsParams httpsParams) {
		super(httpParams, httpsParams, new HttpRequestPraserString());
	}

}
