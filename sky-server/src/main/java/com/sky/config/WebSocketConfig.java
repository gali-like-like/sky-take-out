package com.sky.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpoint;
//当spring.profiles.active = dev时才加载这个bean
@Configuration
@ConditionalOnProperty(name="spring.profiles.active",havingValue = "dev")
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter getServerEndPointExporter() {
        return new ServerEndpointExporter();
    }
}