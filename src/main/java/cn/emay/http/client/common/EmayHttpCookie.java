package cn.emay.http.client.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EmayHttpCookie {

	private String name; // Cookie名称 NAME= ... "$Name" style is reserved
	private String value; // Cookie值 value of NAME
	private String domain; // 生效域名 ;Domain=VALUE ... domain that sees cookie
	private String path; // 生效路径 ;Path=VALUE ... URLs that see the cookie
	private int maxAge = -1; // 最大存活时间 ;Max-Age=VALUE ... cookies auto-expire
	private Date expires; // 到什么时候过期，与maxAge互斥
	private boolean secure = false; // 是否只再https中使用 ;Secure ... e.g. use SSL
	private boolean httpOnly = false;// 是否必须用于http传输

	public EmayHttpCookie() {

	}

	/**
	 * 
	 * @param name
	 *            Cookie名称
	 * @param value
	 *            Cookie值
	 */
	public EmayHttpCookie(String name, String value) {
		this(name, value, null, null, -1, false, false);
	}

	/**
	 * 
	 * @param name
	 *            Cookie名称
	 * @param value
	 *            Cookie值
	 * @param domain
	 *            生效域名
	 * @param path
	 *            生效路径
	 * @param maxAge
	 *            最大存活时间
	 * @param secure
	 *            是否只再https中使用
	 * @param httpOnly
	 *            是否必须用于http传输
	 */
	public EmayHttpCookie(String name, String value, String domain, String path, int maxAge, boolean secure, boolean httpOnly) {
		this.name = name;
		this.value = value;
		this.domain = domain;
		this.path = path;
		this.maxAge = maxAge;
		this.secure = secure;
		this.httpOnly = httpOnly;
	}

	/**
	 * 
	 * @param name
	 *            Cookie名称
	 * @param value
	 *            Cookie值
	 * @param domain
	 *            生效域名
	 * @param path
	 *            生效路径
	 * @param expires
	 *            到什么时候过期
	 * @param secure
	 *            是否只再https中使用
	 * @param httpOnly
	 *            是否必须用于http传输
	 */
	public EmayHttpCookie(String name, String value, String domain, String path, Date expires, boolean secure, boolean httpOnly) {
		this.name = name;
		this.value = value;
		this.domain = domain;
		this.path = path;
		this.expires = expires;
		this.secure = secure;
		this.httpOnly = httpOnly;
	}

	/**
	 * 
	 * @param cookieStr
	 *            cookie字符串
	 */
	public EmayHttpCookie(String cookieStr) {
		if (cookieStr == null || cookieStr.length() == 0) {
			return;
		}
		String[] items = cookieStr.split(";");
		int index = 0;
		for (String item : items) {
			String[] vtems = item.trim().split("=");
			String key = vtems[0];
			String value = item.trim().substring(key.length());
			if (index == 0) {
				this.name = key;
				this.value = value;
			} else {
				if (key.equalsIgnoreCase("Domain")) {
					this.domain = value;
				} else if (key.equalsIgnoreCase("Path")) {
					this.path = value;
				} else if (key.equalsIgnoreCase("Expires")) {
					try {
						this.expires = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'",Locale.US).parse(value);
					} catch (ParseException e) {
					}
				} else if (key.equalsIgnoreCase("Max-Age")) {
					try {
						this.maxAge = Integer.valueOf(value);
					} catch (Exception e) {
					}
				} else if (key.equalsIgnoreCase("HttpOnly")) {
					this.httpOnly = true;
				}
			}
			index++;
		}
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public boolean isSecure() {
		return secure;
	}

	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	public boolean isHttpOnly() {
		return httpOnly;
	}

	public void setHttpOnly(boolean httpOnly) {
		this.httpOnly = httpOnly;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}
}
