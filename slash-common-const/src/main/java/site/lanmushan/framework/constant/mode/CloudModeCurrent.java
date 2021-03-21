package site.lanmushan.framework.constant.mode;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */
@ConditionalOnProperty(value = "slash.cloud",havingValue = "true",matchIfMissing = false)
@Configuration
public class CloudModeCurrent {
}
