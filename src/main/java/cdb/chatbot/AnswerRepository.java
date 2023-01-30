package cdb.chatbot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

	void save(ChatBotIntentionEntity build);

}
