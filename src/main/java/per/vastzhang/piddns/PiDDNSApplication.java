package per.vastzhang.piddns;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import per.vastzhang.piddns.util.*;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
public class PiDDNSApplication implements ApplicationRunner {

    @Value("${api.nowAPI.request}")
    String nowApi;
    @Value("${api.ftqq.active}")
    Boolean wechatPushActive;
    @Autowired
    private TencentApiUtil tencentApiUtil;
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
                ip = IPUtil.getV4IP(nowApi);
                if ("".equals(ip)) {
                    throw new Exception("API1没有得到ip地址");
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("所有API未返回ip地址,暂停5分钟", e);
                TimeUnit.MINUTES.sleep(5);
                continue;
            }
            if (!ip.equals(oldIp)) {
                log.info(" 新的ip为 " + ip);
                try {
                    tencentApiUtil.alterPiIP(ip);
                    String result = "原ip为: " + oldIp + " 新ip为: " + ip;
                    log.info("修改解析值成功");
                    if (wechatPushActive) {
                        weChatMessageUtil.send("解析ip地址成功", result);
                    }
                } catch (Exception e) {
                    log.error("修改解析值异常", e);
                }
                oldIp = ip;
            }
            TimeUnit.MINUTES.sleep(2);
        }
    }
}
