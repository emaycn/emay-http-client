package cn.emay.http.client.common;

/**
 * Http Header
 * 
 * @author Frank
 *
 */
public class HttpHeader {

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 值
	 */
	private String value;

	public HttpHeader() {

	}

	/**
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 */
	public HttpHeader(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
