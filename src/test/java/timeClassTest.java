import com.lab5.timeClass;
import org.junit.Assert;
import org.junit.Test;

public class timeClassTest {
    @Test
    public void testTimeClass(){
        timeClass testTime = new timeClass("Europe/Moscow");
        Assert.assertEquals("Europe/Moscow", testTime.getZone());
        Assert.assertTrue(testTime.getZone() instanceof String);
    }
}
