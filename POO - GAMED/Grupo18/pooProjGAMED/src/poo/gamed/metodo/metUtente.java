/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.gamed.metodo;

import static gamed.GAMED.menuPrincipal;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import poo.gamed.Utente;
import poo.gamed.exception.NoSuchUserException;
import static poo.gamed.metodo.metFile.*;

import static poo.gamed.metodo.metObra.requestUserId;

/**
 *
 * @author Dzign
 */
public class metUtente {
   
    static int idUtente = 0;
  
    final String fileUtente = "C:\\Users\\Dzign\\Desktop\\Grupo18\\pooProjGAMED\\src\\poo\\gamed\\gameDB\\utenteDB.txt";
    
    public static void requestUserName(Utente utente){
       System.out.print("Digite o seu Nome: ");
       String nome = getOther.nextLine();
       utente.setNomeUtente(nome);
    }
   
   
    public static void requestUserEMail(Utente utente){
       System.out.print("Digite o seu E-mail exemplo(joao852): ");
       String email = getOther.nextLine();
       utente.setEmailUtente(email);
    }
   
    
    public boolean addUtente(ArrayList<Utente> lista,ArrayList<Utente> listaDeSessao) throws IOException{
        getOther.nextLine();
        Utente utente = new Utente();
        File file = new File(fileUtente);
        requestUserName(utente);
        requestUserEMail(utente);
        boolean estado = true;
        utente.setStatusUtente(estado);
        utente.setCodigoUtente(++idUtente);
        utente.setDataReg(dataN.getDayOfMonth()+"/"+dataN.getMonthValue()+"/"+dataN.getYear());
        boolean jaExiste = utenteExistente(utente,lista);
        if(jaExiste == true){
            return false;
        }else{
            lista.add(utente);
            listaDeSessao.add(utente);
             try (BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))) {
                escritor.write(utente.getNomeUtente()+";"+utente.getEmailUtente()+";"+
                utente.getIdUtente()+";"+utente.getStatusUtente()+";"+utente.getMultaUtente()+
                ";"+utente.getQntReqUtente()+";"+utente.getDataReg()+";");
                escritor.newLine();
                escritor.close();
             }
            return true;
        }
    }
   
    
    public boolean utenteExistente(Utente utente,ArrayList<Utente> lista){
       boolean existe = false; 
       for(Utente elemento:lista){
           if(utente.getEmailUtente().equals(elemento.getEmailUtente())){
               existe = true;
           }
       }
       return existe;
    }
   
    public static void showUtentes(ArrayList<Utente> lista){
        if(!lista.isEmpty()){
            System.out.println("\t===LISTA DE UTENTES REGISTADOS===");
            int i=0;
            Utente utente;
            while(i<lista.size()){
                utente = lista.get(i);
                exibirUtente(utente);
                i++;
            }
        }else{
            System.out.println("Ainda Não Foram Registrados Utentes!");
        }
    } 
   
    public static void showUtente(ArrayList<Utente> lista){
        int id = requestUserId();
        Utente utente = buscarUtente(id, lista);
        if(utente == null){
            try{
                throw new NoSuchUserException();
            }catch(NoSuchUserException ex){
                System.out.println(ex.exSMS);
            }
        }else{
            exibirUtente(utente);
        }
    }
        
    public static Utente buscarUtente(int id, ArrayList<Utente> lista){
        for(Utente elemento : lista)
            if(id == elemento.getIdUtente()) 
                return elemento;
        return null;
   }
    
    public static void exibirUtente(Utente utente){
        System.out.println(utente.getIdUtente() + " - " + utente.getNomeUtente() 
                + " - " + utente.getEmailUtente() + " - " + utente.getStatusUtente() + " - " + utente.getQntReqUtente());
    }

    public void userRegistrationSuccessful() throws IOException, NoSuchUserException{
       System.out.println("Utente Registado com sucesso!\n1- Menu Inicial\n0- Sair");
         int iopt = ler.nextInt();
            switch(iopt){
                case 1 : 
                    System.exit(0);
                    break;
                case 2 :
                     menuPrincipal();
                    break;
                default:
                    System.err.println("Opcao Inválida");
                    userRegistrationSuccessful();
            }
       
    }
    
    public void pagarmulta(ArrayList<Utente> lista) throws NoSuchUserException,IOException{
        int ID = requestUserId();
        Utente utente = buscarUtente(ID,lista);
        if(utente == null){
            try{
                throw new NoSuchUserException();
            }catch(NoSuchUserException ex){
                System.err.println(ex.exSMS);
            }
        }else{
            if(!utente.getMultaUtente().equals("MULTADO"))
                System.out.println("UTENTE NÃO MULTADO");
            else{
                System.out.println("Pagou a Multa?");
                System.out.println("1 - Sim\n0 - Não");
                int opcao = getNum.nextInt();
                switch(opcao){
                    case 1:{
                        utente.setMultaUtente(false);
                        File file = new File(fileUtente);
                        if(file.delete()){
                            file.createNewFile();
                            if(!file.exists()) {
                                file = new File(metFile.newSaveAs());
                                file.createNewFile();
                            }
                        }
                        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))){
                            for(Utente user : lista){
                                escritor.write(user.getNomeUtente()+";"+user.getEmailUtente()+
                                ";"+user.getIdUtente()+";"+user.getStatusUtente()+";"+user.getMultaUtente()+
                                ";"+user.getQntReqUtente()+";"+user.getDataReg()+";");
                                escritor.newLine();
                            }
                            escritor.close();
                        }
                        break;
                    }
                }
            }
        }
    }   
}
