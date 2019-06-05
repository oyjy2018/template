package com.ydc.beans.shiro.web;

import com.ydc.beans.config.ShiroPropertiesConfig;
import com.ydc.beans.shiro.RedisManagerBean;
import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.serializer.ObjectSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * webshiro配置
 *
 * @author
 * @create 2018-12-05 11:53
 **/
@Configuration
public class WebShiroConfig {

    private final static Logger logger = LoggerFactory.getLogger(WebShiroConfig.class);


    @Autowired
    RedisManagerBean redisManager;


    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * @param securityManager
     * @return
     */
    @Lazy
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        logger.info("--------------------shiro filter-------------------");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置登录页面
        shiroFilterFactoryBean.setLoginUrl("/getLoginParam");
        //配置登录成功页面
        shiroFilterFactoryBean.setSuccessUrl("/loginCode/**");
        //配置未授权页面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/getLoginParam");
        //配置拦截器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new WebShiroUserFormAuthenticationFilter());
        //限制同一帐号同时在线的个数。
//        filterMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //配置权限map
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置退出
        filterChainDefinitionMap.put("/quit", "logout");
        //配置不需要拦截的链接
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/getLoginParam", "anon");
        filterChainDefinitionMap.put("/performance/**","anon");
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/ydhc/**", "anon");
        filterChainDefinitionMap.put("/credit/**","anon");
        filterChainDefinitionMap.put("/vehicleUsed/saveOrUpdateVehicleUsed", "anon");
        filterChainDefinitionMap.put("/demo/**", "anon");
        filterChainDefinitionMap.put("/serviceFunc/**", "anon");
        filterChainDefinitionMap.put("/applyAppr/**", "anon");
        filterChainDefinitionMap.put("/dictionaryDetail/**", "anon");
        //需要拦截的链接
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Lazy
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(webShiroRealm());
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /**
     * 身份认证realm;
     * (这个需要自己写，账号密码校验；权限等)
     *
     * @return
     */
    @Lazy
    @Bean
    public WebShiroRealm webShiroRealm() {
        WebShiroRealm webShiroRealm = new WebShiroRealm();
        webShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return webShiroRealm;
    }
    @Bean
    @Lazy
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager.getRedisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * <p>
     * 使用的是shiro-redis开源插件
     */
    @Lazy
    @Bean
    public WebShiroRedisSessionDAO redisSessionDAO() {
        WebShiroRedisSessionDAO redisSessionDAO = new WebShiroRedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager.getRedisManager());
        redisSessionDAO.setValueSerializer(new ObjectSerializer());
        redisSessionDAO.setKeyPrefix(ShiroPropertiesConfig.SHIRO_SESSION);
        redisSessionDAO.setSessionInMemoryTimeout(Long.valueOf(ShiroPropertiesConfig.SHIRO_EXPIRES) * 1000L);
        return redisSessionDAO;
    }


    @Lazy
    @Bean
    public SessionManager sessionManager() {
        WebShiroSessionManager sessionManager = new WebShiroSessionManager();
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(new WebShiroSessionListener());
        sessionManager.setSessionListeners(listeners);
        sessionManager.setGlobalSessionTimeout(Long.valueOf(ShiroPropertiesConfig.SHIRO_EXPIRES) * 1000L);
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setDeleteInvalidSessions(true);//删除过期的session
        return sessionManager;
    }

    /**
     * cookie对象;
     *
     * @return
     */
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间7天 ,单位秒;-->
        simpleCookie.setMaxAge(604800);
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decodeBase64("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }


    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     *
     * @return
     */
    @Bean
    @Lazy
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");   //散列算法:MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512等。
        hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，默认1次， 设置两次相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    @Lazy
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
//    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return
     */
    @Bean
    public WebShiroKickoutSessionControlFilter kickoutSessionControlFilter() {
        WebShiroKickoutSessionControlFilter kickoutSessionControlFilter = new WebShiroKickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(cacheManager());
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(1);
        kickoutSessionControlFilter.setKickoutUrl("/getLoginParam");
        return kickoutSessionControlFilter;
    }
}
