package com.qhfax.api.controller;

import com.qhfax.api.pojo.dto.SocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ApiController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @MessageMapping("/send")
    @SendTo("/topic/send")
    public SocketMessage send(SocketMessage message) throws Exception {
        System.out.println(message.date + ":" + message.message);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.date = df.format(new Date());
        return message;
    }

    @Scheduled(fixedRate = 3000)
    @SendTo("/topic/callback")
    public Object callback() throws Exception {
        // 发现消息
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messagingTemplate.convertAndSend("/topic/callback", df.format(new Date()));
        return "callback";
    }
}
