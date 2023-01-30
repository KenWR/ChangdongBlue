package cdb.websocket_old.notusenow;
/*
 * package cdb.websocket.notusenow; import java.util.ArrayList; import java.util.Collections; import
 * java.util.LinkedHashMap; import java.util.List; import java.util.Map; import javax.annotation.PostConstruct; import
 * org.springframework.stereotype.Repository;
 * @Repository public class ChatRoomRepository { private Map<String, ChatRoomDTO> chatRoomInfo; // DTO에 저장된 채팅방 정보를
 * key&value로 관리 public ChatRoomDTO createChatRoomDTO(final String name) { final var room = ChatRoomDTO.create(name);
 * chatRoomInfo.put(room.getRoomId(), room);// 고유방번호와 새로 만든 방 이름을 넣어줌 return room; } // 위에 해시셋에 순서대로 담긴대로 뽑아오기 public
 * List<ChatRoomDTO> findAllChatRooms() { final List<ChatRoomDTO> result = new ArrayList<>(chatRoomInfo.values());
 * Collections.reverse(result);// 역순으로 뽑아야 가장 처음임 return result; } // 방번호로 찾기 public ChatRoomDTO findRoomById(final
 * String roomId) { return chatRoomInfo.get(roomId); } // 초기화
 * @PostConstruct private void init() { chatRoomInfo = new LinkedHashMap<>(); // LinkedHashMap을 쓰는 이유는 채팅방이 만들어진 순서를 알기
 * 위해서다 } }
 */