package com.qhfax.api.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class ScheduledTasks {

    @Autowired
    WebSocketListener listener;

    int i=0;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        if ( i == 0 ) {
            listener.pushSystemStatusToWebSocket("ok");
            i = 1;
        } else {
            listener.pushSystemStatusToWebSocket("errore");
            i = 0;
        }
    }
}
