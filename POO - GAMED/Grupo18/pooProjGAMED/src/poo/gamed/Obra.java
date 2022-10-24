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
public class Obra {
    private String tituloObra;
    private String tipoObra;
    private double precoObra;
    private String idObra;
    private int qtdEx;//Qnt Exemplares
    private int qntExFREE;//Qnt Exemplares(Obras) Disponível
    private String catObra;
    private String dataRegObra;

    public String getTituloObra() {
        return tituloObra;
    }

    public void setTituloObra(String tituloObra) {
        this.tituloObra = tituloObra;
    }

    public String getTipoObra() {
        return tipoObra;
    }

    public void setTipoObra(String tipoObra) {
        this.tipoObra = tipoObra;
    }

    public double getPrecoObra() {
        return precoObra;
    }

    public void setPrecoObra(double precoObra) {
        this.precoObra = precoObra;
    }

    public String getIdObra() {
        return idObra;
    }

    public void setIdObra(String idObra) {
        this.idObra = idObra;
    }

    public int getQtdEx() {
        return qtdEx;
    }

    public void setQtdEx(int qtdEx) {
        this.qtdEx = qtdEx;
    }

    public int getQntExFREE() {
        return qntExFREE;
    }

    public void setQntExFREE(int qntEx) {
        this.qntExFREE += qntEx;
    }

    public String getCatObra() {
        return catObra;
    }

    public void setCatObra(String catObra) {
        this.catObra = catObra;
    }

    public String getDataRegObra() {
        return dataRegObra;
    }

    public void setDataRegObra(String dataRegObra) {
        this.dataRegObra = dataRegObra;
    }
}
