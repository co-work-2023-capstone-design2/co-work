// 투두리스트 로컬로 기능 구현 해놓기
const todoForm = document.querySelector(".todo-form");
const todoInput = document.querySelector(".todo-form input");
const todoList = document.querySelector("#todo-list");

const TODOS_KEY = "todos";

let todos = [];

function saveToDos() {
  localStorage.setItem(TODOS_KEY, JSON.stringify(toDos));
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
  todoList.appendChild(todoElement);
}

function handleToDoSubmit(event) {
  event.preventDefault();
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

todoForm.addEventListener("submit", handleToDoSubmit);

// 투두 섹션 선택
$(".meeting-check").on("click", () => {
  $(".meeting-check").addClass("selected");
  $(".meeting-check").css({
    "border-bottom": "1px solid #6063dc",
  });
  $(".personal-check").removeClass("selected");
  $(".personal-check").css({
    "border-bottom": "1px solid white",
  });
});

$(".personal-check").on("click", () => {
  $(".personal-check").addClass("selected");
  $(".personal-check").css({
    "border-bottom": "1px solid #6063dc",
  });
  $(".meeting-check").removeClass("selected");
  $(".meeting-check").css({
    "border-bottom": "1px solid white",
  });
});
