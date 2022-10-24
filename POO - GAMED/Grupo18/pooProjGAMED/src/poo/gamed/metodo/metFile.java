/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.gamed.metodo;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import poo.gamed.*;
import poo.gamed.exception.*;

import static poo.gamed.metodo.metObra.buscarObra;
import static poo.gamed.metodo.metObra.idObra;
import static poo.gamed.metodo.metUtente.buscarUtente;
import static poo.gamed.metodo.metUtente.idUtente;

/**
 *
 * @author Dzign
 * Metodos Usados Para Maniular Ficheiros!!!
 */
public class metFile {
    
    static Scanner getOther = new Scanner(System.in);
    static Scanner getNum = new Scanner(System.in);
    static LocalDate dataN = LocalDate.now();

    
    final String fileUtente = "C:\\Users\\Dzign\\Desktop\\Grupo18\\pooProjGAMED\\src\\poo\\gamed\\gameDB\\utenteDB.txt";
    final String fileObra = "C:\\Users\\Dzign\\Desktop\\Grupo18\\pooProjGAMED\\src\\poo\\gamed\\gameDB\\obraDB.txt";
    final String fileRequisicao = "C:\\Users\\Dzign\\Desktop\\Grupo18\\pooProjGAMED\\src\\poo\\gamed\\gameDB\\requisicaoDB.txt";
    
    public static Scanner ler = new Scanner(System.in);
    static Scanner lerNum = new Scanner(System.in);
    static LocalDate data_ = LocalDate.now();
    //static int idUtente = 0;
    //static int idObra = 0;
    
 
   
