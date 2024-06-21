package clinicaodontologica.service;


import clinicaodontologica.entity.Paciente;
import clinicaodontologica.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;


public interface IPacienteService {
    //CRUD METHOD
    Paciente registrarPaciente(Paciente paciente);
    void actualizarPaciente(Paciente paciente) throws ResourceNotFoundException;
    void eliminarPaciente(Integer id) throws ResourceNotFoundException;
    Optional<Paciente> buscarPorId(Integer id) throws ResourceNotFoundException;
    List<Paciente> buscarTodos();

    List<Paciente> buscarPorProvincia(String procincia);
}
