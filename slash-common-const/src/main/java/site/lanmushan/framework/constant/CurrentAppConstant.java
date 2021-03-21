package site.lanmushan.framework.constant;


import java.util.ResourceBundle;

/**
 * @author Administrator
 */
public class CurrentAppConstant {
    static ResourceBundle bundle = ResourceBundle.getBundle("application");
    public static final String appName = bundle.getString("spring.application.name");
    public static final Boolean cloudMode=Boolean.parseBoolean(bundle.getString("slash.cloud"));
}
