package repositorio;

import config.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 *Obs: verificar o Caminho do ficheiro jogada.mo, de acordo ao caminho de sua maquina
 * o mesmo caminho deve colar na variavel jogadaFile...
 * @author Manuel dos Santos
 */
public class JogadaRepositorio {
    
    JogadorRepositorio jogadorR = new JogadorRepositorio();
    ArrayList<Jogada> jogadas = new ArrayList();
    public static String jogadaFile= "C:\\Users\\D'zign\\OneDrive\\Ambiente de Trabalho\\Jogo De Memoria\\src\\repositorio\\file\\jogada.mo";
  /**
   * Guarda as jogadas todas
   * @param Lista de jogadas
   * @throws IOException 
   */
   public void registarJogadas(ArrayList<Jogada> jogadas) throws IOException{
       FileOutputStream fos= null;
       ObjectOutputStream oos=null;
        try{
            fos=new FileOutputStream(jogadaFile);
            oos= new ObjectOutputStream(fos);
            oos.writeObject(jogadas);
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
   
 /**
  * Funcao responsável por gerar estatistica do jogo no console
  * @param listaJogadores 
  */
   public void getEstatistica(ArrayList<Jogador> listaJogadores){
        int i =0;
        System.out.println("\n\n\nEstatistica do Jogo!!!");
        for (Jogador j: listaJogadores){
            if (i==0)
                System.out.println("\nVencedor "+j.toString()+"\n");
            else
                System.out.println((i+1)+ "o Classificado: "+j.toString());
            i++;
        }
    }
   /**
    * Sem receber parametros esta funcao retorna a lista das jogadas
    * @return listaJogadas;
    */
    public ArrayList<Jogada> getJogadas(){
        ArrayList<Jogada> listaJogadas= null;
        FileInputStream fis;
        ObjectInputStream ois= null;
        try{
            fis=new FileInputStream(jogadaFile);
            ois = new ObjectInputStream(fis);
            listaJogadas=(ArrayList<Jogada>)ois.readObject();
            
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
        return listaJogadas;
    }
   /**
    * Esta Funcao traz um historico das jogadas...
    * 
    */
     public void showLastJogadas(){
        jogadas=getJogadas();
        if (!jogadas.isEmpty()){ 
            for (Jogada jg : jogadas){
                System.out.println(jg);
            }
        }else{
            System.out.println("Nao ha Jogada registados!\n");
        }
    }
    
}

