package cn.emay.http.client;

import org.junit.Test;

import cn.emay.http.client.request.impl.http.EmayHttpRequestKV;
import cn.emay.http.client.request.params.impl.EmayHttpRequestParamsKV;
import cn.emay.http.client.response.impl.EmayHttpResponseString;
import cn.emay.http.client.response.parser.impl.EmayHttpResponseStringPraser;

public class TestEmayHttpClient {

	@Test
	public void testClient() {

		EmayHttpClient client = new EmayHttpClient();
		EmayHttpResponseString string = client.service(new EmayHttpRequestKV(new EmayHttpRequestParamsKV("https://www.baidu.com")), new EmayHttpResponseStringPraser());
		System.out.println("getHttpCode\t" + string.getHttpCode());
		System.out.println("getResult\t" + string.getResult());
		System.out.println("getCookies\t" + string.getCookies());
		System.out.println("getHeaders\t" + string.getHeaders());
		System.out.println("getResultCode\t" + string.getResultCode());
		System.out.println("getThrowable\t" + string.getThrowable());
	}

}
