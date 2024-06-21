package clinicaodontologica.service.impl;


import clinicaodontologica.entity.Turno;
import clinicaodontologica.repository.IOdontologoRepository;
import clinicaodontologica.repository.ITurnoRepository;
import clinicaodontologica.service.ITurnoService;
import clinicaodontologica.exception.ResourceNotFoundException;
import clinicaodontologica.repository.IPacienteRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private IOdontologoRepository odontologoRepository;
    private IPacienteRepository pacienteRepository;
    private ITurnoRepository turnoRepository;
    private ModelMapper modelMapper;
    private static Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);



    public TurnoService(IOdontologoRepository odontologoRepository, IPacienteRepository pacienteRepository, ITurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Turno registrar(Turno turno) {
        Turno turnoADevolver=turnoRepository.save(turno);
        LOGGER.info("turno registrado");
        return turnoADevolver;
    }

    @Override
    public Turno buscarPorId(Integer id) throws ResourceNotFoundException {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        Turno turnoADevolver=null;
        if(turnoOptional.isPresent()){
            LOGGER.info("turno encontrado");
            return turnoOptional.get();
        }
        else {
            LOGGER.error("Turno no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"turno no encontrado\"}");
        }
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }



    @Override
    public void actualizarTurno(Turno turno) throws ResourceNotFoundException {
        Optional<Turno> turnoOptional = turnoRepository.findById(turno.getId());
        if(turnoOptional.isPresent()){
            turnoRepository.save(turno);
            LOGGER.info("turno actualizado");
        }
        else{
            LOGGER.error("Turno no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"turno no encontrado\"}");
        }

    }

    @Override
    public void eliminarTurno(Integer id) throws ResourceNotFoundException {
        Optional<Turno> turno = turnoRepository.findById(id);
        if(turno.isPresent()){
            turnoRepository.deleteById(id);
            LOGGER.info("Turno Eliminado");
        }
        else{
            LOGGER.error("Turno no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"turno no encontrado\"}");
        }
    }

    @Override
    public List<Turno> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate) {
        return  turnoRepository.buscarTurnoEntreFechas(startDate, endDate);
    }


    // metodo que mapea turno en turnoResponseDto

    @Override
    public List<Turno> buscarTurnoDespueFecha() {
        return turnoRepository.buscarTurnoDespueFuturos(LocalDateTime.now().toLocalDate());
    }

}
