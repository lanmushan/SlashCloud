package site.lanmushan.authservice.rpcservice.hystric;

import site.lanmushan.authservice.rpcservice.DictService;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements DictService {
    @Override
    public String sayHiFromClientOne(String name) {
        return "连接失败了,断路器";
    }
}
