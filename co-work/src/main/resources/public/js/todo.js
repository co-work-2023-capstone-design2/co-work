// default: 모임 투두
$(".meeting-check").addClass("selected");
$(".meeting-check").css({
  "border-bottom": "1px solid #6063dc",
});

// 투두리스트 로컬로 기능 구현 해놓기
let isMeetingTodo = true;
const commonForm = document.querySelector("#todo-common-form");
const personalForm = document.querySelector("#todo-personal-form");
const meetingTodoList = document.querySelector("#meeting-todo-list");
const personalTodoList = document.querySelector("#personal-todo-list");

const TODOS_KEY = "todos";

let todos = [];

function saveToDos() {
  localStorage.setItem(TODOS_KEY, JSON.stringify(todos));
}

function paintToDo(newTodo) {
  const todoElement = document.createElement("div");
  todoElement.id = newTodo.id;
  const checkImg = document.createElement("img");
  checkImg.src = "img/check-none.png";
  const input = document.createElement("input");
  input.placeholder = newTodo.text;
  input.readOnly = true;

  todoElement.appendChild(checkImg);
  todoElement.appendChild(input);
  if (isMeetingTodo) {
    meetingTodoList.appendChild(todoElement);
  }
  if (!isMeetingTodo) {
    personalTodoList.appendChild(todoElement);
  }
}

function handleToDoSubmit(event) {
  event.preventDefault();
  let todoInput;
  if (isMeetingTodo) {
    todoInput = document.querySelector("#todo-common-form input");
  }
  if (!isMeetingTodo) {
    todoInput = document.querySelector("#todo-personal-form input");
  }
  const newTodo = todoInput.value;
  todoInput.value = "";
  const newTodoObj = {
    text: newTodo,
    id: Date.now(),
  };
  todos.push(newTodoObj);
  paintToDo(newTodoObj);
  saveToDos();
}

commonForm.addEventListener("submit", handleToDoSubmit);
personalForm.addEventListener("submit", handleToDoSubmit);

// 투두 섹션 선택
$(".meeting-check").on("click", () => {
  isMeetingTodo = true;
  $(".meeting-check").addClass("selected");
  $(".meeting-check").css({
    "border-bottom": "1px solid #6063dc",
  });
  $(".personal-check").removeClass("selected");
  $(".personal-check").css({
    "border-bottom": "1px solid white",
  });

  $(".todo-list-common").removeClass("hide");
  $(".todo-list-personal").addClass("hide");
});

$(".personal-check").on("click", () => {
  isMeetingTodo = false;
  $(".personal-check").addClass("selected");
  $(".personal-check").css({
    "border-bottom": "1px solid #6063dc",
  });
  $(".meeting-check").removeClass("selected");
  $(".meeting-check").css({
    "border-bottom": "1px solid white",
  });

  $(".todo-list-common").addClass("hide");
  $(".todo-list-personal").removeClass("hide");
});
