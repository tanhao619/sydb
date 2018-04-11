function setSessionStorage(key, value){
  sessionStorage.setItem(key, value);
}

function getSessionStorage(key){
 return sessionStorage.getItem(key);
}

function removeSessionStorage(key){
 sessionStorage.removeItem(key); 
}

function clearSessionStorage(){
 sessionStorage.clear(); 
}