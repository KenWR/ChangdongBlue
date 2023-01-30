package cdb.websocket_old.notusenow;

// package cdb.websocket;
//
// import org.springframework.context.annotation.Configuration;
// import org.springframework.messaging.simp.config.MessageBrokerRegistry;
// import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
// import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
// import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
// @Configuration
// @EnableWebSocketMessageBroker // 메시지 브로커가 지원하는 WebSocket 메시지 처리를 활성화
// public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
// // 메모리 기반 메시지 브로커가
// // 접두사가 붙은 목적지에서 클라이언트에게 인사말 메시지를 다시 전달할 수 있도록 호출하는 것
//
// @Override
// public void configureMessageBroker(final MessageBrokerRegistry config) {
// config.enableSimpleBroker("/topic", "queue", "/sub");
// // 1:M , 1:1
// config.setApplicationDestinationPrefixes("/app", "/pub");
// // 해당 경로로 SimpleBroker를 등록. SimpleBroker는 해당하는 경로를 SUBSCRIBE하는 Client에게 메세지를 전달하는 간단한 작업을 수행
// }
//
// @Override
// public void registerStompEndpoints(final StompEndpointRegistry registry) {
// // js: var socket = new SockJS('/my-websocket');
// registry.addEndpoint("/my-websocket").setAllowedOrigins("/*").withSockJS();// chatbot.js 9
// // 보안 설정
// }
//
// }