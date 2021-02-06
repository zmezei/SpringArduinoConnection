package hu.zoltanmezei.springarduinoconnection.controlclasses;

public class ArduinoResponse {
    private int id;
    private String messageToSend;

    public ArduinoResponse(int id, String messageToSend) {
        setId(id);
        setMessageToSend(messageToSend);
    }

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
