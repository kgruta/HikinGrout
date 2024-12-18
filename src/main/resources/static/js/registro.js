
function validateForm(event) {
    event.preventDefault();
    const form = event.target;
    let isValid = true;

    const cookiesAccepted = document.getElementById('accept-cookies').checked;
    if (!cookiesAccepted) {
        isValid = false;
        alert("Debe aceptar la política de cookies para continuar.");
    }

    if (isValid) {
        form.submit();
    }
}

function toggleAccordion() {
    const content = document.getElementById('cookies-info');
    content.classList.toggle('active');
}
