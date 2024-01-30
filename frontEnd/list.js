
const home = document.getElementById("homes")
const clicks = document.getElementById("generate");
const options = document.getElementById("optional");

 home.onclick = () => {
     window.location.href = "index.html";
 }

let message = new SpeechSynthesisUtterance(`Hello dear for further operation ${clicks.innerText} ${options.innerText}`);
window.speechSynthesis.onvoiceschanged = function() {
    const voices = window.speechSynthesis.getVoices();
    message.voice = voices[3];
    message.rate = 0.8;

    window.speechSynthesis.speak(message);}