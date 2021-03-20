package site.lanmushan.framework.configure.websession;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Session;
import org.apache.catalina.session.ManagerBase;
import org.apache.tomcat.util.ExceptionUtils;

import java.io.IOException;
/** 来源
 * @author https://blog.csdn.net/songxiuliang/article/details/108534779
 */
@Slf4j
public class NoSessionManager extends ManagerBase implements Lifecycle {

    @Override
    protected synchronized void startInternal() throws LifecycleException {
        super.startInternal();
        try {
            load();
        } catch (Throwable t) {
            ExceptionUtils.handleThrowable(t);
            t.printStackTrace();
        }
        setState(LifecycleState.STARTING);
    }

    @Override
    protected synchronized void stopInternal() throws LifecycleException {
        setState(LifecycleState.STOPPING);
        try {
            unload();
        } catch (Throwable t) {
            ExceptionUtils.handleThrowable(t);
            t.printStackTrace();
        }
        super.stopInternal();
    }

    @Override
    public void load() throws ClassNotFoundException, IOException {
        log.info("HttpSession已经关闭");
    }

    @Override
    public void unload() throws IOException {}
    @Override
    public Session createSession(String sessionId) {
        return null;
    }

    @Override
    public Session createEmptySession() {
        return null;
    }

    @Override
    public void add(Session session) {}
    @Override
    public Session findSession(String id) throws IOException {
        return null;
    }
    @Override
    public Session[] findSessions(){
        return null;
    }
    @Override
    public void processExpires() {}
}