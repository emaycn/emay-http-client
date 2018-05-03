package cn.emay.http.client.request.impl.https;

import cn.emay.http.client.request.HttpRequest;
import cn.emay.http.client.request.params.HttpRequestParams;
import cn.emay.http.client.request.params.HttpsParams;
import cn.emay.http.client.request.parser.impl.HttpRequestPraserBytes;

/**
 * Https 请求实体<byte[]>
 * 
 * @author Frank
 *
 */
public class HttpsRequestBytes extends HttpRequest<byte[]> {

	/**
	 * 
	 * @param httpParams
	 *            http请求参数
	 * @param httpsParams
	 *            https参数
	 */
	public HttpsRequestBytes(HttpRequestParams<byte[]> httpParams, HttpsParams httpsParams) {
		super(httpParams, httpsParams, new HttpRequestPraserBytes());
	}

}
