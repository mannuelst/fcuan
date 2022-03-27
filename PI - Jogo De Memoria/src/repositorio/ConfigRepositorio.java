/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;

import config.Configuracao;
import java.io.*;
import static repositorio.Menu.scan;


     /*
          
    REGISTO DE JOGADOR:
        Registar Jogador de acordo a qnt definida na propriedade qntJogador na class Configurcao
  
    */
    /*
          
    NUMERO DE RODADA:
        O numero de rodada (qntd de ciclo de jogadas) será definida em funcao do valor definida na variavel numeroRodada  de configuracao
    
    */
    /*
          
     SORTEIO DE RODADAS:
        Os numeros serao aleatorios em funcao da quantidade de algarismos definidos na variavel qntDigito na Classe Configuracao
    
    */

     // Depois de Cada Jogo os dados dos ficheiros jogador.mo e jogada.mo deverao ser limpados!Permitindo ADD novas informacoes referente ao novo jodo
       // File: config.mo --- Actualizar suas informacoes apois os primeiros dado
   
 /*Classe Responsável por Registar as configuracoes do jogo ou seja Guardar num ficheiro 'config.mo' atraves do metodo
    void registarDefinicoes(Configuracao config) e listar as Configuracoes metodo Configuracao getConfiguracao();
    */
/**
 *
 * @author D'zign
 */
public class ConfigRepositorio {
    
    //public static Configuracao config= new Configuracao();
   public static final String configFile= "C:\\Users\\D'zign\\OneDrive\\Ambiente de Trabalho\\Jogo De Memoria\\src\\repositorio\\file\\config.mo";
   
     
     public Configuracao configurarJogo(){
         Configuracao config;
         System.out.print("Quantidade de Jogadores: ");
         int qntJogador= scan.nextInt();
         System.out.print("Numero de Rodadas: ");
         int numRodadas= scan.nextInt();
         System.out.print("Quantidade de Digitos: ");
         int qntDig= scan.nextInt();
         System.out.print("Tempo de Memorizacao(em segundos): ");
         int tempo= scan.nextInt();
        // int qntJogador, int numeroRodada, int qntDigitos, int tempoMemorizacao
     
         config= new Configuracao(qntJogador, numRodadas, qntDig, tempo);
         return config;
     }
     
     public void guardarConfig() throws FileNotFoundException, IOException{
         Configuracao c=configurarJogo();
         FileOutputStream fos= null;
         ObjectOutputStream oos=null;
         try{
             fos=new FileOutputStream(configFile);
            oos= new ObjectOutputStream(fos);
            oos.writeObject(c);
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
    public Configuracao getConfig() throws FileNotFoundException, IOException, ClassNotFoundException{
        Configuracao auxCfg= null;
        FileInputStream fis= null;
        ObjectInputStream ois= null;
        try{
            fis=new FileInputStream(configFile);
            ois = new ObjectInputStream(fis);
            auxCfg=(Configuracao)ois.readObject();
            System.out.print(auxCfg); 
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
    return auxCfg;
    }
}
