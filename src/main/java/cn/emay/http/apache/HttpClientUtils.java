package cn.emay.http.apache;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.util.EntityUtils;

/**
 * http 客户端
 */
public class HttpClientUtils {

	/**
	 * post 字节数组请求<br/>
	 * UTF-8编码，10秒超时时间
	 *
	 * @param url
	 *            链接
	 * @param bytes
	 *            字节数组
	 * @return 结果
	 */
	public static HttpResult posBytes(String url, byte[] bytes) {
		return posBytes(null, url, bytes, null, null, 10000, 10000);
	}

	/**
	 * post 字节数组请求
	 *
	 * @param url
	 *            链接
	 * @param bytes
	 *            字节数组
	 * @param headers
	 *            请求头
	 * @param cookies
	 *            请求cookies
	 * @param connectTimeoutMills
	 *            链接超时时间（毫秒）
	 * @param socketTimeoutMills
	 *            读取超时时间（毫秒）
	 * @return 结果
	 */
	public static HttpResult posBytes(String url, byte[] bytes, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		return posBytes(null, url, bytes, headers, cookies, connectTimeoutMills, socketTimeoutMills);
	}

	/**
	 * post 字节数组请求
	 *
	 * @param proxyHost
	 *            代理地址(ip:port,域名:port)
	 * @param url
	 *            链接
	 * @param bytes
	 *            字节数组
	 * @param headers
	 *            请求头
	 * @param cookies
	 *            请求cookies
	 * @param connectTimeoutMills
	 *            链接超时时间（毫秒）
	 * @param socketTimeoutMills
	 *            读取超时时间（毫秒）
	 * @return 结果
	 */
	public static HttpResult posBytesBak(String proxyHost, String url, byte[] bytes, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		if (url == null) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 601, "url为空"), new NullPointerException("url is null"));
		}
		BasicCookieStore cookieStore = new BasicCookieStore();
		HttpHost proxy = null;
		if (proxyHost != null) {
			String[] ipAndPort = proxyHost.split(":");
			proxy = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
		}
		try (CloseableHttpClient httpClient = HttpClients.custom().setProxy(proxy).setDefaultCookieStore(cookieStore).build()) {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(RequestConfig.custom().setConnectTimeout(connectTimeoutMills).setSocketTimeout(socketTimeoutMills).build());
			if (bytes != null) {
				ByteArrayEntity se = new ByteArrayEntity(bytes);
				httpPost.setEntity(se);
			}
			if (headers != null) {
				httpPost.setHeaders(headers);
			}
			if (cookies != null) {
				cookieStore.addCookies(cookies);
			}
			return httpClient.execute(httpPost, httpResponse -> handleResponse(httpResponse, cookieStore));
		} catch (IOException e) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 600, "http请求异常"), e);
		}
	}

	public static HttpResult posBytes(String proxyHost, String url, byte[] bytes, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		if (url == null) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 601, "url为空"), new NullPointerException("url is null"));
		}
		BasicCookieStore cookieStore = new BasicCookieStore();
		HttpHost proxy = null;
		if (proxyHost != null) {
			String[] ipAndPort = proxyHost.split(":");
			proxy = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
		}
		CloseableHttpClient httpClient = null;
		try {
			SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(false).setSoLinger(1).setSoReuseAddress(true).setSoTimeout(socketTimeoutMills).setTcpNoDelay(true).build();
			RequestConfig config = RequestConfig.custom().setProxy(proxy)//
					.setConnectTimeout(connectTimeoutMills)//
					.setSocketTimeout(socketTimeoutMills)//
					.setConnectionRequestTimeout(connectTimeoutMills)//
					.setCircularRedirectsAllowed(false)//
					.setMaxRedirects(1)//
					.setRedirectsEnabled(true)//
					.build();
			HttpClientBuilder builder = HttpClientBuilder.create();
			// builder.disableAutomaticRetries();
			// builder.disableContentCompression();
			// builder.disableCookieManagement();
			// builder.disableRedirectHandling();
			builder.setConnectionReuseStrategy(new NoConnectionReuseStrategy());
			builder.setDefaultCookieStore(cookieStore).//
					setDefaultSocketConfig(socketConfig).//
					setDefaultRequestConfig(config).build();//
			httpClient = builder.build();//
			HttpPost httpPost = new HttpPost(url);
			if (bytes != null) {
				ByteArrayEntity se = new ByteArrayEntity(bytes);
				httpPost.setEntity(se);
			}
			if (headers != null) {
				httpPost.setHeaders(headers);
			}
			if (cookies != null) {
				cookieStore.addCookies(cookies);
			}
			return httpClient.execute(httpPost, httpResponse -> handleResponse(httpResponse, cookieStore));
		} catch (IOException e) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 600, "http请求异常"), e);
		}
	}

	/**
	 * 简单post json字符串请求<br/>
	 * UTF-8编码，10秒超时时间
	 *
	 * @param url
	 *            链接
	 * @param json
	 *            json字符串
	 * @return 结果
	 */
	public static HttpResult postJson(String url, String json) {
		return postJson(null, url, json, "UTF-8", null, null, 10000, 10000);
	}

	/**
	 * post json字符串请求
	 *
	 * @param url
	 *            链接
	 * @param json
	 *            json字符串
	 * @param charSet
	 *            结果解析字符集
	 * @param headers
	 *            请求头
	 * @param cookies
	 *            请求cookies
	 * @param connectTimeoutMills
	 *            链接超时时间（毫秒）
	 * @param socketTimeoutMills
	 *            读取超时时间（毫秒）
	 * @return 结果
	 */
	public static HttpResult postJson(String url, String json, String charSet, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		return postJson(null, url, json, charSet, headers, cookies, connectTimeoutMills, socketTimeoutMills);
	}

	/**
	 * post json字符串请求
	 *
	 * @param proxyHost
	 *            代理地址(ip:port,域名:port)
	 * @param url
	 *            链接
	 * @param json
	 *            json字符串
	 * @param charSet
	 *            结果解析字符集
	 * @param headers
	 *            请求头
	 * @param cookies
	 *            请求cookies
	 * @param connectTimeoutMills
	 *            链接超时时间（毫秒）
	 * @param socketTimeoutMills
	 *            读取超时时间（毫秒）
	 * @return 结果
	 */
	public static HttpResult postJsonBak(String proxyHost, String url, String json, String charSet, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		if (url == null) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 601, "url为空"), new NullPointerException("url is null"));
		}
		String charSetNew = charSet == null ? "UTF-8" : charSet;
		BasicCookieStore cookieStore = new BasicCookieStore();
		HttpHost proxy = null;
		if (proxyHost != null) {
			String[] ipAndPort = proxyHost.split(":");
			proxy = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
		}
		try (CloseableHttpClient httpClient = HttpClients.custom().setProxy(proxy).setDefaultCookieStore(cookieStore).build()) {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(RequestConfig.custom().setConnectTimeout(connectTimeoutMills).setSocketTimeout(socketTimeoutMills).build());
			httpPost.addHeader(new BasicHeader("content-type", "application/json;charset=UTF-8"));
			if (json != null) {
				StringEntity se = new StringEntity(json, charSetNew);
				httpPost.setEntity(se);
			}
			if (headers != null) {
				httpPost.setHeaders(headers);
			}
			if (cookies != null) {
				cookieStore.addCookies(cookies);
			}
			return httpClient.execute(httpPost, httpResponse -> handleResponse(httpResponse, cookieStore));
		} catch (IOException e) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 600, "http请求异常"), e);
		}
	}

	public static HttpResult postJson(String proxyHost, String url, String json, String charSet, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		if (url == null) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 601, "url为空"), new NullPointerException("url is null"));
		}
		String charSetNew = charSet == null ? "UTF-8" : charSet;
		BasicCookieStore cookieStore = new BasicCookieStore();
		HttpHost proxy = null;
		if (proxyHost != null) {
			String[] ipAndPort = proxyHost.split(":");
			proxy = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
		}
		CloseableHttpClient httpClient = null;
		try {
			SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(false).setSoLinger(1).setSoReuseAddress(true).setSoTimeout(socketTimeoutMills).setTcpNoDelay(true).build();
			RequestConfig config = RequestConfig.custom().setProxy(proxy)//
					.setConnectTimeout(connectTimeoutMills)//
					.setSocketTimeout(socketTimeoutMills)//
					.setConnectionRequestTimeout(connectTimeoutMills)//
					.setCircularRedirectsAllowed(false)//
					.setMaxRedirects(1)//
					.setRedirectsEnabled(true)//
					.build();
			HttpClientBuilder builder = HttpClientBuilder.create();
			// builder.disableAutomaticRetries();
			// builder.disableContentCompression();
			// builder.disableCookieManagement();
			// builder.disableRedirectHandling();
			builder.setConnectionReuseStrategy(new NoConnectionReuseStrategy());
			builder.setDefaultCookieStore(cookieStore).//
					setDefaultSocketConfig(socketConfig).//
					setDefaultRequestConfig(config).build();//
			httpClient = builder.build();//
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader(new BasicHeader("content-type", "application/json;charset=UTF-8"));
			if (json != null) {
				StringEntity se = new StringEntity(json, charSetNew);
				httpPost.setEntity(se);
			}
			if (headers != null) {
				httpPost.setHeaders(headers);
			}
			if (cookies != null) {
				cookieStore.addCookies(cookies);
			}
			return httpClient.execute(httpPost, httpResponse -> handleResponse(httpResponse, cookieStore));
		} catch (IOException e) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 600, "http请求异常"), e);
		}
	}

	/**
	 * post 字符串请求
	 *
	 * @param url
	 *            链接
	 * @param params
	 *            字符串k=v&k=v
	 * @return 结果
	 */
	public static HttpResult postString(String url, String params) {
		return postString(null, url, params, null, null, null, 10000, 10000);
	}

	/**
	 * post 字符串请求
	 *
	 * @param url
	 *            链接
	 * @param params
	 *            字符串k=v&k=v
	 * @param charSet
	 *            结果解析字符集
	 * @param headers
	 *            请求头
	 * @param cookies
	 *            请求cookies
	 * @param connectTimeoutMills
	 *            链接超时时间（毫秒）
	 * @param socketTimeoutMills
	 *            读取超时时间（毫秒）
	 * @return 结果
	 */
	public static HttpResult postString(String url, String params, String charSet, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		return postString(null, url, params, charSet, headers, cookies, connectTimeoutMills, socketTimeoutMills);
	}

	/**
	 * post 字符串请求
	 *
	 * @param proxyHost
	 *            代理地址(ip:port,域名:port)
	 * @param url
	 *            链接
	 * @param params
	 *            字符串k=v&k=v
	 * @param charSet
	 *            结果解析字符集
	 * @param headers
	 *            请求头
	 * @param cookies
	 *            请求cookies
	 * @param connectTimeoutMills
	 *            链接超时时间（毫秒）
	 * @param socketTimeoutMills
	 *            读取超时时间（毫秒）
	 * @return 结果
	 */
	public static HttpResult postStringBak(String proxyHost, String url, String params, String charSet, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		if (url == null) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 601, "url为空"), new NullPointerException("url is null"));
		}
		String charSetNew = charSet == null ? "UTF-8" : charSet;
		BasicCookieStore cookieStore = new BasicCookieStore();
		HttpHost proxy = null;
		if (proxyHost != null) {
			String[] ipAndPort = proxyHost.split(":");
			proxy = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
		}
		try (CloseableHttpClient httpClient = HttpClients.custom().setProxy(proxy).setDefaultCookieStore(cookieStore).build()) {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(RequestConfig.custom().setConnectTimeout(connectTimeoutMills).setSocketTimeout(socketTimeoutMills).build());
			if (params != null) {
				StringEntity se = new StringEntity(params, charSetNew);
				httpPost.setEntity(se);
			}
			if (headers != null) {
				httpPost.setHeaders(headers);
			}
			if (cookies != null) {
				cookieStore.addCookies(cookies);
			}
			return httpClient.execute(httpPost, httpResponse -> handleResponse(httpResponse, cookieStore));
		} catch (IOException e) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 600, "http请求异常"), e);
		}
	}

	public static HttpResult postString(String proxyHost, String url, String params, String charSet, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		if (url == null) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 601, "url为空"), new NullPointerException("url is null"));
		}
		String charSetNew = charSet == null ? "UTF-8" : charSet;
		BasicCookieStore cookieStore = new BasicCookieStore();
		HttpHost proxy = null;
		if (proxyHost != null) {
			String[] ipAndPort = proxyHost.split(":");
			proxy = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
		}
		CloseableHttpClient httpClient = null;
		try {
			SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(false).setSoLinger(1).setSoReuseAddress(true).setSoTimeout(socketTimeoutMills).setTcpNoDelay(true).build();
			RequestConfig config = RequestConfig.custom().setProxy(proxy)//
					.setConnectTimeout(connectTimeoutMills)//
					.setSocketTimeout(socketTimeoutMills)//
					.setConnectionRequestTimeout(connectTimeoutMills)//
					.setCircularRedirectsAllowed(false)//
					.setMaxRedirects(1)//
					.setRedirectsEnabled(true)//
					.build();
			HttpClientBuilder builder = HttpClientBuilder.create();
			// builder.disableAutomaticRetries();
			// builder.disableContentCompression();
			// builder.disableCookieManagement();
			// builder.disableRedirectHandling();
			builder.setConnectionReuseStrategy(new NoConnectionReuseStrategy());
			builder.setDefaultCookieStore(cookieStore).//
					setDefaultSocketConfig(socketConfig).//
					setDefaultRequestConfig(config).build();//
			httpClient = builder.build();//
			HttpPost httpPost = new HttpPost(url);
			if (params != null) {
				StringEntity se = new StringEntity(params, charSetNew);
				httpPost.setEntity(se);
			}
			if (headers != null) {
				httpPost.setHeaders(headers);
			}
			if (cookies != null) {
				cookieStore.addCookies(cookies);
			}
			return httpClient.execute(httpPost, httpResponse -> handleResponse(httpResponse, cookieStore));
		} catch (IOException e) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 600, "http请求异常"), e);
		}
	}

	/**
	 * 简单post请求<br/>
	 * UTF-8编码，10秒超时时间
	 *
	 * @param url
	 *            链接
	 * @param data
	 *            数据
	 * @return 结果
	 */
	public static HttpResult post(String url, Map<String, String> data) {
		return post(null, url, data, "UTF-8", null, null, 10000, 10000);
	}

	/**
	 * post请求
	 *
	 * @param url
	 *            链接
	 * @param data
	 *            数据
	 * @param charSet
	 *            结果解析字符集
	 * @param headers
	 *            请求头
	 * @param cookies
	 *            请求cookies
	 * @param connectTimeoutMills
	 *            链接超时时间（毫秒）
	 * @param socketTimeoutMills
	 *            读取超时时间（毫秒）
	 * @return 结果
	 */
	public static HttpResult post(String url, Map<String, String> data, String charSet, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		return post(null, url, data, charSet, headers, cookies, connectTimeoutMills, socketTimeoutMills);
	}

	/**
	 * post请求
	 *
	 * @param proxyHost
	 *            代理地址(ip:port,域名:port)
	 * @param url
	 *            链接
	 * @param data
	 *            数据
	 * @param charSet
	 *            结果解析字符集
	 * @param headers
	 *            请求头
	 * @param cookies
	 *            请求cookies
	 * @param connectTimeoutMills
	 *            链接超时时间（毫秒）
	 * @param socketTimeoutMills
	 *            读取超时时间（毫秒）
	 * @return 结果
	 */
	public static HttpResult postBak(String proxyHost, String url, Map<String, String> data, String charSet, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		if (url == null) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 601, "url为空"), new NullPointerException("url is null"));
		}
		String charSetNew = charSet == null ? "UTF-8" : charSet;
		BasicCookieStore cookieStore = new BasicCookieStore();
		HttpHost proxy = null;
		if (proxyHost != null) {
			String[] ipAndPort = proxyHost.split(":");
			proxy = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
		}
		try (CloseableHttpClient httpClient = HttpClients.custom().setProxy(proxy).setDefaultCookieStore(cookieStore).build()) {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(RequestConfig.custom().setConnectTimeout(connectTimeoutMills).setSocketTimeout(socketTimeoutMills).build());
			List<NameValuePair> parameters = new ArrayList<>();
			if (data != null && data.size() > 0) {
				for (Map.Entry<String, String> entry : data.entrySet()) {
					String k = entry.getKey();
					String v = entry.getValue();
					parameters.add(new BasicNameValuePair(k, v));
				}
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, charSetNew);
			httpPost.setEntity(formEntity);
			if (headers != null) {
				httpPost.setHeaders(headers);
			}
			if (cookies != null) {
				cookieStore.addCookies(cookies);
			}
			return httpClient.execute(httpPost, httpResponse -> handleResponse(httpResponse, cookieStore));
		} catch (IOException e) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 600, "http请求异常"), e);
		}
	}

	public static HttpResult post(String proxyHost, String url, Map<String, String> data, String charSet, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		if (url == null) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 601, "url为空"), new NullPointerException("url is null"));
		}
		String charSetNew = charSet == null ? "UTF-8" : charSet;
		BasicCookieStore cookieStore = new BasicCookieStore();
		HttpHost proxy = null;
		if (proxyHost != null) {
			String[] ipAndPort = proxyHost.split(":");
			proxy = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
		}
		CloseableHttpClient httpClient = null;
		try {
			SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(false).setSoLinger(1).setSoReuseAddress(true).setSoTimeout(socketTimeoutMills).setTcpNoDelay(true).build();
			RequestConfig config = RequestConfig.custom().setProxy(proxy)//
					.setConnectTimeout(connectTimeoutMills)//
					.setSocketTimeout(socketTimeoutMills)//
					.setConnectionRequestTimeout(connectTimeoutMills)//
					.setCircularRedirectsAllowed(false)//
					.setMaxRedirects(1)//
					.setRedirectsEnabled(true)//
					.build();
			HttpClientBuilder builder = HttpClientBuilder.create();
			// builder.disableAutomaticRetries();
			// builder.disableContentCompression();
			// builder.disableCookieManagement();
			// builder.disableRedirectHandling();
			builder.setConnectionReuseStrategy(new NoConnectionReuseStrategy());
			builder.setDefaultCookieStore(cookieStore).//
					setDefaultSocketConfig(socketConfig).//
					setDefaultRequestConfig(config).build();//
			httpClient = builder.build();//
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> parameters = new ArrayList<>();
			if (data != null && data.size() > 0) {
				for (Map.Entry<String, String> entry : data.entrySet()) {
					String k = entry.getKey();
					String v = entry.getValue();
					parameters.add(new BasicNameValuePair(k, v));
				}
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, charSetNew);
			httpPost.setEntity(formEntity);
			if (headers != null) {
				httpPost.setHeaders(headers);
			}
			if (cookies != null) {
				cookieStore.addCookies(cookies);
			}
			return httpClient.execute(httpPost, httpResponse -> handleResponse(httpResponse, cookieStore));
		} catch (IOException e) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 600, "http请求异常"), e);
		} finally {
			try {
				httpClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 简单get请求<br/>
	 * UTF-8编码，10秒超时时间
	 *
	 * @param url
	 *            链接
	 * @param data
	 *            数据
	 * @return 结果
	 */
	public static HttpResult get(String url, Map<String, String> data) {
		return get(null, url, data, null, null, 10000, 10000);
	}

	/**
	 * get请求
	 *
	 * @param url
	 *            链接
	 * @param data
	 *            数据
	 * @param headers
	 *            请求头
	 * @param cookies
	 *            请求cookies
	 * @param connectTimeoutMills
	 *            链接超时时间（毫秒）
	 * @param socketTimeoutMills
	 *            读取超时时间（毫秒）
	 * @return 结果
	 */
	public static HttpResult get(String url, Map<String, String> data, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		return get(null, url, data, headers, cookies, connectTimeoutMills, socketTimeoutMills);
	}

	/**
	 * get请求
	 *
	 * @param proxyHost
	 *            代理地址(ip:port,域名:port)
	 * @param url
	 *            链接
	 * @param data
	 *            数据
	 * @param headers
	 *            请求头
	 * @param cookies
	 *            请求cookies
	 * @param connectTimeoutMills
	 *            链接超时时间（毫秒）
	 * @param socketTimeoutMills
	 *            读取超时时间（毫秒）
	 * @return 结果
	 */
	public static HttpResult getBak(String proxyHost, String url, Map<String, String> data, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		if (url == null) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 601, "url为空"), new NullPointerException("url is null"));
		}
		HttpHost proxy = null;
		if (proxyHost != null) {
			String[] ipAndPort = proxyHost.split(":");
			proxy = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
		}
		BasicCookieStore cookieStore = new BasicCookieStore();
		try (CloseableHttpClient httpClient = HttpClients.custom().setProxy(proxy).setDefaultCookieStore(cookieStore).build()) {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (data != null && data.size() > 0) {
				data.forEach(uriBuilder::setParameter);
			}
			URI uri = uriBuilder.build();
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setConfig(RequestConfig.custom().setConnectTimeout(connectTimeoutMills).setSocketTimeout(socketTimeoutMills).build());
			if (headers != null) {
				httpGet.setHeaders(headers);
			}
			if (cookies != null) {
				cookieStore.addCookies(cookies);
			}
			return httpClient.execute(httpGet, httpResponse -> handleResponse(httpResponse, cookieStore));
		} catch (IOException | URISyntaxException e) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 600, "http请求异常"), e);
		}
	}

	public static HttpResult get(String proxyHost, String url, Map<String, String> data, Header[] headers, Cookie[] cookies, int connectTimeoutMills, int socketTimeoutMills) {
		if (url == null) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 601, "url为空"), new NullPointerException("url is null"));
		}
		HttpHost proxy = null;
		if (proxyHost != null) {
			String[] ipAndPort = proxyHost.split(":");
			proxy = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
		}
		BasicCookieStore cookieStore = new BasicCookieStore();
		if (cookies != null) {
			cookieStore.addCookies(cookies);
		}
		CloseableHttpClient httpClient = null;
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (data != null && data.size() > 0) {
				data.forEach(uriBuilder::setParameter);
			}
			SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(false).setSoLinger(1).setSoReuseAddress(true).setSoTimeout(socketTimeoutMills).setTcpNoDelay(true).build();
			RequestConfig config = RequestConfig.custom().setProxy(proxy)//
					.setConnectTimeout(connectTimeoutMills)//
					.setSocketTimeout(socketTimeoutMills)//
					.setConnectionRequestTimeout(connectTimeoutMills)//
					.setCircularRedirectsAllowed(false)//
					.setMaxRedirects(1)//
					.setRedirectsEnabled(true)//
					.build();
			HttpClientBuilder builder = HttpClientBuilder.create();
			// builder.disableAutomaticRetries();
			// builder.disableContentCompression();
			// builder.disableCookieManagement();
			// builder.disableRedirectHandling();
			builder.setConnectionReuseStrategy(new NoConnectionReuseStrategy());
			builder.setDefaultCookieStore(cookieStore).//
					setDefaultSocketConfig(socketConfig).//
					setDefaultRequestConfig(config).build();//
			httpClient = builder.build();//
			URI uri = uriBuilder.build();
			HttpGet httpGet = new HttpGet(uri);
			if (headers != null) {
				httpGet.setHeaders(headers);
			}
			return httpClient.execute(httpGet, httpResponse -> handleResponse(httpResponse, cookieStore));
		} catch (IOException | URISyntaxException e) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 600, "http请求异常"), e);
		} finally {
			try {
				httpClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 处理http response
	 *
	 * @param response
	 *            响应
	 * @param cookieStore
	 *            cookie存储
	 * @return 结果
	 * @throws IOException
	 *             http抛出的io异常
	 */
	public static HttpResult handleResponse(HttpResponse response, BasicCookieStore cookieStore) throws IOException {
		if (response == null || response.getStatusLine() == null) {
			return HttpResult.failHttpResult(new BasicStatusLine(HttpVersion.HTTP_1_1, 600, "请求http未返回响应"), null);
		}
		if (response.getStatusLine().getStatusCode() >= 400) {
			return HttpResult.failHttpResult(response.getStatusLine(), null);
		}
		Header[] headers = response.getAllHeaders();
		List<Cookie> cookies = cookieStore.getCookies();
		Cookie[] cookieArray = cookies == null ? null : cookies.toArray(new Cookie[0]);
		if (response.getEntity() == null) {
			return HttpResult.successHttpResult(response.getStatusLine(), null, headers, cookieArray);
		}
		byte[] data = EntityUtils.toByteArray(response.getEntity());
		return HttpResult.successHttpResult(response.getStatusLine(), data, headers, cookieArray);
	}

}
