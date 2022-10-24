/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.gamed;

import java.util.ArrayList;

/**
 *
 * @author FRIDSON FIRMINO
 */
public class Categoria {
    private final ArrayList<String> categoria = new ArrayList();

    public ArrayList<String> getCategoria() {
        categoria.add("OBRAS DE REFERENCIA");
        categoria.add("OBRAS DE FICCAO");
        categoria.add("OBRAS TECNICAS E CIENTIFICAS");
        return categoria;
    }
    
    
   
}
