/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import poo.gamed.repositorio.Metodos;
import poo.gamed.*;
import poo.gamed.exception.NoSuchUserException;
/**
 *
 * @author FRIDSON FIRMINO
 */
public class Main {
    static Scanner tecl = new Scanner(System.in);
    static Metodos metodo = new Metodos();
    static ArrayList <Utente> UTENTES = new ArrayList();
    static ArrayList <Obra> OBRAS = new ArrayList();
    static ArrayList <Requisicoes> REQUISICOES = new ArrayList();
    static ArrayList <Utente> ULTIMOSUTENTES = new ArrayList();
    static ArrayList <Obra> ULTIMASOBRAS = new ArrayList();
    static ArrayList <Requisicoes> ULTIMASREQUISICOES = new ArrayList();
    
    public static void MenuPrincipal(){
        System.out.println("\n");
        System.out.println("**************************");
        System.out.println("***** MENU PRINCIPAL *****");
        System.out.println("**************************\n");
        System.out.println("1 - Abrir");
        System.out.println("2 - Guardar");
        System.out.println("3 - Menu de Gestão de Utentes");
        System.out.println("4 - Menu de Gestão de Obras");
        System.out.println("5 - Menu de Gestão Requisições");
        System.out.println("0 - Sair");
    }
    
    static void subMenuUtente() throws IOException, NoSuchUserException{
        System.out.println("\n");
        System.out.println("**************************");
        System.out.println("****** MENU UTENTES ******");
        System.out.println("**************************\n");
        System.out.println("1 - Registar Utente");
        System.out.println("2 - Mostrar Utente");
        System.out.println("3 - Mostrar Utentes");
        System.out.println("4 - Pagar Multa");
        System.out.println("5 - Voltar");
        
        int opcao = tecl.nextInt();
            switch(opcao){
                case 1 :
                    if(metodo.addUtente(UTENTES, ULTIMOSUTENTES)){
                        metodo.userRegistrationSuccessful();
                    }else{
                        System.out.println("\nJá existe Utente Com O Mesmo Email");
                    }
                    break;
                case 2 :
                    metodo.showUtente(UTENTES);
                    break;
                case 3 :
                    metodo.showUtentes(UTENTES);
                    break;
                case 4 :
                    metodo.pagarmulta(UTENTES);
                    break;
                case 5 :
                    break;
                default:
                    System.out.println("Opcao Inválida");
            }
    }
    
    static void subMenuObras() throws IOException{
        System.out.println("\n");
        System.out.println("**************************");
        System.out.println("******* MENU OBRAS *******");
        System.out.println("**************************\n");
        System.out.println("1 - Registar Obra");
        System.out.println("2 - Mostrar Obra");
        System.out.println("3 - Mostrar Obras");
        System.out.println("4 - Efectuar Pesquisa");
        System.out.println("5 - Voltar");
        
            int opcao = tecl.nextInt();
            switch(opcao){
                case 1 : 
                    if(metodo.addObra(OBRAS,ULTIMASOBRAS)){
                        metodo.WorkRegistrationSuccessful();
                    }else{
                        System.out.println("\nObra já existe");
                    }
                    break;
                case 2 :
                    metodo.showObra(OBRAS);
                    break;
                case 3 :
                    metodo.showObras(OBRAS);
                    break;
                case 4 :
                    metodo.requestSearchTerm(OBRAS);
                    break;
                case 5 :
                    break;
                default:
                    System.out.println("Opcao Inválida");
            }
    }
    
    static void subMenuRequisicoes() throws IOException{
        System.out.println("\n");
        System.out.println("**************************");
        System.out.println("**** MENU REQUISIÇÕES ****");
        System.out.println("**************************\n");
        System.out.println("1 - Requisitar Obra");
        System.out.println("2 - Devolver Obra");
        System.out.println("3 - Voltar");
        
            int opcao = tecl.nextInt();
            switch(opcao){
                case 1 : 
                    metodo.addListaDeRequisicoes(REQUISICOES,OBRAS,UTENTES,ULTIMASREQUISICOES);
                    break;
                case 2 :
                    metodo.devolucao(REQUISICOES, OBRAS, UTENTES);
                    break;
                case 3 : 
                    break;
                default:
                    System.out.println("Opcao Inválida");
            }
    }
    
    public static void main(String [] args) throws IOException, NoSuchUserException{
        boolean ok = true;
        metodo.start_system(REQUISICOES, OBRAS, UTENTES);
        do{
            MenuPrincipal();
            int opcao = tecl.nextInt();
            switch(opcao){
                case 0 : 
                    ok = false;
                    break;
                case 1 : 
                    metodo.abrir();
                    break;
                case 2 :
                    metodo.guardar(ULTIMOSUTENTES,ULTIMASOBRAS,ULTIMASREQUISICOES);
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
                    System.out.println("Opcao Inválida");
            }
        }while(ok);
        
    }
    
}
