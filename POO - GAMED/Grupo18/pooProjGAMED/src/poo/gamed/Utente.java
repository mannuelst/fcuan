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
public class Utente extends Object {
    private String nomeUtente;
    private String emailUtente;
    private int idUtente;
    private boolean statusUtente;
    private boolean multaUtente;
    private int qntReqUtente;
    private String dataReg;
    
     public Utente() {//Por Padrão o Utente inicia com uma quantidade de Requisição ZERO!!!
        this.qntReqUtente = 0;
    }
    public Utente(String nome, String email,int id,boolean estado,boolean multa,String data,int iQntReq){
        this.statusUtente=estado;
        this.multaUtente=multa;
        this.nomeUtente=nome;
        this.emailUtente=email;
        this.idUtente=id;
        this.qntReqUtente = iQntReq;
        this.dataReg = data; 
    }
    public void setDataReg(String dataR) {
        this.dataReg = dataR;
    }
    
    public String getDataReg() {
       return this.dataReg;
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
        if(!emailUtente.contains("@mymail.com"))this.emailUtente = emailUtente + "@mymail.com";
        else this.emailUtente = emailUtente;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setCodigoUtente(int codigoUtente) {
        this.idUtente = codigoUtente;
    }

    public String getStatusUtente() {
        if(statusUtente == true && multaUtente != true && qntReqUtente < 3) return "ACTIVO";
        return "SUSPENSO";
    }

    public void setStatusUtente(boolean estado) {
        this.statusUtente = estado;
    }

    public String getMultaUtente() {
        if(multaUtente==true) return "MULTADO";
        return "LIVRE";
    }

    public void setMultaUtente(boolean multa) {
        this.multaUtente = multa;
    }

    public int getQntReqUtente() {
        return qntReqUtente;
    }
    
    public void setQntReqUtente(int iQnt) {
        this.qntReqUtente +=iQnt;
    }
    public Utente returnUtente(Utente utente){//Retorna Utente!
        return new Utente(utente.getNomeUtente(),utente.getEmailUtente(),utente.getIdUtente(),utente.statusUtente,utente.multaUtente,utente.getDataReg(),utente.getQntReqUtente());
    }
}
