package clinicaodontologica.service.impl;


import clinicaodontologica.service.IPacienteService;
import clinicaodontologica.entity.Paciente;
import clinicaodontologica.exception.ResourceNotFoundException;
import clinicaodontologica.repository.IPacienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private static Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;

    }

    public Paciente registrarPaciente(Paciente paciente){
        Paciente pacienteADevolver= pacienteRepository.save(paciente);
        LOGGER.info("paciente registrado");
        return pacienteADevolver;
    }

    public Optional<Paciente> buscarPorId(Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if(pacienteOptional.isPresent()){
            LOGGER.info("paciente encontrado");
            return pacienteOptional;
        }
        else{
            LOGGER.error("paciente no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        }
    }

    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }

    @Override
    public void actualizarPaciente(Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional = buscarPorId(paciente.getId());
        if (pacienteOptional.isPresent()){
            pacienteRepository.save(paciente);
            LOGGER.info("paciente actualizado");

        }
        else {
            LOGGER.error("paciente no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");

        }
    }

    @Override
    public void eliminarPaciente(Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional = buscarPorId(id);
        if(pacienteOptional.isPresent()) {
            pacienteRepository.deleteById(id);
            LOGGER.info("paciente eliminado");
        }
        else{
            LOGGER.error("paciente no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        }
    }


    @Override
    public List<Paciente> buscarPorProvincia(String provincia) {
        return pacienteRepository.buscarPorProvincia(provincia);
    }
}
