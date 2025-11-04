const API_KEY = "aa188895826b49c99a0d073e4bbb814e";

function searchGame() {
  const query = document.getElementById("search-input").value.trim();
  const resultContainer = document.getElementById("result-container");
  resultContainer.innerHTML = "";

  if (!query) {
    alert("Please input name of game");
    return;
  }

  fetch(`https://api.rawg.io/api/games?key=${API_KEY}&search=${encodeURIComponent(query)}`)
    .then((res) => res.json())
    .then((data) => {
      if (data.results.length === 0) {
        resultContainer.innerHTML = "<p>Game not found</p>";
      }

      data.results.forEach((game) => {
        const card = document.createElement("div");
        const gameGenre = game.genres.slice(0, 1).map((genre) => genre.name);
        card.classList = "hg-card col pb-3";

        card.innerHTML = `
            <a href="description.html?id=${game.id}" class="mb-5">
                <div class="card" class="rounded-3">
                        <img src="${game.background_image}" class="ratio-img rounded-3" alt="${game.name}">
                </div>
                <div class="d-flex flex-column justify-content-center py-3 px-1">
                    <div class="genre-game text-start  sm-text">${gameGenre}</div>
                    <div class="game-title text-start">${game.name}</div>
                </div>
            </a>
        `;
        resultContainer.appendChild(card);
      });
    })
    .catch((error) => {
      console.error("Terjadi kesalahan: ", error);
      resultContainer.innerHTML = "<p>Gagal memuat hasil</p>";
    });
}

const searchForm = document.getElementById("search-form");
searchForm.addEventListener("submit", function (e) {
  e.preventDefault();
  searchGame();
});

const loaderWrapper = document.getElementById("loader-wrapper");
loaderWrapper.classList.add("fade-out");
setTimeout(() => {
  loaderWrapper.classList.add("d-none");
  document.getElementById("content").classList.remove("d-none");
}, 500);
