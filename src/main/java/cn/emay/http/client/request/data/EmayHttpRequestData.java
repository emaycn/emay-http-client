package cn.emay.http.client.request.data;

/**
 * 传输数据
 * 
 * @author Frank
 *
 */
public interface EmayHttpRequestData {

	/**
	 * 将请求参数转换为byte[]<br/>
	 * 
	 * @param charSet
	 *            编码
	 * @return
	 */
	public abstract byte[] toBytes(String charSet);

}
