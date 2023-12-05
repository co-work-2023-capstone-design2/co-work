$("#login-form").on("submit", (event) => {
  event.preventDefault();
  const user_email = $("#user-email").val();
  const user_pw = $("#user-pw").val();
  // to do: check user

  window.localStorage.setItem("user", user_email);
  location.href = "start_setting.html";
});
