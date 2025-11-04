const API_KEY = "aa188895826b49c99a0d073e4bbb814e";
const platforms = "187";
const platforms2 = "4,187,1,18,186,7,3,21";
const url1 = `https://api.rawg.io/api/games?key=${API_KEY}&page_size=5&platforms=${platforms}`;
const url2 = `https://api.rawg.io/api/games?key=${API_KEY}&ordering=-metacritic&page_size=6&platforms=${platforms2}`;
const url3 = `https://api.rawg.io/api/platforms?key=${API_KEY}&page_size=8`;

fetch(url1)
  .then((response) => response.json())
  .then((data) => {
    const hgContainer = document.getElementById("hg-container");

    data.results.forEach((game) => {
      const card = document.createElement("div");
      const gameGenre = game.genres.slice(0, 1).map((genre) => genre.name);
      card.classList = "hg-card col pb-3";

      card.innerHTML = `
           <a href="./pages/description.html?id=${game.id}">
            <img src="${game.background_image}" alt=${game.name} class="w-100 object-fit-cover rounded-3" alt="" style="aspect-ratio:4/5"/>
            <div class="mx-2">
              <p class="sm-text mt-3 fs-7 mb-0">${gameGenre}</p>
              <p class="fw-semibold mt-1">${game.name}</p>
            </div>
          </a>
        `;
      hgContainer.appendChild(card);
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

//   METACRITIC
fetch(url2)
  .then((response) => response.json())
  .then((data) => {
    const mcContainer = document.getElementById("mc-container");

    data.results.forEach((game) => {
      const card = document.createElement("div");
      let shortDesc = game.description;
      card.classList = "hg-card col pb-4";

      fetch(`https://api.rawg.io/api/games/${game.id}?key=${API_KEY}`)
        .then((res) => res.json())
        .then((detail) => {
          card.innerHTML = `
        <a href="./pages/description.html?id=${game.id}">
          <img src="${game.background_image}" alt="${game.name}" class="w-100 object-fit-cover rounded-3" style="aspect-ratio:3/2"/>
          <div class="mx-2">
            <p class="fw-semibold mt-3 mb-0">${game.name}</p>
            <p class="sm-text mt-1 fs-7">${detail.description_raw.slice(0, 150)}...</p>
          </div>
        </a>
      `;
        });
      mcContainer.appendChild(card);
    });
  })
  .catch((error) => {
    console.error("Terjadi kesalahan: ", error);
  });

//   PLATFORM
fetch(url3)
  .then((response) => response.json())
  .then((data) => {
    const pgContainer = document.getElementById("pg-container");

    data.results.forEach((platform) => {
      const card = document.createElement("div");
      card.classList = "hg-card col pb-3";

      card.innerHTML = `
           <a href="./pages/platform.html?idpl=${platform.id}">
            <img src="img/platforms/${platform.slug}.png" alt=${platform.name} class="w-100 object-fit-cover rounded-3" alt="" style="aspect-ratio:1/1"/>
            <div class="mx-2">
            <p class="fw-semibold mt-3 mb-0">${platform.name}
          </a>
        `;
      pgContainer.appendChild(card);
    });
    document.getElementById("loader").classList.add("d-none");
    document.getElementById("content").classList.remove("d-none");
  })
  .catch((error) => {
    console.error("Terjadi kesalahan: ", error);
  });
