package Main;

import com.fazecast.jSerialComm.SerialPort;

import java.io.InputStream;
import java.util.Scanner;

public class SerialConnection {

    private SerialPort serialPort;
    private InputStream inputStream;

    public SerialConnection(String portDescriptor) {
        serialPort = SerialPort.getCommPort(portDescriptor);
        serialPort.setComPortParameters(9600, 8, 1, 0);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
    }

    public boolean openConnection() {
        if (serialPort.openPort()) {
            inputStream = serialPort.getInputStream();
            return true;
        }
        return false;
    }

    public void closeConnection() {
        if (serialPort != null) {
            serialPort.closePort();
        }
    }

    public String readData() {
        Scanner scanner = new Scanner(inputStream);
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }
}