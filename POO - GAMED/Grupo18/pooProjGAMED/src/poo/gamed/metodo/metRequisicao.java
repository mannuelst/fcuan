/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.gamed.metodo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import poo.gamed.*;
import poo.gamed.exception.*;
import static poo.gamed.metodo.metFile.*;

import static poo.gamed.metodo.metFile.newSaveAs;
import static poo.gamed.metodo.metObra.requestWorkId;
import static poo.gamed.metodo.metObra.buscarObra;
import static poo.gamed.metodo.metObra.requestUserId;
import static poo.gamed.metodo.metUtente.buscarUtente;
import static poo.gamed.metodo.metUtente.idUtente;


/**
 *
 * @author Dzign
 */
public class metRequisicao {
    
    

    
    final String fileUtente = "C:\\Users\\Dzign\\Desktop\\Grupo18\\pooProjGAMED\\src\\poo\\gamed\\gameDB\\utenteDB.txt";
    final String fileObra = "C:\\Users\\Dzign\\Desktop\\Grupo18\\pooProjGAMED\\src\\poo\\gamed\\gameDB\\obraDB.txt";
    final String fileRequisicao = "C:\\Users\\Dzign\\Desktop\\Grupo18\\pooProjGAMED\\src\\poo\\gamed\\gameDB\\requisicaoDB.txt";

