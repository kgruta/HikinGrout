function validateLoginForm(event) {
    event.preventDefault();
    const form = event.target;
    const emailInput = form.querySelector("#username");
    const passwordInput = form.querySelector("#password");
    let isValid = true;

    clearErrors(form);

    // Validar email
    if (!isValidEmail(emailInput.value.trim())) {
        showError(emailInput, "Por favor, introduce un correo válido.");
        isValid = false;
    }

    // Validar contraseña
    if (passwordInput.value.trim().length < 6) {
        showError(passwordInput, "La contraseña debe tener al menos 6 caracteres.");
        isValid = false;
    }

    if (isValid) {
        form.submit();
    }
}

function validateEmailInput(inputElement) {
    const value = inputElement.value.trim();
    if (!isValidEmail(value) && value.length > 0) {
        showError(inputElement, "Formato de correo inválido.");
    } else {
        clearFieldError(inputElement);
    }
}

function validatePasswordInput(inputElement) {
    const value = inputElement.value.trim();
    if (value.length > 0 && value.length < 6) {
        showError(inputElement, "Al menos 6 caracteres.");
    } else {
        clearFieldError(inputElement);
    }
}

function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function showError(inputElement, message) {
    let errorDiv = inputElement.parentElement.querySelector(".form-errors");
    if (!errorDiv) {
        errorDiv = document.createElement("div");
        errorDiv.classList.add("form-errors");
        inputElement.parentElement.appendChild(errorDiv);
    }
    errorDiv.textContent = message;
    errorDiv.style.color = "red";
    errorDiv.style.fontSize = "0.8rem";
}

function clearFieldError(inputElement) {
    const errorDiv = inputElement.parentElement.querySelector(".form-errors");
    if (errorDiv) {
        errorDiv.textContent = "";
    }
}

function clearErrors(form) {
    const errors = form.querySelectorAll(".form-errors");
    errors.forEach(error => error.textContent = "");
}
