package com.cpifppiramide.prueba1.restController;

import com.cpifppiramide.prueba1.dao.DAOFactory;
import com.cpifppiramide.prueba1.entidades.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EjemplaresRestController {

    /*@GetMapping("/api/ejemplares/nuevo")
    public List<Prenda> verFormularioNuevoEjemplar(Model model){
        List<Prenda> listaPrendas = DAOFactory.getInstance().getDaoPrendas().lista();

        return "nuevoEjemplar";
    }*/

    @PostMapping("/api/ejemplares/nuevo")
    public Ejemplar insertarNuevoEjemplar(@RequestBody Ejemplar ejemplar){
        DAOFactory.getInstance().getDaoEjemplares().inserta(ejemplar);

        return ejemplar;
    }
}
