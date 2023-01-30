package cdb.chatbot;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatBotIntentionRepository extends JpaRepository<ChatBotIntentionEntity, Long> {

	Optional<ChatBotIntentionEntity> findByNameAndUpper(String token, ChatBotIntentionEntity upper);

}
