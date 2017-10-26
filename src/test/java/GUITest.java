import com.lab5.GUI;
import org.junit.Assert;
import org.junit.Test;

public class GUITest {
    @Test
    public void testGUI() {
        GUI test = new GUI();
        Assert.assertNotNull(test);
        test.run();
        Assert.assertNotNull(test.timerThread.toString());
    }
}
