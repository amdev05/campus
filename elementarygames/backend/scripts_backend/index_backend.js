import { initializeApp } from "https://www.gstatic.com/firebasejs/11.10.0/firebase-app.js";
import { getAuth, onAuthStateChanged, signOut } from "https://www.gstatic.com/firebasejs/11.10.0/firebase-auth.js";

// Konfigurasi Firebase
const firebaseConfig = {
  apiKey: "AIzaSyDdUxG6Kn9YRZAtpn7FXn54cv0Ku0eEUQA",
  authDomain: "elementary-web.firebaseapp.com",
  projectId: "elementary-web",
  storageBucket: "elementary-web.firebasestorage.app",
  messagingSenderId: "1077721408779",
  appId: "1:1077721408779:web:b0631c8a8edf2cb1b3ee0e"
};

// Inisialisasi Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);

const profile = document.getElementById("profile");
const login = document.getElementById("login");
const content = document.getElementById("content");
const loader = document.getElementById("loader");

onAuthStateChanged(auth, (user) => {
  
  if (user) {
    
    profile.classList.remove("d-none");
    login.classList.add("d-none");
    document.getElementById('user-email').textContent = user.email || "elementary@gmail.com";
    document.getElementById('user-photo').src = user.photoURL || "./img/hot-game.png";
  } else {
    
    profile.classList.add("d-none");
    login.classList.remove("d-none");
  }

  
});


// Logout
document.getElementById("logout").addEventListener("click", () => {
  signOut(auth).then(() => {
    window.location.href = "index.html";
  });
});

