const register = "http://localhost:5500/api/auth/signup";

$("#register-form").on("submit", (event) => {
  event.preventDefault();
  const input_name = $("#user-name").val();
  const input_email = $("#user-email").val();
  const input_pw = $("#user-pw").val();
  const user = {
    user_name: input_name,
    user_email: input_email,
    user_pw: input_pw,
  };
  // to do: send user data to server
  fetch(register, {
    method: "POST",
    body: JSON.stringify(user),
  })
    .then((res) => res.json())
    .then((json) => console.log(json));

  // location.href = "login.html";
});
