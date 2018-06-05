package cn.emay.http.client.request;

import cn.emay.http.client.request.params.EmayHttpRequestParams;
import cn.emay.http.client.request.params.EmayHttpsRequestParams;

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
	 */
	public EmayHttpRequest(EmayHttpRequestParams<T> httpParams) {
		this.httpParams = httpParams;
		isHttps = false;
	}

	/**
	 * 
	 * @param httpParams
	 *            http参数
	 * @param httpsParams
	 *            https参数
	 */
	public EmayHttpRequest(EmayHttpRequestParams<T> httpParams, EmayHttpsRequestParams httpsParams) {
		this.httpParams = httpParams;
		this.httpsParams = httpsParams;
		isHttps = true;
	}

	/**
	 * 是否https
	 * 
	 * @return
	 */
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

}
