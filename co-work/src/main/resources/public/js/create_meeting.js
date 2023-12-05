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

// 새로 만들기
$(".btn-new").on("click", () => {
  $(".container").addClass("hide");
  $(".container-new").removeClass("hide");
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

// 새로 만들기 -다음 버튼
$(".btn-new-next").on("click", () => {
  $(".container-new").addClass("hide");
  $(".img-container").removeClass("hide");
  $(".img-new-building").attr("src", buildings[buildingRandom]);
});

// drag-and-drop event
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
  let newBuilbing = document
    .querySelector("body")
    .appendChild(clonedBuildingElement);
  newBuilbing.id = "1"; // todo: change to random id
  document.querySelector(".img-new-building").classList.add("hide");

  newBuilbing.onclick = function () {
    $(".container-make-character").removeClass("hide");
  };
  // todo
  // send to server building's position
});

// 초대 코드로 들어가기 클릭
$(".btn-link").on("click", () => {
  $(".container").addClass("hide");
  $(".container-link").removeClass("hide");
});

// 초대 코드 폼 내 입장하기 버튼 클릭
$(".btn-invite-next").on("click", () => {
  $(".container-link").addClass("hide");
  $(".container-make-character").removeClass("hide");
});

// 캐릭터 생성 다시 뽑기 버튼
let counter = 3;
$("#retry-character").on("click", () => {
  if (counter > 0) {
    counter--;
    $("#retry-character").html(`&#128472; 다시 뽑기 (${counter}회)`);
    // change character img
    characterRandom = Math.floor(Math.random() * characters.length);
    $("#img-character-setting").attr("src", characters[characterRandom]);
    // todo: save character number
  }
});

// 캐릭터 생성 입장하기 버튼
$("#character-setting").on("click", () => {
  window.localStorage.setItem("character", characterRandom);
  location.href = "move.html";
});
