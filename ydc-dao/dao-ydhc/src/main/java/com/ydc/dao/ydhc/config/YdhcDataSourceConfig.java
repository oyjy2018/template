package com.ydc.dao.ydhc.config;


import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.ydc.dao.ydhc",
        sqlSessionTemplateRef = "ydhcSqlSessionTemplate")
public class YdhcDataSourceConfig implements EnvironmentAware {

    @Autowired
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }

    private  HikariDataSource getHikariDataSource(Environment environment) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(environment.getProperty("jdbc-url"));
        dataSource.setUsername(environment.getProperty("username"));// 用户名
        dataSource.setPassword(environment.getProperty("password"));// 密码
        dataSource.setConnectionTestQuery("select 1");
        return dataSource;
    }

    @Bean(name = "ydhcDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ydhc.hikari")
    public DataSource getDataSource() {
        return this.getHikariDataSource(environment);
    }


    @Bean(name = "ydhcSqlSessionTemplate")
    public SqlSessionTemplate ydhcJdbcTemplate(@Qualifier("ydhcSqlSessionFactory") SqlSessionFactory ydhcSqlSessionFactory) {
        return new SqlSessionTemplate(ydhcSqlSessionFactory);
    }

    @Bean(name = "ydhcSqlSessionFactory")
    public SqlSessionFactory ydhcSqlSessionFactory(@Qualifier("ydhcDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/ydhc/*.xml"));
        bean.setTypeAliasesPackage("com.ydc.model.ydhc");
        return bean.getObject();
    }

    @Bean(name = "ydhcTransactionManager")
    public PlatformTransactionManager ydhcTransactionManager(@Qualifier("ydhcDataSource") DataSource prodDataSource) {
        return new DataSourceTransactionManager(prodDataSource);
    }


}
