package cn.emay.http.client.https;

/**
 * https 证书参数<br/>
 * 此处传入的是证书文件xxx.cer
 * 
 * @author Frank
 *
 */
public class HttpsCerParams {

	/**
	 * 类型<br/>
	 * 国际标准类型为X.509
	 */
	private String type;
	/**
	 * 证书文件地址
	 */
	private String cerPath;

	/**
	 * 
	 * @param type
	 *            类型<br/>
	 *            国际标准类型为X.509
	 * @param cerPath
	 *            证书文件地址
	 */
	public HttpsCerParams(String type, String cerPath) {
		this.type = type;
		this.cerPath = cerPath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCerPath() {
		return cerPath;
	}

	public void setCerPath(String cerPath) {
		this.cerPath = cerPath;
	}

}
