// 채팅 사이드바 표시
$("#btn-text-chat").on("click", () => {
  $("#text-chat").removeClass("hide");
});

$(".btn-chat-exit").on("click", () => {
  $("#text-chat").addClass("hide");
  $("#voice-chat").addClass("hide");
});
