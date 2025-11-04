import { initializeApp } from "https://www.gstatic.com/firebasejs/11.9.1/firebase-app.js";
import { getAuth, onAuthStateChanged, signOut } from "https://www.gstatic.com/firebasejs/11.9.1/firebase-auth.js";

const firebaseConfig = {
  apiKey: "AIzaSyDdUxG6Kn9YRZAtpn7FXn54cv0Ku0eEUQA",
  authDomain: "elementary-web.firebaseapp.com",
  projectId: "elementary-web",
  storageBucket: "elementary-web.firebasestorage.app",
  messagingSenderId: "1077721408779",
  appId: "1:1077721408779:web:b0631c8a8edf2cb1b3ee0e"
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);

// Tampilkan info user yang login
onAuthStateChanged(auth, (user) => {
  if (user) {
    document.getElementById('user-name').textContent = user.displayName || "Anonymous";
    document.getElementById('user-email').textContent = user.email;
    document.getElementById('user-photo').src = user.photoURL || "https://www.w3schools.com/howto/img_avatar.png";
  } else {
    window.location.href = "index.html"; // Redirect jika tidak login
  }
});


//logout
document.getElementById('logout').addEventListener('click', () => {
  signOut(auth).then(() => {
    window.location.href = "dashboard.html"; // Balik ke login
  }).catch((error) => {
    alert("Logout gagal: " + error.message);
  });
});

