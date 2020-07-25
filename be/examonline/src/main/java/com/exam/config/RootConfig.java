package com.exam.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.JedisPoolConfig;

import javax.sql.DataSource;

@EnableCaching
@MapperScan(basePackages = "com.exam.dao")
@ComponentScan(basePackages={"com.exam.biz","com.exam.dao"})
@Configuration
@PropertySource({"classpath:jdbc.properties","classpath:redis.properties"})
@EnableTransactionManagement
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
    public TransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean
    public RedisConnectionFactory redisCF(JedisPoolConfig jedisPoolConfig) {
        //单机版jedis
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        //设置redis服务器的host和密码
        redisStandaloneConfiguration.setHostName(env.getProperty("redis.host"));
        redisStandaloneConfiguration.setPassword(RedisPassword.of(env.getProperty("redis.auth")));

        //构造客户端配置对象，配置连接池
        JedisClientConfiguration jedisClientConfiguration=
                JedisClientConfiguration.builder()
                        .usePooling()
                        .poolConfig(jedisPoolConfig)
                        .build();

        //单机配置 + 客户端配置 = jedis连接工厂
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    @Bean
    public JedisPoolConfig redisPC(){
        JedisPoolConfig pc=new JedisPoolConfig();
        pc.setMaxTotal(env.getProperty("redis.maxTotal",Integer.class));
        pc.setMaxIdle(env.getProperty("redis.maxIdle",Integer.class));
        pc.setMaxWaitMillis(env.getProperty("redis.maxWaitMillis",Integer.class));
        pc.setTestOnBorrow(env.getProperty("redis.testOnBorrow",Boolean.class));
        pc.setTestOnReturn(env.getProperty("redis.testOnReturn",Boolean.class));
        return pc;
    }

    @Bean
    public RedisTemplate<String,Object> redisT(RedisConnectionFactory connectionFactory){
        RedisTemplate<String,Object> t=new RedisTemplate<>();
        t.setConnectionFactory(connectionFactory);
        t.setKeySerializer(new StringRedisSerializer());
        t.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return t;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory){
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        //设置CacheManager的值序列化方式为json序列化
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(jsonSerializer);
        RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(pair);
        //设置默认超过期时间是30秒
//        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }
}
