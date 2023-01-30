package cdb.chatbot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Intention")
@Entity
public class ChatBotIntentionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;// 번호

	private String name;

	@ManyToOne // 여러개의 다양한 질문에 정해진 하나의 대답이 나와야한다.
	private AnswerEntity answer;

	@ManyToOne
	private ChatBotIntentionEntity upper;

}
