package com.cpifppiramide.prueba1.controller;

import com.cpifppiramide.prueba1.dao.DAOFactory;
import com.cpifppiramide.prueba1.entidades.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EjemplaresController {

    @GetMapping("/ejemplares/nuevo")
    public String verFormularioNuevoEjemplar(Model model){
        List<Prenda> listaPrendas = DAOFactory.getInstance().getDaoPrendas().lista();

        model.addAttribute("prendas" , listaPrendas);
        model.addAttribute("tipoPrenda", TipoPrenda.values());
        model.addAttribute("colores", Color.values());
        model.addAttribute("tallas", Talla.values());
        return "nuevoEjemplar";
    }

    @PostMapping("/ejemplares/nuevo")
    public String insertarNuevoEjemplar(@RequestParam String marcaEjemplar,
                                        @RequestParam String tipoPrenda,
                                        @RequestParam String color,
                                        @RequestParam String talla,
                                        @RequestParam int stock,
                                        Model model){
        Ejemplar nuevoEjemplar = new Ejemplar(DAOFactory.getInstance().getDaoPrendas().ver(marcaEjemplar),
                Color.valueOf(color),
                Talla.valueOf(talla),
                stock);
        DAOFactory.getInstance().getDaoEjemplares().inserta(nuevoEjemplar);

        return "redirect:/prendas/"+marcaEjemplar+"";
    }
}
