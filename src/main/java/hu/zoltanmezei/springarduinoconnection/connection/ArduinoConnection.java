package hu.zoltanmezei.springarduinoconnection.connection;

import gnu.io.NRSerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;

@Component
public class ArduinoConnection {

    private static final int MESSAGE_SEPARATOR = 255;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${arduinoPortName}")
    private String portName;

    @Value("${arduinoBaudRate}")
    private int baudRate;

    private NRSerialPort serial;


    @PostConstruct
    public void connect() {
        log.info("ArduinoConnection PostConstruct callback: connecting to Arduino...");

        serial = new NRSerialPort(portName, baudRate);
//        serial.connect();

        if (serial.isConnected()) {
            log.info("Arduino connection opened!");
        }
    }

    @PreDestroy
    public void disconnect() {
        log.info("ArduinoConnection PreDestroy callback: disconnecting from Arduino...");

        if (serial != null && serial.isConnected()) {
//            serial.disconnect();

            if (!serial.isConnected()) {
                log.info("Arduino connection closed!");
            }
        }
    }

    public boolean sendMessageToArduino(String messageToSend){
        try {
            // Actual values sent to Arduino will be in proper unsigned byte range (0..255)
            byte[] originalTextBytes = messageToSend.getBytes(StandardCharsets.UTF_8);
            byte[] messageBytes = new byte[originalTextBytes.length + 1];
            System.arraycopy(originalTextBytes, 0, messageBytes, 0, originalTextBytes.length);

            messageBytes[messageBytes.length - 1] = (byte) MESSAGE_SEPARATOR;

//            DataOutputStream stream = new DataOutputStream(serial.getOutputStream());
//            stream.write(messageBytes);

            log.info("Arduino control messageBytes sent (messageToSend={})!", messageToSend);
            return  true;
        } catch (Exception ex) {
            log.error("Error while sending control message: ", ex);
            return false;
        }
    }

}
