// move.js로 들어왔을 때 세션
const socket = new WebSocket(socketUrl);

// 연결 이벤트 핸들러
socket.addEventListener('open', (event) => {
  console.log('WebSocket 연결이 열렸습니다.', event);
});

// 메시지 수신 이벤트 핸들러
socket.addEventListener('message', (event) => {
  console.log('서버로부터 메시지 수신:', event.data);
});

// 오류 이벤트 핸들러
socket.addEventListener('error', (event) => {
  console.error('웹소켓 오류:', event);
});

// 연결 닫힘 이벤트 핸들러
socket.addEventListener('close', (event) => {
  console.log('WebSocket 연결이 닫혔습니다.', event);
});

// 채팅 사이드바 표시
$("#btn-text-chat").on("click", () => {
  $("#text-chat").removeClass("hide");
});

$(".btn-chat-exit").on("click", () => {
  $("#text-chat").addClass("hide");
  $("#voice-chat").addClass("hide");
});
