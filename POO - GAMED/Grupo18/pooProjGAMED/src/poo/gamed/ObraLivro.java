/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.gamed;

/**
 *
 * @author Dzign
 */
public class ObraLivro extends Obra{
    private String autor[];
    private String ISBN;
    private int qtdAutor;

    public String[] getAutor() {
        return autor;
    }

    public void setAutor(String[] autor) {
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
            this.ISBN = ISBN;
    }
    public int getQtdAutor() {
        return qtdAutor;
    }

    public void setQtdAutor(int qtdAutor) {
        this.qtdAutor = qtdAutor;
    }
    
}
