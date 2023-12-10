const meetingCode = location.href.split("?")[1];
const meetingInfo = {
  gathering_code: meetingCode
}

// move.js로 들어왔을 때 초기화
function createChat() {
  fetch(createChatRoom, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(meetingInfo)
  }).then((res) => res.json()).then((data) => console.log(data));
}
createChat();

// 초기 연결
socket.addEventListener('open', (event) => {
  console.log('WebSocket 연결이 열렸습니다.', event);
  initChatting();
});

function initChatting() {
  const message = {
    type: 'ENTER',
    gathering_code: meetingCode,
    content: '',// ENTER type일 떄, 여긴 비워서 보내주세요
    sender: localStorage.getItem("user")
  }
  socket.send(JSON.stringify(message));
}

// 채팅 사이드바 표시
$("#btn-text-chat").on("click", () => {
  $("#text-chat").removeClass("hide");
  createChat();
});

$(".btn-chat-exit").on("click", () => {
  $("#text-chat").addClass("hide");
  $("#voice-chat").addClass("hide");
});

// 메시지 보내기
function sendMessage() {
  const message = {
    type: "TALK",
    gathering_code: meetingCode,
    content: $("#input-message").val(),
    sender: localStorage.getItem("user")
  }
  socket.send(JSON.stringify(message));
}

$(".chatting-form").on("submit", (e) => {
  e.preventDefault();
  sendMessage();
  $("#input-message").val("");
})

// 메시지 수신
socket.addEventListener('message', (event) => {
  const message = JSON.parse(event.data);
  printMessage(message);
});

function printMessage(message) {
  const sender = message.sender;
  const content = message.content;
  const sendTime = sendTimeFormat(message.sendTime);
  const chatLog = $("#chatting-log");
  chatLog.append(
      `<div id="${sendTime}" class="chat-unit">
         <b style="font-size: 20px;">${sender}</b>
         <span>${sendTime}</span>
         <div style="font-size: 20px;">${content}</div>
       </div>`
  );
  chatLog.scrollTop(chatLog[0].scrollHeight);
}

function sendTimeFormat(sendTime) {
  const date = sendTime.split("T")[0];
  const time = sendTime.split("T")[1];
  const hour = time.split(":")[0];
  const min = time.split(":")[1];
  const sec = (time.split(":")[2]).split(".")[0];
  return `${date} ${hour}:${min}:${sec}`;
}

// 오류 이벤트 핸들러
socket.addEventListener('error', (event) => {
  console.error('웹소켓 오류:', event);
});

// 연결 닫힘 이벤트 핸들러
socket.addEventListener('close', (event) => {
  console.log('WebSocket 연결이 닫혔습니다.', event);
});