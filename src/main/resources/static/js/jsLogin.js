function validateLoginForm(event) {
    event.preventDefault();
    const email = document.getElementById('username');
    const password = document.getElementById('password');
    let isValid = true;

    // Clear previous errors
    document.querySelectorAll('.error-message').forEach(el => el.textContent = '');

    // Email Validation
    if (!email.value.includes('@') || !email.value.includes('.')) {
        document.getElementById('email-error').textContent = 'Por favor, introduce un correo válido.';
        isValid = false;
    }

    // Password Validation
    if (password.value.length < 6) {
        document.getElementById('password-error').textContent = 'La contraseña debe tener al menos 6 caracteres.';
        isValid = false;
    }

    // Submit if valid
    if (isValid) {
        event.target.submit();
    }
}