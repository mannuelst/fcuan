/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.gamed.repositorio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import poo.gamed.*;
import poo.gamed.exception.*;

/**
 * CLASSE METODOS
 * Nesta classe são criados todos os metodos
 * que serão utilizados na construção do Sistema
 * tais como os Metodos das classes UTENTE, OBRAS E REQUISIÕES.
 */
public class Metodos {
    static Scanner ler = new Scanner(System.in);
    static Scanner lerNum = new Scanner(System.in);
    static LocalDate data_ = LocalDate.now();
    static int codigoUtente = 0;
    static int codigoObra = 0;
    static Categoria cate = new Categoria();
    final ArrayList<String> listaCategoria = cate.getCategoria();
    final String fUtente = "C:\\Users\\Dzign\\Desktop\\GAMED\\src\\poo\\gamed\\bd\\UTENTES.txt";
    final String fObra = "C:\\Users\\Dzign\\Desktop\\GAMED\\src\\poo\\gamed\\bd\\OBRAS.txt";
    final String fRequisicao = "C:\\Users\\Dzign\\Desktop\\GAMED\\src\\poo\\gamed\\bd\\REQUISICOES.txt";
    final String fU = "C:\\Users\\Dzign\\Desktop\\GAMED\\src\\poo\\gamed\\ultima_sessao\\UTENTES.txt";
    final String fO = "C:\\Users\\Dzign\\Desktop\\GAMED\\src\\poo\\gamed\\ultima_sessao\\OBRAS.txt";
    final String fR = "C:\\Users\\Dzign\\Desktop\\GAMED\\src\\poo\\gamed\\ultima_sessao\\REQUISICOES.txt";
    
    public String openFile(){
        System.out.println("Qual dos Ficheiros Deseja Abrir?\nU - UTENTES");
        System.out.println("O - OBRAS");
        System.out.println("R - REQUISIÇOES");
        String opcao = ler.next();
        switch(opcao.charAt(0)){
            case 'u':
            case 'U':return fU;
            case 'o':
            case 'O':return fO;
            case 'r':
            case 'R':return fR;
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
                System.out.println(e.mensagem);
            }
        }else{
            FileReader fr = new FileReader(file);
            if(fileName.equals(fU)){
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
            if(fileName.equals(fO)){
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
            if(fileName.equals(fR)){
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
    
    public String newSaveAs(){
        System.out.print("Ficheiro Não Encontrado\nDigite O Nome do Ficheiro: ");
        String ficheiro = ler.next();
        return ficheiro;
    }
    
    public void guardar(ArrayList<Utente> lu,ArrayList<Obra> lo,ArrayList<Requisicoes> lr) throws IOException{
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
        File futente = new File(fU);
        if(futente.delete()){
            futente.createNewFile();
            if(!futente.exists()) {
                futente = new File(newSaveAs());
                futente.createNewFile();
            }
        }
        if (lu.isEmpty())  return false;
        else{
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(fU,true))) {
                for (Utente utente : lu)
                {
                escritor.write(utente.getNomeUtente()+";"+utente.getEmailUtente()+
                ";"+utente.getCodigoUtente()+";"+utente.getEstado()+";"+utente.getMulta()+
                ";"+utente.getTotRequisicoes()+";"+utente.getData_registro()+";");
                escritor.newLine();
                }
                escritor.close();
            }
            return true;
        }
    }
    
    public boolean guardarRequisicoes(ArrayList<Requisicoes> lr) throws IOException{
        File requi = new File(fR);
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
                for(Requisicoes requisicao : lr)
                {
                    escritor.write(requisicao.getData_requisicao()+
                    ";"+requisicao.getObraRequisitada().getIdObra()
                    +";"+requisicao.getRequisitante().getCodigoUtente()
                    +";"+requisicao.getData_devolucao()+";");
                    escritor.newLine();
                }
                escritor.close();
            }
            return true;
        }
    }
    
