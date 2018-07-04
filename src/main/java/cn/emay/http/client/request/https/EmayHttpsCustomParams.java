package cn.emay.http.client.request.https;

/**
 * https 自定义参数
 * 
 * @author Frank
 *
 */
public class EmayHttpsCustomParams {

	/**
	 * 密钥
	 */
	private String password;
	/**
	 * 密钥库文件地址
	 */
	private String keyStorePath;
	/**
	 * 信任库文件地址
	 */
	private String trustStorePath;
	/**
	 * 指定交换数字证书的加密标准:JKS
	 */
	private String algorithm;

	public EmayHttpsCustomParams() {

	}

	/**
	 * 
	 * @param password
	 *            密钥
	 * @param keyStorePath
	 *            密钥库文件地址
	 * @param trustStorePath
	 *            信任库文件地址
	 * @param algorithm
	 *            指定交换数字证书的加密标准:JKS
	 */
	public EmayHttpsCustomParams(String password, String keyStorePath, String trustStorePath, String algorithm) {
		this.password = password;
		this.keyStorePath = keyStorePath;
		this.trustStorePath = trustStorePath;
		this.algorithm = algorithm;
	}

	/**
	 * 是否有效
	 * 
	 * @return
	 */
	public boolean isValid() {
		return password != null && keyStorePath != null && trustStorePath != null && algorithm != null;
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
