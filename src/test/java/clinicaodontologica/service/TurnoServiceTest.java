package clinicaodontologica.service;

import clinicaodontologica.entity.Domicilio;
import clinicaodontologica.entity.Odontologo;
import clinicaodontologica.entity.Paciente;
import clinicaodontologica.entity.Turno;
import clinicaodontologica.service.impl.OdontologoService;
import clinicaodontologica.service.impl.PacienteService;
import clinicaodontologica.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TurnoServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    private Turno turno;
    private Paciente paciente;
    private Odontologo odontologo;


    @BeforeEach
    void setUp(){
        //Crear un paciente
        paciente = new Paciente();
        paciente.setNombre("Menganito");
        paciente.setApellido("Cosme");
        paciente.setDni("464646");
        paciente.setFechaIngreso(LocalDate.of(2024,01,12));
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle falsa");
        domicilio.setNumero(123);
        domicilio.setLocalidad("San Pedro");
        domicilio.setProvincia("Jujuy");
        paciente.setDomicilio(domicilio);

        paciente =pacienteService.registrarPaciente(paciente);

        //Crear un Odontologo
        odontologo =new Odontologo();
        odontologo.setNroMatricula("1212");
        odontologo.setNombre("Federico");
        odontologo.setApellido("Patinez");
        odontologo=odontologoService.agregarOdontologo(odontologo);

        // Se cerara un turno
        turno= new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.of(2023,8,12));


    }
    @Test
    @DisplayName("Testear que un turno gue guardado")
    void testPacienteGuardado(){
        Turno turnoDesdeLaBD = turnoService.registrar(turno);
        assertNotNull(turnoDesdeLaBD);
    }
}
