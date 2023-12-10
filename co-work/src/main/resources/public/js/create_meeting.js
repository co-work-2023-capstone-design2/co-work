let buildingRandom = 0;
let characterRandom = 0;
const buildings = [
  "img/buildings/building_1.png",
  "img/buildings/building_2.png",
  "img/buildings/building_3.png",
  "img/buildings/building_4.png",
  "img/buildings/building_5.png",
];
const characters = [
  "img/character/bear.png",
  "img/character/cat.png",
  "img/character/crocodile.png",
  "img/character/dog.png",
  "img/character/mouse.png",
  "img/character/penguin.png",
  "img/character/rabbit.png",
  "img/character/squirrel.png",
];
let inviteCode;
let isOwner;

// 새로 만들기
function setOwner() {
  const currentUser = localStorage.getItem("user");
  $("#meeting-owner").attr("placeholder", currentUser);
}

function setMeetingCode() {
  fetch(getMeetingCode, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  }).then((res) => res.json()).then((data) => {
    inviteCode = data.gathering_code;
    $("#meeting-code").attr("placeholder", inviteCode);
  });
}

$(".btn-new").on("click", () => {
  isOwner = 1; // 모임 생성자
  $(".container").addClass("hide");
  $(".container-new").removeClass("hide");

  setOwner();
  setMeetingCode();
});

// 새로 만들기 -다시 뽑기 버튼
$(".btn-retry").on("click", () => {
  buildingRandom = Math.floor(Math.random() * buildings.length);
  $("#img-building-setting").attr("src", buildings[buildingRandom]);
});

// 새로 만들기 -건물 층수 설정
let floors = 1;
$("#btn-minus").on("click", () => {
  floors--;
  if (floors > 0) {
    $("#floors").html(floors);
  }
  if (floors < 1) {
    alert("층수는 1층부터 가능합니다.");
  }
});

$("#btn-plus").on("click", () => {
  floors++;
  if (floors < 4) {
    $("#floors").html(floors);
  }
  if (floors > 3) {
    alert("층수는 3층까지 가능합니다.");
  }
});

// 새로 만들기 -다음 버튼(모임 생성)
function createMeeting() {
  const meetingInfo = {
    gathering_code: inviteCode,
    gathering_exterior: buildingRandom,
    gathering_name: $(".input-meeting-name").val(),
    gathering_owner: $("#meeting-owner").attr("placeholder"),
    gathering_floor: floors,
    gathering_explanation: $("#meeting-info").val(),
  };
  fetch(setMeeting, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(meetingInfo),
  }).then((res) => res.json()).then((data) => {
    console.log(data);
  })
}

$(".btn-new-next").on("click", () => {
  // 모임 생성 api 연결
  createMeeting();

  $(".container-new").addClass("hide");
  $(".img-container").removeClass("hide");
  $(".img-new-building").attr("src", buildings[buildingRandom]);
});

// drag-and-drop event + 건물 위치 조정
function setBuildingLocation(posX, posY) {
  const buildingPos = {
    gathering_code: inviteCode,
    gathering_coord_x: posX,
    gathering_coord_y: posY,
  };
  fetch(setLocation, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(buildingPos),
  }).then((res) => res.json()).then((data) => console.log(data));
}

document
  .querySelector(".img-new-building")
  .addEventListener("dragstart", (event) => {
    const startX = event.offsetX;
    const startY = event.offsetY;
    event.dataTransfer.setData("text/plain", `${startX},${startY}`);
  });

document.querySelector("body").addEventListener("dragover", (event) => {
  event.preventDefault();
  event.stopPropagation();
});

document.querySelector("body").addEventListener("drop", (event) => {
  event.preventDefault();
  event.stopPropagation();

  // put building
  const buildingElement = document.querySelector(".img-new-building");
  const clonedBuildingElement = buildingElement.cloneNode(); // copy
  // copy position setting
  const [startX, startY] = event.dataTransfer.getData("text/plain").split(",");
  const posX = event.offsetX - parseInt(startX);
  const posY = event.offsetY - parseInt(startY);
  clonedBuildingElement.style.left = posX + "px";
  clonedBuildingElement.style.top = posY + "px";
  clonedBuildingElement.style.position = "fixed";
  // input copy to drop container
  let newBuilding = document
    .querySelector("body")
    .appendChild(clonedBuildingElement);
  newBuilding.id = "1"; // todo: change to random id

  // send to server building's position
  setBuildingLocation(posX, posY);

  document.querySelector(".img-new-building").classList.add("hide");
  newBuilding.onclick = function () {
    $(".container-make-character").removeClass("hide");
  };
});

// 초대 코드로 들어가기 클릭
$(".btn-link").on("click", () => {
  isOwner = 2;
  $(".container").addClass("hide");
  $(".container-link").removeClass("hide");
});

// 초대 코드 입력 시
function getInviteMeetingInfo(code) {
  fetch(getInviteInfo + code, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  }).then((res) => res.json()).then((data) => {
      $("#invite-owner").attr("placeholder", data.gathering_owner);
      $("#invite-explain").attr("placeholder", data.gathering_explanation);
      $("#img-building-setting-invite").attr("src", buildings[parseInt(data.gathering_exterior)]);
  });
}
$("#form-meeting-name").on("submit", (e) => {
  e.preventDefault();
  const inputCode = $("#input-code").val();
  getInviteMeetingInfo(inputCode);
  inviteCode = inputCode;
});

// 초대 코드 폼 내 입장하기 버튼 클릭
$(".btn-invite-next").on("click", () => {
  $(".container-link").addClass("hide");
  $(".container-make-character").removeClass("hide");
  $("#character-name").attr("placeholder", localStorage.getItem("user"));
});

// 캐릭터 이름
$("#character-name").attr("placeholder", localStorage.getItem("user"));

// 캐릭터 생성 다시 뽑기 버튼
let counter = 3;
$("#retry-character").on("click", () => {
  if (counter > 0) {
    counter--;
    $("#retry-character").html(`&#128472; 다시 뽑기 (${counter}회)`);
    // change character img
    characterRandom = Math.floor(Math.random() * characters.length);
    $("#img-character-setting").attr("src", characters[characterRandom]);
  }
});

// 캐릭터 생성 입장하기 버튼
$("#character-setting").on("click", () => {
  window.localStorage.setItem("character", characterRandom);
  createCharacter();
  location.href = `move.html?${inviteCode}`;
});

// 내가 생성하는 경우 1, 초대원 2
function createCharacter() {
  const characterInfo = {
    gathering_code: inviteCode,
    user_email: localStorage.getItem("user"),
    user_image: characterRandom,
    member_role: isOwner,
    member_explanation: $("#character-introduce").val(),
  }

  fetch(setCharacter, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(characterInfo)
  }).then((res) => res.json()).then((data) => console.log(data));

  localStorage.setItem("user-name", $("#character-name").val());
}