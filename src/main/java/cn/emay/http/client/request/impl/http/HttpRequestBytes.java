package cn.emay.http.client.request.impl.http;

import cn.emay.http.client.request.HttpRequest;
import cn.emay.http.client.request.params.HttpRequestParams;
import cn.emay.http.client.request.parser.impl.HttpRequestPraserBytes;

/**
 * Http 请求实体<byte[]>
 * 
 * @author Frank
 *
 */
public class HttpRequestBytes extends HttpRequest<byte[]> {

	/**
	 * 
	 * @param httpParams
	 *            请求参数
	 */
	public HttpRequestBytes(HttpRequestParams<byte[]> httpParams) {
		super(httpParams, new HttpRequestPraserBytes());
	}

}
