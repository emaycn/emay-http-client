package cn.emay.http.client.logic;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.emay.http.client.common.HttpHeader;
import cn.emay.http.client.common.HttpMethod;
import cn.emay.http.client.common.HttpResultCode;
import cn.emay.http.client.https.HttpsCerParams;
import cn.emay.http.client.https.HttpsStoreParams;
import cn.emay.http.client.response.HttpResponse;

/**
 * Http请求逻辑<br/>
 * 
 * @author Frank
 *
 */
public class HttpLogic {

	private Logger logger = LoggerFactory.getLogger(HttpLogic.class);

	/**
	 * 是否DEBUG
	 */
	private boolean debug = false;

	/**
	 * 单例
	 */
	private static HttpLogic LOGIC = new HttpLogic();

	/**
	 * 单例
	 */
	private HttpLogic() {

	}

	/**
	 * 获取单例
	 */
	public static HttpLogic getInstance() {
		return LOGIC;
	}

	/**
	 * 打开DEBUG
	 */
	public void openDebug() {
		debug = true;
	}

	/**
	 * 关闭DEBUG
	 */
	public void closeDebug() {
		debug = false;
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @return
	 */
	public HttpResponse service(String url, HttpMethod method) {
		return service(url, method, "UTF-8", null, null, null, 30, 30, null, null);
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @return
	 */
	public HttpResponse service(String url, HttpMethod method, byte[] requestData) {
		return service(url, method, "UTF-8", null, null, requestData, 30, 30, null, null);
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @param charSet
	 *            编码
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @return
	 */
	public HttpResponse service(String url, HttpMethod method, String charSet, byte[] requestData) {
		return service(url, method, charSet, null, null, requestData, 30, 30, null, null);
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @return
	 */
	public HttpResponse service(String url, HttpMethod method, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] requestData) {
		return service(url, method, charSet, headers, cookies, requestData, 30, 30, null, null);
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @return
	 */
	public HttpResponse service(String url, HttpMethod method, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] requestData, int connectionTimeOut, int readTimeOut) {
		return service(url, method, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, null, null);
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @param httpsCerParams
	 *            自定义Https证书参数
	 * @return
	 */
	public HttpResponse service(String url, HttpMethod method, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] requestData, int connectionTimeOut, int readTimeOut,
			HttpsCerParams httpsCerParams) {
		return service(url, method, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, httpsCerParams, null);
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @param httpsStoreParams
	 *            自定义Https密钥库参数
	 * @return
	 */
	public HttpResponse service(String url, HttpMethod method, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] requestData, int connectionTimeOut, int readTimeOut,
			HttpsStoreParams httpsStoreParams) {
		return service(url, method, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, null, httpsStoreParams);
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            链接
	 * @param method
	 *            方法
	 * @param charSet
	 *            编码
	 * @param headers
	 *            Http头信息
	 * @param cookies
	 *            Cookie
	 * @param requestData
	 *            传输数据【Get可以不传】
	 * @param connectionTimeOut
	 *            链接超时时间
	 * @param readTimeOut
	 *            读取数据超时时间
	 * @param httpsCerParams
	 *            自定义Https证书参数[与httpsCerParams二选一]，此参数优先
	 * @param HttpsStoreParams
	 *            自定义Https密钥库参数[与HttpsStoreParams二选一]
	 * @return
	 */
	public HttpResponse service(String url, HttpMethod method, String charSet, List<HttpHeader> headers, List<HttpCookie> cookies, byte[] requestData, int connectionTimeOut, int readTimeOut,
			HttpsCerParams httpsCerParams, HttpsStoreParams httpsStoreParams) {
		HttpResponse response = null;
		if (url == null || url.length() == 0) {
			response = new HttpResponse(HttpResultCode.ERROR_URL, -1, null, null, null, null);
			if (debug) {
				logger.error("url is null");
			}
			return response;
		}
		boolean isHttps0 = false;
		String url0 = url;
		if (url.startsWith("http://")) {
			url0 = url;
			isHttps0 = false;
		} else if (url.startsWith("https://")) {
			url0 = url;
			isHttps0 = true;
		} else {
			if (httpsStoreParams == null && httpsCerParams == null) {
				url0 = "http://" + url;
				isHttps0 = false;
			} else {
				url0 = "https://" + url;
				isHttps0 = true;
			}
		}
		HttpMethod method0 = method != null ? method : HttpMethod.GET;
		String charSet0 = charSet != null ? charSet : "UTF-8";
		int connectionTimeOut0 = connectionTimeOut > 0 ? connectionTimeOut : 30;
		int readTimeOut0 = readTimeOut > 0 ? readTimeOut : 30;
		List<HttpHeader> headers0 = headers;
		List<HttpCookie> cookies0 = cookies;
		HttpsStoreParams httpsStoreParams0 = httpsStoreParams;
		HttpsCerParams httpsCerParams0 = httpsCerParams;

		HttpURLConnection conn0 = null;
		
		try {
			try {
				conn0 = createConnection(url0, isHttps0, httpsCerParams0, httpsStoreParams0);
			} catch (KeyManagementException e1) {
				response = new HttpResponse(HttpResultCode.ERROR_HTTPS, -1, null, null, null, e1);
			} catch (UnrecoverableKeyException e1) {
				response = new HttpResponse(HttpResultCode.ERROR_HTTPS, -1, null, null, null, e1);
			} catch (NoSuchAlgorithmException e1) {
				response = new HttpResponse(HttpResultCode.ERROR_HTTPS, -1, null, null, null, e1);
			} catch (MalformedURLException e1) {
				response = new HttpResponse(HttpResultCode.ERROR_HTTPS, -1, null, null, null, e1);
			} catch (KeyStoreException e1) {
				response = new HttpResponse(HttpResultCode.ERROR_HTTPS, -1, null, null, null, e1);
			} catch (CertificateException e1) {
				response = new HttpResponse(HttpResultCode.ERROR_HTTPS, -1, null, null, null, e1);
			}catch (FileNotFoundException e1) {
				response = new HttpResponse(HttpResultCode.ERROR_HTTPS, -1, null, null, null, e1);
			} catch (IOException e1) {
				response = new HttpResponse(HttpResultCode.ERROR_CONNECT, -1, null, null, null, e1);
			} catch (Throwable e) {
				response = new HttpResponse(HttpResultCode.ERROR_OTHER, -1, null, null, null, e);
			}
			if(conn0 == null) {
				log(response, method0, url0);
				return response;
			}
			
			fillTimeout(conn0, connectionTimeOut0, readTimeOut0);
			filleMethod(conn0, method0);
			fillHeaders(conn0, headers0);
			fillCookies(conn0, isHttps0, cookies0);
			
			boolean requestOK = false;
			try {
				request(conn0 , requestData);
				requestOK = true;
			} catch (SocketTimeoutException e1) {
				response = new HttpResponse(HttpResultCode.ERROR_REQUEST_TIMEOUT, -1, null, null, null, e1);
			} catch (IOException e1) {
				response = new HttpResponse(HttpResultCode.ERROR_REQUEST, -1, null, null, null, e1);
			} catch (Throwable e) {
				response = new HttpResponse(HttpResultCode.ERROR_OTHER, -1, null, null, null, e);
			}
			if(!requestOK) {
				log(response, method0, url0);
				return response;
			}
			
			try {
				int httpCode0 = conn0.getResponseCode();
				List<HttpHeader> responseHeaders0 = this.getHeaders(conn0, charSet0);
				List<HttpCookie> responseCookies0 = this.getCookies(conn0, charSet0);
				byte[] responseData0 = this.getResult(conn0);
				response = new HttpResponse(HttpResultCode.SUCCESS, httpCode0, responseHeaders0, responseCookies0, responseData0, null);
			} catch (SocketTimeoutException e) {
				response = new HttpResponse(HttpResultCode.ERROR_RESPONSE_TIMEOUT, -1, null, null, null, e);
			} catch (UnsupportedEncodingException e) {
				response = new HttpResponse(HttpResultCode.ERROR_RESPONSE_CHARSET, -1, null, null, null, e);
			} catch (IOException e) {
				response = new HttpResponse(HttpResultCode.ERROR_RESPONSE, -1, null, null, null, e);
			} catch (Throwable e) {
				response = new HttpResponse(HttpResultCode.ERROR_OTHER, -1, null, null, null, e);
			}
			log(response, method0, url0);
			return response;
		} finally {
			if (conn0 != null) {
				try {
					conn0.disconnect();
				} catch (Exception e2) {
					if (debug) {
						logger.error("close connection error :", e2);
					}
				}
			}
		}
	}
	
	private void log(HttpResponse response ,HttpMethod method0,String url0) {
		if (debug) {
			if (response.isSuccess()) {
				logger.info("http " + method0.toString() + " " + url0 + " success.");
			} else {
				logger.error("http " + method0.toString() + " " + url0 + " error. resultCode: " + response.getResultCode().getCode() + ", httpCode:" + response.getHttpCode(), response.getThrowable());
			}
		}
	}

	/**
	 * 创建Http链接
	 * 
	 * @param url
	 * @param isHttps
	 * @param httpsParams
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws UnrecoverableKeyException
	 * @throws KeyStoreException
	 * @throws CertificateException
	 */
	private HttpURLConnection createConnection(String url, boolean isHttps, HttpsCerParams httpsCerParams, HttpsStoreParams httpsStoreParams)
			throws NoSuchAlgorithmException, KeyManagementException, MalformedURLException,FileNotFoundException, UnrecoverableKeyException, KeyStoreException, CertificateException , IOException{
		HttpURLConnection conn = null;
		URL console = new URL(url);
		if (isHttps) {
			SSLContext ctx = SSLContext.getInstance("TLS");
			if (httpsCerParams != null) {
				InputStream cerInputStream = new FileInputStream(httpsCerParams.getCerPath());
				CertificateFactory certificateFactory = CertificateFactory.getInstance(httpsCerParams.getType());
				Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(cerInputStream);
				KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
				keyStore.load(null, null);
				int index = 0;
				if (null != certificates) {
					for (Certificate certificate : certificates) {
						String certificateAlias = Integer.toString(index++);
						keyStore.setCertificateEntry(certificateAlias, certificate);
					}
				}
				cerInputStream.close();
				KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				keyManagerFactory.init(keyStore, null);
				TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
				trustManagerFactory.init(keyStore);
				ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
			} else if (httpsStoreParams != null) {
				KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
				KeyStore keyStore = getKeyStore(httpsStoreParams.getPassword(), httpsStoreParams.getAlgorithm(), httpsStoreParams.getKeyStorePath());
				keyManagerFactory.init(keyStore, httpsStoreParams.getPassword().toCharArray());
				KeyStore trustStore = getKeyStore(httpsStoreParams.getPassword(), httpsStoreParams.getAlgorithm(), httpsStoreParams.getTrustStorePath());
				trustManagerFactory.init(trustStore);
				ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
			} else {
				ctx.init(null, new TrustManager[] { new X509TrustManager() {
					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					@Override
					public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					}

					@Override
					public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					}
				} }, new java.security.SecureRandom());
			}
			HttpsURLConnection sconn = (HttpsURLConnection) console.openConnection();
			sconn.setSSLSocketFactory(ctx.getSocketFactory());
			sconn.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}

			});
			conn = sconn;
		} else {
			conn = (HttpURLConnection) console.openConnection();
		}
		return conn;
	}

	/**
	 * 获取Https的密钥库
	 * 
	 * @param password
	 * @param algorithm
	 * @param sotrePath
	 * @return
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 */
	private KeyStore getKeyStore(String password, String algorithm, String sotrePath) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore ks = KeyStore.getInstance(algorithm);
		FileInputStream is = new FileInputStream(sotrePath);
		ks.load(is, password.toCharArray());
		is.close();
		return ks;
	}

	/**
	 * 设置Http请求的超时时间
	 * 
	 * @param conn
	 * @param httpConnectionTimeOut
	 * @param httpReadTimeOut
	 */
	private void fillTimeout(HttpURLConnection conn, int httpConnectionTimeOut, int httpReadTimeOut) {
		conn.setConnectTimeout(httpConnectionTimeOut * 1000);
		conn.setReadTimeout(httpReadTimeOut * 1000);
	}

	/**
	 * 设置Http请求的方法
	 * 
	 * @param conn
	 * @param method
	 */
	private void filleMethod(HttpURLConnection conn, HttpMethod method) {
		try {
			conn.setRequestMethod(method.toString());
		} catch (ProtocolException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 把Headers放入请求
	 * 
	 * @param conn
	 * @param headers
	 */
	private void fillHeaders(HttpURLConnection conn, List<HttpHeader> headers) {
		if (headers == null || headers.isEmpty()) {
			return;
		}
		for (HttpHeader entry : headers) {
			fillHeader(conn, entry.getName(), entry.getValue());
		}
	}

	/**
	 * 把Header放入请求
	 * 
	 * @param conn
	 * @param key
	 * @param value
	 */
	private void fillHeader(HttpURLConnection conn, String key, String value) {
		conn.setRequestProperty(key, value);
	}

	/**
	 * 把Cookies放入请求
	 * 
	 * @param conn
	 * @param isHttps
	 * @param cookies
	 */
	private void fillCookies(HttpURLConnection conn, boolean isHttps, List<HttpCookie> cookies) {
		if (cookies == null || cookies.isEmpty()) {
			return;
		}
		StringBuffer buffer = new StringBuffer();
		for (HttpCookie cookie : cookies) {
			if (cookie.getName() != null && cookie.getValue() != null) {
				if (cookie.getSecure() && !isHttps) {
					continue;
				}
				buffer.append(cookie.toString()).append(";");
			}
		}
		String param = buffer.toString();
		param = param.substring(0, param.length() - 1);
		conn.setRequestProperty("Cookie", param);
	}

	/**
	 * 请求
	 * 
	 * @param conn
	 * @param requestData
	 * @throws IOException
	 */
	private void request(HttpURLConnection conn, byte[] requestData) throws IOException {
		conn.setDoOutput(true);
		if (requestData == null || requestData.length == 0) {
			conn.connect();
			return;
		}
		fillHeader(conn, "Content-Length", String.valueOf(requestData.length));
		conn.connect();
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		try {
			out.write(requestData);
			out.flush();
		} catch (IOException e) {
			throw e;
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 从响应获取Headers
	 * 
	 * @param conn
	 * @param charSet
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private List<HttpHeader> getHeaders(HttpURLConnection conn, String charSet) throws UnsupportedEncodingException {
		List<HttpHeader> list = new ArrayList<HttpHeader>();
		Map<String, List<String>> headers = conn.getHeaderFields();
		if (headers == null || headers.isEmpty()) {
			return list;
		}
		for (Entry<String, List<String>> entry : headers.entrySet()) {
			if ("Set-Cookie".equalsIgnoreCase(entry.getKey())) {
				continue;
			}
			if (entry.getValue() == null || entry.getValue().isEmpty()) {
				continue;
			}
			String name = entry.getKey();
			for (String value : entry.getValue()) {
				String value0 = new String(value.getBytes("ISO-8859-1"), charSet);
				list.add(new HttpHeader(name, value0));

			}
		}
		return list;
	}

	/**
	 * 从响应获取Cookies
	 * 
	 * @param conn
	 * @param charSet
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private List<HttpCookie> getCookies(HttpURLConnection conn, String charSet) throws UnsupportedEncodingException {
		List<HttpCookie> list = new ArrayList<HttpCookie>();
		Map<String, List<String>> headers = conn.getHeaderFields();
		if (headers == null || headers.isEmpty()) {
			return list;
		}
		List<String> cookies = headers.get("Set-Cookie");
		if (cookies == null || cookies.isEmpty()) {
			return list;
		}
		for (String value : cookies) {
			String value0 = new String(value.getBytes("ISO-8859-1"), charSet);
			list.addAll(HttpCookie.parse(value0));

		}
		return list;
	}

	/**
	 * 从response输入流获取响应数据
	 * 
	 * @param conn
	 * @return
	 * @throws IOException
	 */
	private byte[] getResult(HttpURLConnection conn) throws IOException {
		byte[] buffer = null;
		InputStream is = conn.getInputStream();
		if (is == null) {
			return buffer;
		}
		ByteArrayOutputStream outStream = null;
		int length = conn.getContentLength();
		try {
			if (length <= 0) {
				outStream = new ByteArrayOutputStream();
				byte[] buffer0 = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer0)) != -1) {
					outStream.write(buffer0, 0, len);
				}
				buffer = outStream.toByteArray();
			} else {
				buffer = new byte[length];
				is.read(buffer);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			is.close();
			if (outStream != null) {
				outStream.close();
			}
		}
		return buffer;

	}

}
