async function loadSuper(){
    const main=document.getElementById("main");

    const response = await fetch('/super');
    const data= await response.json();
    main.appendChild(getCompetitionList(data));

    const users=await getUsers();
    const data1= await users.json();
    main.appendChild(getAddCompetitionElement(data1));
 //   users.then(value=>{addCompetitionElement(main,value)});
     

}


function getCompetitionList(data){
 
    var ul=document.createElement("ul");

    for (var i=0;i<data.length;i++){
        var li = document.createElement('li');
        var  button =document.createElement("button");
        button.innerText="delete";
        let id= data[i].id;
        button.onclick=function(){deleteCompetition(id)};
        li.innerHTML= data[i].name+' '+data[i].admin.username+' '+data[i].id;
        li.appendChild(button);
        ul.appendChild(li);
    }
    return ul;
 
}

  function getAddCompetitionElement(users){

    var div=document.createElement('div');
    var newButton = document.createElement('button');
    newButton.innerText="new Competition";
    newButton.onclick=function(){onNewCompetition()};
   
    const form = document.createElement('form');
    form.setAttribute(id='newCompetitionForm');
    const compLabel= document.createElement('label');
    compLabel.innerHTML="Competition name: ";
    const compInput=document.createElement('input');
    compInput.setAttribute('name',"Name");

    const adminLabel= document.createElement('label');
    adminLabel.innerHTML="Choose admin: ";
    const adminSelect= document.createElement('select');
   
   let user;
    for (var i=0;i<users.length;i++){
        const option= document.createElement('option');
        option.text=users[i].username;
        adminSelect.add(option,null);
    }
    
    const addButton = document.createElement('input');
    addButton.value="Add";
    addButton.setAttribute("type", "submit");
    form.addEventListener('submit',function(e){
        onAddCompetition();
        e.preventDefault();
    }); 


    form.appendChild(compLabel);
    form.appendChild(compInput);
    form.appendChild(adminLabel);
    form.appendChild(adminSelect);
    form.appendChild(addButton);
    

    div.appendChild(newButton);
    div.appendChild(form);

    return div;
    }
    
function onAddCompetition(){
    const form=document.getElementById('newCompetitionForm');
    
}

async function getUsers(){
    const response= await fetch('/api/super/');
    return response;
}


async function deleteCompetition(id){
    let response = await fetch('/super/'+id ,{method:'DELETE',
    headers: { 'X-XSRF-TOKEN': getCookie('XSRF-TOKEN') },});
   if (response.ok){
        loadSuper();
    }
    else{
 
        alert("Error deleting Competition: "+response.status);
    }

}


function addCompetition(){

    addComp=document.createElement('div');
    document.getElementById('div');
}




function getCookie(cName) {
    const name = cName + "=";
    const cDecoded = decodeURIComponent(document.cookie); //to be careful
    const cArr = cDecoded.split('; ');
    let res;
    cArr.forEach(val => {
      if (val.indexOf(name) === 0) res = val.substring(name.length);
    })
    return res
  }