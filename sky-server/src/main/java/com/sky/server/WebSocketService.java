package com.sky.server;

import com.alibaba.fastjson.JSON;
import com.sky.config.WebSocketConfig;
import com.sky.controller.user.UserController;
import com.sky.dto.WebSocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static io.lettuce.core.pubsub.PubSubOutput.Type.message;
//只有webSocket类时加载WebSocketService
@Component
@Slf4j
@ConditionalOnClass(WebSocketConfig.class)
@ServerEndpoint("/ws/{sid}")
public class WebSocketService {

    private static ConcurrentHashMap<String,Session> userSessions = new ConcurrentHashMap<>();
    private final UserController user;

    public WebSocketService(UserController user) {
        this.user = user;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        userSessions.put(sid,session);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("id") String sid) {
        WebSocketMessage webSocketMessage = (WebSocketMessage) JSON.parse(message);
        Long id = webSocketMessage.getId();
        Boolean type = webSocketMessage.getType();
        String msgData = webSocketMessage.getData();
        LocalDateTime sendTime = webSocketMessage.getSendTime();
        log.info("{} {}{}",sendTime,id,msgData);
    }

    @OnClose
    public void onClose(@PathParam("id") String id) {
        userSessions.remove(id);
        log.info("{}关闭了webSocket连接",id);
    }

    public void sendToAllClient(Long id,Boolean type,String data) {
        WebSocketMessage webSocketMessage = new WebSocketMessage(id,type,data,LocalDateTime.now());
        String message = JSON.toJSONString(webSocketMessage);
        Collection<Session> sessions = userSessions.values();
        for (Session session:sessions) {
            session.getAsyncRemote().sendText(message);
        }
    }
}
