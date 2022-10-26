/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamed;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import poo.gamed.*;
import poo.gamed.exception.*;
import poo.gamed.metodo.metFile;
import poo.gamed.metodo.metObra;
import poo.gamed.metodo.metRequisicao;
import poo.gamed.metodo.metUtente;


/**
 *
 * @author Dzign
 */
public class GAMED {
    static metFile metF = new metFile();
    static metUtente metU = new metUtente();
    static metObra metO = new metObra();
    static metRequisicao metR = new metRequisicao();
    public static Scanner ler=new Scanner(System.in);
    public static int opt;

    static ArrayList <Utente> UTENTES = new ArrayList();
    static ArrayList <Obra> OBRAS = new ArrayList();
    static ArrayList <Requisicao> REQUISICOES = new ArrayList();
    static ArrayList <Utente> ULTIMOSUTENTES = new ArrayList();
    static ArrayList <Obra> ULTIMASOBRAS = new ArrayList();
    static ArrayList <Requisicao> ULTIMASREQUISICOES = new ArrayList();
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, NoSuchUserException {
        
       // metodo.start_system(REQUISICOES, OBRAS, UTENTES);
       menuPrincipal();
    }
      public static void menuPrincipal() throws IOException, NoSuchUserException{
        System.out.println("***** MENU PRINCIPAL *****");
        System.out.println("1 - Abrir\n2 - Guardar\n3 - Menu de Gestão de Utentes\n4 - Menu de Gestão de Obras\n5 - Menu de Gestão Requisições\n0 - Sair");
       do{
            
            opt = ler.nextInt();
            switch(opt){
                case 0 : 
                    System.exit(0);
                    break;
                case 1 : 
                   metF.abrir();
                    break;
                case 2 :
                    metF.guardar(ULTIMOSUTENTES,ULTIMASOBRAS,ULTIMASREQUISICOES);
                    break;
                case 3 :
                    subMenuUtente();
                    break;
                case 4 :
                   subMenuObras();
                    break;
                case 5 :
                    subMenuRequisicoes();
                    break;
                default:
                    System.err.println("Opcao Inválida");
                    menuPrincipal();
            }
        }while(opt!=0);
    }
     static void subMenuUtente() throws IOException, NoSuchUserException{
        
        System.out.println("****** MENU UTENTES ******");
        System.out.println("1 - Registar Utente\n2 - Mostrar Utente\n3 - Mostrar Utentes\n4 - Pagar Multa\n5 - Voltar");
        opt = ler.nextInt();
            switch(opt){
                case 1 :
                    if(metU.addUtente(UTENTES, ULTIMOSUTENTES)){
                        metU.userRegistrationSuccessful();
                    }else{
                        System.err.println("\nJá existe Utente Com O Mesmo Email");
                    }
                    break;
                case 2 :
                    metUtente.showUtente(UTENTES);
                    break;
                case 3 :
                    metUtente.showUtentes(UTENTES);
                    break;
                case 4 :
                    metU.pagarmulta(UTENTES);
                    break;
                case 5 :
                    menuPrincipal();
                    break;
                default:
                    System.err.println("Opcao Inválida");
                    subMenuUtente();
            }
    }
    
    static void subMenuObras() throws IOException, NoSuchUserException{

        System.out.println("******* MENU OBRAS *******");
        System.out.println("1 - Registar Obra\n2 - Mostrar Obra\n3 - Mostrar Obras\n4 - Efectuar Pesquisa\n5 - Voltar");
       
        
            opt= ler.nextInt();
            switch(opt){
                case 1 : 
                    if(metO.addObra(OBRAS,ULTIMASOBRAS)){
                        metO.WorkRegistrationSuccessful();
                    }else{
                        System.out.println("\nObra já existe");
                    }
                    break;
                case 2 :
                    metO.showObra(OBRAS);
                    break;
                case 3 :
                   metO.showObras(OBRAS);
                    break;
                case 4 :
                    metO.requestSearchTerm(OBRAS);
                    break;
                case 5 :
                     menuPrincipal();
                    break;
                default:
                    System.err.println("Opcao Inválida");
                    subMenuObras();
            }
    }
    
    static void subMenuRequisicoes() throws IOException, NoSuchUserException{
       
        System.out.println("**** MENU REQUISIÇÕES ****");
        System.out.println("1 - Requisitar Obra\n2 - Devolver Obra3 - Voltar\n");
             opt = ler.nextInt();
            switch(opt){
                case 1 : 
                    metR.addListaDeRequisicoes(REQUISICOES,OBRAS,UTENTES,ULTIMASREQUISICOES);
                    break;
                case 2 :
                    metR.devolucao(REQUISICOES, OBRAS, UTENTES);
                    break;
                case 3 : 
                    menuPrincipal();
                    break;
                default:
                    System.err.println("Opcao Inválida");
                    subMenuRequisicoes();
            }
    }
 
}
