$("#btn-voice-chat").on("click", () => {
  $("#voice-chat").removeClass("hide");
});

// 음성 채팅 내 프로필
const characterN = window.localStorage.getItem("character");
$(".profile-me").append(`<img src="${characters[characterN]}" >`);
