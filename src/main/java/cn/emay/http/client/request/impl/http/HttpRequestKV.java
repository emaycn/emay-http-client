package cn.emay.http.client.request.impl.http;

import java.util.Map;

import cn.emay.http.client.request.HttpRequest;
import cn.emay.http.client.request.params.HttpRequestParams;
import cn.emay.http.client.request.parser.impl.HttpRequestPraserKV;

/**
 * Http 请求实体<Map<String, String>>
 * 
 * @author Frank
 *
 */
public class HttpRequestKV extends HttpRequest<Map<String, String>> {

	/**
	 * 
	 * @param httpParams
	 *            请求参数
	 */
	public HttpRequestKV(HttpRequestParams<Map<String, String>> httpParams) {
		super(httpParams, new HttpRequestPraserKV());
	}


}
