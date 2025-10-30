package com.cpifppiramide.prueba1.controller;

import com.cpifppiramide.prueba1.dao.DAOFactory;
import com.cpifppiramide.prueba1.dao.prendas.DAOPrendas;
import com.cpifppiramide.prueba1.entidades.Ejemplar;
import com.cpifppiramide.prueba1.entidades.Prenda;
import com.cpifppiramide.prueba1.entidades.TipoPrenda;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PrendaController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/prendas")
    public String verPrendas(Model model){
        List<Prenda> listaPrendas= DAOFactory.getInstance().getDaoPrendas().lista();

        model.addAttribute("prendas", listaPrendas);
        return "prendas";
    }

    @GetMapping("/prendas/{marca}")
    public String verDetalles(@PathVariable String marca, Model model){
        Prenda prenda= DAOFactory.getInstance().getDaoPrendas().ver(marca);

        List<Ejemplar> listaEjemplares = DAOFactory.getInstance().getDaoEjemplares().get(prenda);

        model.addAttribute("marca", marca);
        model.addAttribute("ejemplares", listaEjemplares);
        model.addAttribute("tipoPrenda", listaEjemplares.get(0).getPrenda().getTipoPrenda());
        return "prendaDetalle";
    }

    @GetMapping("/prendas/nueva")
    public String verFormularioAñadirPrenda(Model model){
        model.addAttribute("tipoPrenda", TipoPrenda.values());
        return "nuevaPrenda";
    }

    @PostMapping("/prendas/nueva")
    public String insertarPrenda(@RequestParam String marca, @RequestParam String tipoPrenda, Model model){
        Prenda prendaNueva= new Prenda(marca, TipoPrenda.valueOf(tipoPrenda));
        DAOFactory.getInstance().getDaoPrendas().inserta(prendaNueva);

        return "redirect:/prendas";
    }
}
