$("#login-form").on("submit", (event) => {
  event.preventDefault();
  const input_email = $("#user-email").val();
  const input_pw = $("#user-pw").val();
  const user = {
    user_email: input_email,
    user_pw: input_pw,
  };

  fetch(login, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  }).then((res) => res.json()).then((data) => {
    if (data.state === 200) {
      console.log("성공");
      window.localStorage.setItem("user", data.user_email);
      location.href = "start_setting.html";
    } else {
      console.log("실패");
    }
  })
});
