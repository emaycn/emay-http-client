package cn.emay.http.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
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

import cn.emay.http.client.common.EmayHttpCookie;
import cn.emay.http.client.common.EmayHttpResultCode;
import cn.emay.http.client.request.data.EmayHttpRequestData;
import cn.emay.http.client.request.params.EmayHttpRequestParams;
import cn.emay.http.client.request.params.EmayHttpsRequestParams;
import cn.emay.http.client.response.EmayHttpResponse;

/**
 * EMAY http客户端
 * 
 * @author Frank
 *
 */
public class EmayHttpClient {

	/**
	 * 链接超时时间(s)
	 */
	private int httpConnectionTimeOut = 30;

	/**
	 * 数据传输超时时间(s)
	 */
	private int httpReadTimeOut = 30;

	public EmayHttpClient() {

	}

	/**
	 * 
	 * @param httpConnectionTimeOut
	 *            链接超时时间(s)
	 * @param httpReadTimeOut
	 *            数据传输超时时间(s)
	 */
	public EmayHttpClient(int httpConnectionTimeOut, int httpReadTimeOut) {
		this.httpConnectionTimeOut = httpConnectionTimeOut;
		this.httpReadTimeOut = httpReadTimeOut;
	}

	public <T> EmayHttpResponse<T> service(EmayHttpRequestParams httpParams, EmayHttpsRequestParams httpsParams, EmayHttpRequestData requestData, Class<EmayHttpResponse<T>> clazz) {
		boolean isHttps = true;
		boolean isCustomHttps = httpsParams.isValid();
		return service0(httpParams, isHttps, isCustomHttps, httpsParams, requestData, clazz);
	}

	public <T> EmayHttpResponse<T> service(EmayHttpRequestParams httpParams, boolean isHttps, EmayHttpRequestData requestData, Class<EmayHttpResponse<T>> clazz) {
		boolean isCustomHttps = false;
		return service0(httpParams, isHttps, isCustomHttps, null, requestData, clazz);
	}

