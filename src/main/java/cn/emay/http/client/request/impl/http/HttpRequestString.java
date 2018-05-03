package cn.emay.http.client.request.impl.http;

import cn.emay.http.client.request.HttpRequest;
import cn.emay.http.client.request.params.HttpRequestParams;
import cn.emay.http.client.request.parser.impl.HttpRequestPraserString;

/**
 * Http 请求实体<String>
 * 
 * @author Frank
 *
 */
public class HttpRequestString extends HttpRequest<String> {

	/**
	 * 
	 * @param httpParams
	 *            请求参数
	 */
	public HttpRequestString(HttpRequestParams<String> httpParams) {
		super(httpParams, new HttpRequestPraserString());
	}

}
