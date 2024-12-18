// Validar en tiempo real el nombre/apellido
function validateNameInput(inputElement) {
    const value = inputElement.value.trim();
    let errorDiv = inputElement.parentElement.querySelector(".form-errors");
    if (!errorDiv) {
        errorDiv = document.createElement("div");
        errorDiv.classList.add("form-errors");
        inputElement.parentElement.appendChild(errorDiv);
    }

    // Primero verificar caracteres
    if (!isValidName(value) && value.length > 0) {
        errorDiv.textContent = "Solo se permiten letras y espacios.";
        errorDiv.style.color = "red";
        errorDiv.style.fontSize = "0.8rem";
    } else if (value.length > 0 && value.length < 2) {
        // Si cumple caracteres, pero es muy corto
        errorDiv.textContent = "Debe tener al menos 2 caracteres.";
        errorDiv.style.color = "red";
        errorDiv.style.fontSize = "0.8rem";
    } else {
        // Sin errores
        errorDiv.textContent = "";
    }
}

// Validar el formulario al enviar
function validateForm(event) {
    event.preventDefault(); // Evita el envío del formulario por defecto
    const form = event.target;
    let isValid = true;

    // Limpiar errores previos
    const errors = document.querySelectorAll(".form-errors");
    errors.forEach(error => (error.textContent = ""));

    // Validar Nombre
    const nombre = form.nombre.value.trim();
    if (!isValidName(nombre)) {
        showError(form.nombre, "El nombre solo puede contener letras y espacios.");
        isValid = false;
    } else if (nombre.length < 2) {
        showError(form.nombre, "El nombre debe tener al menos 2 caracteres.");
        isValid = false;
    }

    // Validar Apellido
    const apellido = form.apellido.value.trim();
    if (!isValidName(apellido)) {
        showError(form.apellido, "El apellido solo puede contener letras y espacios.");
        isValid = false;
    } else if (apellido.length < 2) {
        showError(form.apellido, "El apellido debe tener al menos 2 caracteres.");
        isValid = false;
    }

    // Validar Correo Electrónico
    const email = form.email.value.trim();
    if (!isValidEmail(email)) {
        showError(form.email, "Debes ingresar un correo electrónico válido.");
        isValid = false;
    }

    // Validar Contraseña
    const contrasena = form.contrasena.value.trim();
    if (!isValidPassword(contrasena)) {
        showError(form.contrasena, "La contraseña debe tener al menos 8 caracteres, una letra y un número.");
        isValid = false;
    }

    // Validar Año de Nacimiento
    const fechaNacimiento = form.fechaNacimiento.value;
    if (!isValidYear(fechaNacimiento)) {
        showError(form.fechaNacimiento, "Debes ingresar un año válido entre 1700 y 2023.");
        isValid = false;
    }

    // Validar Género
    const genero = form.genero.value;
    if (!genero) {
        showError(form.genero, "Debes seleccionar un género.");
        isValid = false;
    }

    // Validar Aceptación de Cookies
    const cookiesAccepted = document.getElementById("accept-cookies").checked;
    const cookiesErrorDiv = document.querySelector("#cookies-info .form-errors");
    if (cookiesErrorDiv) {
        cookiesErrorDiv.textContent = "";
    }
    if (!cookiesAccepted) {
        if (cookiesErrorDiv) {
            cookiesErrorDiv.textContent = "Debes aceptar la política de cookies para continuar.";
            cookiesErrorDiv.style.color = "red";
            cookiesErrorDiv.style.fontSize = "0.8rem";
        }
        isValid = false;
    }

    // Si todo es válido, se envía el formulario
    if (isValid) {
        form.submit();
    }
}

// Mostrar errores en el formulario
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

// Validar formato del correo electrónico
function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

// Validar la fortaleza de la contraseña
function isValidPassword(password) {
    // Al menos 8 caracteres, 1 letra y 1 número
    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    return passwordRegex.test(password);
}

// Validar año de nacimiento
function isValidYear(year) {
    const currentYear = new Date().getFullYear();
    return year >= 1700 && year <= currentYear;
}

// Validar que el nombre/apellido solo contenga letras y espacios
function isValidName(name) {
    // Permite letras (incluyendo acentos, Ñ/ñ), mayúsculas, minúsculas y espacios
    const nameRegex = /^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]*$/;
    return nameRegex.test(name);
}

// Función para mostrar/ocultar el contenido de las cookies
function toggleAccordion() {
    const content = document.getElementById("cookies-info");
    content.classList.toggle("hidden");
}
