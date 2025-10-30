package com.cpifppiramide.prueba1.dao.ejemplares;

import com.cpifppiramide.prueba1.context.DBConnection;
import com.cpifppiramide.prueba1.entidades.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOEjemplaresSQL implements DAOEjemplares{
    @Override
    public List<Ejemplar> get(Prenda prenda) {
        List<Ejemplar> listaEjemplares= new ArrayList<>();

        String query = "select *, (select tipoPrenda from prendas where marca = ?) as tipoPrenda from ejemplares where prenda = ?" ;

        try {
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setString(1, prenda.getMarca());
            statement.setString(2, prenda.getMarca());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Ejemplar ejemplar = new Ejemplar(new Prenda(rs.getString("prenda") , TipoPrenda.valueOf(rs.getString("tipoPrenda"))), Color.valueOf(rs.getString("color")), Talla.valueOf(rs.getString("talla")), rs.getInt("stock"));
                listaEjemplares.add(ejemplar);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEjemplares;
    }

    @Override
    public void inserta(Ejemplar ejemplar) {
        String query= "insert into ejemplares (prenda, color, talla, stock) values (?, ?, ?, ?)";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setString(1, ejemplar.getPrenda().getMarca());
            statement.setString(2, ejemplar.getColor().toString());
            statement.setString(3, ejemplar.getTalla().toString());
            statement.setInt(4, ejemplar.getStock());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
