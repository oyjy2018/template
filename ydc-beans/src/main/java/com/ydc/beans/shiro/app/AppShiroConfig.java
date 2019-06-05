package com.ydc.beans.shiro.app;

import com.ydc.beans.config.ShiroPropertiesConfig;
import com.ydc.beans.shiro.RedisManagerBean;
import com.ydc.commom.constant.ShiroConstant;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.serializer.ObjectSerializer;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Appshiro管理
 *
 * @author
 * @create 2018-12-05 19:28
 **/
@Configuration
public class AppShiroConfig {

    @Resource
    private RedisManagerBean redisManager;

    @Bean
    @Lazy
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置拦截器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new UserFormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //配置权限map
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置需要拦截的链接
        filterChainDefinitionMap.put("/login/checkMemberPassword", "authc");
        //配置不需要拦截的链接
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/companyCustomer/login/**", "anon");
        filterChainDefinitionMap.put("/companyCustomer/sendValidateCode/**", "anon");
        filterChainDefinitionMap.put("/companyCustomer/doRegister/**", "anon");
        filterChainDefinitionMap.put("/companyCustomer/doLogout/**", "anon");
        filterChainDefinitionMap.put("/commodity/**", "anon");
        filterChainDefinitionMap.put("/actuator/**", "anon");
        filterChainDefinitionMap.put("/test/**", "anon");
        filterChainDefinitionMap.put("/vehicle/getVehicleConfig","anon");
        filterChainDefinitionMap.put("/vehicle/getVehicleModelBySeries","anon");
        filterChainDefinitionMap.put("/ydhcCommon/**","anon");
        filterChainDefinitionMap.put("/credit/**","anon");
        filterChainDefinitionMap.put("/vehicleUsed/**","anon");
//        filterChainDefinitionMap.put("/h5integral/**","anon");
//        filterChainDefinitionMap.put("/order/**","anon");
        filterChainDefinitionMap.put("/bCarServiceShop/**","anon");
        filterChainDefinitionMap.put("/oil/**","anon");
        filterChainDefinitionMap.put("/config/**","anon");
        filterChainDefinitionMap.put("/serviceFunc/**","anon");
        filterChainDefinitionMap.put("/common/**","anon");
        filterChainDefinitionMap.put("/carValuation/**","anon");
        filterChainDefinitionMap.put("/pay/getOpenIdCode","anon");
        filterChainDefinitionMap.put("/MP_verify_RfC2BkRKeEx3jLgi.txt","anon");
        filterChainDefinitionMap.put("/common/**", "anon");
//        filterChainDefinitionMap.put("/vehicle/**","anon");

        //filterChainDefinitionMap.put("/violation/**", "anon");
        filterChainDefinitionMap.put("/app-carzone/**", "anon");
        filterChainDefinitionMap.put("/loanApply/getApplyNum","anon");
        filterChainDefinitionMap.put("/common/getOverheadChargeDetail","anon");
        filterChainDefinitionMap.put("/main/**","anon");
        //测试接口屏蔽登陆
//        filterChainDefinitionMap.put("/**", "anon");
        //需要拦截的链接
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    @Lazy
    public SessionsSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setCacheManager(cacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    @Lazy
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }

    @Bean
    @Lazy
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        myShiroRealm.setAuthenticationCachingEnabled(true);
//        myShiroRealm.setAuthenticationCacheName("userName");
//        myShiroRealm.setCacheManager(cacheManager());
        return myShiroRealm;
    }

    @Bean
    @Lazy
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager.getRedisManager());
//        redisCacheManager.setKeyPrefix("shiro:loginCache:");
//        redisCacheManager.setExpire(60*60);
        redisCacheManager.setPrincipalIdFieldName("id");
        return redisCacheManager;
    }

    @Bean
    @Lazy
    public MyRedisSessionDAO redisSessionDAO() {
        MyRedisSessionDAO redisSessionDAO = new MyRedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager.getRedisManager());
        redisSessionDAO.setValueSerializer(new ObjectSerializer());
        redisSessionDAO.setKeyPrefix(ShiroPropertiesConfig.SHIRO_SESSION);
//        redisSessionDAO.setKeyPrefix("CGJ:APP_SHIRO:SESSION:");
        redisSessionDAO.setSessionInMemoryTimeout(Long.valueOf(ShiroPropertiesConfig.SHIRO_EXPIRES) * 1000L);
//        redisSessionDAO.setSessionInMemoryTimeout(30*24*60*60*1000L);
        return redisSessionDAO;
    }


    @Bean
    @Lazy
    public MySessionManager sessionManager() {
        MySessionManager sessionManager = new MySessionManager();
        sessionManager.setGlobalSessionTimeout(Long.valueOf(ShiroPropertiesConfig.SHIRO_EXPIRES) * 1000L);
//        sessionManager.setGlobalSessionTimeout(30*24*60*60*1000L);
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 授权所有配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 使注解起作用
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
     * Shiro生命周期处理器
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("DatabaseException", "databaseError");
        properties.setProperty("UnauthorizedException", "403");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        // simpleMappingExceptionResolver.setDefaultErrorView("error");
        simpleMappingExceptionResolver.setExceptionAttribute("ex");
        return simpleMappingExceptionResolver;
    }
}