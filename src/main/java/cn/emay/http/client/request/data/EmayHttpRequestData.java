package cn.emay.http.client.request.data;

/**
 * 传输数据
 * 
 * @author Frank
 *
 */
public interface EmayHttpRequestData {

	/**
	 * 将请求参数转换为String<br/>
	 * 用于get方法传输
	 * 
	 * @param charSet
	 *            编码
	 * @return
	 */
	public abstract String toString(String charSet);

	/**
	 * 将请求参数转换为byte[]<br/>
	 * 用于post方法传输
	 * 
	 * @param charSet
	 *            编码
	 * @return
	 */
	public abstract byte[] toBytes(String charSet);

}
