<?php
session_start();
?>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Daftar Game dan Software - SAZNET</title>
    <link rel="stylesheet" href="style/aplikasi.css" />
</head>

<body>
    <header>
      <nav class="container">
        <h1><a href="./index.php">SAZNET.</a></h1>
        <ul>
          <li><a href="./tarif.php" class="menu">DAFTAR TARIF</a></li>
          <li><a href="./aplikasi.php" class="menu">GAME & SOFTWARE</a></li>
          <?php if (isset($_SESSION['username'])): ?>
            <!-- sudah login -->
             <li><a href="pesan.php" class="menu">PESAN</a></li>
            <li><a href="./backend/logout.php" class="login">LOGOUT</a></li>
          <?php else: ?>
            <!-- belum login -->
            <li><a href="login.php" class="login">LOGIN</a></li>
          <?php endif; ?>
        </ul>
      </nav>
    </header>

    <main>
        <h1 class="section-title">DAFTAR GAME DAN SOFTWARE</h1>

        <section class="games-grid container">
            <article class="game-card" tabindex="0"
                aria-label="Apex Legends game card with genre and platform information">
                <img src="image/gambar/apez.jpg"
                    alt="gambar" />
                <div class="game-info">
                    <h3>APEX LEGENDS</h3>
                    <p>Genre: <span>Battle Royale</span></p>
                    <p>Platform: <span>Steam</span></p>
                </div>
            </article>

            <article class="game-card" tabindex="0" aria-label="Valorant game card with genre and platform information">
                <img src="image/gambar/EGS_VALORANT_RiotGames_S1_2560x1440-7d279548324d3a3cbef40e1dc7e84994.webp"
                    alt="gambar" />
                <div class="game-info">
                    <h3>VALORANT</h3>
                    <p>Genre: <span>FPS Tactical</span></p>
                    <p>Platform: <span>Riot Games</span></p>
                </div>
            </article>

            <article class="game-card" tabindex="0" aria-label="Dota 2 game card with genre and platform information">
                <img src="image/gambar/dota 2.jpg"
                    alt="gambar" />
                <div class="game-info">
                    <h3>DOTA 2</h3>
                    <p>Genre: <span>MOBA</span></p>
                    <p>Platform: <span>Steam</span></p>
                </div>
            </article>

            <article class="game-card" tabindex="0"
                aria-label="Counter Strike 2 game card with genre and platform information">
                <img src="image/gambar/counter strike 2.jpg"
                    alt="gambar" />
                <div class="game-info">
                    <h3>COUNTER STRIKE 2</h3>
                    <p>Genre: <span>FPS</span></p>
                    <p>Platform: <span>Steam</span></p>
                </div>
            </article>

            <article class="game-card" tabindex="0" aria-label="PUBG game card with genre and platform information">
                <img src="image/gambar/PUBG.jpg" alt="gambar" />
                <div class="game-info">
                    <h3>PUBG</h3>
                    <p>Genre: <span>Battle Royale</span></p>
                    <p>Platform: <span>Steam</span></p>
                </div>
            </article>

            <article class="game-card" tabindex="0" aria-label="Roblox game card with genre and platform information">
                <img src="image/gambar/ROBLOX.webp"
                    alt="gambar" />
                <div class="game-info">
                    <h3>ROBLOX</h3>
                    <p>Genre: <span>Sandbox</span></p>
                    <p>Platform: <span>Steam</span></p>
                </div>
            </article>

            <article class="game-card" tabindex="0"
                aria-label="League of Legends game card with genre and platform information">
                <img src="image/gambar/league of legends.jpg"
                    alt="gambar" />
                <div class="game-info">
                    <h3>LEAGUE OF LEGENDS</h3>
                    <p>Genre: <span>MOBA</span></p>
                    <p>Platform: <span>Riot Games</span></p>
                </div>
            </article>

            <article class="game-card" tabindex="0" aria-label="GTA V game card with genre and platform information">
                <img src="image/gambar/GTA V.jpg" alt="gambar" />
                <div class="game-info">
                    <h3>GTA V</h3>
                    <p>Genre: <span>Open World</span></p>
                    <p>Platform: <span>Steam</span></p>
                </div>
            </article>

            <article class="game-card" tabindex="0"
                aria-label="Point Blank game card with genre and platform information">
                <img src="image/gambar/pointblank.jpg" alt="gambar" />
                <div class="game-info">
                    <h3>POINT BLANK</h3>
                    <p>Genre: <span>FPS</span></p>
                    <p>Platform: <span>Zepetto</span></p>
                </div>
            </article>
        </section>

        <h1 class="section-title soft-title">DAFTAR SOFTWARE</h1>
        <section class="software-section container" aria-label="Daftar software populer">
            <div class="software-list">
                <div class="software-item" tabindex="0" aria-label="Discord software icon and name">
                    <img src="image/gambar/DISCORD.png"
                        alt="gambar" />
                    <span>DISCORD</span>
                </div>
                <div class="software-item" tabindex="0" aria-label="OBS Studio software icon and name">
                    <img src="image/gambar/OBS STUDIO.png"
                        alt="gambar" />
                    <span>OBS STUDIO</span>
                </div>
                <div class="software-item" tabindex="0" aria-label="Office 2024 software icon and name">
                    <img src="image/gambar/OFFICE 2024.webp"
                        alt="gambar" />
                    <span>OFFICE 2024</span>
                </div>
                <div class="software-item" tabindex="0" aria-label="Photoshop software icon and name">
                    <img src="image/gambar/Ps.png" alt="gambar" />
                    <span>PHOTOSHOP</span>
                </div>
                <div class="software-item" tabindex="0" aria-label="Corel Draw software icon and name">
                    <img src="image/gambar/COREL DRAW.webp"
                        alt="gambar" />
                    <span>COREL DRAW</span>
                </div>
                <div class="software-item" tabindex="0" aria-label="CapCut software icon and name">
                    <img src="image/gambar/CAPCUT.png"
                        alt="gambar" />
                    <span>CAPCUT</span>
                </div>
            </div>
        </section>
    </main>

    <footer>
        <div class="container">
          <p>Copyrights © 2025 - SAZNET.</p>
        </div>
      </footer>
</body>

</html>