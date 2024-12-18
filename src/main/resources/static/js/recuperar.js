document.querySelector('form').addEventListener('submit', function (event) {
    const email = document.querySelector('#email').value.trim();
    if (!email) {
        alert('Por favor, introduce un correo electrónico válido.');
        event.preventDefault();
    }
});
