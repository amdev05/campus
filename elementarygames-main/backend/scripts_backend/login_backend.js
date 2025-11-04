import { initializeApp } from "https://www.gstatic.com/firebasejs/11.9.1/firebase-app.js";
import { getAuth, signInWithEmailAndPassword , sendPasswordResetEmail, GoogleAuthProvider, signInWithPopup} from "https://www.gstatic.com/firebasejs/11.9.1/firebase-auth.js";


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
    signInWithEmailAndPassword(auth, email, password)
    .then((userCredential) => {
        // Sign in
        const user = userCredential.user;
        alert("Login account")
        window.location.href = "../index.html";
        // ...
    })
    .catch((error) => {
      const errorCode = error.code;

      if (errorCode === "auth/user-not-found") {
        alert("Email belum terdaftar.");
      } else if (errorCode === "auth/wrong-password") {
        alert("Password salah.");
      } else if (errorCode === "auth/invalid-email") {
        alert("Format email tidak valid.");
      } else if (errorCode === "auth/too-many-requests") {
        alert("Terlalu banyak percobaan. Coba lagi nanti.");
      }else if(errorCode === "auth/invalid-credential"){
          alert("Email belum terdaftar");
      } else {
        alert("Login gagal: " + error.message);
      }
    });
  })
  

  

    // //login with google
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
  
    