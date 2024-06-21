package clinicaodontologica.service.impl;


import clinicaodontologica.entity.Odontologo;
import clinicaodontologica.exception.ResourceNotFoundException;
import clinicaodontologica.repository.IOdontologoRepository;
import clinicaodontologica.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo agregarOdontologo(Odontologo odontologo){
        Odontologo odontologoADevolver =odontologoRepository.save(odontologo);
        LOGGER.info("paciente agregado");
        return odontologoADevolver;
    }

    public Optional<Odontologo> buscarUnOdontologo(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        if(odontologoOptional.isPresent()){
            LOGGER.info("paciente actualizado");
            return odontologoOptional;
        }
        else{
            LOGGER.error("paciente no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"odontologo no encontrado\"}");
        }

    }
    public List<Odontologo> buscarTodosOdontologos(){
        return odontologoRepository.findAll();
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = buscarUnOdontologo(odontologo.getId());
        if(odontologoOptional.isPresent()){
            odontologoRepository.save(odontologo);;
            LOGGER.info("paciente actualizado");
        }
        else{
            LOGGER.error("paciente no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"odontologo no encontrado\"}");
        }

    }

    @Override
    public void eliminarOdontologo(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = buscarUnOdontologo(id);
        if(odontologoOptional.isPresent()){
            odontologoRepository.deleteById(id);
            LOGGER.info("paciente eliminado");
        }
        else{
            LOGGER.error("paciente no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"odontologo no encontrado\"}");
        }
    }


    @Override
    public List<Odontologo> buscarPorApellido(String apellido) {
        return odontologoRepository.buscarPorApellido(apellido);
    }

    @Override
    public List<Odontologo> buscarPorNombre(String nombre) {
        return odontologoRepository.findByNombreLike(nombre);
    }


}
