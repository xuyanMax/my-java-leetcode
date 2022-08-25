package unit;

import org.junit.Assert;
import org.junit.Test;

public class UnitTest {

    @Test
    public void template() {
        Assert.assertEquals(10, 10);
        Integer a = new Integer(10);
        Integer b = a;
        Assert.assertSame(a, b);
    }
}
