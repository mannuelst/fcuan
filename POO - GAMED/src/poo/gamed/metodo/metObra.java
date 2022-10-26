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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import poo.gamed.*;
import poo.gamed.exception.NoSuchWorkException;
import static poo.gamed.metodo.metFile.*;

/**
 *
 * @author Dzign
 */
public class metObra {
   
    
   
    static int idObra = 0;
    static ObraCategoria cate = new ObraCategoria();
    final ArrayList<String> listaCategoria = cate.getCategoria();
    //final String fileUtente = "C:\\Users\\Dzign\\Desktop\\Grupo18\\pooProjGAMED\\src\\poo\\gamed\\gameDB\\utenteDB.txt";
    final String fileObra = "C:\\Users\\Dzign\\Desktop\\Grupo18\\pooProjGAMED\\src\\poo\\gamed\\gameDB\\obraDB.txt";
   // final String fileRequisicao = "C:\\Users\\Dzign\\Desktop\\Grupo18\\pooProjGAMED\\src\\poo\\gamed\\gameDB\\requisicaoDB.txt";
    
    
    public void requestTitle(Obra obra){
        System.out.print("Título: ");
        String titutlo = getOther.nextLine();
        obra.setTituloObra(titutlo);
    }
    
    public void requestPrice(Obra obra){
        System.out.print("Preço: ");
        double preco = getNum.nextInt();
        obra.setPrecoObra(preco);
    }
    
    public void requestNumCopies(Obra obra){
        System.out.print("Numero de Exemplares: ");
        int numExem = getNum.nextInt();
        obra.setQtdEx(numExem);
    }
    
    public void requestAutor(ObraLivro livro){
        System.out.print("Numero de autores: ");
        int num = getNum.nextInt();
        livro.setQtdAutor(num);
        String[] autor = new String[livro.getQtdAutor()];
        for (int i = 0; i < autor.length; i++) {
            System.out.print((i+1) + "º Autor: ");
            autor[i] = getOther.nextLine();
        }
        livro.setAutor(autor);
    }
    public void requestISBN(ObraLivro livro){
        System.out.print("ISBN: ");
        String isbn = getOther.nextLine();
        if(isbn.length()>10) livro.setISBN(isbn.substring(0,10));
        else livro.setISBN(isbn);
    }
    
    public void requestRealizador(ObraDVD dvd){
        System.out.print("Realizador: ");
        String realizador = getOther.nextLine();
        dvd.setRealizador(realizador);
    }
    
    public void requestDNDAC(ObraDVD dvd){
        System.out.print("DNDAC: ");
        String dndac = getOther.nextLine();
        if(dndac.length()>6) dvd.setDNDAC(dndac.substring(0,5));
        else dvd.setDNDAC(dndac);
    }
    
    public String requestTipo(){
        getOther.nextLine();
        System.out.println("\n*** TIPO DE OBRA ***");
        System.out.println("1 - LIVRO");
        System.out.println("2 - DVD");
            int opcao = getNum.nextInt();
            switch(opcao){
                case 1 : 
                    return "LIVRO";
                case 2 :
                    return "DVD";
                default:
                    System.out.print("Opcao Errada...\nTente outra Vez");
                    return requestTipo();
            }
    }
    
    public void listarCategoria(Obra obra){
        System.out.println("\n*** LISTA DE CATEGORIA ***");
        String categoria;
        int i=0;
        while(i<listaCategoria.size()){
            categoria = listaCategoria.get(i++);
            System.out.println(listaCategoria.indexOf(categoria) + 1 +" - "+categoria);
        }
       int opcao = getNum.nextInt();
            switch(opcao){
                case 1 : 
                    obra.setCatObra("OBRAS DE REFERENCIA");
                    break;
                case 2 :
                    obra.setCatObra("OBRAS DE FICCAO");
                    break;
                case 3 :
                    obra.setCatObra("OBRAS TECNICAS E CIENTIFICAS");
                    break;
                default:
                    System.out.println("Opcao Inválida\n");
            }
    }
    
