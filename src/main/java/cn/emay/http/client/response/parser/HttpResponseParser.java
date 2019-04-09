package cn.emay.http.client.response.parser;

import java.net.HttpCookie;
import java.util.List;

import cn.emay.http.client.common.HttpHeader;

/**
 * 响应数据解析器
 * 
 * @author Frank
 *
 * @param <T>
 */
public interface HttpResponseParser<T> {

	/**
	 * 解析
	 * 
	 * @param httpCode
	 *            HTTP状态码
	 * @param headers
	 *            头信息
	 * @param cookies
	 *            Cookie
	 * @param charSet
	 *            编码
	 * @param data
	 *            数据
	 * @return
	 */
	public T parseData(int httpCode, List<HttpHeader> headers, List<HttpCookie> cookies, String charSet, byte[] data);

}
