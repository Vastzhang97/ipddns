package per.vastzhang.piddns;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import per.vastzhang.piddns.util.IPUtil;
import per.vastzhang.piddns.util.TencentAPIUtil;
import per.vastzhang.piddns.util.WeChatMessageUtil;

import java.text.SimpleDateFormat;

@Slf4j
@SpringBootApplication
public class PiDDNSApplication implements ApplicationRunner {

    @Value("${api.nowAPI.request}")
    String nowAPI;
    @Value("${api.tianqiAPI.request}")
    String tianqiAPI;
    @Value("${api.taobaoAPI.request}")
    String taobaoAPI;
    @Autowired
    private TencentAPIUtil tencentAPIUtil;
    @Autowired
    private WeChatMessageUtil weChatMessageUtil;

    public static void main(String[] args) {
        SpringApplication.run(PiDDNSApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String ip = "";
        String oldIp = "";
        while (true) {
            try {
                ip = IPUtil.getV4IP(nowAPI);
                if (ip.equals("")) {
                    throw new Exception("API1没有得到ip地址");
                }
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    ip = IPUtil.getV4IP(tianqiAPI);
                    if (ip.equals("")) {
                        throw new Exception("API2没有得到ip地址");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                    try {
                        ip = IPUtil.getV4IP(taobaoAPI);
                        if (ip.equals("")) {
                            throw new Exception("API3没有得到ip地址");
                        }
                    } catch (Exception e2) {
                        log.error("所有API未返回ip地址,暂停5分钟", e2);
                        Thread.sleep(1000 * 60 * 5);
                        continue;
                    }
                }
            }
            if (!ip.equals(oldIp)) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                log.info(" 新的ip为 " + ip);
                try {
                    tencentAPIUtil.alterPiIP(ip);
                    log.info("修改解析值成功");
                    weChatMessageUtil.send("解析ip地址成功", "* 原ip为: " + oldIp + "\n\n* 新ip为: " + ip);
                } catch (Exception e) {
                    log.error("修改解析值异常", e);
                }
                oldIp = ip;
            }
            Thread.sleep(1000 * 60 * 2);
        }
    }
}
