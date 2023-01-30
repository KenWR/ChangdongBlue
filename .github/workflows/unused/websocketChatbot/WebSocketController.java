package cdb.websocket_old.notusenow;

// package cdb.websocket;
//
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
//
// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.SendTo;
// import org.springframework.messaging.simp.SimpMessagingTemplate;
// import org.springframework.stereotype.Controller;
//
// import cdb.websocket.chatbot.ChatbotMessage;
// import cdb.websocket.chatbot.ClientMessage;
// import cdb.websocket.chatbot.KomoranService;
// import cdb.websocket.dto.ChatMessageDTO;
// import lombok.RequiredArgsConstructor;
//
// @Controller
// @RequiredArgsConstructor
// public class WebSocketController {
//
//	// @formatter:off
//  private final SimpMessagingTemplate template;
//  private final KomoranService service;
// // @formatter:on
//
// // 메시지 처리
// // client가 send할 수 있는 경로
// // 누군가가 입장 했을때
// // "/pub/chat/enter"
// @MessageMapping(value = "/chat/enter")
// public void enter(final ChatMessageDTO message) {
// message.setMessage(message.getWriter() + "님이 채팅방에 들어왔습니다.");
// template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);// 언제든변경해야됨
// }
//
// @MessageMapping("/hello")
// @SendTo("/topic/greetings") // stompClient.subscribe
// public ChatbotMessage greeting(final ClientMessage message) throws Exception {
// Thread.sleep(800); // 구동 딜레이
// final var today = LocalDateTime.now();
// final var formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
// final var formattedDay = today.format(formatter); // 년월일
// final var formattedTime = today.format(DateTimeFormatter.ofPattern("a h:mm"));// 오전or오후 시분
// return new ChatbotMessage("<div>" + formattedDay + "</div>" + "<div>" + "<p>새로운 소식을 확인하세요!</p>" + "</div>"
// + "<div class='menu-item'><span onclick='menuClicked(this)'>공지사항</span></div>"
// + "<div class='menu-item'><span onclick='menuClicked(this)'>메신져</span></div>"
// + "<div class='menu-item'><span onclick='menuClicked(this)'>메일함</span></div>"
// + "<div class='menu-item'><span onclick='menuClicked(this)'>쪽지함</span></div>" + "<div class='time'>"
// + formattedTime + "</div>");
// }
//
// @MessageMapping(value = "/chat/message")
// public void message(final ChatMessageDTO message) {
// template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);// 언제든변경해야됨
// }
//
// @MessageMapping("/message")
// @SendTo("/topic/message") // stompClient.subscribe
// public ChatbotMessage message(final ClientMessage message) throws Exception {
// Thread.sleep(800); // simulated delay
// final var today = LocalDateTime.now();
// // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
// // String formattedDay = today.format(formatter); // 년월일
// final var formattedTime = today.format(DateTimeFormatter.ofPattern("a h:mm"));// 오전or오후 시분
// service.nlpAnalyze(message.getContent());
// final var responseText = message.getContent() + "에 대한 정보에요!";
// return new ChatbotMessage(
// "<div>" + "<p>" + responseText + "</p>" + "</div>" + "<div class='time'>" + formattedTime + "</div>");
// }
//
// }
