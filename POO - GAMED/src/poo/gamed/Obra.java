/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.gamed;

/**
 *
 * @author FRIDSON FIRMINO
 */
public class Obra extends Object{
    private String titulo;
    private String tipo;
    private double preco;
    private String idObra;
    private int qtdExemplar;
    private int exemplaresDispo;
    private String categoria;
    private String data_registro;

    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public void setIdObra(String id) {
        this.idObra = id;
    }
    
    public String getIdObra() {
        return this.idObra;
    }

    public int getQtdExemplar() {
        return qtdExemplar;
    }

    public void setQtdExemplar(int qtdExemplar) {
        this.qtdExemplar = qtdExemplar;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getExemplaresDispo() {
        return exemplaresDispo;
    }

    public void setExemplaresDispo(int qtdExemplar) {
        this.exemplaresDispo += qtdExemplar;
    }
    
    public void setData_registro(String data_registro) {
        this.data_registro = data_registro;
    }
    
    public String getData_registro() {
        return this.data_registro;
    }
    
    
    
}
