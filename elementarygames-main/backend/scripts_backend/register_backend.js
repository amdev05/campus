import { initializeApp } from "https://www.gstatic.com/firebasejs/11.9.1/firebase-app.js";
import { getAuth, createUserWithEmailAndPassword, GoogleAuthProvider, signInWithPopup  } from "https://www.gstatic.com/firebasejs/11.9.1/firebase-auth.js";
import { sendEmailVerification } from "https://www.gstatic.com/firebasejs/11.9.1/firebase-auth.js";


  // TODO: Add SDKs for Firebase products that you want to use
  // https://firebase.google.com/docs/web/setup#available-libraries

  // Your web app's Firebase configuration
 const firebaseConfig = {
  apiKey: "AIzaSyDdUxG6Kn9YRZAtpn7FXn54cv0Ku0eEUQA",
  authDomain: "elementary-web.firebaseapp.com",
  projectId: "elementary-web",
  storageBucket: "elementary-web.firebasestorage.app",
  messagingSenderId: "1077721408779",
  appId: "1:1077721408779:web:b0631c8a8edf2cb1b3ee0e"
};

  // Initialize Firebase
  const app = initializeApp(firebaseConfig);

  const auth = getAuth(app);

  const provider = new GoogleAuthProvider();

  // input



  //submit button
  const submit = document.getElementById('submit')
  submit.addEventListener("click", function(event){
    event.preventDefault()
    
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    createUserWithEmailAndPassword(auth, email, password)
    .then((userCredential) => {
       // Kirim email verifikasi
       const user = userCredential.user;
      sendEmailVerification(user)
        .then(() => {
          alert("Akun berhasil dibuat. Silakan cek email untuk verifikasi.");
          window.location.href = "./login.html";
        })
        .catch((error) => {
          alert("Gagal mengirim email verifikasi: " + error.message);
        });
    })
    .catch((error) => {
        const errorCode = error.code;
        const errorMessage = error.message;
        // alert(errorMessage)
        if (errorCode === "auth/email-already-in-use") {
          alert("Email sudah digunakan.");
        }
        // ..
    });
  })


  // login with google
  const login_google = document.getElementById('login-with-google');
  login_google.addEventListener("click", function(event){
    event.preventDefault()
    signInWithPopup(auth, provider)
    .then((result) => {
        // This gives you a Google Access Token. You can use it to access the Google API.
        const credential = GoogleAuthProvider.credentialFromResult(result);
        const token = credential.accessToken;
        // The signed-in user info.
        const user = result.user;
        // IdP data available using getAdditionalUserInfo(result)
        // ...
        alert("login berhasil")
        window.location.href = "../index.html";
    }).catch((error) => {
        // Handle Errors here.
        const errorCode = error.code;
        const errorMessage = error.message;
        // The email of the user's account used.
        const email = error.customData.email;
        // The AuthCredential type that was used.
        const credential = GoogleAuthProvider.credentialFromError(error);
        // ...
        alert(errorMessage)
    });
  })

  