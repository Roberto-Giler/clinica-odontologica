package clinicaodontologica.service;


import clinicaodontologica.entity.Odontologo;
import clinicaodontologica.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;


public interface IOdontologoService {
    //CRUD method
    Odontologo agregarOdontologo(Odontologo odontologo);
    void modificarOdontologo(Odontologo odontologo) throws ResourceNotFoundException;
    void eliminarOdontologo(Integer id) throws ResourceNotFoundException;
    Optional<Odontologo> buscarUnOdontologo(Integer id) throws ResourceNotFoundException;
    List<Odontologo> buscarTodosOdontologos();





    // Metodos con HQL
    List<Odontologo> buscarPorApellido(String apellido);
    List<Odontologo> buscarPorNombre(String nombre);
}