    final String futUS = "C:\\Users\\Dzign\\Desktop\\Grupo18\\pooProjGAMED\\src\\poo\\gamed\\gameDB\\ultimasessao\\utenteUS.txt";
    final String fobUS = "C:\\Users\\Dzign\\Desktop\\Grupo18\\pooProjGAMED\\src\\poo\\gamed\\gameDB\\ultimasessao\\obraUS.txt";
    final String freqUS = "C:\\Users\\Dzign\\Desktop\\Grupo18\\pooProjGAMED\\src\\poo\\gamed\\gameDB\\ultimasessaorequisicaoUS.txt";
    
    
     @SuppressWarnings("UnusedAssignment")
    public void start_system(ArrayList<Requisicao> lista,ArrayList<Obra> listaObra,ArrayList<Utente> listaUtente) throws IOException{
        File file = new File(fileUtente);
        try (BufferedReader leitor = new BufferedReader(new FileReader(file))) {
            String vector[] = new String[7],linha = leitor.readLine();
            while(linha != null){
                Utente u = new Utente();
                vector = linha.split(";");
                u.setNomeUtente(vector[0]);
                u.setCodigoUtente(atoi(vector[2]));
                u.setEmailUtente(vector[1]);
                if(vector[3].equals("ACTIVO")) u.setStatusUtente(true);
                else if(vector[3].equals("SUSPENSO")) u.setStatusUtente(false);
                if(vector[4].equals("LIVRE")) u.setMultaUtente(false);
                else if(vector[4].equals("MULTADO")) u.setMultaUtente(true);
                u.setQntReqUtente(atoi(vector[5]));
                u.setDataReg(vector[6]);
                listaUtente.add(u);
                ++idUtente;
                linha = leitor.readLine();
            }
            leitor.close();
        }
        file = new File(fileObra);
        try (BufferedReader leitor = new BufferedReader(new FileReader(file))) {
            String super_vector[] = new String[10],linha = leitor.readLine();
            while(linha != null){
                Obra obra;
                super_vector = linha.split(";");
                if(super_vector[0].equals("LIVRO")){
                    obra = new ObraLivro();
                    obra.setTipoObra(super_vector[0]);
                    obra.setIdObra(super_vector[1]);
                    obra.setTituloObra(super_vector[2]);
                    String str="",vectorautor[];
                    int i=0,j;
                    while(i<super_vector[3].length()){
                        if(super_vector[3].charAt(i)=='[' || super_vector[3].charAt(i)==']') i++;
                        else str += super_vector[3].charAt(i++);
                    }
                    str += ',';
                    i=j=0;
                    while(i<str.length()){
                        if(str.charAt(i)==',') j++;
                        i++;
                    }
                    vectorautor = new String[j];
                    vectorautor = str.split(",");
                    ((ObraLivro)obra).setQtdAutor(vectorautor.length);
                    ((ObraLivro)obra).setAutor(vectorautor);
                    obra.setPrecoObra(atoi(super_vector[4]));
                    obra.setCatObra(super_vector[5]);
                    ((ObraLivro)obra).setISBN(super_vector[6]);
                    obra.setQtdEx(atoi(super_vector[7]));
                    obra.setQntExFREE(atoi(super_vector[8]));
                    obra.setDataRegObra(super_vector[9]);
                    listaObra.add(obra);
                }
                if(super_vector[0].equals("DVD")){
                    obra = new ObraDVD();
                    obra.setTipoObra(super_vector[0]);
                    obra.setIdObra(super_vector[1]);
                    obra.setTituloObra(super_vector[2]);
                    ((ObraDVD)obra).setRealizador(super_vector[3]);
                    obra.setPrecoObra(atoi(super_vector[4]));
                    obra.setCatObra(super_vector[5]);
                    ((ObraDVD)obra).setDNDAC(super_vector[6]);
                    obra.setQtdEx(atoi(super_vector[7]));
                    obra.setQntExFREE(atoi(super_vector[8]));
                    obra.setDataRegObra(super_vector[9]);
                    listaObra.add(obra);
                }
                ++idObra;
                linha = leitor.readLine();
            }
            leitor.close();
        }
        file = new File(fileRequisicao);
        try (BufferedReader leitor = new BufferedReader(new FileReader(file))) {
            String vector[] = new String[4],linha = leitor.readLine();
            while(linha != null){
                Requisicao requi = new Requisicao();
                vector = linha.split(";");
                requi.setUtenteReq(buscarUtente(atoi(vector[2]),listaUtente));
                if(requi.getUtenteReq()==null){
                    try{
                        throw new NoSuchUserException();
                    } catch (NoSuchUserException ex) {
                        System.err.println(ex.exSMS);
                    }
                }
                requi.setObraReq(buscarObra(listaObra,vector[1]));
                if(requi.getObraReq()==null){
                    try{
                        throw new NoSuchWorkException();
                    }catch(NoSuchWorkException ex){
                        System.out.println(ex.exSMS);
                    }
                }
                requi.setDataReq(vector[0]);
                requi.setDataDev(vector[3]);
                int i=0;
                String mes="",dia="",ano="";
                while(i<vector[3].length()){
                    if(vector[3].charAt(i)=='/'){
                        i++;
                        while(vector[3].charAt(i)!='/'){
                            mes += vector[3].charAt(i);
                            i++;
                        }
                        i++;
                        while(i<vector[3].length()){
                            ano += vector[3].charAt(i);
                            i++;
                        }
                        break;
                    }
                    dia += vector[3].charAt(i);
                    i++;
                }
                if(data_.getMonthValue()==atoi(mes) && data_.getDayOfMonth()>atoi(dia)) requi.getUtenteReq().setMultaUtente(true);
                else if(data_.getMonthValue()>atoi(mes)) requi.getUtenteReq().setMultaUtente(true);
                else if(data_.getMonthValue()>atoi(mes)  && data_.getYear()>atoi(ano)) requi.getUtenteReq().setMultaUtente(true);
                else if(data_.getMonthValue()<atoi(mes) && data_.getYear()>atoi(ano) && data_.getDayOfMonth()>atoi(dia)) requi.getUtenteReq().setMultaUtente(true);
                lista.add(requi);
                ++idUtente;
                linha = leitor.readLine();
            }
            leitor.close();
        }
    }
    
      public String openFile(){
        System.out.println("Qual dos Ficheiros Deseja Abrir?\nU - UTENTES");
        System.out.println("O - OBRAS");
        System.out.println("R - REQUISIÇOES");
        String opcao = ler.next();
        switch(opcao.charAt(0)){
            case 'u':
            case 'U':return futUS;
            case 'o':
            case 'O':return fobUS;
            case 'r':
            case 'R':return freqUS;
        }
        return "";
    }
    
