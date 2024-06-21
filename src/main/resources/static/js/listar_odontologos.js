const tableBody = document.querySelector("#odontologosTable tbody");
url=""
function fetchOdontologos() {
  // listando los odontologos

  fetch(url+`/odontologo`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // Limpiar el contenido actual de la tabla
      tableBody.innerHTML = "";

      // Insertar los datos en la tabla
      data.forEach((odontologo, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
                <td>${odontologo.id}</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
                <td>${odontologo.nroMatricula}</td>
                <td>
                  <button class="btn btn-primary btn-sm" onclick="redireccionarOdontologo(${odontologo.id}, '${odontologo.nombre}', '${odontologo.apellido}', '${odontologo.nroMatricula}')">Modificar</button>
                  <button class="btn btn-danger btn-sm" onclick="deleteOdontologo(${odontologo.id})">Eliminar</button>
                </td>
              `;

        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });

 

}
 // Redireccionar para modificar un odontologo
function redireccionarOdontologo(id, nombre, apellido, nroMatricula){
  
  localStorage.setItem('odontologo_id', JSON.stringify(id))
  
  window.location.assign("/modificar_odontologos.html");
  

}
// eliminar un odontologo
function deleteOdontologo(id){
fetch(url+`/odontologo/`+id , {
  method: "DELETE",
  headers: {
    "Content-Type": "application/json",
  }
} )
.then((response) => response.json())
.then((data) => {
  fetchOdontologos();
  alert("Eliminado con exito");
})
.catch((error) => {
  console.error("Error eliminando al odontologo ERROR:", error);
  alert("Error eliminando al odontologo ERROR:")
});
}


fetchOdontologos();
