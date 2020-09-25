package cn.emay.http.client.request;

/**
 * 传输数据
 *
 * @author Frank
 */
public interface HttpRequestData {

    /**
     * 将请求参数转换为byte[]<br/>
     *
     * @param charSet 编码
     * @return 字节数组
     */
    byte[] toBytes(String charSet);

}