    public void abrir() throws FileNotFoundException, IOException{
        String fileName = openFile();
        File file;
        file = new File(fileName);
        if(fileName.equals("")){
            try{
                throw new FileOpenFailedException();
            }catch(FileOpenFailedException e){
                System.err.println(e.exSMS);
            }
        }else{
            FileReader fr = new FileReader(file);
            if(fileName.equals(futUS)){
                try (BufferedReader leitor = new BufferedReader(fr)) {
                    String vector[] = new String[7],linha = leitor.readLine();
                    if(linha==null) System.out.println("Sem Utentes Registrados Na Última Sessão");
                    else{
                        System.out.println("Lista de Utentes");
                        while(linha != null){
                            vector = linha.split(";");
                            System.out.println("ID: "+vector[2]+"Nome: "+vector[0]+
                            "Email: "+vector[1]+"Estado: "+vector[3]+"Requisições Feitas: "+vector[5]);
                            linha = leitor.readLine();
                        }
                        System.out.println("Fim da Lista");
                    }
                    leitor.close();
                    fr.close();
                }
            }
            if(fileName.equals(fobUS)){
                try (BufferedReader leitor = new BufferedReader(fr)) {
                    String vector[] = new String[10],linha = leitor.readLine();
                    if(linha==null) System.out.println("Sem Obras Registradas Na última Sessão");
                    else{
                        System.out.println("Lista de Obras");
                        while(linha != null){
                            vector = linha.split(";");
                            if(vector[0].equals("LIVRO"))
                                System.out.println("Tipo: " + vector[0] + " Título: "
                                + vector[2] + "ID: " + vector[1] + 
                                "Categoria: " + vector[5] + "ISBN"+ vector[6] + "Preço: " + vector[4] 
                                + "Total de Exemplares: " + vector[7] + "Exemplares Disponíveis: " +
                                vector[8]);
                            else if(vector[0].equals("DVD"))
                                System.out.println("Tipo: " + vector[0] + " Título: "
                                + vector[2] + "ID: " + vector[1] + 
                                "Categoria: " + vector[5] + "DNDAC"+ vector[6] + "Preço: " + vector[4] 
                                + "Total de Exemplares: " + vector[7] + "Exemplares Disponíveis: " +
                                vector[8]);
                            linha = leitor.readLine();
                        }
                        System.out.println("Fim da Lista");
                    }
                    leitor.close();
                    fr.close();
                }
            }
            if(fileName.equals(freqUS)){
                try (BufferedReader leitor = new BufferedReader(fr)) {
                    String vector[] = new String[4],linha = leitor.readLine();
                    if(linha==null) System.out.println("Sem Requisições Feitas Na Última Sessão");
                    else{
                        System.out.println("Lista de requisições");
                        while(linha != null){
                            vector = linha.split(";");
                            System.out.println("ID do Requisitante: "+vector[2]+"ID da Obra"+vector[1]+
                            "Data de Requisição: "+vector[0]+"Data de Devolução:"+vector[3]);
                            linha = leitor.readLine();
                        }
                        System.out.println("Fim da Lista");
                    }
                    leitor.close();
                    fr.close();
                }
            }
        }
    }
    
    public static String newSaveAs(){
        System.out.print("Ficheiro Não Encontrado\nDigite O Nome do Ficheiro: ");
        String ficheiro = ler.next();
        return ficheiro;
    }
    
    public void guardar(ArrayList<Utente> lu,ArrayList<Obra> lo,ArrayList<Requisicao> lr) throws IOException{
        System.out.println("DESEJA GUARDAR TODOS OS DADOS DESTA SESSÃO?");
        System.out.println("1 - Sim\n2 - Não");
        int resposta = lerNum.nextInt();
        switch(resposta){
            case 1:{
                if(guardarDadosUtentes(lu))System.out.println("Dados de Utentes Guardados Com Sucesso!!!");
                else System.out.println("Sem Utentes registrados Nesta Sessão!!!");
                if(guardarDadosObras(lo)) System.out.println("Dados de Obras Guardados Com Sucesso!!!");
                else System.out.println("Sem Obras registradas Nesta Sessão!!!");
                if(guardarRequisicoes(lr))System.out.println("Dados de Requisicoes Guardados Com Sucesso!!!");
                else System.out.println("Sem Requisições Feitas Nesta Sessão!!!");
            }
            break;
            case 2:
                System.out.println("Os Dados Não Foram Não Guardados");
                break;
            default: System.out.println("Opção Errada\nOs Dados Não Foram Não Guardados");
        }
    }
    
