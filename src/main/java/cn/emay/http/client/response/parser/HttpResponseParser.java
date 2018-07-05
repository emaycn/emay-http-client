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
	 * @param outputStream
	 *            http响应数据字节流
	 */
	public T parseData(int httpCode, List<HttpHeader> headers, List<HttpCookie> cookies, String charSet, byte[] data);

}
