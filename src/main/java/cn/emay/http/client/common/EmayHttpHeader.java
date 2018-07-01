package cn.emay.http.client.common;

public class EmayHttpHeader {

	private String name;
	private String value;

	public EmayHttpHeader() {

	}

	/**
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 */
	public EmayHttpHeader(String name, String value) {
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
