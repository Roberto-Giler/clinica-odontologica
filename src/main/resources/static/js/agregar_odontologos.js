const form = document.getElementById("agregarForm");
url=""
form.addEventListener("submit", function (event) {
  event.preventDefault();
  
  const nombre = document.getElementById("agregarNombre").value;
  const apellido = document.getElementById("agregarApellido").value;
  const matricula = document.getElementById("agregarMatricula").value;

  // llamando al endpoint de agregar

  fetch(url+`/odontologo`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ nombre, apellido, nroMatricula: matricula }),
  } )
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("Odontólogo agregado con éxito");
      form.reset(); // Resetear el formulario
    })
    .catch((error) => {
      console.error("Error agregando odontólogo:", error);
      alert("Error al agregar odontólogo");
    });
});
