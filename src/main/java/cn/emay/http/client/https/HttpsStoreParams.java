package cn.emay.http.client.https;

/**
 * https 密钥信任库参数<br/>
 * 拿到证书后，执行命令keytool -import -trustcacerts -alias xxxx -storepass yyyyy -file
 * xxxx.cer -keystore cacerts把证书导入密钥库。<br/>
 * 此处传入的是密钥库文件cacerts，而非证书
 * 
 * @author Frank
 *
 */
public class HttpsStoreParams {

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
	 * 指定交换数字证书的加密标准<br/>
	 * 通用:pkcs12<br/>
	 * JAVA自己定义的一种：JKS
	 */
	private String algorithm;

	/**
	 * 
	 * @param password
	 *            密钥
	 * @param keyStorePath
	 *            密钥库文件地址
	 * @param trustStorePath
	 *            信任库文件地址,一般于密钥库文件相同
	 * @param algorithm
	 *            指定交换数字证书的加密标准<br/>
	 *            通用:pkcs12<br/>
	 *            JAVA自己定义的一种：JKS
	 */
	public HttpsStoreParams(String password, String keyStorePath, String trustStorePath, String algorithm) {
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
