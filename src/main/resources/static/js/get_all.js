window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de odontologos con el método GET
      //nos devolverá un JSON con una colección de odontologos
      const url = '/odontologos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(function(respuesta){
            return respuesta.json();
            })
      .then(function (info) {
        console.log(info);
      //recorremos la colección de odontologos del JSON
for(let i=0;i<info.length;i++){
        //por cada odontologo armaremos una fila de la tabla
        //cada fila tendrá un id
        var table = document.getElementById("odontologoTable");
        var odontologoRow =table.insertRow();
        let tr_id = 'tr_' + info[i].id;
        odontologoRow.id = tr_id;


        //armamos cada columna de la fila
        odontologoRow.innerHTML = '<td class=\"td_nombre\">' + info[i].nombre.toUpperCase() + '</td>' +
                '<td class=\"td_apellido\">' + info[i].apellido.toUpperCase() + '</td>';

    };

})
})

(function(){
  let pathname = window.location.pathname;
  if (pathname == "/Listas.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
  }
})
})
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de pacientes con el método GET
      //nos devolverá un JSON con una colección de pacientes
      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(function(respuesta){
            return respuesta.json();
            })
      .then(function (info) {
        console.log(info);
      //recorremos la colección de pacientes del JSON
for(let i=0;i<info.length;i++){
        //por cada paciente armaremos una fila de la tabla
        //cada fila tendrá un id
        var table = document.getElementById("pacienteTable");
        var pacienteRow =table.insertRow();
        let tr_id = 'tr_' + info[i].id;
        pacienteRow.id = tr_id;


        //armamos cada columna de la fila
        pacienteRow.innerHTML = '<td class=\"td_nombre\">' + info[i].nombre.toUpperCase() + '</td>' +
                '<td class=\"td_apellido\">' + info[i].apellido.toUpperCase() + '</td>';

    };

})
})

(function(){
  let pathname = window.location.pathname;
  if (pathname == "/Listas.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
  }
})
})
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de turnos con el método GET
      //nos devolverá un JSON con una colección de turnos
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(function(respuesta){
            return respuesta.json();
            })
      .then(function (info) {
        console.log(info);
      //recorremos la colección de turnos del JSON
for(let i=0;i<info.length;i++){
        //por cada turno armaremos una fila de la tabla
        //cada fila tendrá un id
        var table = document.getElementById("turnoTable");
        var turnoRow =table.insertRow();
        let tr_id = 'tr_' + info[i].id;
        turnoRow.id = tr_id;


        //armamos cada columna de la fila
        turnoRow.innerHTML = '<td class=\"td_fecha\">' + info[i].fecha + '</td>' +
                '<td class=\"td_paciente\">' + info[i].paciente.nombre +  '</td>' +
                '<td class=\"td_odontologo\">' + info[i].odontologo.nombre + '</td>';

    };

})
})

(function(){
  let pathname = window.location.pathname;
  if (pathname == "/Listas.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
  }
})
})