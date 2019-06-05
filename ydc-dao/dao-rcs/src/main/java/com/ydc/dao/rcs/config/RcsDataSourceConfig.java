package com.ydc.dao.rcs.config;


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
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.ydc.dao.rcs",
        sqlSessionTemplateRef = "rcsSqlSessionTemplate")
public class RcsDataSourceConfig {
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

    @Bean(name = "rcsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.rcs.hikari")
    public DataSource getDataSourceSlave() {
        return this.getHikariDataSource(environment);
    }


    @Bean(name = "rcsSqlSessionTemplate")
    public SqlSessionTemplate rcsJdbcTemplate(@Qualifier("rcsSqlSessionFactory") SqlSessionFactory rcsSqlSessionFactory) {
        return new SqlSessionTemplate(rcsSqlSessionFactory);
    }


    @Bean(name = "rcsSqlSessionFactory")
    public SqlSessionFactory rcsSqlSessionFactory(@Qualifier("rcsDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/rcs/*.xml"));
        bean.setTypeAliasesPackage("com.ydc.model.rcs");
        return bean.getObject();
    }


    @Bean(name = "rcsTransactionManager")
    public PlatformTransactionManager rcsTransactionManager(@Qualifier("rcsDataSource") DataSource sitDataSource) {
        return new DataSourceTransactionManager(sitDataSource);
    }


}
