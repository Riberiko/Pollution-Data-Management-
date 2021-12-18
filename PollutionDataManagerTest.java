
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;

public class PollutionDataManagerTest extends Assertions {
    PollutionDataManager data = new PollutionDataManager(new File("./src/RiverPollutionData.txt"),new File("./src/RiverPollutionDataCounts.txt"));;
    @Test
    public void ConstructorAndGetters(){
        assertEquals(-1,data.findRiver("NOt FOUND"));
        assertEquals(0,data.findRiver("Angelina River"));
        assertEquals(35,data.getRiverCount());
        assertEquals(26,data.getPollutionReadingCount(0,1));
        assertEquals(new PollutionData("Angelina River",1,1,4,0,0.2,0.383), data.getPollutionData(0,1,0));
    }
}
