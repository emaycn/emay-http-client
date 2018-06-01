package cn.emay.http.client.request;

import cn.emay.http.client.request.params.EmayHttpRequestParams;
import cn.emay.http.client.request.params.EmayHttpsRequestParams;
import cn.emay.http.client.request.parser.EmayHttpRequestPraser;

/**
 * Http 请求实体<请求数据类型>
 * 
 * @author Frank
 *
 * @param <T>
 */
public class EmayHttpRequest<T> {

	/**
	 * http参数
	 */
	private EmayHttpRequestParams<T> httpParams;

	/**
	 * https参数
	 */
	private EmayHttpsRequestParams httpsParams;

	/**
	 * 内容解析器
	 */
	private EmayHttpRequestPraser<T> contentPraser;

	/**
	 * 是否https请求
	 */
	private boolean isHttps;

	/**
	 * 
	 */
	protected EmayHttpRequest() {

	}

	/**
	 * 
	 * @param httpParams
	 *            http参数
	 * @param contentPraser
	 *            内容解析器
	 */
	protected EmayHttpRequest(EmayHttpRequestParams<T> httpParams, EmayHttpRequestPraser<T> contentPraser) {
		this.httpParams = httpParams;
		this.contentPraser = contentPraser;
		isHttps = false;
	}

	/**
	 * 
	 * @param httpParams
	 *            http参数
	 * @param httpsParams
	 *            https参数
	 * @param contentPraser
	 *            内容解析器
	 */
	protected EmayHttpRequest(EmayHttpRequestParams<T> httpParams, EmayHttpsRequestParams httpsParams, EmayHttpRequestPraser<T> contentPraser) {
		this.httpParams = httpParams;
		this.httpsParams = httpsParams;
		this.contentPraser = contentPraser;
		isHttps = true;
	}

	public boolean isHttps() {
		return isHttps;
	}

	public EmayHttpRequestParams<T> getHttpParams() {
		return httpParams;
	}

	public void setHttpParams(EmayHttpRequestParams<T> httpParams) {
		this.httpParams = httpParams;
	}

	public EmayHttpsRequestParams getHttpsParams() {
		return httpsParams;
	}

	public void setHttpsParams(EmayHttpsRequestParams httpsParams) {
		this.httpsParams = httpsParams;
	}

	public EmayHttpRequestPraser<T> getContentPraser() {
		return contentPraser;
	}

	public void setContentPraser(EmayHttpRequestPraser<T> contentPraser) {
		this.contentPraser = contentPraser;
	}

}
