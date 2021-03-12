package per.vastzhang.piddns.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WeChatMessageUtil {

    @Value("${api.ftqq.url}")
    String ftqqUrl;
    @Value("${api.ftqq.sckey}")
    String ftqqSCKEY;

    public void send(String text, String desp) throws Exception {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("text", text);
        requestMap.put("desp", desp);
        NetUtil.NetRequest(getUrl(requestMap));
    }

    private String getUrl(Map<String, String> requestMap) {
        return ftqqUrl + ftqqSCKEY + ".send?" + NetUtil.formatUrlParam(requestMap, "UTF-8", false, true);
    }
}
