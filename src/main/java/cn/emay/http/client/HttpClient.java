package cn.emay.http.client;

import cn.emay.http.client.common.HttpHeader;
import cn.emay.http.client.common.HttpMethod;
import cn.emay.http.client.https.HttpsCerParams;
import cn.emay.http.client.https.HttpsStoreParams;
import cn.emay.http.client.logic.HttpLogic;
import cn.emay.http.client.request.HttpRequestData;
import cn.emay.http.client.response.HttpResponse;

import java.net.HttpCookie;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * EMAY http客户端<br/>
 * <br/>
 * 1.链接、读数据超时时间默认均为30秒<br/>
 * 2.默认编码为UTF-8<br/>
 * 3.提示：传输的数据注意进行UrlEncode<br/>
 * 4.提示：发送请求时，header会被服务端去重；接收响应时，cookie会被客户端去重；
 *
 * @author Frank
 */
public class HttpClient {

    /* debug */

    /**
     * 打开DEBUG
     */
    public static void openDebug() {
        HttpLogic.getInstance().openDebug();
    }

    /**
     * 关闭DEBUG
     */
    public static void closeDebug() {
        HttpLogic.getInstance().closeDebug();
    }

    /* post */

    /**
     * post请求
     *
     * @param url         链接
     * @param requestData 传输数据【Get可以不传】
     * @return 响应
     */
    public static HttpResponse post(String url, byte[] requestData) {
        return HttpLogic.getInstance().service(url, HttpMethod.POST, requestData);
    }

    /**
     * post请求
     *
     * @param url         链接
     * @param requestData 传输数据【Get可以不传】
     * @return 响应
     */
    public static HttpResponse post(String url, HttpRequestData requestData) {
        return HttpLogic.getInstance().service(url, HttpMethod.POST, requestData.toBytes("UTF-8"));
    }

    /**
     * post请求
     *
     * @param url         链接
     * @param charSet     编码
     * @param requestData 传输数据【Get可以不传】
     * @return 响应
     */
    public static HttpResponse post(String url, String charSet, byte[] requestData) {
        return HttpLogic.getInstance().service(url, HttpMethod.POST, charSet, requestData);
    }

    /**
     * post请求
     *
     * @param url         链接
     * @param charSet     编码
     * @param requestData 传输数据【Get可以不传】
     * @return 响应
     */
    public static HttpResponse post(String url, String charSet, HttpRequestData requestData) {
        return HttpLogic.getInstance().service(url, HttpMethod.POST, charSet, requestData.toBytes(charSet));
    }

