package cn.emay.http.client.response.parser;

import java.util.List;

import cn.emay.http.client.common.EmayHttpCookie;
import cn.emay.http.client.common.EmayHttpHeader;

public interface EmayHttpResponseParser<T> {
	
	/**
	 * 解析
	 * 
	 * @param outputStream
	 *            http响应数据字节流
	 */
	public T parseData(int httpCode, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, String charSet, byte[] data);

}
