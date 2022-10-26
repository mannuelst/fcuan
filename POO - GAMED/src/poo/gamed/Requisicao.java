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
public class Requisicao {
    private String dataReq;
    private String dataDev;
    private Obra obraReq;
    private Utente utenteReq;

    public String getDataReq() {
        return dataReq;
    }

    public void setDataReq(String dataReq) {
        this.dataReq = dataReq;
    }

    public String getDataDev() {
        return dataDev;
    }

    public void setDataDev(String dataDev) {
        this.dataDev = dataDev;
    }

    public Obra getObraReq() {
        return obraReq;
    }

    public void setObraReq(Obra obraReq) {
        this.obraReq = obraReq;
    }

    public Utente getUtenteReq() {
        return utenteReq;
    }

    public void setUtenteReq(Utente utenteReq) {
        this.utenteReq = utenteReq;
    }
    public Requisicao() {}
      
}
