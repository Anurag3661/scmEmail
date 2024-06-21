console.log("script is loaded");

let currentTheme = getTheme();
document.addEventListener("DOMContentLoaded", () =>{
    changeTheme();
});

function changeTheme() {
    //set theme to web page
    changePageTheme(currentTheme, "");

    //set the listener to change theme  button
    const changeThemeButton = document.querySelector("#theme_change_button");


    changeThemeButton.addEventListener("click", (event) =>{

        let oldTheme = currentTheme;

        console.log("theme button clicked");

        if(currentTheme === "dark"){
        //change theme to light 
        currentTheme = "light";
        }
        else{
            //change theme to dark
            currentTheme = "dark";
        }

        changePageTheme(currentTheme, oldTheme);
    });
}

//set theme to local storage
function setTheme(theme){
    localStorage.setItem("theme",theme);
}

//get theme from local storage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}


//change current page theme method
function changePageTheme(theme,oldTheme){
      //update the theme in the local storage 
      setTheme(currentTheme);

      if(oldTheme){
        //remove the current theme
      document.querySelector("html").classList.remove(oldTheme);
      }

      //set the current theme
      document.querySelector("html").classList.add(theme);

       //change the text after the theme applied
       document.querySelector("#theme_change_button").querySelector("span").textContent = theme == "light" ? "Dark" : "Light";
}