package site.lanmushan.auth.api.service;

/**
 * @author Administrator
 */
public interface AuthApiRegisterService  {
    /**
     * 刷新所有URL
     */
    public void refreshAllApiJurisdiction();

    /**
     * 刷新一个url
     * @param url
     */
    public void refreshApiJurisdiction(String url);

}
