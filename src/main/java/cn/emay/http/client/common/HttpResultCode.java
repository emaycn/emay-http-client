package cn.emay.http.client.common;

/**
 * HTTP 访问结果编码
 * 
 * @author Frank
 *
 */
public enum HttpResultCode {

	/**
	 * URL错误
	 */
	ERROR_URL("URL错误", "ERROR_URL"),
	/**
	 * HTTPS异常
	 */
	ERROR_HTTPS("HTTPS异常", "ERROR_HTTPS"),
	/**
	 * 链接创建失败
	 */
	ERROR_CONNECT("链接创建失败", "ERROR_CONNECT"),
	/**
	 * 请求超时
	 */
	ERROR_REQUEST_TIMEOUT("请求超时", "ERROR_REQUEST_TIMEOUT"),
	/**
	 * 请求失败
	 */
	ERROR_REQUEST("请求失败", "ERROR_REQUEST"),
	/**
	 * 响应超时
	 */
	ERROR_RESPONSE_TIMEOUT("响应超时", "ERROR_RESPONSE_TIMEOUT"),
	/**
	 * 响应失败-编码错误
	 */
	ERROR_RESPONSE_CHARSET("响应失败-编码错误", "ERROR_RESPONSE_CHARSET"),
	/**
	 * 响应失败
	 */
	ERROR_RESPONSE("响应失败", "ERROR_RESPONSE"),
	/**
	 * 成功
	 */
	SUCCESS("成功", "SUCCESS"),
	/**
	 * 其他异常
	 */
	ERROR_OTHER("其他异常", "ERROR_OTHER"),;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 编码
	 */
	private String code;

	private HttpResultCode(String name, String code) {
		this.name = name;
		this.code = code;
	}

	/**
	 * 根据Code查名称
	 * 
	 * @param code
	 * @return
	 */
	public static String findNameByCode(String code) {
		for (HttpResultCode oc : HttpResultCode.values()) {
			if (oc.getCode().equals(code)) {
				return oc.getName();
			}
		}
		return null;
	}

	/**
	 * 根据名称查Code
	 * 
	 * @param name
	 * @return
	 */
	public static String findCodeByName(String name) {
		for (HttpResultCode oc : HttpResultCode.values()) {
			if (oc.getName().equals(name)) {
				return oc.getCode();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
