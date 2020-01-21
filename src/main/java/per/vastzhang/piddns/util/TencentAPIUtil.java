package per.vastzhang.piddns.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import per.vastzhang.piddns.pojo.AlterResultBean;
import per.vastzhang.piddns.pojo.RecordListBean;
import per.vastzhang.piddns.pojo.Records;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class TencentAPIUtil {
    @Value("${api.tencent.secretId}")
    private String secretId;
    @Value("${api.tencent.secretKey}")
    private String secretKey;
    @Value("${api.tencent.domain}")
    private String domain;
    @Value("${api.tencent.proUrl}")
    private String proUrl;

    public List<Records> getRecordList() throws Exception {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("Action", "RecordList");
        requestMap.put("Timestamp", Long.toString(getTimestamp()));
        requestMap.put("Nonce", Integer.toString((int) (Math.random() * 10000)));
        requestMap.put("SecretId", secretId);
        requestMap.put("domain", domain);
        String json = NetUtil.NetRequest(getUrl(requestMap));
        JSONObject userJson = JSONObject.parseObject(json);
        RecordListBean recordListBean = JSON.toJavaObject(userJson, RecordListBean.class);
        return recordListBean.getData().getRecords();
    }

    public boolean alterRecord(Records records) throws Exception {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("Action", "RecordModify");
        requestMap.put("Timestamp", Long.toString(getTimestamp()));
        requestMap.put("Nonce", Integer.toString((int) (Math.random() * 10000)));
        requestMap.put("SecretId", secretId);
        requestMap.put("domain", domain);
        requestMap.put("recordId", Long.toString(records.getId()));
        requestMap.put("subDomain", records.getName());
        requestMap.put("recordType", records.getType());
        requestMap.put("recordLine", records.getLine());
        requestMap.put("value", records.getValue());
        requestMap.put("ttl", Integer.toString(records.getTtl()));
        String url = getUrl(requestMap);
        String json = NetUtil.NetRequest(url);
        JSONObject userJson = JSONObject.parseObject(json);
        log.info("修改解析值返回结果 " + json);
        AlterResultBean alterResultBean = JSON.toJavaObject(userJson, AlterResultBean.class);
        return alterResultBean.getCodeDesc().equals("Success");
    }

    public void alterPiIP(String ip) throws Exception {
        List<Records> list = getRecordList();
        Records piRecords = new Records();
        for (Records records : list) {
            if (records.getName().equals("pi")) {
                piRecords = records;
                break;
            }
        }
        piRecords.setValue(ip);
        int num = 0;
        while (!alterRecord(piRecords)) {
            num++;
            if (num > 5) {
                throw new Exception("修改解析值超过重试次数");
            }
        }
    }


    private long getTimestamp() {
        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.toEpochSecond(zoneOffset);
    }

    private String getUrl(Map<String, String> requestMap) {
        String url = NetUtil.formatUrlParam(requestMap, "UTF-8", false, false);
        url = "GET" + proUrl + url;
        String Signature = HMAC_SHA1Util.genHMAC(url, secretKey);
        requestMap.put("Signature", Signature);
        return "https://" + proUrl + NetUtil.formatUrlParam(requestMap, "UTF-8", false, true);
    }
}
