API_KEY = "aa188895826b49c99a0d073e4bbb814e";
const urlParams = new URLSearchParams(window.location.search);
const gameId = urlParams.get("id");
//${game.}
fetch(`https://api.rawg.io/api/games/${gameId}?key=${API_KEY}`)
  .then((res) => res.json())
  .then((game) => {
    const gameGenre = game.genres && game.genres.length > 0 ? game.genres.slice(0, 3).map((genre) => genre.name) : ["-"];
    const gameFeatures = game.tags && game.tags.length > 0 ? game.tags.slice(0, 3).map((tag) => tag.name) : ["-"];
    const gamePlatforms = game.platforms.slice(0, 3).map((p) => p.platform.name);

    const container = document.getElementById("desc-contain");

    container.innerHTML = `
        <div id="game-title" class="game-title text-center mt-6">${game.name}</div>

      <div class="game-image mb-4">
        <img src="${game.background_image}" alt="Fortnite Game Image" />
      </div>

      <div class="row">
        <!-- Info Left -->
        <div class="col-md-4 mb-3">
          <div class="card p-3 mb-3">
            <p><b>Name:</b> ${game.name}</p>
            <p><b>Rating:</b> ${game.rating}</p>
            <p><b>Released:</b> ${game.released}</p>
            <p><b>Genres:</b> ${gameGenre}</p>
            <p><b>Features:</b> ${gameFeatures}</p>
            <p><b>Platforms:</b> ${gamePlatforms}</p>
          </div>
          <!-- <button class="btn btn-primary w-100 ">Bookmark</button> -->
        </div>

        <!-- Info Right -->
        <div class="col-md-8">
          <div class="card p-4">
            <p class="fs-5 fw-semibold">Description</p>
            <p>${game.description}</p>
          </div>
        </div>
      </div>
    `;

    const loaderWrapper = document.getElementById("loader-wrapper");
    loaderWrapper.classList.add("fade-out");
    setTimeout(() => {
      loaderWrapper.classList.add("d-none");
      document.getElementById("content").classList.remove("d-none");
    }, 500);
  })
  .catch((error) => {
    console.error("Terjadi kesalahan: ", error);
  });
