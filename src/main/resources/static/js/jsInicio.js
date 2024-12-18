
    let currentSlide = 0;
    const carousel = document.getElementById('carouselRutas');
    const carouselItems = document.querySelectorAll('.carousel-item');
    const title = document.getElementById('carousel-title');
    const location = document.getElementById('carousel-location');
    const duration = document.getElementById('carousel-duration');
    const difficulty = document.getElementById('carousel-difficulty');
    const description = document.getElementById('carousel-description');

    function updateCarouselInfo() {
    const activeItem = carouselItems[currentSlide];
    title.textContent = activeItem.dataset.title;
    location.textContent = activeItem.dataset.location;
    duration.textContent = activeItem.dataset.duration;
    difficulty.textContent = activeItem.dataset.difficulty;
    description.textContent = activeItem.dataset.description;
}

    function moveCarousel(direction) {
    currentSlide = (currentSlide + direction + carouselItems.length) % carouselItems.length;
    const offset = -currentSlide * 100;
    carousel.style.transform = `translateX(${offset}%)`;
    updateCarouselInfo();
}

    // Ajustar el carrusel al tamaño de la ventana al cargar la página
    window.addEventListener('resize', () => {
    const offset = -currentSlide * carouselItems[0].clientWidth;
    carousel.style.transform = `translateX(${offset}px)`;
});

    updateCarouselInfo();

