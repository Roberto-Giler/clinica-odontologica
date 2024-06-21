package clinicaodontologica.repository;

import clinicaodontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
    //Buscar paciente por id

    @Query("SELECT p FROM Paciente p WHERE p.domicilio.provincia = :provincia")
    List<Paciente> buscarPorProvincia(@Param("provincia") String provincia);



}
