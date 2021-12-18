import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PollutionDataTest extends Assertions{
    @Test
    public void getDataTest(){
        PollutionData data = new PollutionData("River Name", 1,2,3,4,5.2,6.3);
        assertEquals("River Name",data.riverName());
        assertEquals(1,data.month());
        assertEquals(2,data.day());
        assertEquals(3,data.arsic());
        assertEquals(4,data.lead());
        assertEquals(5.2,data.fertilizer());
        assertEquals(6.3,data.pesticide());
    }
}
