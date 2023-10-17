package com.example;

import java.sql.SQLException;
import com.example.repository.ActoresPeliculaRepository;
import com.google.gson.Gson;

public class App 
{
    public static void main( String[] args ) {
    ActoresPeliculaRepository repo = new ActoresPeliculaRepository();
    try {
        Gson g = new Gson();
        String json = g.toJson(repo.getActoresPelicula(1)).toString();
        System.out.println(json);
    } catch (SQLException e) {
        e.printStackTrace();  // o manejo de excepción personalizado
    }
}

}
