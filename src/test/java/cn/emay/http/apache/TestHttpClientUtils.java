package cn.emay.http.apache;

import java.util.HashMap;
import java.util.Map;

public class TestHttpClientUtils {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("page_id", "4845534524194232");
        map.put("_item_pwd", "191919");
        HttpResult result = HttpClientUtils.post("https://www.showdoc.cc/server/index.php?s=/api/page/info", map);
        System.out.println(result.getDataStringUTF8());
    }
}
