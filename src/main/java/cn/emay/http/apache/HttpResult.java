package cn.emay.http.apache;

import cn.emay.json.JsonHelper;
import com.google.gson.reflect.TypeToken;
import org.apache.http.Header;
import org.apache.http.StatusLine;
import org.apache.http.cookie.Cookie;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class HttpResult {

    /**
     * 是否请求成功
     */
    private final boolean success;
    /**
     * 响应状态
     */
    private final StatusLine statusLine;
    /**
     * 异常
     */
    private final Throwable throwable;
    /**
     * 返回的数据
     */
    private final byte[] data;
    /**
     * Http响应头
     */
    private final Header[] headers;
    /**
     * http响应Cookies
     */
    private final Cookie[] cookies;

    public HttpResult(boolean success, StatusLine statusLine, Throwable throwable, byte[] data, Header[] headers, Cookie[] cookies) {
        this.success = success;
        this.statusLine = statusLine;
        this.throwable = throwable;
        this.data = data;
        this.headers = headers;
        this.cookies = cookies;
    }

    public static HttpResult successHttpResult(StatusLine statusLine, byte[] data, Header[] headers, Cookie[] cookies) {
        return new HttpResult(true, statusLine, null, data, headers, cookies);
    }

    public static HttpResult failHttpResult(StatusLine statusLine, Throwable throwable) {
        return new HttpResult(false, statusLine, throwable, null, null, null);
    }

    /**
     * 是否请求成功
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 异常
     */
    public Throwable getThrowable() {
        return throwable;
    }

    /**
     * Http响应头
     */
    public Header[] getHeaders() {
        return headers;
    }

    /**
     * http响应Cookies
     */
    public Cookie[] getCookies() {
        return cookies;
    }

    /**
     * 响应状态
     */
    public StatusLine getStatusLine() {
        return statusLine;
    }

    /**
     * 响应数据
     */
    public byte[] getData() {
        return data;
    }

    /**
     * utf8编码的响应字符串数据
     */
    public String getDataStringUTF8() {
        return getDataString(StandardCharsets.UTF_8);
    }

    /**
     * 响应字符串数据
     */
    public String getDataString(Charset charset) {
        if (data == null) {
            return null;
        }
        if (charset != null) {
            return new String(data, charset);
        } else {
            return new String(data, StandardCharsets.UTF_8);
        }
    }

    /**
     * 响应字符串数据
     */
    public String getDataString(String charset) {
        if (data == null) {
            return null;
        }
        if (charset != null) {
            try {
                return new String(data, charset);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return new String(data, StandardCharsets.UTF_8);
        }
    }

    /**
     * 响应对象（Json转换）数据
     */
    public <T> T getDataObjectByJson(Charset charset, TypeToken<T> token) {
        if (token == null) {
            return null;
        }
        String dataString;
        if (charset == null) {
            dataString = getDataStringUTF8();
        } else {
            dataString = getDataString(charset);
        }
        if (dataString == null) {
            return null;
        }
        return JsonHelper.fromJson(token, dataString);
    }

    /**
     * 响应对象（Json转换）数据
     */
    public <T> T getDataObjectByJson(Charset charset, Class<T> clazz) {
        if (clazz == null) {
            return null;
        }
        String dataString;
        if (charset == null) {
            dataString = getDataStringUTF8();
        } else {
            dataString = getDataString(charset);
        }
        if (dataString == null) {
            return null;
        }
        return JsonHelper.fromJson(clazz, dataString);
    }

    /**
     * 响应对象（Json转换）数据
     */
    public <T> T getDataObjectByJson(String charset, TypeToken<T> token) {
        if (token == null) {
            return null;
        }
        String dataString;
        if (charset == null) {
            dataString = getDataStringUTF8();
        } else {
            dataString = getDataString(charset);
        }
        if (dataString == null) {
            return null;
        }
        return JsonHelper.fromJson(token, dataString);
    }

    /**
     * 响应对象（Json转换）数据
     */
    public <T> T getDataObjectByJson(String charset, Class<T> clazz) {
        if (clazz == null) {
            return null;
        }
        String dataString;
        if (charset == null) {
            dataString = getDataStringUTF8();
        } else {
            dataString = getDataString(charset);
        }
        if (dataString == null) {
            return null;
        }
        return JsonHelper.fromJson(clazz, dataString);
    }

    /**
     * 响应对象（Json转换）数据
     */
    public <T> T getDataObjectByJson(TypeToken<T> token) {
        return getDataObjectByJson(StandardCharsets.UTF_8, token);
    }

    /**
     * 响应对象（Json转换）数据
     */
    public <T> T getDataObjectByJson(Class<T> clazz) {
        return getDataObjectByJson(StandardCharsets.UTF_8, clazz);
    }


}