     public void devolucao(ArrayList<Requisicao> lista,ArrayList<Obra> listaObra,ArrayList<Utente> listaUtente) throws IOException{
        Requisicao requisicao = null;
        Utente utReq;
        Obra obra;
        
        int idUtente = metObra.requestUserId();
        utReq = buscarUtente(idUtente,listaUtente);
        if(utReq==null){
            try{
                throw new NoSuchUserException();
            } catch (NoSuchUserException ex) {
                System.err.println(ex.exSMS);
            }
        }else{
            String idObr = requestWorkId();
            obra = buscarObra(listaObra,idObr);
            if(obra==null){
                try{
                    throw new NoSuchWorkException();
                }catch(NoSuchWorkException ex){
                    System.out.println(ex.exSMS);
                }
            }else{
                int i=0;
                while(i < lista.size()){
                    if(lista.get(i).getObraReq().equals(obra)&&lista.get(i).getUtenteReq().equals(utReq)){
                        requisicao = lista.get(i);
                        break;
                    }
                    i++;
                }
                if(requisicao==null){
                    try{
                        throw new WorkNotBorrowedByUserException();
                    } catch (WorkNotBorrowedByUserException ex) {
                        System.err.println(ex.exSMS);
                    }
                }else{
                    utReq.setQntReqUtente(-1);
                    if( utReq.getMultaUtente().equals("MULTADO")) utReq.setStatusUtente(false);
                    else  utReq.setStatusUtente(true);
                    obra.setQntExFREE(1);
                    Requisicao requi = lista.remove(lista.indexOf(requisicao));
                    i=0;
                    File file = new File(fileRequisicao);
                    if(file.delete()){
                        file.createNewFile();
                        if(!file.exists()) {
                            file = new File(newSaveAs());
                            file.createNewFile();
                        }
                    }
                    try(BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))){
                        while(i<lista.size()){
                            escritor.write(lista.get(i).getDataReq()+
                            ";"+lista.get(i).getObraReq().getIdObra()
                            +";"+lista.get(i).getUtenteReq().getIdUtente()
                            +";"+lista.get(i).getDataDev()+";");
                            escritor.newLine();
                            i++;
                        }
                        escritor.close();
                        if(requi.getUtenteReq().getMultaUtente().equals("MULTADO"))
                            System.out.println("Obra devolvida com sucesso...\n"
                            + "Não fez a devolução dentro do prazo...\n"
                            + "Precisa pagar a multa para fazer outras requisicoes!!!");
                        else
                            System.out.println("Obra devolvida com sucesso!!!");
                    }
                    file = new File(fileUtente);
                    if(file.delete()){
                        file.createNewFile();
                        if(!file.exists()) {
                            file = new File(newSaveAs());
                            file.createNewFile();
                        }
                    }
                    try(BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))){
                        for(Utente utente : listaUtente){
                            escritor.write(utente.getNomeUtente()+";"+utente.getEmailUtente()+
                            ";"+utente.getIdUtente()+";"+utente.getStatusUtente()+";"+utente.getMultaUtente()+
                            ";"+utente.getQntReqUtente()+";"+utente.getDataReg()+";");
                            escritor.newLine();
                        }
                        escritor.close();
                    }
                    file = new File(fileObra);
                    if(file.delete()){
                        file.createNewFile();
                        if(!file.exists()) {
                            file = new File(newSaveAs());
                            file.createNewFile();
                        }
                    }
                    try(BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))){
                        for(Obra obra1 : listaObra){
                            if(obra1 instanceof ObraDVD){
                                escritor.write(obra1.getTipoObra()+";"+obra1.getIdObra()+
                                ";"+obra1.getTituloObra()+";"+((ObraDVD) obra1).getRealizador()+
                                ";"+(int)obra1.getPrecoObra()+";"+obra1.getCatObra()+";"+
                                ((ObraDVD) obra1).getDNDAC()+";"+obra1.getQtdEx()+
                                ";"+obra1.getQntExFREE()+";"+obra1.getDataRegObra()+";");
                                escritor.newLine();
                            }
                            if(obra1 instanceof ObraLivro){
                                escritor.write(obra1.getTipoObra()+";"+obra1.getIdObra()+";"+obra1.getTituloObra()+";"+
                                Arrays.toString(((ObraLivro) obra1).getAutor())+
                                ";"+(int)obra1.getPrecoObra()+";"+obra1.getCatObra()+";"+
                                ((ObraLivro) obra1).getISBN()+";"+obra1.getQtdEx()+
                                ";"+obra1.getQntExFREE()+";"+obra1.getDataRegObra()+";");
                                escritor.newLine();
                            }
                        }
                        escritor.close();
                    }
                }
            }
        }
    }
    public void addListaDeRequisicoes(ArrayList<Requisicao> lista,ArrayList<Obra> listaObra,ArrayList<Utente> listaUtente,ArrayList<Requisicao> listaDeSessao) throws IOException{
        Requisicao requisicao = new Requisicao();
        File file = new File(fileRequisicao);
        Utente requisitante;
        Obra obra;
        int idUtent = requestUserId();
        requisitante = buscarUtente(idUtente,listaUtente);
        if(requisitante==null){
            try{
                throw new NoSuchUserException();
            }catch(NoSuchUserException ex){
                System.out.println(ex.exSMS);
            }
        }else{
            if(requisitante.getStatusUtente().equalsIgnoreCase("ACTIVO")){
                if(requisitante.getQntReqUtente() < 3){
                    String idObr = requestWorkId();
                    obra = buscarObra(listaObra,idObr);
                    if(obra==null){
                        try{
                            throw new NoSuchWorkException();
                        }catch(NoSuchWorkException ex){
                            System.err.println(ex.exSMS);
                        }
                    }else if(!requisicaoJaFeita(lista,requisitante,obra)){
                        if(!obra.getCatObra().equalsIgnoreCase("OBRAS DE REFERENCIA")){
                            if(!(obra.getPrecoObra()>10000.00)){
                                if(obra.getQntExFREE()==0){
                                    System.out.println(requestReturnNotificationPreference()+"\n1 - SIM\n2 - NÃO");
                                    int op = getNum.nextInt();
                                }else{
                                    requisitante.setQntReqUtente(1);
                                    if(requisitante.getQntReqUtente()==3)requisitante.setStatusUtente(false);
                                    obra.setQntExFREE(-1);
                                    requisicao.setUtenteReq(requisitante.returnUtente(requisitante));
                                    requisicao.setObraReq(obra);
                                    requisicao.setDataReq(dataN.getDayOfMonth()+"/"+dataN.getMonthValue()+"/"+dataN.getYear());
                                    String dataDevol = dataN.getDayOfMonth()+ "/";
                                    switch (dataN.getMonthValue()){
                                        case 12:
                                            dataDevol += "1";
                                            dataDevol +=  "/" + (dataN.getYear()+1);
                                            break;
                                        default:
                                            dataDevol += (dataN.getMonthValue()+1);
                                            dataDevol +=  "/" + dataN.getYear();
                                    }
                                    requisicao.setDataDev(dataDevol);
                                    lista.add(requisicao);
                                    listaDeSessao.add(requisicao);
                                    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))) {
                                        escritor.write(requisicao.getDataReq()+
                                        ";"+requisicao.getObraReq().getIdObra()
                                        +";"+requisicao.getUtenteReq().getIdUtente()
                                        +";"+requisicao.getDataDev()+";");
                                        escritor.newLine();
                                        escritor.close();
                                    }
                                    file = new File(fileUtente);
                                    if(file.delete()){
                                        file.createNewFile();
                                        if(!file.exists()) {
                                            file = new File(newSaveAs());
                                            file.createNewFile();
                                        }
                                    }
                                    try(BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))){
                                        for(Utente utente : listaUtente){
                                            escritor.write(utente.getNomeUtente()+";"+utente.getEmailUtente()+
                                            ";"+utente.getIdUtente()+";"+utente.getStatusUtente()+";"+utente.getMultaUtente()+
                                            ";"+utente.getQntReqUtente()+";"+utente.getDataReg()+";");
                                            escritor.newLine();
                                        }
                                        escritor.close();
                                    }
                                    file = new File(fileObra);
                                    if(file.delete()){
                                        file.createNewFile();
                                        if(!file.exists()) {
                                            file = new File(newSaveAs());
                                            file.createNewFile();
                                        }
                                    }
                                    try(BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))){
                                        for(Obra obra1 : listaObra){
                                            if(obra1 instanceof ObraDVD){
                                                escritor.write(obra1.getTipoObra()+";"+obra1.getIdObra()+
                                                ";"+obra1.getTituloObra()+";"+((ObraDVD) obra1).getRealizador()+
                                                ";"+(int)obra1.getPrecoObra()+";"+obra1.getCatObra()+";"+
                                                ((ObraDVD) obra1).getDNDAC()+";"+obra1.getQtdEx()+
                                                ";"+obra1.getQntExFREE()+";"+obra1.getDataRegObra()+";");
                                                escritor.newLine();
                                            }
                                            if(obra1 instanceof ObraLivro){
                                                escritor.write(obra1.getTipoObra()+";"+obra1.getIdObra()+";"+obra1.getTituloObra()+";"+
                                                Arrays.toString(((ObraLivro) obra1).getAutor())+
                                                ";"+(int)obra1.getPrecoObra()+";"+obra1.getCatObra()+";"+
                                                ((ObraLivro) obra1).getISBN()+";"+obra1.getQtdEx()+
                                                ";"+obra1.getQntExFREE()+";"+obra1.getDataRegObra()+";");
                                                escritor.newLine();
                                            }
                                        }
                                        escritor.close();
                                    }
                                    System.out.println(workReturnDay(requisicao));
                                }
                                }else{
                                try{
                                    throw new RuleFailedException();
                                }catch(RuleFailedException ex){
                                    System.out.println(ex.exSMS);
                                }
                            }
                        }else{
                            try{
                                throw new RuleFailedException();
                            }catch(RuleFailedException ex){
                                System.out.println(ex.exSMS);
                            }
                        }
                    }else{
                        try{
                            throw new RuleFailedException();
                        }catch(RuleFailedException ex){
                            System.out.println(ex.exSMS);
                        }
                    }
                }else{
                    try{
                        throw new RuleFailedException();
                    }catch(RuleFailedException ex){
                        System.err.println(ex.exSMS);
                    }
                }
            }else{
                try{
                    throw new RuleFailedException();
                }catch(RuleFailedException ex){
                    System.err.println(ex.exSMS);
                }
            }
        }
    }
    
    public String workReturnDay(Requisicao r){
        return "Vai Devolver Esta Obra Nesta Data " + r.getDataDev();
    }


    public String requestReturnNotificationPreference(){
        return "Já Não Há Cópias Disponíveis...\nDeseja Ser Notificado Caso Esta Obra Esteja Disponível?";
    }
        public boolean requisicaoJaFeita(ArrayList<Requisicao> lista,Utente requisitante,Obra obra){
        if(!lista.isEmpty()){
            int i=0;
            Requisicao requi;
            while(i<lista.size()){
                requi=lista.get(i);
                if(requi.getUtenteReq().getIdUtente()==requisitante.getIdUtente() &&
                   requi.getObraReq().getIdObra().equals(obra.getIdObra())) return true;
                i++;
            }
        }
        return false;
    }
}