    public boolean guardarDadosUtentes(ArrayList<Utente> lu) throws IOException{
        File futente = new File(futUS);
        if(futente.delete()){
            futente.createNewFile();
            if(!futente.exists()) {
                futente = new File(newSaveAs());
                futente.createNewFile();
            }
        }
        if (lu.isEmpty())  return false;
        else{
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(futUS,true))) {
                for (Utente utente : lu)
                {
                escritor.write(utente.getNomeUtente()+";"+utente.getEmailUtente()+
                ";"+utente.getIdUtente()+";"+utente.getStatusUtente()+";"+utente.getMultaUtente()+
                ";"+utente.getQntReqUtente()+";"+utente.getDataReg()+";");
                escritor.newLine();
                }
                escritor.close();
            }
            return true;
        }
    }
    
    public boolean guardarRequisicoes(ArrayList<Requisicao> lr) throws IOException{
        File requi = new File(freqUS);
        if(requi.delete()){
            requi.createNewFile();
            if(!requi.exists()) {
                requi = new File(newSaveAs());
                requi.createNewFile();
            }
        }
        if (lr.isEmpty())  return false;
        else{
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(requi,true))) {
                for(Requisicao requisicao : lr)
                {
                    escritor.write(requisicao.getDataReq()+
                    ";"+requisicao.getObraReq().getIdObra()
                    +";"+requisicao.getUtenteReq().getIdUtente()
                    +";"+requisicao.getDataDev()+";");
                    escritor.newLine();
                }
                escritor.close();
            }
            return true;
        }
    }
    
    public boolean guardarDadosObras(ArrayList<Obra> lo) throws IOException{
        File Obra = new File(fobUS);
        if(Obra.delete()){
            Obra.createNewFile();
            if(!Obra.exists()) {
                Obra = new File(newSaveAs());
                Obra.createNewFile();
            }
        }
        if(lo.isEmpty()) return false;
        else{
            for (Obra obra : lo) {
                if(obra instanceof ObraDVD){
                    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(fobUS,true))) {
                        escritor.write(obra.getTipoObra()+";"+obra.getIdObra()+";"+obra.getTituloObra()+";"+
                        ((ObraDVD) obra).getRealizador()+
                        ";"+obra.getPrecoObra()+";"+obra.getCatObra()+";"+
                        ((ObraDVD) obra).getDNDAC()+";"+obra.getQtdEx()+
                        ";"+obra.getQntExFREE()+";"+obra.getDataRegObra()+";");
                        escritor.newLine();
                        escritor.close();
                    }
                }
                if(obra instanceof ObraLivro){
                    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(fobUS,true))) {
                        escritor.write(obra.getTipoObra()+";"+obra.getIdObra()+";"+obra.getTituloObra()+";"+
                        Arrays.toString(((ObraLivro) obra).getAutor())+
                        ";"+obra.getPrecoObra()+";"+obra.getCatObra()+";"+
                        ((ObraLivro) obra).getISBN()+";"+obra.getQtdEx()+
                        ";"+obra.getQntExFREE()+";"+obra.getDataRegObra()+";");
                        escritor.newLine();
                        escritor.close();
                    }
                }
            }
            return true;
        }
    }
  public boolean isNumeric(String str){
        char vector [] = str.toCharArray();
        int i=0;
        while(i<str.length()){
            if(vector[i]<48 || vector[i]>57) return false;
            i++;
        }
        return true;
    }
    
    public int atoi(String str){
        if(!isNumeric(str)) return -1;
        char vector [] = str.toCharArray();
        int inteiro=0,i,base,intercambio;
        i=base=1;
        while(i<str.length()){
            base*=10;
            i++;
        }
        i=0;
        while(i<str.length()){
            switch(vector[i]){
                case 48:{
                    if(inteiro%10==0)base/=10;
                    else{
                        inteiro*=10;
                        base/=10;  
                    }
                    break;
                }
                case 49:{
                    intercambio=1*base;
                    base/=10;
                    inteiro+=intercambio;
                    break;
                }
                case 50:{
                    intercambio=2*base;
                    base/=10;
                    inteiro+=intercambio;
                    break;
                }
                case 51:{
                    intercambio=3*base;
                    base/=10;
                    inteiro+=intercambio;
                    break;
                }
                case 52:{
                    intercambio=4*base;
                    base/=10;
                    inteiro+=intercambio;
                    break;
                }
                case 53:{
                    intercambio=5*base;
                    base/=10;
                    inteiro+=intercambio;
                    break;
                }
                case 54:{
                    intercambio=6*base;
                    base/=10;
                    inteiro+=intercambio;
                    break;
                }
                case 55:{
                    intercambio=7*base;
                    base/=10;
                    inteiro+=intercambio;
                    break;
                }
                case 56:{
                    intercambio=8*base;
                    base/=10;
                    inteiro+=intercambio;
                    break;
                }
                case 57:{
                    intercambio=9*base;
                    base/=10;
                    inteiro+=intercambio;
                    break;
                }
            }
            i++;
        }
        return inteiro;
    }
    
}