	private <T> EmayHttpResponse<T> service0(EmayHttpRequestParams httpParams, boolean isHttps, boolean isCustomHttps, EmayHttpsRequestParams httpsParams, EmayHttpRequestData requestData,
			Class<EmayHttpResponse<T>> clazz) {
		if (httpParams == null) {
			return newInstance(clazz, EmayHttpResultCode.ERROR_PAMARS, -1, null, null, null, null, null);
		}
		if (httpParams.getUrl() == null || httpParams.getUrl().length() == 0) {
			return newInstance(clazz, EmayHttpResultCode.ERROR_URL, -1, null, null, httpParams.getCharSet(), null, null);
		}
		HttpURLConnection conn = null;
		int httpCode = -1;
		Map<String, String> headers = null;
		List<EmayHttpCookie> cookies = null;
		ByteArrayOutputStream outputStream = null;
		Throwable exp = null;
		EmayHttpResultCode code = null;
		try {
			String url = genUrl(httpParams, requestData);
			conn = createConnection(httpsParams, isHttps, isCustomHttps, url);
			fillConnection(conn, httpParams);
			request(conn, httpParams.getCharSet(), httpParams.getMethod(), requestData);
			httpCode = conn.getResponseCode();
			headers = this.getHeaders(conn, httpParams.getCharSet());
			cookies = this.getCookies(conn, httpParams.getCharSet());
			outputStream = this.getResultOutputStream(conn);
			code = EmayHttpResultCode.SUCCESS;
		} catch (SocketTimeoutException e) {
			code = EmayHttpResultCode.ERROR_TIMEOUT;
			exp = e;
		} catch (KeyManagementException e) {
			code = EmayHttpResultCode.ERROR_HTTPS_SSL;
			exp = e;
		} catch (NoSuchAlgorithmException e) {
			code = EmayHttpResultCode.ERROR_HTTPS_SSL;
			exp = e;
		} catch (ProtocolException e) {
			code = EmayHttpResultCode.ERROR_METHOD;
			exp = e;
		} catch (UnsupportedEncodingException e) {
			code = EmayHttpResultCode.ERROR_CHARSET;
			exp = e;
		} catch (MalformedURLException e) {
			code = EmayHttpResultCode.ERROR_URL;
			httpCode = 500;
			exp = e;
		} catch (IOException e) {
			code = EmayHttpResultCode.ERROR_CONNECT;
			exp = e;
		} catch (UnrecoverableKeyException e) {
			code = EmayHttpResultCode.ERROR_HTTPS_SSL;
			exp = e;
		} catch (KeyStoreException e) {
			code = EmayHttpResultCode.ERROR_HTTPS_SSL;
			exp = e;
		} catch (CertificateException e) {
			code = EmayHttpResultCode.ERROR_HTTPS_SSL;
			exp = e;
		} catch (Throwable e) {
			code = EmayHttpResultCode.ERROR_OTHER;
			exp = e;
		}  finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		try {
			return newInstance(clazz, code, httpCode, headers, cookies, httpParams.getCharSet(), outputStream, exp);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					throw new IllegalArgumentException(e);
				}
			}
		}
	}

	private ByteArrayOutputStream getResultOutputStream(HttpURLConnection conn) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		InputStream is = conn.getInputStream();
		try {
			if (is != null) {
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					outStream.write(buffer, 0, len);
				}
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return outStream;
	}

	private List<EmayHttpCookie> getCookies(HttpURLConnection conn, String charSet) throws UnsupportedEncodingException {
		List<EmayHttpCookie> resultC = new ArrayList<EmayHttpCookie>();
		List<String> cookies = null;
		Map<String, List<String>> header = conn.getHeaderFields();
		if (header != null && !header.isEmpty()) {
			cookies = header.get("Set-Cookie");
		}
		if (cookies != null) {
			for (String cookie : cookies) {
				resultC.add(new EmayHttpCookie(new String(cookie.getBytes("ISO-8859-1"), charSet)));
			}
		}
		return resultC;
	}

	private Map<String, String> getHeaders(HttpURLConnection conn, String charSet) throws UnsupportedEncodingException {
		Map<String, String> resultHeaders = new HashMap<String, String>();
		Map<String, List<String>> header = conn.getHeaderFields();
		if (header != null && !header.isEmpty()) {
			for (Entry<String, List<String>> entry : header.entrySet()) {
				if (!"Set-Cookie".equalsIgnoreCase(entry.getKey())) {
					String valuer = "";
					if (entry.getValue() != null && !entry.getValue().isEmpty()) {
						for (String value : entry.getValue()) {
							valuer += new String(value.getBytes("ISO-8859-1"), charSet) + ",";
						}
						valuer = valuer.substring(0, valuer.length() - 1);
					}
					resultHeaders.put(entry.getKey(), valuer);
				}
			}
		}
		return resultHeaders;
	}

	private void request(HttpURLConnection conn, String charSet, String method, EmayHttpRequestData requestData) throws IOException {
		// TODO 具体区别
		if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method)) {
			conn.setDoOutput(true);
			if (requestData == null) {
				conn.connect();
				return;
			}
			byte[] content = requestData.toBytes(charSet);
			if (content == null || content.length == 0) {
				conn.connect();
				return;
			}
			int length = content.length;
			fillHeader(conn, "Content-Length", String.valueOf(length));
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.write(content);
			out.flush();
			out.close();
		} else {
			conn.connect();
		}
	}

	private void fillConnection(HttpURLConnection conn, EmayHttpRequestParams httpParams) throws ProtocolException {
		this.fillTimeout(conn);
		this.filleMethod(conn, httpParams.getMethod());
		this.fillHeaders(conn, httpParams.getHeaders());
		this.fillCookies(conn, httpParams.getCookies());
	}

	private void fillTimeout(HttpURLConnection conn) {
		if (httpConnectionTimeOut >= 0) {
			conn.setConnectTimeout(httpConnectionTimeOut * 1000);
		}
		if (httpReadTimeOut >= 0) {
			conn.setReadTimeout(httpReadTimeOut * 1000);
		}
	}

	private void filleMethod(HttpURLConnection conn, String method) throws ProtocolException {
		conn.setRequestMethod(method.toUpperCase());
	}

	private void fillHeaders(HttpURLConnection conn, Map<String, String> headers) {
		if (headers == null || headers.isEmpty()) {
			return;
		}
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			fillHeader(conn, entry.getKey(), entry.getValue());
		}
	}

	private void fillHeader(HttpURLConnection conn, String key, String value) {
		conn.setRequestProperty(key, value);
	}

	private void fillCookies(HttpURLConnection conn, List<EmayHttpCookie> cookies) {
		if (cookies == null || cookies.isEmpty()) {
			return;
		}
		StringBuffer buffer = new StringBuffer();
		for(EmayHttpCookie cookie : cookies) {
			if(cookie.getName() != null && cookie.getValue() != null) {
				buffer.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
			}
		}
		String param = buffer.toString();
		param = param.substring(0, param.length() - 1);
		conn.setRequestProperty("Cookie", param);
	}

	private HttpURLConnection createConnection(EmayHttpsRequestParams httpsParams, boolean isHttps, boolean isCustomHttps, String url)
			throws NoSuchAlgorithmException, KeyManagementException, MalformedURLException, IOException, UnrecoverableKeyException, KeyStoreException, CertificateException {
		HttpURLConnection conn = null;
		URL console = new URL(url);
		if (isHttps) {
			SSLContext ctx = SSLContext.getInstance("TLS");
			if (isCustomHttps) {
				KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
				KeyStore keyStore = getKeyStore(httpsParams.getPassword(), httpsParams.getAlgorithm(), httpsParams.getKeyStorePath());
				keyManagerFactory.init(keyStore, httpsParams.getPassword().toCharArray());
				KeyStore trustStore = getKeyStore(httpsParams.getPassword(), httpsParams.getAlgorithm(), httpsParams.getTrustStorePath());
				trustManagerFactory.init(trustStore);
				ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new java.security.SecureRandom());
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

	private KeyStore getKeyStore(String password, String algorithm, String sotrePath) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore ks = KeyStore.getInstance(algorithm);
		FileInputStream is = new FileInputStream(sotrePath);
		ks.load(is, password.toCharArray());
		is.close();
		return ks;
	}

	private String genUrl(EmayHttpRequestParams httpParams, EmayHttpRequestData requestData) {
		if (requestData == null) {
			return httpParams.getUrl();
		}
		// TODO 具体区别
		if ("POST".equalsIgnoreCase(httpParams.getMethod()) || "PUT".equalsIgnoreCase(httpParams.getMethod())) {
			return httpParams.getUrl();
		}
		String getprams = requestData.toString(httpParams.getCharSet());
		if (getprams == null || !"".equalsIgnoreCase(getprams)) {
			return httpParams.getUrl();
		}
		if (httpParams.getUrl().indexOf("?") > 0) {
			return httpParams + "&" + getprams;
		} else {
			return httpParams + "?" + getprams;
		}
	}

	private <T> EmayHttpResponse<T> newInstance(Class<EmayHttpResponse<T>> clazz, EmayHttpResultCode resultCode, int httpCode, Map<String, String> headers, List<EmayHttpCookie> cookies, String charSet,
			ByteArrayOutputStream outputStream, Throwable throwable) {
		EmayHttpResponse<T> response;
		try {
			response = clazz.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
		response.setParams(resultCode, httpCode, headers, cookies, charSet, outputStream, throwable);
		return response;
	}

}
