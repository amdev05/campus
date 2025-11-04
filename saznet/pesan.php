<?php
session_start();
?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="style/pesan.css" />
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

    <main id="main" class="container">
      <h2>PESAN PAKET WARNET</h2>
      <form action="">
        <select name="jenis_paket" id="jenis_paket">
          <option value="" disabled selected hidden>PILIH JENIS PAKET</option>
          <option value="SPRS">PAKET REGULER STANDAR</option>
          <option value="SPRV">PAKET REGULER VIP</option>
          <option value="SPMS">PAKET MALAM STANDAR</option>
          <option value="SPMV">PAKET MALAM VIP</option>
        </select>
        <select name="paket" id="paket">
          <option value="" disabled selected hidden>PILIH PAKET</option>
          <option value="SPRS1">SPRS1 - 1 JAM - RP6000</option>
          <option value="SPRS2">SPRS2 - 2 JAM - RP9000</option>
          <option value="SPRS3">SPRS3 - 3 JAM - RP12000</option>
          <option value="SPRS4">SPRS4 - 4 JAM - RP17000</option>
          <option value="SPRS5">SPRS5 - 5 JAM - RP20000</option>
          <option value="SPRS6">SPRS6 - 6 JAM - RP25000</option>
        </select>
        <select name="pembayaran" id="pembayaran">
          <option value="va">QRIS</option>
          <!-- <option value="va">Virtual Account</option> -->
        </select>
        <button type="button" id="bayar" onclick="qrisBayar()">BAYAR</button>
      </form>
    </main>

    <div id="qris" class="qris" onclick="hilang()">
      <div>
        <h3>QRIS PEMBAYARAN</h3>
        <div class="qris_image">
          <img src="./image/qris.jpg" alt="" />
          <p>SPRS - 3 JAM - RP12000</p>
        </div>
        <button type="button" onclick="aktif()">PEMBAYARAN SELESAI</button>
      </div>
    </div>

    <section id="pesanan_aktif" class="pesanan_aktif container">
            <h2>PAKET AKTIF</h2>
            <p>JENIS PAKET: SPRS - 3 JAM - RP12000</p>
    </section>

    <script>
      function qrisBayar() {
        document.getElementById("qris").classList.add("muncul");
        document.getElementById("qris").classList.remove("hilang");
      }
      function hilang() {
        document.getElementById("qris").classList.toggle("hilang");
        document.getElementById("qris").classList.remove("muncul");
      }
      function aktif(){
        document.getElementById("main").classList.add("hilang");
        document.getElementById("main").classList.remove("muncul");
        document.getElementById("pesanan_aktif").classList.add("muncul")
      }
    </script>
  </body>
</html>
