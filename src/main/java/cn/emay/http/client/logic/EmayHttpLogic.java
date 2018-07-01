package cn.emay.http.client.logic;

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
import cn.emay.http.client.common.EmayHttpHeader;
import cn.emay.http.client.common.EmayHttpResultCode;
import cn.emay.http.client.request.data.EmayHttpRequestData;
import cn.emay.http.client.request.https.EmayHttpsCustomParams;
import cn.emay.http.client.response.EmayHttpResponse;

/**
 * 逻辑<br/>
 * 
 * @author Frank
 *
 */
public class EmayHttpLogic {

	private static EmayHttpLogic LOGIC = new EmayHttpLogic();

	private EmayHttpLogic() {

	}

	public static EmayHttpLogic getInstance() {
		return LOGIC;
	}

	public EmayHttpResponse service(String url, String method) {
		return service(url, method, "UTF-8", null, null, null, 30, 30, null);
	}

	public EmayHttpResponse service(String url, String method, EmayHttpRequestData requestData) {
		return service(url, method, "UTF-8", null, null, requestData, 30, 30, null);
	}

	public EmayHttpResponse service(String url, String method, String charSet, EmayHttpRequestData requestData) {
		return service(url, method, charSet, null, null, requestData, 30, 30, null);
	}

	public EmayHttpResponse service(String url, String method, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData) {
		return service(url, method, charSet, headers, cookies, requestData, 30, 30, null);
	}

	public EmayHttpResponse service(String url, String method, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData, int connectionTimeOut,
			int readTimeOut) {
		return service(url, method, charSet, headers, cookies, requestData, connectionTimeOut, readTimeOut, null);
	}

	public EmayHttpResponse service(String url, String method, String charSet, List<EmayHttpHeader> headers, List<EmayHttpCookie> cookies, EmayHttpRequestData requestData, int connectionTimeOut,
			int readTimeOut, EmayHttpsCustomParams customHttpsParams) {
		if (url == null || url.length() == 0) {
			return new EmayHttpResponse(EmayHttpResultCode.ERROR_URL, -1, null, null, null, null);
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
			url0 = "http://" + url;
			isHttps0 = false;
		}
		String method0 = "GET";
		if (method != null) {
			method0 = method.toUpperCase();
		}
		String charSet0 = "UTF-8";
		if (charSet != null) {
			charSet0 = charSet;
		}
		int connectionTimeOut0 = 30;
		if (connectionTimeOut > 0) {
			connectionTimeOut0 = connectionTimeOut;
		}
		int readTimeOut0 = 30;
		if (readTimeOut > 0) {
			readTimeOut0 = readTimeOut;
		}
		EmayHttpRequestData requestData0 = requestData;
		List<EmayHttpHeader> headers0 = headers;
		List<EmayHttpCookie> cookies0 = cookies;
		EmayHttpsCustomParams customHttpsParams0 = customHttpsParams;
		if (customHttpsParams0 != null && !customHttpsParams.isValid()) {
			return new EmayHttpResponse(EmayHttpResultCode.ERROR_CUSTOM_HTTPS_PAMARS, -1, null, null, null, null);
		}

		HttpURLConnection conn0 = null;
		try {
			url0 = genUrl(url0, method0, charSet0, requestData0);
			conn0 = createConnection(url0, isHttps0, customHttpsParams0);
			fillTimeout(conn0, connectionTimeOut0, readTimeOut0);
			filleMethod(conn0, method0);
			fillHeaders(conn0, headers0);
			fillCookies(conn0, isHttps0, cookies0);
			request(conn0, charSet0, method0, requestData0);
			int httpCode0 = conn0.getResponseCode();
			List<EmayHttpHeader> responseHeaders0 = this.getHeaders(conn0, charSet0);
			List<EmayHttpCookie> responseCookies0 = this.getCookies(conn0, charSet0);
			byte[] responseData0 = this.getResultOutputStream(conn0);
			return new EmayHttpResponse(EmayHttpResultCode.SUCCESS, httpCode0, responseHeaders0, responseCookies0, responseData0, null);
		} catch (SocketTimeoutException e) {
			return new EmayHttpResponse(EmayHttpResultCode.ERROR_TIMEOUT, -1, null, null, null, e);
		} catch (KeyManagementException e) {
			return new EmayHttpResponse(EmayHttpResultCode.ERROR_HTTPS_SSL, -1, null, null, null, e);
		} catch (NoSuchAlgorithmException e) {
			return new EmayHttpResponse(EmayHttpResultCode.ERROR_HTTPS_SSL, -1, null, null, null, e);
		} catch (ProtocolException e) {
			return new EmayHttpResponse(EmayHttpResultCode.ERROR_METHOD, -1, null, null, null, e);
		} catch (UnsupportedEncodingException e) {
			return new EmayHttpResponse(EmayHttpResultCode.ERROR_CHARSET, -1, null, null, null, e);
		} catch (MalformedURLException e) {
			return new EmayHttpResponse(EmayHttpResultCode.ERROR_URL, -1, null, null, null, e);
		} catch (IOException e) {
			return new EmayHttpResponse(EmayHttpResultCode.ERROR_CONNECT, -1, null, null, null, e);
		} catch (UnrecoverableKeyException e) {
			return new EmayHttpResponse(EmayHttpResultCode.ERROR_HTTPS_SSL, -1, null, null, null, e);
		} catch (KeyStoreException e) {
			return new EmayHttpResponse(EmayHttpResultCode.ERROR_HTTPS_SSL, -1, null, null, null, e);
		} catch (CertificateException e) {
			return new EmayHttpResponse(EmayHttpResultCode.ERROR_HTTPS_SSL, -1, null, null, null, e);
		} catch (Throwable e) {
			return new EmayHttpResponse(EmayHttpResultCode.ERROR_OTHER, -1, null, null, null, e);
		} finally {
			if (conn0 != null) {
				conn0.disconnect();
			}
		}
	}

