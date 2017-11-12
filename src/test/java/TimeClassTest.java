import com.lab5.TimeClass;
import org.junit.Assert;
import org.junit.Test;

public class TimeClassTest {
    @Test
    public void TimeClassTest(){
        TimeClass testTime = new TimeClass("Europe/Moscow");
        Assert.assertEquals("Europe/Moscow", testTime.getZone());
        Assert.assertTrue(testTime.getZone() instanceof String);
    }
}
