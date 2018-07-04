package cn.emay.http.client;

import org.junit.Test;

import cn.emay.http.client.response.EmayHttpResponse;
import cn.emay.http.client.response.parser.impl.EmayHttpResponseParserString;

public class TestEmayHttpClient {

	@Test
	public void testClient() {
		
		//URLENCODE POST/GET TEST

		EmayHttpClient.openDebug();
		
		EmayHttpResponse response = EmayHttpClient.get("https://www.baidu.com");
		String st = response.getData(new EmayHttpResponseParserString(), "UTF-8");
		System.out.println(st);

	}

}
