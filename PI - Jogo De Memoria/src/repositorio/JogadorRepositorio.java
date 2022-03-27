package repositorio;

import config.*;
import java.io.*;
import java.util.ArrayList;
import static repositorio.Menu.menuVerJogador;
import static repositorio.Menu.scan;


/**
 *
 * @author D'zign
 */
public class JogadorRepositorio {
 
    Jogador j = new Jogador();
    public static String jogadorFile= "C:\\Users\\D'zign\\OneDrive\\Ambiente de Trabalho\\Jogo De Memoria\\src\\repositorio\\file\\jogador.mo";
   static ArrayList<Jogador> jogadores = new ArrayList<>();
  
    public JogadorRepositorio() {
    }

   /**
    * 
    * @throws IOException
    * @throws FileNotFoundException
    * @throws ClassNotFoundException 
    */
    public void criarJogador() throws IOException, FileNotFoundException, ClassNotFoundException{
        System.out.println("\nNome: ");
        String nome = scan.next();
        System.out.println("\nGenero\n\tM - Masculino\n\tF - Femenino : ");
        String genero = scan.next();
        if(genero.equalsIgnoreCase("M")){
            genero="Masculino";
        }else if (genero.equalsIgnoreCase("F")){
            genero="Femenino";
        }else{
            genero="Indefinido";
        }
     
       Jogador novoJogador= new Jogador(nome, genero);
       jogadores.add(novoJogador);
       guardarJogadores(jogadores);
       System.out.print("Jogador Criado com Sucesso!\n");
    }
   
   /**
    * 
    * @param idJogador
    * @return 
    */
    public  Jogador encontrarJogador(int idJogador){
        Jogador jogador = null;
        if(!jogadores.isEmpty()){
            for(Jogador jL: jogadores){
                if(idJogador==jL.getIdJogador()){
                    jogador = jL;
                }
            }
        }
        return jogador;
    }
    /**
     * 
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InterruptedException 
     */
        
    public void funLocalizarJogador() throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException{
        System.out.print("Digite o ID do Jogador: ");
        int id=scan.nextInt();
        Jogador auxj = encontrarJogador(id);
        if(auxj!=null){
            System.out.print(auxj.toString());
            menuVerJogador();
        }else{
            System.err.print("Jogador Inexistente!\n");
            funLocalizarJogador();
        }
    }
   
      /**
       * Funcao que lista Todos Jogadores no console!
       * @throws IOException
       * @throws ClassNotFoundException 
       */
    public void listarJogadores() throws IOException, ClassNotFoundException{
        jogadores = getJogadores();
        if (!jogadores.isEmpty()){ 
            for (Jogador jg : jogadores){
                System.out.println(jg);
            }
        }else{
            System.out.println("Nao ha jogadores registados!\n");
        }
    }
        /*public void eliminarJogador(int id){
          
        }*/
       
    /**
     * 
     * @param jogadores
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void guardarJogadoes(ArrayList<Jogador> jogadores) throws FileNotFoundException, IOException, ClassNotFoundException{
             //F:\Java\NetBeansProjects\java_alg\Jogo De Memoria\src\repositorio\file
        try(FileOutputStream fos = new FileOutputStream(jogadorFile)){
            try(ObjectOutputStream oos = new ObjectOutputStream (fos)){
                oos.writeObject(jogadores);   
                oos.close();
            }catch( NotSerializableException e1 ){
            }
             
        }
    }
    /**
     * 
     * @return listaJ
     */
    public ArrayList<Jogador> getJogadores(){
        ArrayList<Jogador> listaJ= null;
        FileInputStream fis= null;
        ObjectInputStream ois= null;
        try{
            fis=new FileInputStream(jogadorFile);
            ois = new ObjectInputStream(fis);
            listaJ=(ArrayList<Jogador>)ois.readObject();
            
        }catch(FileNotFoundException e){
             System.err.println("Arquivo nao Encontrado");
        }catch(IOException | ClassNotFoundException e){
              System.err.println("Erro na criacao do arquivo");
        }finally{
            if(ois!=null){
                try{
                    ois.close();
                }catch(IOException e){
                    System.err.println("Erro ao fechar arquivo\n");
                }
            }
         }
        return listaJ;
    }
    
    public static void guardarJogadores(ArrayList<Jogador> jogadores){
        FileOutputStream fos;
        ObjectOutputStream oos=null;
        try{
            fos=new FileOutputStream(jogadorFile);
            oos= new ObjectOutputStream(fos);
            oos.writeObject(jogadores);
        }catch(FileNotFoundException e){
            System.err.println("Arquivo nao Encontrado");
        }catch(IOException e1){
            System.err.println("Erro na criacao do arquivo");
        }finally{
            if(oos!=null){
                try{
                    oos.close();
                    System.out.println("Salvo com Sucesso!");
                }catch(IOException e1){
                    System.err.println("Erro ao fechar arquivo\n");
                }
            }
        }
    }
    
    
   
  
}

