<?php
session_start();
?>

<!DOCTYPE html>
<html lang="id">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Daftar Tarif - SAZNET</title>
    <link rel="stylesheet" href="style/tarif.css" />
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

    <main class="container">
      <h2>DAFTAR TARIF</h2>
      <p class="note">*Dapatkan tawaran spesial dan promo khusus untuk Member</p>

      <section>
        <h3>PAKET REGULER STANDAR</h3>
        <table>
          <tr>
            <th>Kode</th>
            <th>Nama Paket</th>
            <th>Harga</th>
            <th>Masa Aktif</th>
          </tr>
          <tr>
            <td>SPRS1</td>
            <td>Paket Reguler Std 1 Jam</td>
            <td>Rp6.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPRS2</td>
            <td>Paket Reguler Std 2 Jam</td>
            <td>Rp9.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPRS3</td>
            <td>Paket Reguler Std 3 Jam</td>
            <td>Rp12.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPRS4</td>
            <td>Paket Reguler Std 4 Jam</td>
            <td>Rp17.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPRS5</td>
            <td>Paket Reguler Std 5 Jam</td>
            <td>Rp20.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPRS6</td>
            <td>Paket Reguler Std 6 Jam</td>
            <td>Rp25.000</td>
            <td>24 Jam</td>
          </tr>
        </table>
      </section>

      <section>
        <h3>PAKET REGULER VIP</h3>
        <table>
          <tr>
            <th>Kode</th>
            <th>Nama Paket</th>
            <th>Harga</th>
            <th>Masa Aktif</th>
          </tr>
          <tr>
            <td>SPRV1</td>
            <td>Paket Reguler VIP 1 Jam</td>
            <td>Rp7.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPRV2</td>
            <td>Paket Reguler VIP 2 Jam</td>
            <td>Rp10.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPRV3</td>
            <td>Paket Reguler VIP 3 Jam</td>
            <td>Rp14.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPRV4</td>
            <td>Paket Reguler VIP 4 Jam</td>
            <td>Rp18.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPRV5</td>
            <td>Paket Reguler VIP 5 Jam</td>
            <td>Rp22.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPRV6</td>
            <td>Paket Reguler VIP 6 Jam</td>
            <td>Rp25.000</td>
            <td>24 Jam</td>
          </tr>
        </table>
      </section>

      <section>
        <h3>PAKET MALAM STANDAR</h3>
        <table>
          <tr>
            <th>Kode</th>
            <th>Nama Paket</th>
            <th>Harga</th>
            <th>Masa Aktif</th>
          </tr>
          <tr>
            <td>SPMS1</td>
            <td>Paket Malam Std 1 Jam</td>
            <td>Rp4.000</td>
            <td>23.00 - 07.00</td>
          </tr>
          <tr>
            <td>SPMS2</td>
            <td>Paket Malam Std 2 Jam</td>
            <td>Rp6.000</td>
            <td>23.00 - 07.00</td>
          </tr>
          <tr>
            <td>SPMS3</td>
            <td>Paket Malam Std 3 Jam</td>
            <td>Rp7.000</td>
            <td>23.00 - 07.00</td>
          </tr>
          <tr>
            <td>SPMS4</td>
            <td>Paket Malam Std 4 Jam</td>
            <td>Rp9.000</td>
            <td>23.00 - 07.00</td>
          </tr>
          <tr>
            <td>SPMS5</td>
            <td>Paket Malam Std 5 Jam</td>
            <td>Rp11.000</td>
            <td>23.00 - 07.00</td>
          </tr>
          <tr>
            <td>SPMS6</td>
            <td>Paket Malam Std 6 Jam</td>
            <td>Rp13.000</td>
            <td>23.00 - 07.00</td>
          </tr>
        </table>
      </section>

      <section>
        <h3>PAKET MALAM VIP</h3>
        <table>
          <tr>
            <th>Kode</th>
            <th>Nama Paket</th>
            <th>Harga</th>
            <th>Masa Aktif</th>
          </tr>
          <tr>
            <td>SPMV1</td>
            <td>Paket Malam VIP 1 Jam</td>
            <td>Rp7.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPMV2</td>
            <td>Paket Malam VIP 2 Jam</td>
            <td>Rp10.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPMV3</td>
            <td>Paket Malam VIP 3 Jam</td>
            <td>Rp12.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPMV4</td>
            <td>Paket Malam VIP 4 Jam</td>
            <td>Rp16.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPMV5</td>
            <td>Paket Malam VIP 5 Jam</td>
            <td>Rp20.000</td>
            <td>24 Jam</td>
          </tr>
          <tr>
            <td>SPMV6</td>
            <td>Paket Malam VIP 6 Jam</td>
            <td>Rp25.000</td>
            <td>24 Jam</td>
          </tr>
        </table>
      </section>
    </main>

    <footer>
        <div class="container">
          <p>Copyrights © 2025 - SAZNET.</p>
        </div>
      </footer>
  </body>
</html>
