package cdb.websocket_old.notusenow;
/*
 * package cdb.websocket.notusenow; import org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import org.springframework.web.bind.annotation.RequestMapping;
 * import org.springframework.web.bind.annotation.RequestParam; import org.springframework.web.servlet.ModelAndView;
 * import org.springframework.web.servlet.mvc.support.RedirectAttributes; import
 * cdb.websocket.domain.dto.ChatRoomRepository; import lombok.RequiredArgsConstructor; import
 * lombok.extern.log4j.Log4j2;
 * @RequestMapping(value = "/chat")
 * @Log4j2
 * @Controller
 * @RequiredArgsConstructor public class ChatRoomController { private final ChatRoomRepository chatRoomRepo; // 채팅방
 * create
 * @PostMapping(value = "/room") public String create(@RequestParam final String name, final RedirectAttributes
 * redirectAttributes) { // RedirectAttributes : 기존의 Redirect는 GET방식이라 보안에취약하나 // 새로 확장을 하면서 RedirectAttributes로 URL을
 * 노출시키지 않는 방식으로 바꾼다. log.info("# Create Chat Room, name : " + name); redirectAttributes.addFlashAttribute("name",
 * chatRoomRepo.createChatRoomDTO(name)); // 위의 주석처럼 URL에 노출시키지 않도록 addAttribute 대신 사용을 했고 값이 2개이상이 // 넘어가게되면 소멸하는 특징이
 * 있으므로 MAP collection으로 한번에 값을 전달해야한다. return "redirect:/chat/rooms"; } // 채팅방
 * @GetMapping("/room") public void getRoom(final String roomId, final Model model) {
 * log.info("# get Chat Room, roomID : " + roomId); model.addAttribute("room", chatRoomRepo.findRoomById(roomId)); } //
 * 채팅방 리스트
 * @GetMapping(value = { "/rooms", "/messengers" }) public ModelAndView rooms() { log.info("# ALL Chat Rooms"); final
 * var mv = new ModelAndView("chat/rooms"); mv.addObject("list", chatRoomRepo.findAllChatRooms()); return mv; } }
 */