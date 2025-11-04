<?php
session_start();

$valid_username = "admin";
$valid_password = "admin123";
$errorMessage = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  $username = $_POST['username'];
  $password = $_POST['password'];

  if ($username === $valid_username && $password === $valid_password) {
      $_SESSION['username'] = $username;
      header("Location: index.php"); 
      exit();
  } else {      
      $errorMessage = 'Username atau password salah!';
  }
}
?>


<!DOCTYPE html>
<html lang="id">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login - SAZNET</title>
    <link rel="stylesheet" href="style/login.css" />
  </head>
  <body>
    <div class="login-box">
      <h2>Login Member</h2>

      <?php if (!empty($errorMessage)) : ?>
        <div class="error">
          <?= htmlspecialchars($errorMessage) ?>
        </div>
      <?php endif; ?>

      <form action="login.php" method="post">
        <input type="text" name="username" placeholder="Username" required />
        <input type="password" name="password" placeholder="Password" required />
        <button class="login-btn" type="submit">Login</button>
      </form>
      <a href="index.php" class="back-link">Kembali ke Beranda</a>
    </div>

    
  </body>
</html>
