package site.lanmushan.auth.api.service;

/**
 * @author Administrator
 */
public interface AuthApiRegisterService  {
    /**
     * 刷新redis中的api权限关联
     */
    public void refreshAllApiJurisdiction();
    public void refreshApiJurisdiction(String url);

}
