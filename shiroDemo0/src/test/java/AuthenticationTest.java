import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tomgao
 * @Description Shiro 认证过程 测试类
 * @Date 创建于 2021/11/18
 */
public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        simpleAccountRealm.addAccount("tomgao", "111111");
    }

    @Test
    public void testAuthentication() {


        //1. 构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager); // 设置SecurityManager环境

        Subject subject = SecurityUtils.getSubject(); // 获得当前主体
        UsernamePasswordToken token = new UsernamePasswordToken("tomgao", "111111");
        subject.login(token);

        System.out.println("isAuthenticated: " + subject.isAuthenticated());
        subject.checkRoles("user");
        subject.logout();

        System.out.println("isAuthenticated: " + subject.isAuthenticated());


    }
}
