package Main;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Connector {
    private SerialPort comPort;
    public StringBuilder dataBuffer = new StringBuilder();
    private Consumer<Map<String, Integer>> dataCallback;

    public Connector(String port) {
        comPort = SerialPort.getCommPort(port);
        comPort.setBaudRate(9600);

        if (comPort.openPort()) {
            System.out.println("Port is open.");
        } else if (comPort.isOpen()) {
            System.out.println("Port is already open.");
        } else {
            System.out.println("Failed to open port.");
            return;
        }

        comPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return;
                }
                byte[] newData = new byte[comPort.bytesAvailable()];
                comPort.readBytes(newData, newData.length);
                dataBuffer.append(new String(newData));

                Map<String, Integer> parsedData = readFromPort();
                if (dataCallback != null && !parsedData.isEmpty()) {
                    dataCallback.accept(parsedData);
                }
            }
        });
    }

    public void setDataCallback(Consumer<Map<String, Integer>> callback) {
        this.dataCallback = callback;
    }

    public Map<String, Integer> readFromPort() {
        int endOfMessageIndex = dataBuffer.indexOf("\n");
        if (endOfMessageIndex != -1) {
            String completeMessage = dataBuffer.substring(0, endOfMessageIndex).trim();
            dataBuffer.delete(0, endOfMessageIndex + 1);
            Map<String, Integer> parsedData = parseData(completeMessage);
//            System.out.println(parsedData);
            return parsedData;
        }
        return new HashMap<>();
    }

    public Map<String, Integer> parseData(String data) {
        if (data == null || data.trim().isEmpty()) {
            return new HashMap<>();
        }
        Map<String, Integer> dataMap = new HashMap<>();
        Pattern pattern = Pattern.compile(
            "\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*"
        );

        String[] lines = data.split("\n");
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                dataMap.put("crossroad_1_green", Integer.parseInt(matcher.group(1)));
                dataMap.put("crossroad_1_red", Integer.parseInt(matcher.group(2)));
                dataMap.put("crossroad_1_yellow", Integer.parseInt(matcher.group(3)));
                dataMap.put("crossroad_2_green", Integer.parseInt(matcher.group(4)));
                dataMap.put("crossroad_2_red", Integer.parseInt(matcher.group(5)));
                dataMap.put("crossroad_2_yellow", Integer.parseInt(matcher.group(6)));
            }
        }
//        System.out.println(dataMap);
        return dataMap;
    }

    public void closeConnection() {
        if (comPort != null && comPort.isOpen()) {
            comPort.closePort();
            System.out.println("Port is closed.");
        } else {
            System.out.println("Port is already closed or not initialized.");
        }
    }
}
