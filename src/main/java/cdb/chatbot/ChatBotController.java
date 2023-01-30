package cdb.chatbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChatBotController {

	private final KomoranService komoranService;

	@PostMapping("/firstHello")
	public String welcomeMessage(final String message, final Model model) throws Exception {
		var answer = "안녕하세요";

		if (!"안녕".equals(message)) answer = komoranService.nlpAnalyze(message);

		final var today = LocalDateTime.now();
		final var dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		final var timeFormatter = DateTimeFormatter.ofPattern("a H:mm");

		model.addAttribute("today", today.format(dateFormatter));
		model.addAttribute("time", today.format(timeFormatter));
		model.addAttribute("answer", answer);
		Thread.sleep(800); // 지연시간

		return "layout/fragments/chatting";

	}

}