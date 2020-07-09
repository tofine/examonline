package connect;

import com.exam.config.RootConfig;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.lang.management.PlatformLoggingMXBean;
import java.sql.*;

public class ConnectTest {

    UserMapper mapper;

    AnnotationConfigApplicationContext ioc;

    @Before
    public void a(){
        ioc=new AnnotationConfigApplicationContext(RootConfig.class);
        mapper=ioc.getBean(UserMapper.class);

    }

    @Test
    public void connect(){
        System.out.println(mapper.getCount());
        Assert.assertEquals(1,mapper.getCount());
    }

    //测试数据源
    @Test
    public void test2() throws SQLException {

        DataSource dataSource = (DataSource) ioc.getBean("dataSource");

        System.out.println(dataSource);
        Connection connection=dataSource.getConnection();
        ResultSet resultSet= connection.createStatement().executeQuery("select * from user_info");
        while(resultSet.next()){
            System.out.println(resultSet.getString("user"));
        }

    }


}
