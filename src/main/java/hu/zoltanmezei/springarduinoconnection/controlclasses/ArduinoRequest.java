package hu.zoltanmezei.springarduinoconnection.controlclasses;

public class ArduinoRequest {
    private int id;
    private String messageToSend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageToSend() {
        return messageToSend;
    }

    public void setMessageToSend(String messageToSend) {
        this.messageToSend = messageToSend;
    }
}
