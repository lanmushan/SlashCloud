package site.lanmushan.cms.web.globalInstructionHander;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import site.lanmushan.cms.api.entity.CmsTbRequestMapping;
import site.lanmushan.cms.mapper.CmsTbRequestMappingMapper;
import site.lanmushan.cms.web.dynamicrequest.DynamicRequestMappingRegisterComponent;
import site.lanmushan.cms.web.dynamicrequest.HttpRequest;
import site.lanmushan.framework.constant.GlobalInstructionConstant;
import site.lanmushan.framework.redis.GlobalInstructionEntity;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionHander;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionSubscription;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UpdateRequestMappingGlobalInstructionHander implements GlobalInstructionHander {
    @Autowired
    GlobalInstructionSubscription globalInstructionSubscription;
    private static String cmd = GlobalInstructionConstant.UPDATE_REQUEST_MAPPING;
    @Autowired
    DynamicRequestMappingRegisterComponent requestMappingRegisterService;
    @Autowired
    CmsTbRequestMappingMapper cmsTbRequestMappingMapper;

    @Override
    public void doInstructionExecute(GlobalInstructionEntity instruction) {
        requestMappingRegisterService.removeAll();
        List<CmsTbRequestMapping> cmsTbRequestMappingList = cmsTbRequestMappingMapper.selectAll();
        List<HttpRequest> httpRequestList = new ArrayList<>(cmsTbRequestMappingList.size());
        cmsTbRequestMappingList.forEach(it -> {
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.setRequestMethod(it.getRequestMethod());
            httpRequest.setRequestUrl(it.getRequestUrl());
            httpRequestList.add(httpRequest);
        });
        requestMappingRegisterService.registerHttpRequestList(httpRequestList);
        log.info("重新加载RequestMapping完成");
    }

    @PostConstruct
    public void init() {
        globalInstructionSubscription.registerGlobalInstructionHander(cmd, null, this);
        this.doInstructionExecute(null);
    }
}
