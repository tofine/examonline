package connect;

import com.exam.config.RootConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.clients.jedis.Jedis;

public class JedisConnectTest {

    private AnnotationConfigApplicationContext ioc;
    @Before
    public void init(){
        ioc=new AnnotationConfigApplicationContext(RootConfig.class);
    }

    @Test
    public void connect(){
        Jedis jedis=new Jedis("39.98.113.19");
        jedis.auth("123456");
        jedis.set("test","success");
        System.out.println(jedis.get("test"));
    }

    @Test
    public void redisTemplateTest(){
        RedisTemplate template=ioc.getBean(RedisTemplate.class);
        ValueOperations valueOperations = template.opsForValue();
//        valueOperations.set("first","hello word");
//        System.out.println(valueOperations.get("first"));
        System.out.println(template.getExpire("first"));
    }

}
