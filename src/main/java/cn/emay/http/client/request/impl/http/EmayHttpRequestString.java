package cn.emay.http.client.request.impl.http;

import cn.emay.http.client.request.EmayHttpRequest;
import cn.emay.http.client.request.params.EmayHttpRequestParams;
import cn.emay.http.client.request.parser.impl.EmayHttpRequestPraserString;

/**
 * Http 请求实体<String>
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestString extends EmayHttpRequest<String> {

	/**
	 * 
	 * @param httpParams
	 *            请求参数
	 */
	public EmayHttpRequestString(EmayHttpRequestParams<String> httpParams) {
		super(httpParams, new EmayHttpRequestPraserString());
	}

}
