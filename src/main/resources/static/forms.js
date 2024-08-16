const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});

document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault();
    const email = document.getElementById("email2").value;
    const password = document.getElementById("password2").value;

    fetch("/login", {
            method: "POST",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                email: email,
                password: password,
            })
        })
        .then(response => {
            if (!response.ok) {
                return response.json().then(errors => {
                    const errorMessagesDiv = document.getElementById("errorMessages2");
                    errorMessagesDiv.innerHTML = "";
                    errors.forEach(error => {
                        const errorItem = document.createElement("div");
                        errorItem.textContent = error;
                        errorMessagesDiv.appendChild(errorItem);
                    });
                });
            } else {
                window.location.href = "/home";
            }
        })
        .catch(error => console.error('Error:', error));
});

document.getElementById("registrationForm").addEventListener("submit", function(event) {
    event.preventDefault();
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const repeat = document.getElementById("repeat").value;

    fetch("/register", {
            method: "POST",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                name: name,
                email: email,
                password: password,
                repeat: repeat
            })
        })
        .then(response => {
            if (!response.ok) {
                return response.json().then(errors => {
                    const errorMessagesDiv = document.getElementById("errorMessages");
                    errorMessagesDiv.innerHTML = "";
                    errors.forEach(error => {
                        const errorItem = document.createElement("div");
                        errorItem.textContent = error;
                        errorMessagesDiv.appendChild(errorItem);
                    });
                });
            } else {
                container.classList.remove("active");;
            }
        })
        .catch(error => console.error('Error:', error));
});

let toggle = document.getElementById("btnToggle");
let toggle2 = document.getElementById("btnToggle2");

function togglePassword(inputs, icon) {
    inputs.forEach(input => {
        if (input.type === "password") {
            input.type = "text";
        } else {
            input.type = "password";
        }
    });

    if (icon.classList.contains("fa-eye-slash")) {
        icon.classList.remove("fa-eye-slash");
    } else {
        icon.classList.add("fa-eye-slash");
    }
}

toggle.addEventListener("click", function() {
    togglePassword([document.getElementById("password2")], document.getElementById("eyeIcon"));
}, false);

toggle2.addEventListener("click", function() {
    togglePassword([document.getElementById("password"), document.getElementById("repeat")], document.getElementById("eyeIcon2"));
}, false);