    public boolean guardarDadosObras(ArrayList<Obra> lo) throws IOException{
        File Obra = new File(fO);
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
                if(obra instanceof DVD){
                    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(fO,true))) {
                        escritor.write(obra.getTipo()+";"+obra.getIdObra()+";"+obra.getTitulo()+";"+
                        ((DVD) obra).getRealizador()+
                        ";"+obra.getPreco()+";"+obra.getCategoria()+";"+
                        ((DVD) obra).getDNDAC()+";"+obra.getQtdExemplar()+
                        ";"+obra.getExemplaresDispo()+";"+obra.getData_registro()+";");
                        escritor.newLine();
                        escritor.close();
                    }
                }
                if(obra instanceof Livro){
                    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(fO,true))) {
                        escritor.write(obra.getTipo()+";"+obra.getIdObra()+";"+obra.getTitulo()+";"+
                        Arrays.toString(((Livro) obra).getAutor())+
                        ";"+obra.getPreco()+";"+obra.getCategoria()+";"+
                        ((Livro) obra).getISBN()+";"+obra.getQtdExemplar()+
                        ";"+obra.getExemplaresDispo()+";"+obra.getData_registro()+";");
                        escritor.newLine();
                        escritor.close();
                    }
                }
            }
            return true;
        }
    }
    
    public void requestUserName(Utente utente){
       System.out.print("Digite o seu Nome: ");
       String nome = ler.nextLine();
       utente.setNomeUtente(nome);
    }
   
    /**
    * Metodos DA CLASS UTENTE
    * @param utente
    * @description O metodo requestUserEMail() solicita que o Utente digite o seu email
    * depois de digitado, o email é validado para poder proceguir com o processo
    */
    public void requestUserEMail(Utente utente){
       System.out.print("Digite o seu E-mail exemplo(joao852): ");
       String email = ler.nextLine();
       utente.setEmailUtente(email);
    }
   
    /**
    * Metodos DA CLASS UTENTE
     * @param lista
     * @param listaDeSessao
    * @return inteiro
     * @throws java.io.IOException
    * @description O metodo addUtente() regista utente, é feita uma verificação previa
    * de que se o utente já tiver sido registado o metodo retornará false(0)
    * e o utente não poderá ser registado de novo senão o metodo retornará true(1)
    * e o utente podera ser registado
    */
    public boolean addUtente(ArrayList<Utente> lista,ArrayList<Utente> listaDeSessao) throws IOException{
        ler.nextLine();
        Utente utente = new Utente();
        File file = new File(fUtente);
        requestUserName(utente);
        requestUserEMail(utente);
        boolean estado = true;
        utente.setEstado(estado);
        utente.setCodigoUtente(++codigoUtente);
        utente.setData_registro(data_.getDayOfMonth()+"/"+data_.getMonthValue()+"/"+data_.getYear());
        boolean jaExiste = jaExisteUtente(utente,lista);
        if(jaExiste == true){
            return false;
        }else{
            lista.add(utente);
            listaDeSessao.add(utente);
             try (BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))) {
                escritor.write(utente.getNomeUtente()+";"+utente.getEmailUtente()+";"+
                utente.getCodigoUtente()+";"+utente.getEstado()+";"+utente.getMulta()+
                ";"+utente.getTotRequisicoes()+";"+utente.getData_registro()+";");
                escritor.newLine();
                escritor.close();
             }
            return true;
        }
    }
   
    /**
     * Metodos DA CLASS UTENTE
     * @param utente
     * @param lista
     * @return boolean
     * @description O metodo jaExisteUtente() verifica se já existe um utente com
     * os mesmos dados registado no sistema
     */
    public boolean jaExisteUtente(Utente utente,ArrayList<Utente> lista){
       boolean existe = false; 
       for(Utente elemento:lista){
           if(utente.getEmailUtente().equals(elemento.getEmailUtente())){
               existe = true;
           }
       }
       return existe;
    }
    
    /**
     * Metodos DA CLASS UTENTE
     * @param lista 
     * @description O metodo showUtentes() mostra todos os utentes registado no sistema
     */
    public void showUtentes(ArrayList<Utente> lista){
        if(!lista.isEmpty()){
            System.out.println("\t*** LISTA DE UTENTES REGISTADOS ***");
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
   
    public void showUtente(ArrayList<Utente> lista){
        int id = requestUserId();
        Utente utente = buscarUtente(id, lista);
        if(utente == null){
            try{
                throw new NoSuchUserException();
            }catch(NoSuchUserException ex){
                System.out.println(ex.mensagem);
            }
        }else{
            exibirUtente(utente);
        }
    }
        
    public Utente buscarUtente(int id, ArrayList<Utente> lista){
        for(Utente elemento : lista)
            if(id == elemento.getCodigoUtente()) 
                return elemento;
        return null;
   }
    
    public void exibirUtente(Utente utente){
        System.out.println(utente.getCodigoUtente() + " - " + utente.getNomeUtente() 
                + " - " + utente.getEmailUtente() + " - " + utente.getEstado() + " - " + utente.getTotRequisicoes());
    }

    public void userRegistrationSuccessful(){
       System.out.println("Utente Registado com sucesso!\n");
    }
    
    public void pagarmulta(ArrayList<Utente> lista) throws NoSuchUserException,IOException{
        int ID = requestUserId();
        Utente utente = buscarUtente(ID,lista);
        if(utente == null){
            try{
                throw new NoSuchUserException();
            }catch(NoSuchUserException ex){
                System.out.println(ex.mensagem);
            }
        }else{
            if(!utente.getMulta().equals("MULTADO"))
                System.out.println("UTENTE NÃO MULTADO");
            else{
                System.out.println("Pagou a Multa?");
                System.out.println("1 - Sim\n0 - Não");
                int opcao = lerNum.nextInt();
                switch(opcao){
                    case 1:{
                        utente.setMulta(false);
                        File file = new File(fUtente);
                        if(file.delete()){
                            file.createNewFile();
                            if(!file.exists()) {
                                file = new File(newSaveAs());
                                file.createNewFile();
                            }
                        }
                        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))){
                            for(Utente user : lista){
                                escritor.write(user.getNomeUtente()+";"+user.getEmailUtente()+
                                ";"+user.getCodigoUtente()+";"+user.getEstado()+";"+user.getMulta()+
                                ";"+user.getTotRequisicoes()+";"+user.getData_registro()+";");
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
    
   
    /**
    *  Metodos para as obras
    * @param obra
    */
    public void requestTitle(Obra obra){
        System.out.print("Título: ");
        String titutlo = ler.nextLine();
        obra.setTitulo(titutlo);
    }
    
    public void requestPrice(Obra obra){
        System.out.print("Preço: ");
        double preco = lerNum.nextInt();
        obra.setPreco(preco);
    }
    
    public void requestNumCopies(Obra obra){
        System.out.print("Numero de Exemplares: ");
        int numExem = lerNum.nextInt();
        obra.setQtdExemplar(numExem);
    }
    
    public void requestAutor(Livro livro){
        System.out.print("Numero de autores: ");
        int num = lerNum.nextInt();
        livro.setQtdAutor(num);
        String[] autor = new String[livro.getQtdAutor()];
        for (int i = 0; i < autor.length; i++) {
            System.out.print((i+1) + "º Autor: ");
            autor[i] = ler.nextLine();
        }
        livro.setAutor(autor);
    }
    public void requestISBN(Livro livro){
        System.out.print("ISBN: ");
        String isbn = ler.nextLine();
        if(isbn.length()>10) livro.setISBN(isbn.substring(0,10));
        else livro.setISBN(isbn);
    }
    
    public void requestRealizador(DVD dvd){
        System.out.print("Realizador: ");
        String realizador = ler.nextLine();
        dvd.setRealizador(realizador);
    }
    
    public void requestDNDAC(DVD dvd){
        System.out.print("DNDAC: ");
        String dndac = ler.nextLine();
        if(dndac.length()>6) dvd.setDNDAC(dndac.substring(0,5));
        else dvd.setDNDAC(dndac);
    }
    
    public String requestTipo(){
        ler.nextLine();
        System.out.println("\n*** TIPO DE OBRA ***");
        System.out.println("1 - LIVRO");
        System.out.println("2 - DVD");
            int opcao = lerNum.nextInt();
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
       int opcao = lerNum.nextInt();
            switch(opcao){
                case 1 : 
                    obra.setCategoria("OBRAS DE REFERENCIA");
                    break;
                case 2 :
                    obra.setCategoria("OBRAS DE FICCAO");
                    break;
                case 3 :
                    obra.setCategoria("OBRAS TECNICAS E CIENTIFICAS");
                    break;
                default:
                    System.out.println("Opcao Inválida\n");
            }
    }
    
    public boolean addObra(ArrayList<Obra> lista, ArrayList<Obra> listaDeSessao) throws IOException{
        File file = new File(fObra);
        Obra obra1;
        boolean existe;
        String tipo = requestTipo(),numReg = Integer.toString(++codigoObra),ID ="";
        switch (numReg.length()) {
            case 1:
                ID = "000" + numReg + "/" + data_.getYear();
                break;
            case 2:
                ID = "00" + numReg + "/" + data_.getYear();
                break;
            case 3:
                ID = "0" + numReg + "/" + data_.getYear();
                break;
            case 4:
                ID = numReg + "/" + data_.getYear();
                break;
        }
        
        if(tipo.equals("LIVRO")){
            obra1 = new Livro();
            obra1.setTipo(tipo);
            requestTitle(obra1);
            requestAutor((Livro)obra1);
            listarCategoria(obra1);
            requestPrice(obra1);
            requestNumCopies(obra1);
            obra1.setExemplaresDispo(obra1.getQtdExemplar());
            requestISBN((Livro)obra1);
            obra1.setIdObra(ID);
            obra1.setData_registro(data_.getDayOfMonth()+"/"+data_.getMonthValue()+"/"+data_.getYear());
            existe = jaExisteObra(lista,obra1);
            if(!existe){
                if(lista.add(obra1)){
                    listaDeSessao.add(obra1);
                    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))) {
                        escritor.write(obra1.getTipo()+";"+obra1.getIdObra()+";"+obra1.getTitulo()+";"+
                        Arrays.toString(((Livro) obra1).getAutor())+
                        ";"+(int)obra1.getPreco()+";"+obra1.getCategoria()+";"+
                        ((Livro) obra1).getISBN()+";"+obra1.getQtdExemplar()+
                        ";"+obra1.getExemplaresDispo()+";"+obra1.getData_registro()+";");
                        escritor.newLine();
                        escritor.close();
                    }
                }
                return true;
            }
        }
        if(tipo.equals("DVD")){
            obra1 = new DVD();
            obra1.setTipo(tipo);
            requestTitle(obra1);
            requestRealizador((DVD)obra1);
            listarCategoria(obra1);
            requestPrice(obra1);
            requestNumCopies(obra1);
            obra1.setExemplaresDispo(obra1.getQtdExemplar());
            requestDNDAC((DVD)obra1);
            obra1.setIdObra(ID);
            obra1.setData_registro(data_.getDayOfMonth()+"/"+data_.getMonthValue()+"/"+data_.getYear());
            existe = jaExisteObra(lista,obra1);
            if(!existe){
                if(lista.add(obra1)){
                    listaDeSessao.add(obra1);
                    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))) {
                        escritor.write(obra1.getTipo()+";"+obra1.getIdObra()+";"+obra1.getTitulo()+";"+
                        ((DVD) obra1).getRealizador()+
                        ";"+(int)obra1.getPreco()+";"+obra1.getCategoria()+";"+
                        ((DVD) obra1).getDNDAC()+";"+obra1.getQtdExemplar()+
                        ";"+obra1.getExemplaresDispo()+";"+obra1.getData_registro()+";");
                        escritor.newLine();
                        escritor.close();
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean jaExisteObra(ArrayList<Obra> lista, Obra obra){
        boolean existe = false;
        if(obra instanceof Livro){
            for(Obra elemento: lista){
                if(elemento instanceof Livro){
                    if(((Livro) elemento).getISBN().equals(((Livro) obra).getISBN())){
                        existe = true;
                    }
                }
            }
        }else if(obra instanceof DVD){
            for(Obra elemento: lista){
                if(elemento instanceof DVD){
                    if(((DVD) elemento).getDNDAC().equals(((DVD) obra).getDNDAC())){
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
                if(obra instanceof Livro){
                    System.out.println(obra.getIdObra() + " - " 
                            + obra.getTipo() + " - " + obra.getTitulo() + 
                            " - " + obra.getCategoria() + " - " + obra.getQtdExemplar() 
                            + " - " + obra.getExemplaresDispo() + " - "
                            + Arrays.toString(((Livro) obra).getAutor()) + 
                            " - " + ((Livro) obra).getISBN());
                }else if(obra instanceof DVD){
                    System.out.println(obra.getIdObra() + " - "
                    + obra.getTipo() + " - " + obra.getTitulo() + 
                    " - " + obra.getCategoria() + " - " + obra.getQtdExemplar() 
                    + " - " + obra.getExemplaresDispo() + " - " +
                    ((DVD) obra).getRealizador() + " - " + ((DVD) obra).getDNDAC());
                }
                i++;
            }
        }else{
           System.out.println("Ainda Nao Foi Registrada Nenhuma Obra!!!");
        }
    }
   
    public int requestUserId(){
        System.out.print("Digite o Id do usuário: ");
        int id = lerNum.nextInt(); 
        return id;
    } 
    
    public void WorkRegistrationSuccessful(){
       System.out.println("Obra Registada com sucesso!");
   }
    public String requestWorkId(){
        System.out.print("Digite o ID da Obra: ");
        String Id = ler.nextLine();
        
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
                System.out.println(ex.mensagem);
            }
        }else{
            if(obra instanceof Livro){
                if(Id.equals(obra.getIdObra())){
                    find = true;
                    System.out.println(obra.getIdObra() + " - " 
                    + obra.getTipo() + " - " + obra.getTitulo() 
                    + " - " + obra.getCategoria() + " - " + obra.getQtdExemplar() 
                    + " - " + obra.getExemplaresDispo() + " - " 
                    + Arrays.toString(((Livro) obra).getAutor()) + 
                    " - " + ((Livro) obra).getISBN());
                 }
            }else if(obra instanceof DVD){
                if(Id.equals(obra.getIdObra())){
                    find = true;
                    System.out.println(obra.getIdObra() + " - "
                    + obra.getTipo() + " - " + obra.getTitulo() + 
                    " - " + obra.getCategoria() + " - " + 
                    obra.getQtdExemplar() + " - " + obra.getExemplaresDispo()
                    + " - " + ((DVD) obra).getRealizador() +
                    " - " + ((DVD) obra).getDNDAC());
                }
            }
        }
    }   
    
    public Obra buscarObra(ArrayList<Obra> lista, String id){
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
        String termo = ler.nextLine();
        int i = 0;
        for(Obra elemento : lista){
               if(elemento instanceof Livro){
                    String autor[] = ((Livro) elemento).getAutor();
                    if(conteinsIgnoreCase(termo,autor[i]) || conteinsIgnoreCase(termo,elemento.getTitulo())){
                        find = true;
                        System.out.println(elemento.getIdObra() + " - " 
                                + elemento.getTipo() + " - " + elemento.getTitulo()
                                + " - " + elemento.getCategoria() + " - " + 
                                elemento.getQtdExemplar() + " - " +
                                elemento.getExemplaresDispo() + " - "
                                + Arrays.toString(((Livro) elemento).getAutor()) 
                                + " - " + ((Livro) elemento).getISBN());
                        i++;
                    }
                }else if(elemento instanceof DVD){
                    if(conteinsIgnoreCase(termo,elemento.getTitulo()) || conteinsIgnoreCase(termo,((DVD) elemento).getRealizador())){
                        find = true;
                        System.out.println(elemento.getIdObra() + 
                                " - " + elemento.getTipo() + " - "
                                + elemento.getTitulo() + " - " +
                                elemento.getCategoria() + " - " +
                                elemento.getQtdExemplar() + " - "
                                + elemento.getExemplaresDispo() + 
                                " - " + ((DVD) elemento).getRealizador() +
                                " - " + ((DVD) elemento).getDNDAC());
                    }
                }
            }
        if(find == false){
           System.out.println("\nTermo não encontrado!");
        }
    }
    
    public String requestReturnNotificationPreference(){
        return "Já Não Há Cópias Disponíveis...\nDeseja Ser Notificado Caso Esta Obra Esteja Disponível?";
    }

    public String workReturnDay(Requisicoes r){
        return "Vai Devolver Esta Obra Nesta Data " + r.getData_devolucao();
    }

    public boolean requisicaoJaFeita(ArrayList<Requisicoes> lista,Utente requisitante,Obra obra){
        if(!lista.isEmpty()){
            int i=0;
            Requisicoes requi;
            while(i<lista.size()){
                requi=lista.get(i);
                if(requi.getRequisitante().getCodigoUtente()==requisitante.getCodigoUtente() &&
                   requi.getObraRequisitada().getIdObra().equals(obra.getIdObra())) return true;
                i++;
            }
        }
        return false;
    }

    public void addListaDeRequisicoes(ArrayList<Requisicoes> lista,ArrayList<Obra> listaObra,ArrayList<Utente> listaUtente,ArrayList<Requisicoes> listaDeSessao) throws IOException{
        Requisicoes requisicao = new Requisicoes();
        File file = new File(fRequisicao);
        Utente requisitante;
        Obra obra;
        int idUtente = requestUserId();
        requisitante = buscarUtente(idUtente,listaUtente);
        if(requisitante==null){
            try{
                throw new NoSuchUserException();
            }catch(NoSuchUserException ex){
                System.out.println(ex.mensagem);
            }
        }else{
            if(requisitante.getEstado().equalsIgnoreCase("ACTIVO")){
                if(requisitante.getTotRequisicoes() < 3){
                    String idObra = requestWorkId();
                    obra = buscarObra(listaObra,idObra);
                    if(obra==null){
                        try{
                            throw new NoSuchWorkException();
                        }catch(NoSuchWorkException ex){
                            System.out.println(ex.mensagem);
                        }
                    }else if(!requisicaoJaFeita(lista,requisitante,obra)){
                        if(!obra.getCategoria().equalsIgnoreCase("OBRAS DE REFERENCIA")){
                            if(!(obra.getPreco()>10000.00)){
                                if(obra.getExemplaresDispo()==0){
                                    System.out.println(requestReturnNotificationPreference()+"\n1 - SIM\n2 - NÃO");
                                    int op = lerNum.nextInt();
                                }else{
                                    requisitante.setTotRequisicoes(1);
                                    if(requisitante.getTotRequisicoes()==3)requisitante.setEstado(false);
                                    obra.setExemplaresDispo(-1);
                                    requisicao.setRequisitante(requisitante.retUtente(requisitante));
                                    requisicao.setObraRequisitada(obra);
                                    requisicao.setData_requisicao(data_.getDayOfMonth()+"/"+data_.getMonthValue()+"/"+data_.getYear());
                                    String data_devol = data_.getDayOfMonth()+ "/";
                                    switch (data_.getMonthValue()){
                                        case 12:
                                            data_devol += "1";
                                            data_devol +=  "/" + (data_.getYear()+1);
                                            break;
                                        default:
                                            data_devol += (data_.getMonthValue()+1);
                                            data_devol +=  "/" + data_.getYear();
                                    }
                                    requisicao.setData_devolucao(data_devol);
                                    lista.add(requisicao);
                                    listaDeSessao.add(requisicao);
                                    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))) {
                                        escritor.write(requisicao.getData_requisicao()+
                                        ";"+requisicao.getObraRequisitada().getIdObra()
                                        +";"+requisicao.getRequisitante().getCodigoUtente()
                                        +";"+requisicao.getData_devolucao()+";");
                                        escritor.newLine();
                                        escritor.close();
                                    }
                                    file = new File(fUtente);
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
                                            ";"+utente.getCodigoUtente()+";"+utente.getEstado()+";"+utente.getMulta()+
                                            ";"+utente.getTotRequisicoes()+";"+utente.getData_registro()+";");
                                            escritor.newLine();
                                        }
                                        escritor.close();
                                    }
                                    file = new File(fObra);
                                    if(file.delete()){
                                        file.createNewFile();
                                        if(!file.exists()) {
                                            file = new File(newSaveAs());
                                            file.createNewFile();
                                        }
                                    }
                                    try(BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))){
                                        for(Obra obra1 : listaObra){
                                            if(obra1 instanceof DVD){
                                                escritor.write(obra1.getTipo()+";"+obra1.getIdObra()+
                                                ";"+obra1.getTitulo()+";"+((DVD) obra1).getRealizador()+
                                                ";"+(int)obra1.getPreco()+";"+obra1.getCategoria()+";"+
                                                ((DVD) obra1).getDNDAC()+";"+obra1.getQtdExemplar()+
                                                ";"+obra1.getExemplaresDispo()+";"+obra1.getData_registro()+";");
                                                escritor.newLine();
                                            }
                                            if(obra1 instanceof Livro){
                                                escritor.write(obra1.getTipo()+";"+obra1.getIdObra()+";"+obra1.getTitulo()+";"+
                                                Arrays.toString(((Livro) obra1).getAutor())+
                                                ";"+(int)obra1.getPreco()+";"+obra1.getCategoria()+";"+
                                                ((Livro) obra1).getISBN()+";"+obra1.getQtdExemplar()+
                                                ";"+obra1.getExemplaresDispo()+";"+obra1.getData_registro()+";");
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
                                    System.out.println(ex.mensagem);
                                }
                            }
                        }else{
                            try{
                                throw new RuleFailedException();
                            }catch(RuleFailedException ex){
                                System.out.println(ex.mensagem);
                            }
                        }
                    }else{
                        try{
                            throw new RuleFailedException();
                        }catch(RuleFailedException ex){
                            System.out.println(ex.mensagem);
                        }
                    }
                }else{
                    try{
                        throw new RuleFailedException();
                    }catch(RuleFailedException ex){
                        System.out.println(ex.mensagem);
                    }
                }
            }else{
                try{
                    throw new RuleFailedException();
                }catch(RuleFailedException ex){
                    System.out.println(ex.mensagem);
                }
            }
        }
    }

    public void devolucao(ArrayList<Requisicoes> lista,ArrayList<Obra> listaObra,ArrayList<Utente> listaUtente) throws IOException{
        Requisicoes requisicao = null;
        Utente requisitante;
        Obra obra;
        int idUtente = requestUserId();
        requisitante = buscarUtente(idUtente,listaUtente);
        if(requisitante==null){
            try{
                throw new NoSuchUserException();
            } catch (NoSuchUserException ex) {
                System.out.println(ex.mensagem);
            }
        }else{
            String idObra = requestWorkId();
            obra = buscarObra(listaObra,idObra);
            if(obra==null){
                try{
                    throw new NoSuchWorkException();
                }catch(NoSuchWorkException ex){
                    System.out.println(ex.mensagem);
                }
            }else{
                int i=0;
                while(i < lista.size()){
                    if(lista.get(i).getObraRequisitada().equals(obra)&&lista.get(i).getRequisitante().equals(requisitante)){
                        requisicao = lista.get(i);
                        break;
                    }
                    i++;
                }
                if(requisicao==null){
                    try{
                        throw new WorkNotBorrowedByUserException();
                    } catch (WorkNotBorrowedByUserException ex) {
                        System.out.println(ex.mensagem);
                    }
                }else{
                    requisitante.setTotRequisicoes(-1);
                    if(requisitante.getMulta().equals("MULTADO")) requisitante.setEstado(false);
                    else requisitante.setEstado(true);
                    obra.setExemplaresDispo(1);
                    Requisicoes requi = lista.remove(lista.indexOf(requisicao));
                    i=0;
                    File file = new File(fRequisicao);
                    if(file.delete()){
                        file.createNewFile();
                        if(!file.exists()) {
                            file = new File(newSaveAs());
                            file.createNewFile();
                        }
                    }
                    try(BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))){
                        while(i<lista.size()){
                            escritor.write(lista.get(i).getData_requisicao()+
                            ";"+lista.get(i).getObraRequisitada().getIdObra()
                            +";"+lista.get(i).getRequisitante().getCodigoUtente()
                            +";"+lista.get(i).getData_devolucao()+";");
                            escritor.newLine();
                            i++;
                        }
                        escritor.close();
                        if(requi.getRequisitante().getMulta().equals("MULTADO"))
                            System.out.println("Obra devolvida com sucesso, porém o requisitante tem Multa...\n"
                            + "Não fez a devolução dentro do prazo...\n"
                            + "Precisa pagar a multa para fazer outras requisicoes!!!");
                        else
                            System.out.println("Obra devolvida com sucesso!!!");
                    }
                    file = new File(fUtente);
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
                            ";"+utente.getCodigoUtente()+";"+utente.getEstado()+";"+utente.getMulta()+
                            ";"+utente.getTotRequisicoes()+";"+utente.getData_registro()+";");
                            escritor.newLine();
                        }
                        escritor.close();
                    }
                    file = new File(fObra);
                    if(file.delete()){
                        file.createNewFile();
                        if(!file.exists()) {
                            file = new File(newSaveAs());
                            file.createNewFile();
                        }
                    }
                    try(BufferedWriter escritor = new BufferedWriter(new FileWriter(file,true))){
                        for(Obra obra1 : listaObra){
                            if(obra1 instanceof DVD){
                                escritor.write(obra1.getTipo()+";"+obra1.getIdObra()+
                                ";"+obra1.getTitulo()+";"+((DVD) obra1).getRealizador()+
                                ";"+(int)obra1.getPreco()+";"+obra1.getCategoria()+";"+
                                ((DVD) obra1).getDNDAC()+";"+obra1.getQtdExemplar()+
                                ";"+obra1.getExemplaresDispo()+";"+obra1.getData_registro()+";");
                                escritor.newLine();
                            }
                            if(obra1 instanceof Livro){
                                escritor.write(obra1.getTipo()+";"+obra1.getIdObra()+";"+obra1.getTitulo()+";"+
                                Arrays.toString(((Livro) obra1).getAutor())+
                                ";"+(int)obra1.getPreco()+";"+obra1.getCategoria()+";"+
                                ((Livro) obra1).getISBN()+";"+obra1.getQtdExemplar()+
                                ";"+obra1.getExemplaresDispo()+";"+obra1.getData_registro()+";");
                                escritor.newLine();
                            }
                        }
                        escritor.close();
                    }
                }
            }
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
    
    
    @SuppressWarnings("UnusedAssignment")
    public void start_system(ArrayList<Requisicoes> lista,ArrayList<Obra> listaObra,ArrayList<Utente> listaUtente) throws IOException{
        File file = new File(fUtente);
        try (BufferedReader leitor = new BufferedReader(new FileReader(file))) {
            String vector[] = new String[7],linha = leitor.readLine();
            while(linha != null){
                Utente u = new Utente();
                vector = linha.split(";");
                u.setNomeUtente(vector[0]);
                u.setCodigoUtente(atoi(vector[2]));
                u.setEmailUtente(vector[1]);
                if(vector[3].equals("ACTIVO")) u.setEstado(true);
                else if(vector[3].equals("SUSPENSO")) u.setEstado(false);
                if(vector[4].equals("LIVRE")) u.setMulta(false);
                else if(vector[4].equals("MULTADO")) u.setMulta(true);
                u.setTotRequisicoes(atoi(vector[5]));
                u.setData_registro(vector[6]);
                listaUtente.add(u);
                ++codigoUtente;
                linha = leitor.readLine();
            }
            leitor.close();
        }
        file = new File(fObra);
        try (BufferedReader leitor = new BufferedReader(new FileReader(file))) {
            String super_vector[] = new String[10],linha = leitor.readLine();
            while(linha != null){
                Obra obra;
                super_vector = linha.split(";");
                if(super_vector[0].equals("LIVRO")){
                    obra = new Livro();
                    obra.setTipo(super_vector[0]);
                    obra.setIdObra(super_vector[1]);
                    obra.setTitulo(super_vector[2]);
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
                    ((Livro)obra).setQtdAutor(vectorautor.length);
                    ((Livro)obra).setAutor(vectorautor);
                    obra.setPreco(atoi(super_vector[4]));
                    obra.setCategoria(super_vector[5]);
                    ((Livro)obra).setISBN(super_vector[6]);
                    obra.setQtdExemplar(atoi(super_vector[7]));
                    obra.setExemplaresDispo(atoi(super_vector[8]));
                    obra.setData_registro(super_vector[9]);
                    listaObra.add(obra);
                }
                if(super_vector[0].equals("DVD")){
                    obra = new DVD();
                    obra.setTipo(super_vector[0]);
                    obra.setIdObra(super_vector[1]);
                    obra.setTitulo(super_vector[2]);
                    ((DVD)obra).setRealizador(super_vector[3]);
                    obra.setPreco(atoi(super_vector[4]));
                    obra.setCategoria(super_vector[5]);
                    ((DVD)obra).setDNDAC(super_vector[6]);
                    obra.setQtdExemplar(atoi(super_vector[7]));
                    obra.setExemplaresDispo(atoi(super_vector[8]));
                    obra.setData_registro(super_vector[9]);
                    listaObra.add(obra);
                }
                ++codigoObra;
                linha = leitor.readLine();
            }
            leitor.close();
        }
        file = new File(fRequisicao);
        try (BufferedReader leitor = new BufferedReader(new FileReader(file))) {
            String vector[] = new String[4],linha = leitor.readLine();
            while(linha != null){
                Requisicoes requi = new Requisicoes();
                vector = linha.split(";");
                requi.setRequisitante(buscarUtente(atoi(vector[2]),listaUtente));
                if(requi.getRequisitante()==null){
                    try{
                        throw new NoSuchUserException();
                    } catch (NoSuchUserException ex) {
                        System.out.println(ex.mensagem);
                    }
                }
                requi.setObraRequisitada(buscarObra(listaObra,vector[1]));
                if(requi.getObraRequisitada()==null){
                    try{
                        throw new NoSuchWorkException();
                    }catch(NoSuchWorkException ex){
                        System.out.println(ex.mensagem);
                    }
                }
                requi.setData_requisicao(vector[0]);
                requi.setData_devolucao(vector[3]);
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
                if(data_.getMonthValue()==atoi(mes) && data_.getDayOfMonth()>atoi(dia)) requi.getRequisitante().setMulta(true);
                else if(data_.getMonthValue()>atoi(mes)) requi.getRequisitante().setMulta(true);
                else if(data_.getMonthValue()>atoi(mes)  && data_.getYear()>atoi(ano)) requi.getRequisitante().setMulta(true);
                else if(data_.getMonthValue()<atoi(mes) && data_.getYear()>atoi(ano) && data_.getDayOfMonth()>atoi(dia)) requi.getRequisitante().setMulta(true);
                lista.add(requi);
                ++codigoUtente;
                linha = leitor.readLine();
            }
            leitor.close();
        }
    }
    
    
}