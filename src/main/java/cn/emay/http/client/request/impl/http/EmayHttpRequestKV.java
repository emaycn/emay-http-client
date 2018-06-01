package cn.emay.http.client.request.impl.http;

import java.util.Map;

import cn.emay.http.client.request.EmayHttpRequest;
import cn.emay.http.client.request.params.EmayHttpRequestParams;
import cn.emay.http.client.request.parser.impl.EmayHttpRequestPraserKV;

/**
 * Http 请求实体<Map<String, String>>
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestKV extends EmayHttpRequest<Map<String, String>> {

	/**
	 * 
	 * @param httpParams
	 *            请求参数
	 */
	public EmayHttpRequestKV(EmayHttpRequestParams<Map<String, String>> httpParams) {
		super(httpParams, new EmayHttpRequestPraserKV());
	}


}
