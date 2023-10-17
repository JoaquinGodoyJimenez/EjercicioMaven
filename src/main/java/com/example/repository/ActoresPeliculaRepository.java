package com.example.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Actores;
import com.example.model.Peliculas;

public class ActoresPeliculaRepository {
    public Peliculas getActoresPelicula(int id) throws SQLException {
        Statement st = con.createStatement();
        String sql = "select a.nombre as nombre, p.nombre as titulo, p.idioma as idioma from pelicula p "
                + "join pelicula_actor pa on pa.id_pelicula = p.id_pelicula "
                + "join actor a on a.id_actor = pa.id_actor "
                + "where p.id_pelicula = " + id;
        ResultSet rs = st.executeQuery(sql);
        Peliculas pel = new Peliculas();
        List<Actores> actores = new ArrayList<>();
        while (rs.next()) {
            pel.setIdioma(rs.getString("idioma"));
            pel.setNombre(rs.getString("nombre"));
            actores.add(new Actores(0, rs.getString("nombre")));
            System.out.println(actores);
        }
        pel.setActores(actores);
        return pel;
    }

    ConnectionDB conDB = new ConnectionDB();
    Connection con = null;

    public ActoresPeliculaRepository() {
        try {
            con = conDB.createConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void closeConnection() {
        try {
            conDB.closeConnection(con);
        } catch (SQLException ex) {
            System.out.println("ERROR CLOSING");
        }
    }
}
