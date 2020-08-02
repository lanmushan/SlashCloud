package site.lanmushan.framework.util.api;

import site.lanmushan.framework.entity.DaiYuApiInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author cayden
 * @date 2020/7/5 17:32
 */
@Slf4j
public class ApiScannerUtils {

    public static List<DaiYuApiInfo> initApiList() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(RequestMapping.class));
        Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents("site.lanmushan.*..controller");

        List<DaiYuApiInfo> allApiInfo = new ArrayList<>();
        for (BeanDefinition beanDefinition : beanDefinitionSet) {
            resolveApis(beanDefinition.getBeanClassName(),allApiInfo);
        }
        return allApiInfo;
    }

    private static void resolveApis(String controllerFullClassName, List<DaiYuApiInfo> apiInfos) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(controllerFullClassName);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
            return;
        }

        String apiPrefix = "";

        RequestMapping controllerMapping = clazz.getAnnotation(RequestMapping.class);
        String[] controllerMappingValues = controllerMapping.value();
        if (controllerMappingValues != null && controllerMappingValues.length > 1) {
            throw new RuntimeException("只能填写一个path");
        }
        if (controllerMapping != null) {
            apiPrefix = controllerMappingValues[0];
        }

        Boolean hasSlashEnd = apiPrefix.endsWith("/");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String apiStr = apiPrefix;
            String[] paths;
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if (requestMapping != null) {
                paths = requestMapping.value();
                String path = paths[0];
                if (path.startsWith("/") && hasSlashEnd) {
                    apiStr += path.substring(1);
                } else if (!path.startsWith("/") && !hasSlashEnd) {
                    apiStr += ("/" + path);
                } else {
                    apiStr += path;
                }
            } else {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                if (getMapping != null) {
                    paths = getMapping.value();
                    String path = paths[0];
                    if (path.startsWith("/") && hasSlashEnd) {
                        apiStr += path.substring(1);
                    } else if (!path.startsWith("/") && !hasSlashEnd) {
                        apiStr += ("/" + path);
                    } else {
                        apiStr += path;
                    }
                } else {
                    PostMapping postMapping = method.getAnnotation(PostMapping.class);
                    if (postMapping != null) {
                        paths = postMapping.value();
                        String path = paths[0];
                        if (path.startsWith("/") && hasSlashEnd) {
                            apiStr += path.substring(1);
                        } else if (!path.startsWith("/") && !hasSlashEnd) {
                            apiStr += ("/" + path);
                        } else {
                            apiStr += path;
                        }
                    } else {
                        continue;
                    }
                }
            }
            DaiYuApiInfo apiInfo = new DaiYuApiInfo();
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            String apiRemark = (apiOperation == null || StringUtils.isBlank(apiOperation.value())) ? method.getName() : apiOperation.value();
            apiInfo.setApiName(apiRemark);
            apiInfo.setApiUrl(apiStr);
            apiInfos.add(apiInfo);
        }
    }
}
