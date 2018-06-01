package cn.emay.http.client.response.parser;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import cn.emay.http.client.common.EmayHttpResultCode;

/**
 * 
 * Http响应解析器
 * 
 * @author Frank
 *
 * @param <T>
 *            http响应数据转换后实体
 */
public interface EmayHttpResponsePraser<T> {

	/**
	 * 解析
	 * 
	 * @param resultCode
	 *            Http 结果代码
	 * @param httpCode
	 *            Http链接Code
	 * @param headers
	 *            Http响应头
	 * @param cookies
	 *            http响应Cookies
	 * @param charSet
	 *            http字符集
	 * @param result
	 *            http响应数据
	 */
	public T prase(EmayHttpResultCode resultCode, int httpCode, Map<String, String> headers, List<String> cookies, String charSet, ByteArrayOutputStream outputStream, Throwable throwable);

}
