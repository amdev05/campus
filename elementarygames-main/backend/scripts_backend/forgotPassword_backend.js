import { initializeApp } from "https://www.gstatic.com/firebasejs/11.9.1/firebase-app.js";
import { getAuth,  sendPasswordResetEmail,} from "https://www.gstatic.com/firebasejs/11.9.1/firebase-auth.js";


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
   



const forgotpassword = document.getElementById('submit');
  forgotpassword.addEventListener("click", function(event){
    event.preventDefault()
    
    const email = document.getElementById('email').value;
    sendPasswordResetEmail(auth, email)
    .then(() => {
        alert('Password email send')
        })
    .catch((error) => {
        const errorCode = error.code;
        const errorMessage = error.message;
        // ..
        alert('Password tidak terkirim')
    });

  })