    /**
     * post请求
     *
     * @param url         链接
     * @param charSet     编码
     * @param headers     Http头信息
     * @param cookies     Cookie
     * @param requestData 传输数据【Get可以不传】
     * @return 响应
     */
    public static HttpResponse post(String url, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] requestData) {
        return HttpLogic.getInstance().service(url, HttpMethod.POST, charSet, headers, cookies, requestData);
    }

    /**
     * post请求
     *
     * @param url         链接
     * @param charSet     编码
     * @param headers     Http头信息
     * @param cookies     Cookie
     * @param requestData 传输数据【Get可以不传】
     * @return 响应
     */
    public static HttpResponse post(String url, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, HttpRequestData requestData) {
        return HttpLogic.getInstance().service(url, HttpMethod.POST, charSet, headers, cookies, requestData.toBytes(charSet));
    }

    /**
     * post请求
     *
     * @param url               链接
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param requestData       传输数据【Get可以不传】
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @return 响应
     */
    public static HttpResponse post(String url, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] requestData, int connectionTimeOut, int readTimeOut) {
        return HttpLogic.getInstance().service(url, HttpMethod.POST, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut);
    }

    /**
     * post请求
     *
     * @param url               链接
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param requestData       传输数据【Get可以不传】
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @return 响应
     */
    public static HttpResponse post(String url, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, HttpRequestData requestData, int connectionTimeOut, int readTimeOut) {
        return HttpLogic.getInstance().service(url, HttpMethod.POST, charSet, headers, cookies, requestData.toBytes(charSet), connectionTimeOut, readTimeOut);
    }

    /**
     * post请求
     *
     * @param url               链接
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param requestData       传输数据【Get可以不传】
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @param httpsStoreParams  自定义Https证书相关参数
     * @return 响应
     */
    public static HttpResponse post(String url, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] requestData, int connectionTimeOut, int readTimeOut,
                                    HttpsStoreParams httpsStoreParams) {
        return HttpLogic.getInstance().service(url, HttpMethod.POST, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, httpsStoreParams);
    }

    /**
     * post请求
     *
     * @param url               链接
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param requestData       传输数据【Get可以不传】
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @param httpsCerParams    自定义Https证书参数
     * @return 响应
     */
    public static HttpResponse post(String url, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] requestData, int connectionTimeOut, int readTimeOut,
                                    HttpsCerParams httpsCerParams) {
        return HttpLogic.getInstance().service(url, HttpMethod.POST, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, httpsCerParams);
    }

    /**
     * post请求
     *
     * @param url               链接
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param requestData       传输数据【Get可以不传】
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @param httpsStoreParams  自定义Https证书相关参数
     * @return 响应
     */
    public static HttpResponse post(String url, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, HttpRequestData requestData, int connectionTimeOut, int readTimeOut,
                                    HttpsStoreParams httpsStoreParams) {
        return HttpLogic.getInstance().service(url, HttpMethod.POST, charSet, headers, cookies, requestData.toBytes(charSet), connectionTimeOut, readTimeOut, httpsStoreParams);
    }

    /**
     * post请求
     *
     * @param url               链接
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param requestData       传输数据【Get可以不传】
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @param httpsCerParams    自定义Https证书参数
     * @return 响应
     */
    public static HttpResponse post(String url, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, HttpRequestData requestData, int connectionTimeOut, int readTimeOut,
                                    HttpsCerParams httpsCerParams) {
        return HttpLogic.getInstance().service(url, HttpMethod.POST, charSet, headers, cookies, requestData.toBytes(charSet), connectionTimeOut, readTimeOut, httpsCerParams);
    }

    /* get */

    /**
     * get请求
     *
     * @param url 链接
     * @return 响应
     */
    public static HttpResponse get(String url) {
        return HttpLogic.getInstance().service(url, HttpMethod.GET);
    }

    /**
     * get请求
     *
     * @param baseUrl   基础URL
     * @param urlParams URL参数
     * @return 响应
     */
    public static HttpResponse get(String baseUrl, Map<String, String> urlParams) {
        return HttpLogic.getInstance().service(genGetUrl(baseUrl, urlParams), HttpMethod.GET);
    }

    /**
     * get请求
     *
     * @param url     链接
     * @param charSet 编码
     * @return 响应
     */
    public static HttpResponse get(String url, String charSet) {
        return HttpLogic.getInstance().service(url, HttpMethod.GET, charSet, null);
    }

    /**
     * get请求
     *
     * @param url     链接
     * @param headers Http头信息
     * @param cookies Cookie
     * @return 响应
     */
    public static HttpResponse get(String url, List<HttpHeader> headers, List<HttpCookie> cookies) {
        return HttpLogic.getInstance().service(url, HttpMethod.GET, "UTF-8", headers, cookies, null);
    }

    /**
     * get请求
     *
     * @param baseUrl   基础URL
     * @param urlParams URL参数
     * @param charSet   编码
     * @param headers   Http头信息
     * @param cookies   Cookie
     * @return 响应
     */
    public static HttpResponse get(String baseUrl, Map<String, String> urlParams, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies) {
        return HttpLogic.getInstance().service(genGetUrl(baseUrl, urlParams), HttpMethod.GET, charSet, headers, cookies, null);
    }

    /**
     * get请求
     *
     * @param url     链接
     * @param charSet 编码
     * @param headers Http头信息
     * @param cookies Cookie
     * @return 响应
     */
    public static HttpResponse get(String url, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies) {
        return HttpLogic.getInstance().service(url, HttpMethod.GET, charSet, headers, cookies, null);
    }

    /**
     * get请求
     *
     * @param baseUrl           基础URL
     * @param urlParams         URL参数
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @return 响应
     */
    public static HttpResponse get(String baseUrl, Map<String, String> urlParams, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, int connectionTimeOut, int readTimeOut) {
        return HttpLogic.getInstance().service(genGetUrl(baseUrl, urlParams), HttpMethod.GET, charSet, headers, cookies, null, connectionTimeOut, readTimeOut);
    }

    /**
     * get请求
     *
     * @param url               链接
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @return 响应
     */
    public static HttpResponse get(String url, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, int connectionTimeOut, int readTimeOut) {
        return HttpLogic.getInstance().service(url, HttpMethod.GET, charSet, headers, cookies, null, connectionTimeOut, readTimeOut);
    }

    /**
     * get请求
     *
     * @param baseUrl           基础URL
     * @param urlParams         URL参数
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @param httpsStoreParams  自定义Https证书相关参数
     * @return 响应
     */
    public static HttpResponse get(String baseUrl, Map<String, String> urlParams, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, int connectionTimeOut, int readTimeOut,
                                   HttpsStoreParams httpsStoreParams) {
        return HttpLogic.getInstance().service(genGetUrl(baseUrl, urlParams), HttpMethod.GET, charSet, headers, cookies, null, connectionTimeOut, readTimeOut, httpsStoreParams);
    }

    /**
     * get请求
     *
     * @param baseUrl           基础URL
     * @param urlParams         URL参数
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @param httpsCerParams    自定义Https证书参数
     * @return 响应
     */
    public static HttpResponse get(String baseUrl, Map<String, String> urlParams, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, int connectionTimeOut, int readTimeOut,
                                   HttpsCerParams httpsCerParams) {
        return HttpLogic.getInstance().service(genGetUrl(baseUrl, urlParams), HttpMethod.GET, charSet, headers, cookies, null, connectionTimeOut, readTimeOut, httpsCerParams);
    }

    /**
     * get请求
     *
     * @param url               链接
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @param httpsStoreParams  自定义Https证书相关参数
     * @return 响应
     */
    public static HttpResponse get(String url, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, int connectionTimeOut, int readTimeOut, HttpsStoreParams httpsStoreParams) {
        return HttpLogic.getInstance().service(url, HttpMethod.GET, charSet, headers, cookies, null, connectionTimeOut, readTimeOut, httpsStoreParams);
    }

    /**
     * get请求
     *
     * @param url               链接
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @param httpsCerParams    自定义Https证书参数
     * @return 响应
     */
    public static HttpResponse get(String url, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, int connectionTimeOut, int readTimeOut, HttpsCerParams httpsCerParams) {
        return HttpLogic.getInstance().service(url, HttpMethod.GET, charSet, headers, cookies, null, connectionTimeOut, readTimeOut, httpsCerParams);
    }

    /* service */

    /**
     * http请求
     *
     * @param url    链接
     * @param method 方法
     * @return 响应
     */
    public static HttpResponse service(String url, HttpMethod method) {
        return HttpLogic.getInstance().service(url, method);
    }

    /**
     * http请求
     *
     * @param url         链接
     * @param method      方法
     * @param requestData 传输数据【Get可以不传】
     * @return 响应
     */
    public static HttpResponse service(String url, HttpMethod method, byte[] requestData) {
        return HttpLogic.getInstance().service(url, method, requestData);
    }

    /**
     * http请求
     *
     * @param url         链接
     * @param method      方法
     * @param charSet     编码
     * @param requestData 传输数据【Get可以不传】
     * @return 响应
     */
    public static HttpResponse service(String url, HttpMethod method, String charSet, byte[] requestData) {
        return HttpLogic.getInstance().service(url, method, charSet, requestData);
    }

    /**
     * http请求
     *
     * @param url         链接
     * @param method      方法
     * @param charSet     编码
     * @param headers     Http头信息
     * @param cookies     Cookie
     * @param requestData 传输数据【Get可以不传】
     * @return 响应
     */
    public static HttpResponse service(String url, HttpMethod method, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] requestData) {
        return HttpLogic.getInstance().service(url, method, charSet, headers, cookies, requestData);
    }

    /**
     * http请求
     *
     * @param url               链接
     * @param method            方法
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param requestData       传输数据【Get可以不传】
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @return 响应
     */
    public static HttpResponse service(String url, HttpMethod method, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] requestData, int connectionTimeOut, int readTimeOut) {
        return HttpLogic.getInstance().service(url, method, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut);
    }

    /**
     * http请求
     *
     * @param url               链接
     * @param method            方法
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param requestData       传输数据【Get可以不传】
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @param httpsStoreParams  自定义Https证书相关参数
     * @return 响应
     */
    public static HttpResponse service(String url, HttpMethod method, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] requestData, int connectionTimeOut, int readTimeOut,
                                       HttpsStoreParams httpsStoreParams) {
        return HttpLogic.getInstance().service(url, method, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, httpsStoreParams);
    }

    /**
     * http请求
     *
     * @param url               链接
     * @param method            方法
     * @param charSet           编码
     * @param headers           Http头信息
     * @param cookies           Cookie
     * @param requestData       传输数据【Get可以不传】
     * @param connectionTimeOut 链接超时时间
     * @param readTimeOut       读取数据超时时间
     * @param httpsCerParams    自定义Https证书参数
     * @return 响应
     */
    public static HttpResponse service(String url, HttpMethod method, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] requestData, int connectionTimeOut, int readTimeOut,
                                       HttpsCerParams httpsCerParams) {
        return HttpLogic.getInstance().service(url, method, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, httpsCerParams);
    }

    /* util */

    /**
     * 将基础URL与URL参数拼接成完整URL<br/>
     *
     * @param baseUrl   基础URL
     * @param urlParams URL参数
     * @return URL
     */
    public static String genGetUrl(String baseUrl, Map<String, String> urlParams) {
        if (urlParams == null || urlParams.isEmpty()) {
            return baseUrl;
        }
        StringBuilder buffer = new StringBuilder();
        for (Entry<String, String> entry : urlParams.entrySet()) {
            if (entry.getValue() != null) {
                buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        String param = buffer.toString();
        String getPrams = param.substring(0, param.length() - 1);
        if ("".equalsIgnoreCase(getPrams)) {
            return baseUrl;
        }
        if (baseUrl.indexOf("?") > 0) {
            return baseUrl + "&" + getPrams;
        } else {
            return baseUrl + "?" + getPrams;
        }
    }

}
