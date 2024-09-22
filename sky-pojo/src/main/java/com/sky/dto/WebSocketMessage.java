package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
//WebSocket发送消息格式
public class WebSocketMessage {
    private Long id;
    private Boolean type;//0来单,1催单
    private String data;//你有新的订单了/用户催单了
    private LocalDateTime sendTime;//发送日期时间
}
