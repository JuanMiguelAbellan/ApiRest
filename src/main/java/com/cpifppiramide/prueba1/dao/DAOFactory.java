package com.cpifppiramide.prueba1.dao;

import com.cpifppiramide.prueba1.dao.ejemplares.DAOEjemplares;
import com.cpifppiramide.prueba1.dao.ejemplares.DAOEjemplaresSQL;
import com.cpifppiramide.prueba1.dao.prendas.DAOPrendas;
import com.cpifppiramide.prueba1.dao.prendas.DAOPrendasSQL;

public class DAOFactory {

    private static final DAOFactory daoFactory = new DAOFactory();
    private DAOPrendas daoPrendas;
    private DAOEjemplares daoEjemplares;

    private DAOFactory(){}

    public static DAOFactory getInstance() {
        return daoFactory;
    }

    public DAOPrendas getDaoPrendas(){
        if(daoPrendas == null){
            daoPrendas = new DAOPrendasSQL();
        }
        return daoPrendas;
    }

    public DAOEjemplares getDaoEjemplares(){
        if(daoEjemplares == null){
            daoEjemplares = new DAOEjemplaresSQL();
        }
        return daoEjemplares;
    }

}
