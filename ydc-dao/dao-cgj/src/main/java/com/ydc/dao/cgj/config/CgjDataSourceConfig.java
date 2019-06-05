package com.ydc.dao.cgj.config;


import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
//@PropertySource("classpath:jdbc-native.properties")
@MapperScan(basePackages = "com.ydc.dao.cgj.*",
        sqlSessionTemplateRef = "cgjSqlSessionTemplate")
public class CgjDataSourceConfig {

    @Autowired
    Environment environment;

    private  HikariDataSource getHikariDataSource(Environment environment) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(environment.getProperty("jdbc-url"));
        dataSource.setUsername(environment.getProperty("username"));// 用户名
        dataSource.setPassword(environment.getProperty("password"));// 密码
        dataSource.setConnectionTestQuery("select 1");
        return dataSource;
    }

    @Primary
    @Bean(name = "cgjDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.cgj.hikari")
    public DataSource getDataSource() {
        return this.getHikariDataSource(environment);
    }


    @Primary
    @Bean(name = "cgjSqlSessionTemplate")
    public SqlSessionTemplate cgjJdbcTemplate(@Qualifier("cgjSqlSessionFactory") SqlSessionFactory cgjSqlSessionFactory) {
        return new SqlSessionTemplate(cgjSqlSessionFactory);
    }

    @Primary
    @Bean(name = "cgjSqlSessionFactory")
    public SqlSessionFactory cgjSqlSessionFactory(@Qualifier("cgjDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/cgj/*.xml"));
        bean.setTypeAliasesPackage("com.ydc.model.cgj");
        return bean.getObject();
    }

    @Primary
    @Bean(name = "cgjTransactionManager")
    public PlatformTransactionManager cgjTransactionManager(@Qualifier("cgjDataSource") DataSource prodDataSource) {
        return new DataSourceTransactionManager(prodDataSource);
    }


}
