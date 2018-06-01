package cn.emay.http.client.request.params;

/**
 * https 参数
 * 
 * @author Frank
 *
 */
public class EmayHttpsRequestParams {

	private String password;// 密钥库密钥
	private String keyStorePath;// 密钥库文件地址
	private String trustStorePath;// 信任库文件地址
	private String algorithm;// 指定交换数字证书的加密标准:JKS

	public EmayHttpsRequestParams() {

	}

	/**
	 * 
	 * @param password
	 *            密钥库密钥
	 * @param keyStorePath
	 *            密钥库文件地址
	 * @param trustStorePath
	 *            信任库文件地址
	 * @param algorithm
	 *            指定交换数字证书的加密标准:JKS
	 */
	public EmayHttpsRequestParams(String password, String keyStorePath, String trustStorePath, String algorithm) {
		this.password = password;
		this.keyStorePath = keyStorePath;
		this.trustStorePath = trustStorePath;
		this.algorithm = algorithm;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKeyStorePath() {
		return keyStorePath;
	}

	public void setKeyStorePath(String keyStorePath) {
		this.keyStorePath = keyStorePath;
	}

	public String getTrustStorePath() {
		return trustStorePath;
	}

	public void setTrustStorePath(String trustStorePath) {
		this.trustStorePath = trustStorePath;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

}
