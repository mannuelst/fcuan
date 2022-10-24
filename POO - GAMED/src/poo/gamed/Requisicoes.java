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
public class Requisicoes {
    private String data_requisicao;
    private String data_devolucao;
    private Obra obraRequisitada;
    private Utente requisitante;

    public Requisicoes() {}

    public void setData_requisicao(String data_requisicao) {
        this.data_requisicao = data_requisicao;
    }

    public void setData_devolucao(String data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
    
    public String getData_requisicao() {
       
       return this.data_requisicao;
    }

    public String getData_devolucao() {
        return data_devolucao;
    }

    public Obra getObraRequisitada() {
        return obraRequisitada;
    }

    public void setObraRequisitada(Obra obraRequisitada) {
        this.obraRequisitada = obraRequisitada;
    }

    public Utente getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(Utente requisitante) {
        this.requisitante = requisitante;
    }
    
    
    
}
