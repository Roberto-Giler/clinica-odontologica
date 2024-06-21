url=""

const form = document.getElementById("modificarForm");

const id=localStorage.getItem('odontologo_id')

form.addEventListener("submit", function (event) {
    
  event.preventDefault();

  const id = localStorage.getItem('odontologo_id');
  const nombre = document.getElementById("nombreModificar").value;
  const apellido = document.getElementById("apellidoModificar").value;
  const matricula = document.getElementById("matriculaModificar").value;
      // llamando al endpoint de agregar

    fetch(url+`/odontologo`, {
       method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
       body: JSON.stringify({id, nombre, apellido, nroMatricula: matricula}),
     })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        alert("Odontólogo modificado con éxito");
        form.reset(); // Resetear el formulario
        window.location.assign("/listar_odontologos.html");
       })
       .catch((error) => {
        console.error("Error modificando odontólogo", error);
        alert("Error modificando odontólogo");
      });
});
  


