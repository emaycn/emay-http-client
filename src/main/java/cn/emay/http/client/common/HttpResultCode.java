package cn.emay.http.client.common;

/**
 * HTTP 访问结果编码
 * 
 * @author Frank
 *
 */
public enum HttpResultCode {

	ERROR_URL("URL访问失败", "ERROR_URL"), //
	ERROR_HTTPS("HTTPS异常", "ERROR_HTTPS"), //

	ERROR_CONNECT("链接创建失败", "ERROR_CONNECT"), //

	ERROR_REQUEST_TIMEOUT("请求超时", "ERROR_REQUEST_TIMEOUT"), //
	ERROR_REQUEST("请求失败", "ERROR_REQUEST"), //

	ERROR_RESPONSE_TIMEOUT("响应超时", "ERROR_RESPONSE_TIMEOUT"), //
	ERROR_RESPONSE_CHARSET("响应失败-编码错误", "ERROR_RESPONSE_CHARSET"), //
	ERROR_RESPONSE("响应失败", "ERROR_RESPONSE"), //

	SUCCESS("成功", "SUCCESS"), //

	ERROR_OTHER("其他异常", "ERROR_OTHER"), //
	;

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

	public static String findNameByCode(String code) {
		for (HttpResultCode oc : HttpResultCode.values()) {
			if (oc.getCode().equals(code)) {
				return oc.getName();
			}
		}
		return null;
	}

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
