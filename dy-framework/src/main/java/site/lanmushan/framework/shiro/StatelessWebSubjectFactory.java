package site.lanmushan.framework.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatelessWebSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        // 不创建 session，如果之后调用 Subject.getSession()将抛出 DisabledSessionException 异常
            context.setSessionCreationEnabled(false);
            return super.createSubject(context);
    }
}
