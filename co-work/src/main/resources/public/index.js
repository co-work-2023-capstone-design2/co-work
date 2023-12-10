window.localStorage.removeItem("user");
// 현재 사용자 확인
$("#btn-start").on("click", (e) => {
    e.preventDefault();
    const currentUser = localStorage.getItem("user");
    if (currentUser !== null) {
        location.href = "start_setting.html";
    }
    if (currentUser === null) {
        location.href = "login.html";
    }
})