	private String genUrl(String url, String method, String charSet, EmayHttpRequestData requestData) {
		if (requestData == null) {
			return url;
		}
		if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method)) {
			return url;
		}
		String getprams = requestData.toString(charSet);
		if (getprams == null || !"".equalsIgnoreCase(getprams)) {
			return url;
		}
		if (url.indexOf("?") > 0) {
			return url + "&" + getprams;
		} else {
			return url + "?" + getprams;
		}
	}

	private HttpURLConnection createConnection(String url, boolean isHttps, EmayHttpsCustomParams httpsParams)
			throws NoSuchAlgorithmException, KeyManagementException, MalformedURLException, IOException, UnrecoverableKeyException, KeyStoreException, CertificateException {
		HttpURLConnection conn = null;
		URL console = new URL(url);
		if (isHttps) {
			SSLContext ctx = SSLContext.getInstance("TLS");
			if (httpsParams != null) {
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

	private KeyStore getKeyStore(String password, String algorithm, String sotrePath) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore ks = KeyStore.getInstance(algorithm);
		FileInputStream is = new FileInputStream(sotrePath);
		ks.load(is, password.toCharArray());
		is.close();
		return ks;
	}

	private void fillTimeout(HttpURLConnection conn, int httpConnectionTimeOut, int httpReadTimeOut) {
		conn.setConnectTimeout(httpConnectionTimeOut * 1000);
		conn.setReadTimeout(httpReadTimeOut * 1000);
	}

	private void filleMethod(HttpURLConnection conn, String method) throws ProtocolException {
		conn.setRequestMethod(method);
	}

	private void fillHeaders(HttpURLConnection conn, List<EmayHttpHeader> headers) {
		if (headers == null || headers.isEmpty()) {
			return;
		}
		for (EmayHttpHeader entry : headers) {
			fillHeader(conn, entry.getName(), entry.getValue());
		}
	}

	private void fillHeader(HttpURLConnection conn, String key, String value) {
		conn.setRequestProperty(key, value);
	}

	private void fillCookies(HttpURLConnection conn, boolean isHttps, List<EmayHttpCookie> cookies) {
		if (cookies == null || cookies.isEmpty()) {
			return;
		}
		StringBuffer buffer = new StringBuffer();
		for (EmayHttpCookie cookie : cookies) {
			if (cookie.getName() != null && cookie.getValue() != null) {
				if (cookie.isSecure() && !isHttps) {
					continue;
				}
				buffer.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
			}
		}
		String param = buffer.toString();
		param = param.substring(0, param.length() - 1);
		conn.setRequestProperty("Cookie", param);
	}

	private void request(HttpURLConnection conn, String charSet, String method, EmayHttpRequestData requestData) throws IOException {
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
			try {
				out.write(content);
				out.flush();
			} catch (IOException e) {
				throw e;
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} else {
			conn.connect();
		}
	}

	private List<EmayHttpHeader> getHeaders(HttpURLConnection conn, String charSet) throws UnsupportedEncodingException {
		List<EmayHttpHeader> list = new ArrayList<EmayHttpHeader>();
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
				list.add(new EmayHttpHeader(name, value0));

			}
		}
		return list;
	}

	private List<EmayHttpCookie> getCookies(HttpURLConnection conn, String charSet) throws UnsupportedEncodingException {
		List<EmayHttpCookie> list = new ArrayList<EmayHttpCookie>();
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
			list.add(new EmayHttpCookie(value0));

		}
		return list;
	}

	private byte[] getResultOutputStream(HttpURLConnection conn) throws IOException {
		byte[] buffer = null;
		int length = conn.getContentLength();
		if (length <= 0) {
			return buffer;
		}
		buffer = new byte[length];
		InputStream is = conn.getInputStream();
		if (is != null) {
			try {
				is.read(buffer);
			} catch (IOException e) {
				throw e;
			} finally {
				is.close();
			}
		}
		return buffer;

	}

}
