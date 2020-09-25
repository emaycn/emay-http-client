package cn.emay.http.client.response;

import cn.emay.http.client.common.HttpHeader;
import cn.emay.http.client.common.HttpResultCode;
import cn.emay.http.client.response.parser.HttpResponseParser;
import cn.emay.http.client.response.parser.impl.HttpResponseParserString;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

/**
 * Http响应
 *
 * @author Frank
 */
public class HttpResponse {

    /**
     * Http 结果代码
     */
    private final HttpResultCode resultCode;

    /**
     * Http链接Code
     */
    private final int httpCode;

    /**
     * Http响应头
     */
    private final List<HttpHeader> headers;

    /**
     * http响应Cookies
     */
    private final List<HttpCookie> cookies;

    /**
     * http响应数据
     */
    private final byte[] data;

    /**
     * 异常
     */
    private final Throwable throwable;

    /**
     * @param resultCode Http 结果代码
     * @param httpCode   Http链接Code
     * @param headers    Http响应头
     * @param cookies    http响应Cookies
     * @param data       http响应数据
     * @param throwable  异常
     */
    public HttpResponse(HttpResultCode resultCode, int httpCode, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] data, Throwable throwable) {
        this.resultCode = resultCode;
        this.httpCode = httpCode;
        this.headers = headers;
        this.cookies = cookies;
        this.data = data;
        this.throwable = throwable;
    }

    /**
     * 是否成功的响应
     *
     * @return 是否成功的响应
     */
    public boolean isSuccess() {
        return HttpResultCode.SUCCESS.equals(resultCode);
    }

    /**
     * 获取自定义转换规则的数据
     *
     * @param parser  数据转换器
     * @param charSet 编码
     * @return 自定义转换规则的数据
     */
    public <T> T getData(HttpResponseParser<T> parser, String charSet) {
        return parser.parseData(httpCode, headers, cookies, charSet, data);
    }

    /**
     * 获取String类型的数据
     *
     * @param charSet 编码
     * @return String类型的数据
     */
    public String getStringData(String charSet) {
        return getData(new HttpResponseParserString(), charSet);
    }

    /**
     * 获取String类型的数据(UTF-8编码)
     *
     * @return String类型的数据(UTF - 8编码)
     */
    public String getStringData() {
        return getData(new HttpResponseParserString(), "UTF-8");
    }

    /**
     * 获取头信息
     *
     * @param name 头名称
     * @return 头信息
     */
    public List<HttpHeader> getHeader(String name) {
        if (name == null) {
            return null;
        }
        if (headers == null || headers.isEmpty()) {
            return null;
        }
        List<HttpHeader> list = new ArrayList<>();
        for (HttpHeader header : headers) {
            if (header.getName().equals(name)) {
                list.add(header);
            }
        }
        return list;
    }

    /**
     * 获取单个头信息
     *
     * @param name 头名称
     * @return 单个头信息
     */
    public HttpHeader getHeaderSingle(String name) {
        List<HttpHeader> headerList = getHeader(name);
        if (headerList == null || headerList.isEmpty()) {
            return null;
        }
        return headerList.get(0);
    }

    /**
     * 获取Cookie
     *
     * @param name Cookie名称
     * @return Cookie
     */
    public List<HttpCookie> getCookie(String name) {
        if (name == null) {
            return null;
        }
        if (cookies == null || cookies.isEmpty()) {
            return null;
        }
        List<HttpCookie> list = new ArrayList<>();
        for (HttpCookie header : cookies) {
            if (header.getName().equals(name)) {
                list.add(header);
            }
        }
        return list;
    }

    /**
     * 获取单个Cookie
     *
     * @param name Cookie名称
     * @return 单个Cookie
     */
    public HttpCookie getCookieSingle(String name) {
        List<HttpCookie> cookieList = getCookie(name);
        if (cookieList == null || cookieList.isEmpty()) {
            return null;
        }
        return cookieList.get(0);
    }

    public HttpResultCode getResultCode() {
        return resultCode;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public List<HttpHeader> getHeaders() {
        return headers;
    }

    public List<HttpCookie> getCookies() {
        return cookies;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public byte[] getData() {
        return data;
    }

}
