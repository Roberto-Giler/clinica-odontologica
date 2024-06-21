package clinicaodontologica.service;


import clinicaodontologica.entity.Odontologo;
import clinicaodontologica.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OdontologoServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoServiceTest.class);
    @Autowired
    private OdontologoService odontologoService;
    private Odontologo odontologo;

    @BeforeEach
    void setUp(){
        //Crear un Odontologo
        odontologo =new Odontologo();
        odontologo.setNroMatricula("1212");
        odontologo.setNombre("Federico");
        odontologo.setApellido("Patinez");
    }
    @Test
    @DisplayName("Testear que un odontologo oueda ser guardado")
    void testPacienteGuardado(){
        Odontologo odontologoDesdeLaBD = odontologoService.agregarOdontologo(odontologo);
        assertNotNull(odontologoDesdeLaBD);
    }


}
