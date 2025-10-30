package com.cpifppiramide.prueba1.dao.prendas;

import com.cpifppiramide.prueba1.context.DBConnection;
import com.cpifppiramide.prueba1.dao.DAOFactory;
import com.cpifppiramide.prueba1.dao.ejemplares.DAOEjemplares;
import com.cpifppiramide.prueba1.dao.ejemplares.DAOEjemplaresSQL;
import com.cpifppiramide.prueba1.entidades.Ejemplar;
import com.cpifppiramide.prueba1.entidades.Prenda;
import com.cpifppiramide.prueba1.entidades.TipoPrenda;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPrendasSQL implements DAOPrendas{
    @Override
    public List<Prenda> lista() {
        List<Prenda> listaPrendas= new ArrayList<>();

        String query = "select p.*, (select count(*) from ejemplares e where e.prenda = p.marca) as numEjemplares from prendas p";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Prenda prenda = new Prenda(rs.getString("marca"), TipoPrenda.valueOf(rs.getString("tipoPrenda")));
                List<Ejemplar> listaEjemplares= DAOFactory.getInstance().getDaoEjemplares().get(prenda);
                prenda.setEjemplares(listaEjemplares);

                listaPrendas.add(prenda);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPrendas;
    }

    @Override
    public Prenda ver(String marca) {
        Prenda prenda = null;

        String query= "select * from prendas where marca = ?";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setString(1, marca);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                prenda= new Prenda(rs.getString("marca"), TipoPrenda.valueOf(rs.getString("tipoPrenda")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prenda;
    }

    @Override
    public void inserta(Prenda prenda) {
        String query= "insert into prendas (marca, tipoPrenda) values (?, ?)";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setString(1, prenda.getMarca());
            statement.setString(2, prenda.getTipoPrenda().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
