<?php
session_start();
$jumlahMember = 121;
$jumlahGuest = 219;
$jumlahPc = 30;
?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>HOME | SAZNET.</title>
    <link rel="stylesheet" href="style/index.css" />
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
      <section id="hero">
        <h1>SAZNET.</h1>
        <a href="#statistik">LIHAT SELENGKAPNYA</a>
      </section>

      <section id="statistik" class="container">
        <div class="stat-text">
          <h2>STATISTIK</h2>
          <p>
            Lebih dari 100+ orang telah terdaftar menjadi bagian dari SAZNET. Dengan mendaftar menjadi Member SAZNET, kamu akan mendapatkan banyak keuntungan dan bonus setiap harinya, bahkan kamu juga
            bisa menukarkan poin membermu dengan hadiah yang menarik.
          </p>
          <ul>
            <li>
              <h4><?=$jumlahMember ?></h4>
              <p>Member</p>
            </li>
            <li>
              <h4><?=$jumlahGuest ?></h4>
              <p>Guest</p>
            </li>
            <li>
              <h4><?=$jumlahPc ?></h4>
              <p>PC</p>
            </li>
          </ul>
          <button>SELENGKAPNYA</button>
        </div>
        <div class="stat-img">
          <img src="./image/thumbnail.png" alt="" />
        </div>
      </section>

      <section id="spesifikasi" class="container">
        <h2>SPESIFIKASI PC</h2>
        <div class="spek">
          <div>
            <h3>STANDAR</h3>
            <ul>
              <li>CPU: Intel Core i5-12400F</li>
              <li>GPU: NVIDIA RTX 3060</li>
              <li>RAM: 16GB DDR4 3200MHz</li>
              <li>MONITOR: 24" FHD 120Hz</li>
              <li>KEYBOARD: Digital Alliance MS104</li>
              <li>MOUSE: Logitech G102</li>
              <li>EARPHOPNE: Fantech HG11</li>
            </ul>
          </div>
          <div>
            <h3>VIP</h3>
            <ul>
              <li>CPU: Intel Core i7-13700KF</li>
              <li>GPU: NVIDIA RTX 4070 Super</li>
              <li>RAM: 32GB DDR5 5600MHz</li>
              <li>MONITOR: 27" FHD 240Hz</li>
              <li>KEYBOARD: Razer Cynosa V2</li>
              <li>MOUSE: Razer Viper Mini</li>
              <li>EARPHOPNE: Razer BlackShark V2</li>
            </ul>
          </div>
        </div>
      </section>

      <section id="informasi" class="container">
        <div class="informasi-map">
          <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d5499.929092976777!2d107.61337477957736!3d-6.886400603906732!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x2e68e6f8aa08188b%3A0x632d24e6061e8903!2sUniversitas%20Komputer%20Indonesia%20(UNIKOM)!5e0!3m2!1sid!2sid!4v1754102787092!5m2!1sid!2sid"
            style="border: 0"
            allowfullscreen=""
            loading="lazy"
            referrerpolicy="no-referrer-when-downgrade"
          ></iframe>
        </div>
        <div class="informasi-text">
          <h2>INFORMASI</h2>
          <h3>
            BUKA SABTU-KAMIS <br />
            24 JAM
          </h3>
          <ul>
            <li>
              <img src="./image/icon/Instagram.png" alt="">
              <span>Instagram: saznet.id</span>
            </li>
            <li>
              <img src="./image/icon/WhatsApp.png" alt="">
              <span>Whatsapp: 085678356128</span>
            </li>
            <li>
              <img src="./image/icon/Email.png" alt="">
              <span>Email: saznetid@gmail.com</span>
            </li>
            <li>
              <img src="./image/icon/maps.png" alt="">
              <span>Alamat: Jl. Dipati Ukur No.112-116</span>
            </li>
          </ul>
        </div>
      </section>

      <section id="testimoni" class="container">
        <h2>TESTIMONI</h2>
        <div class="testimoni">
          <div>
            <p class="testi-text">"Buka 24 jam tuh mantep sih, cocok buat anak begadang macem gue 😅"</p>
            <p class="testi-info"><span>Rilsuk</span><span>&#11088;&#11088;&#11088;&#11088;&#11088;</span></p>
          </div>
          <div>
            <p class="testi-text">"Tempatnya cozy, suasananya tenang, cocok buat ngepush rank atau kerjaan."</p>
            <p class="testi-info"><span>Paza</span><span>&#11088;&#11088;&#11088;&#11088;&#11088;</span></p>
          </div>
          <div>
            <p class="testi-text">"Nugas sambil nyantai di sini mah juara, tempatnya adem"</p>
            <p class="testi-info"><span>Dea</span><span>&#11088;&#11088;&#11088;&#11088;&#11088;</span></p>
          </div>
          <div>
            <p class="testi-text">"Warnetnya enak banget buat ngegame, jaringan ngebut, gak pake ngelag."</p>
            <p class="testi-info"><span>Aril</span><span>&#11088;&#11088;&#11088;&#11088;&#11088;</span></p>
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
