package clinicaodontologica.service;


import clinicaodontologica.entity.Turno;
import clinicaodontologica.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoService {
    //CRUD method
    Turno registrar(Turno turno);

    void actualizarTurno(Turno turno) throws ResourceNotFoundException;
    void eliminarTurno(Integer id) throws ResourceNotFoundException;
    Turno buscarPorId(Integer id) throws ResourceNotFoundException;

    List<Turno> buscarTodos();
    // HQL
    List<Turno> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate);
    List<Turno> buscarTurnoDespueFecha();

}
