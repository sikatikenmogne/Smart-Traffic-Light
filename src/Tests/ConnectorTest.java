package Tests;

import Main.Connector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;

public class ConnectorTest {
    private Connector connector;

    @BeforeEach
    public void setUp() {
        connector = new Connector("COM3");
    }

    @Test
    public void readFromPort_shouldReturnParsedData_whenCompleteMessageReceived() {
        String inputData = "1200, 2000, 3000, 2000, 1000, 3000\n";
        connector.dataBuffer.append(inputData);
        Map<String, Integer> result = connector.readFromPort();
        assertEquals(1, result.size());
        assertEquals(1200, result.get("crossroad_1_green"));
        assertEquals(2000, result.get("crossroad_1_red"));
        assertEquals(3000, result.get("crossroad_1_yellow"));
        assertEquals(2000, result.get("crossroad_2_green"));
        assertEquals(1000, result.get("crossroad_2_red"));
        assertEquals(3000, result.get("crossroad_2_yellow"));
    }

    @Test
    public void readFromPort_shouldReturnEmptyList_whenNoCompleteMessageReceived() {
        String inputData = "1200, 2000, 3000, 2000, 1000";
        connector.dataBuffer.append(inputData);
        Map<String, Integer> result = connector.readFromPort();
        assertTrue(result.isEmpty());
    }

    @Test
    public void parseData_shouldReturnEmptyList_whenDataIsEmpty() {
        Map<String, Integer> result = connector.parseData("");
        assertTrue(result.isEmpty());
    }

    @Test
    public void parseData_shouldReturnParsedData_whenDataIsValid() {
        String inputData = "1200, 2000, 3000, 2000, 1000, 3000\n1400, 2555, 3000, 2000, 1000, 3000";
        Map<String, Integer> result = connector.parseData(inputData);
        assertEquals(2, result.size());
        assertEquals(1200, result.get("crossroad_1_green"));
        assertEquals(2555, result.get("crossroad_1_red"));
    }

    @Test
    public void parseData_shouldIgnoreInvalidLines() {
        String inputData = "1200, 2000, 3000, 2000, 1000, 3000\ninvalid,data,line";
        Map<String, Integer> result = connector.parseData(inputData);
        assertEquals(1, result.size());
        assertEquals(1200, result.get("crossroad_1_green"));
    }

    @Test
    public void parseData_shouldHandleMultipleValidLines() {
        String inputData = "1200, 2000, 3000, 2000, 1000, 3000\n1400, 2555, 3000, 2000, 1000, 3000\n1600, 2700, 3100, 2100, 1100, 3200";
        Map<String, Integer> result = connector.parseData(inputData);
        assertEquals(3, result.size());
        assertEquals(1200, result.get("crossroad_1_green"));
        assertEquals(2555, result.get("crossroad_1_red"));
        assertEquals(3200, result.get("crossroad_2_yellow"));
    }

    @Test
    public void parseData_shouldHandleLeadingAndTrailingWhitespace() {
        String inputData = "  1200, 2000, 3000, 2000, 1000, 3000  \n  1400, 2555, 3000, 2000, 1000, 3000  ";
        Map<String, Integer> result = connector.parseData(inputData);
        assertEquals(2, result.size());
        assertEquals(1200, result.get("crossroad_1_green"));
        assertEquals(2555, result.get("crossroad_1_red"));
    }
}