package com.sky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpoint;

@Configuration
@ServerEndpoint("/ws/{sid}")
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter getServerEndPointExporter() {
        return new ServerEndpointExporter();
    }
}