# 亿美JAVA组件：HttpClient组件

```java

/**
*   HttpClient組件
**/
cn.emay.http.client.HttpClient

/**
* example
**/
HttpResponse res = HttpClient.get("http://www.baidu.com");
String resString = res.getData(new HttpResponseParserString(),"UTF-8");

```