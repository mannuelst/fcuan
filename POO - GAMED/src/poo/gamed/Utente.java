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
public class Utente extends Object{
    private String nomeUtente;
    private String emailUtente;
    private int codigoUtente;
    private boolean estado;
    private boolean multa;
    private int totRequisicoes;
    private String data_registro;
    
    public Utente() {
        this.totRequisicoes = 0;
    }

    public Utente(String nome, String email,int codigo,boolean estado,boolean multa,String data,int tot3){
        this.estado=estado;
        this.multa=multa;
        this.nomeUtente=nome;
        this.emailUtente=email;
        this.codigoUtente=codigo;
        this.totRequisicoes = tot3;
        this.data_registro = data; 
    }

    public void setData_registro(String data_registro) {
        this.data_registro = data_registro;
    }
    
    public String getData_registro() {
       return this.data_registro;
    }
    
    public Utente retUtente(Utente utente){
        return new Utente(utente.getNomeUtente(),utente.getEmailUtente(),utente.getCodigoUtente(),utente.estado,utente.multa,utente.getData_registro(),utente.getTotRequisicoes());
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getEmailUtente() {
        return emailUtente;
    }

    public void setEmailUtente(String emailUtente) {
        if(!emailUtente.contains("@mymail.com")) this.emailUtente = emailUtente + "@mymail.com";
        else this.emailUtente = emailUtente;
    }

    public int getCodigoUtente() {
        return codigoUtente;
    }

    public void setCodigoUtente(int codigoUtente) {
        this.codigoUtente = codigoUtente;
    }

    public String getEstado() {
        if(estado == true && multa != true && totRequisicoes < 3) return "ACTIVO";
        return "SUSPENSO";
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMulta() {
        if(multa==true) return "MULTADO";
        return "LIVRE";
    }

    public void setMulta(boolean multa) {
        this.multa = multa;
    }

    public int getTotRequisicoes() {
        return totRequisicoes;
    }
    
    public void setTotRequisicoes(int tot) {
        this.totRequisicoes +=tot;
    }
    
    
    
}


