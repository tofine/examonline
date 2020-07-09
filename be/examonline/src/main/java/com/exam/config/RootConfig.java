package com.exam.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@MapperScan(basePackages = "connect")
@Configuration
@PropertySource("classpath:jdbc.properties")
public class RootConfig {

    private Environment env;

    @Bean
    public DataSource dataSource(){
        PooledDataSource ds=new PooledDataSource();
        ds.setPassword(env.getProperty("jdbc.password"));
        ds.setUsername(env.getProperty("jdbc.username"));
        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setDriver(env.getProperty("jdbc.driverClass"));
        return ds;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    public TransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }
}
