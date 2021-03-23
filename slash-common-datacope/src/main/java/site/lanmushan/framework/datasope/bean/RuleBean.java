package site.lanmushan.framework.datasope.bean;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class RuleBean {
    private String ruleValue;
    private String ruleCondition;
    private Integer rulePriority;
    private String tableName;
    private String appName;
    private String ruleMethod;
}
