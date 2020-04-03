package com.hyp.learn.shiro.business.shiro.credentials;

import com.hyp.learn.shiro.util.PasswordUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Shiro-密码凭证匹配器（验证密码有效性）
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.shiro.credentials
 * hyp create at 20-3-29
 **/
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    /**
     * 比较输入密码跟数据库密码是否相等
     *
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(utoken.getPassword());
        //获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        try {
            dbPassword = PasswordUtil.decrypt(dbPassword, utoken.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return this.equals(inPassword, dbPassword);
    }
}
