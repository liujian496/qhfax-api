package com.qhfax.api.common;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;

    @Autowired
    private MyService myService;

    @OnOpen
    public void onOpen(Session session) {
//        session.setMaxIdleTimeout(0);
        this.session = session;
        webSocketSet.add(this);
        logger.info("open id=" + session.getId() + "," + myService);
    }

    @PostConstruct
    public void init() {
        logger.info("***," + myService);
    }

    @OnClose
    public void onClose(Session session) {
        logger.info("close id=" + session.getId());
        webSocketSet.remove(this);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("message from the client:" + message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
//        logger.error("websocket error:" + throwable.getMessage(), throwable);
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentAmount() {
        for (WebSocketServer item : webSocketSet) {
            logger.info("===," + myService);
            JSONObject json = new JSONObject();
            json.put("kkk","123456");
            json.put("ppp","123147");
            item.sendMsg(json.toJSONString());
        }
    }

    public void sendMsg(String msg) {
        try {
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            logger.error("send msg error:" + e.getMessage(), e);
        }
    }
}
