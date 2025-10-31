package com.cpifppiramide.prueba1.restController;

import com.cpifppiramide.prueba1.dao.DAOFactory;
import com.cpifppiramide.prueba1.entidades.Ejemplar;
import com.cpifppiramide.prueba1.entidades.Prenda;
import com.cpifppiramide.prueba1.entidades.PrendaDTO;
import com.cpifppiramide.prueba1.entidades.TipoPrenda;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrendaRestController {

    @GetMapping("/api/prendas")
    public List<Prenda> verPrendas(){
        List<Prenda> listaPrendas= DAOFactory.getInstance().getDaoPrendas().lista();

        return listaPrendas;
    }

    @GetMapping("/api/prendas/{marca}")
    public List<Ejemplar> verDetalles(@PathVariable String marca){
        Prenda prenda= DAOFactory.getInstance().getDaoPrendas().ver(marca);
        List<Ejemplar> ejemplarList = DAOFactory.getInstance().getDaoEjemplares().get(prenda);
        //prenda.setEjemplares(DAOFactory.getInstance().getDaoEjemplares().get(prenda));
        return ejemplarList;
    }

    /*@GetMapping("/api/prendas/nueva")
    public String verFormularioAñadirPrenda(Model model){
        model.addAttribute("tipoPrenda", TipoPrenda.values());
        return "nuevaPrenda";
    }*/

    @PostMapping("/api/prendas/nueva")
    public Prenda insertarPrenda(@RequestBody PrendaDTO prenda){
        Prenda prendaNueva= new Prenda(prenda.getMarca(), prenda.getTipoPrenda());
        DAOFactory.getInstance().getDaoPrendas().inserta(prendaNueva);

        return prendaNueva;
    }
}
