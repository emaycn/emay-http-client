package cn.emay.http.client.response.parser;

import cn.emay.http.client.common.HttpHeader;

import java.net.HttpCookie;
import java.util.List;

/**
 * 响应数据解析器
 *
 * @param <T>
 * @author Frank
 */
public interface HttpResponseParser<T> {

    /**
     * 解析
     *
     * @param httpCode HTTP状态码
     * @param headers  头信息
     * @param cookies  Cookie
     * @param charSet  编码
     * @param data     数据
     * @return 数据
     */
    T parseData(int httpCode, List<HttpHeader> headers, List<HttpCookie> cookies, String charSet, byte[] data);

}
