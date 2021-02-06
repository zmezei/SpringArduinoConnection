package hu.zoltanmezei.springarduinoconnection.controller;

import hu.zoltanmezei.springarduinoconnection.connection.ArduinoConnection;
import hu.zoltanmezei.springarduinoconnection.controlclasses.ArduinoRequest;
import hu.zoltanmezei.springarduinoconnection.controlclasses.ArduinoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArduinoController {

    private final ArduinoConnection arduinoConnection;

    @Autowired
    public ArduinoController(ArduinoConnection arduinoConnection) {
        this.arduinoConnection = arduinoConnection;
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello Zoltan!";
    }

    @RequestMapping(value = "/arduino/execute", method = RequestMethod.POST, consumes = "application/json")
    public ArduinoResponse sendMessageToArduino(@RequestBody ArduinoRequest request) {
        boolean sent = arduinoConnection.sendMessageToArduino(request.getMessageToSend());
        if (!sent) {
            throw new RuntimeException("Command not sent :(");
        }

        return new ArduinoResponse(request.getId(), request.getMessageToSend());
    }

}
