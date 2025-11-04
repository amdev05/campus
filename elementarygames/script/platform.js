const API_KEY = "aa188895826b49c99a0d073e4bbb814e";
const urlParams = new URLSearchParams(window.location.search);
const plid = urlParams.get("idpl");

const url1 = `https://api.rawg.io/api/platforms/${plid}?key=${API_KEY}`;

fetch(url1)
  .then((res) => res.json())
  .then((data) => {
    document.getElementById("pl-title").textContent = "Platform: " + data.name;
  })
  .catch((error) => {
    console.error("Terjadi kesalahan: ", error);
  });
// &page_size=12
const url2 = `https://api.rawg.io/api/games?key=${API_KEY}&platforms=${plid}`;

//   METACRITIC
fetch(url2)
  .then((response) => response.json())
  .then((data) => {
    const platformFilter = document.getElementById("platform-filter");

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
      platformFilter.appendChild(card);
    });

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
