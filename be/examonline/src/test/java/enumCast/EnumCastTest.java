package enumCast;

import com.exam.commons.Sex;
import org.junit.Assert;
import org.junit.Test;

public class EnumCastTest {

    @Test
    public void test(){
        Assert.assertEquals(Sex.男,Sex.class.getEnumConstants()[1]);
    }

    @Test
    public void test1(){
        System.out.println("abcABC".indexOf('d'));
    }

}