    public boolean addObra(ArrayList<Obra> lista, ArrayList<Obra> listaDeSessao) throws IOException{
        File file = new File(fileObra);
        Obra obra1;
        boolean existe;
        String tipo = requestTipo(),numReg = Integer.toString(++idObra),ID ="";
        switch (numReg.length()) {
            case 1:
                ID = "000" + numReg + "/" + dataN.getYear();
                break;
            case 2:
                ID = "00" + numReg + "/" + dataN.getYear();
                break;
            case 3:
                ID = "0" + numReg + "/" + dataN.getYear();
                break;
            case 4:
                ID = numReg + "/" + dataN.getYear();
                break;
        }
        
        if(tipo.equals("LIVRO")){
            obra1 = new ObraLivro();
            obra1.setTipoObra(tipo);
            requestTitle(obra1);
            requestAutor((ObraLivro)obra1);
            listarCategoria(obra1);
            requestPrice(obra1);
            requestNumCopies(obra1);
            obra1.setQntExFREE(obra1.getQtdEx());
            requestISBN((ObraLivro)obra1);
            obra1.setIdObra(ID);
            obra1.setDataRegObra(dataN.getDayOfMonth()+"/"+dataN.getMonthValue()+"/"+dataN.getYear());
            existe = ObraExistente(lista,obra1);
            if(!existe){
                if(lista.add(obra1)){
                    listaDeSessao.add(obra1);
                    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))) {
                        escritor.write(obra1.getTipoObra()+";"+obra1.getIdObra()+";"+obra1.getTituloObra()+";"+
                        Arrays.toString(((ObraLivro) obra1).getAutor())+
                        ";"+(int)obra1.getPrecoObra()+";"+obra1.getCatObra()+";"+
                        ((ObraLivro) obra1).getISBN()+";"+obra1.getQtdEx()+
                        ";"+obra1.getQntExFREE()+";"+obra1.getDataRegObra()+";");
                        escritor.newLine();
                        escritor.close();
                    }
                }
                return true;
            }
        }
        if(tipo.equals("DVD")){
            obra1 = new ObraDVD();
            obra1.setTipoObra(tipo);
            requestTitle(obra1);
            requestRealizador((ObraDVD)obra1);
            listarCategoria(obra1);
            requestPrice(obra1);
            requestNumCopies(obra1);
            obra1.setQntExFREE(obra1.getQtdEx());
            requestDNDAC((ObraDVD)obra1);
            obra1.setIdObra(ID);
            obra1.setDataRegObra(dataN.getDayOfMonth()+"/"+dataN.getMonthValue()+"/"+dataN.getYear());
            existe = ObraExistente(lista,obra1);
            if(!existe){
                if(lista.add(obra1)){
                    listaDeSessao.add(obra1);
                    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))) {
                        escritor.write(obra1.getTipoObra()+";"+obra1.getIdObra()+";"+obra1.getTituloObra()+";"+
                        ((ObraDVD) obra1).getRealizador()+
                        ";"+(int)obra1.getPrecoObra()+";"+obra1.getCatObra()+";"+
                        ((ObraDVD) obra1).getDNDAC()+";"+obra1.getQtdEx()+
                        ";"+obra1.getQntExFREE()+";"+obra1.getDataRegObra()+";");
                        escritor.newLine();
                        escritor.close();
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean ObraExistente(ArrayList<Obra> lista, Obra obra){
        boolean existe = false;
        if(obra instanceof ObraLivro){
            for(Obra elemento: lista){
                if(elemento instanceof ObraLivro){
                    if(((ObraLivro) elemento).getISBN().equals(((ObraLivro) obra).getISBN())){
                        existe = true;
                    }
                }
            }
        }else if(obra instanceof ObraDVD){
            for(Obra elemento: lista){
                if(elemento instanceof ObraDVD){
                    if(((ObraDVD) elemento).getDNDAC().equals(((ObraDVD) obra).getDNDAC())){
                        existe = true;
                    }
                }
            }
        }
        return existe;
    }
    
    public void showObras(ArrayList<Obra> lista){
        if(!lista.isEmpty()){
            System.out.println("\n*** LISTA DE OBRAS REGISTRADAS ***");
            Obra obra;
            int i=0;
            while(i<lista.size()){
                obra = lista.get(i);
                if(obra instanceof ObraLivro){
                    System.out.println(obra.getIdObra() + " - " 
                            + obra.getTipoObra() + " - " + obra.getTituloObra() + 
                            " - " + obra.getCatObra() + " - " + obra.getQtdEx() 
                            + " - " + obra.getQntExFREE() + " - "
                            + Arrays.toString(((ObraLivro) obra).getAutor()) + 
                            " - " + ((ObraLivro) obra).getISBN());
                }else if(obra instanceof ObraDVD){
                    System.out.println(obra.getIdObra() + " - "
                    + obra.getTipoObra() + " - " + obra.getTituloObra() + 
                    " - " + obra.getCatObra() + " - " + obra.getQtdEx() 
                    + " - " + obra.getQntExFREE() + " - " +
                    ((ObraDVD) obra).getRealizador() + " - " + ((ObraDVD) obra).getDNDAC());
                }
                i++;
            }
        }else{
           System.out.println("Ainda Nao Foi Registrada Nenhuma Obra!!!");
        }
    }
   
    public static int requestUserId(){
        System.out.print("Digite o Id do usuário: ");
        int id = getNum.nextInt(); 
        return id;
    } 
    
    public  void WorkRegistrationSuccessful(){
       System.out.println("Obra Registada com sucesso!");
   }
    public static String requestWorkId(){
        System.out.print("Digite o ID da Obra: ");
        String Id = getOther.nextLine();
        
        return Id;
    }
    
    public void showObra(ArrayList<Obra> lista){
        boolean find = false;
        String Id = requestWorkId();
        Obra obra = buscarObra(lista,Id);
        if(obra == null){
            try{
                throw new NoSuchWorkException();
            }catch(NoSuchWorkException ex){
                System.err.println(ex.exSMS);
            }
        }else{
            if(obra instanceof ObraLivro){
                if(Id.equals(obra.getIdObra())){
                    find = true;
                    System.out.println(obra.getIdObra() + " - " 
                    + obra.getTipoObra() + " - " + obra.getTituloObra() 
                    + " - " + obra.getCatObra() + " - " + obra.getQtdEx() 
                    + " - " + obra.getQntExFREE() + " - " 
                    + Arrays.toString(((ObraLivro) obra).getAutor()) + 
                    " - " + ((ObraLivro) obra).getISBN());
                 }
            }else if(obra instanceof ObraDVD){
                if(Id.equals(obra.getIdObra())){
                    find = true;
                    System.out.println(obra.getIdObra() + " - "
                    + obra.getTipoObra() + " - " + obra.getTituloObra() + 
                    " - " + obra.getCatObra() + " - " + 
                    obra.getQtdEx() + " - " + obra.getQntExFREE()
                    + " - " + ((ObraDVD) obra).getRealizador() +
                    " - " + ((ObraDVD) obra).getDNDAC());
                }
            }
        }
    }   
    
    public static Obra buscarObra(ArrayList<Obra> lista, String id){
        if(!lista.isEmpty()){
            for(Obra obra1 : lista){
                if(obra1.getIdObra().equalsIgnoreCase(id)) return obra1;
            }
        }
        return null;
    }
   

    
    public boolean conteinsIgnoreCase(String termo,String sequencia){
        boolean contem = false;
        String termoUpper = termo.toUpperCase();
        String sequenciaUpper = sequencia.toUpperCase();
        if(sequenciaUpper.contains(termoUpper)){
            contem = true;
        }
        return contem;
    }
    
    public void requestSearchTerm(ArrayList<Obra> lista){
        boolean find = false;
        System.out.print("Informe o termo da pesquisa: ");
        String termo = getOther.nextLine();
        int i = 0;
        for(Obra elemento : lista){
               if(elemento instanceof ObraLivro){
                    String autor[] = ((ObraLivro) elemento).getAutor();
                    if(conteinsIgnoreCase(termo,autor[i]) || conteinsIgnoreCase(termo,elemento.getTituloObra())){
                        find = true;
                        System.out.println(elemento.getIdObra() + " - " 
                                + elemento.getTipoObra() + " - " + elemento.getTituloObra()
                                + " - " + elemento.getCatObra() + " - " + 
                                elemento.getQtdEx() + " - " +
                                elemento.getQntExFREE() + " - "
                                + Arrays.toString(((ObraLivro) elemento).getAutor()) 
                                + " - " + ((ObraLivro) elemento).getISBN());
                        i++;
                    }
                }else if(elemento instanceof ObraDVD){
                    if(conteinsIgnoreCase(termo,elemento.getTituloObra()) || conteinsIgnoreCase(termo,((ObraDVD) elemento).getRealizador())){
                        find = true;
                        System.out.println(elemento.getIdObra() + 
                                " - " + elemento.getTipoObra() + " - "
                                + elemento.getTituloObra() + " - " +
                                elemento.getCatObra() + " - " +
                                elemento.getQtdEx() + " - "
                                + elemento.getQntExFREE() + 
                                " - " + ((ObraDVD) elemento).getRealizador() +
                                " - " + ((ObraDVD) elemento).getDNDAC());
                    }
                }
            }
        if(find == false){
           System.out.println("\nTermo não encontrado!");
        }
    }
    
    


    
}
