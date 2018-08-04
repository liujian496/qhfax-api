package com.qhfax.api.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WebSocketListener {

    @Autowired
    private SimpMessagingTemplate webSocket;

    @Async
    public void pushSystemStatusToWebSocket (String newStatus){
        System.out.println("inviooooooooooooooooooooooooooo");
        webSocket.convertAndSend("/topic/messages", newStatus);
    }
}
