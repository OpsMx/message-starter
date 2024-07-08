package com.opsmx.messageservice.consumer;

public interface MessageConsumer {

    public void handleEvent(String message);

}
