/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;

import config.*;
import config.Jogador;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static repositorio.Menu.limparConsole;
import static repositorio.Menu.scan;

/**
 * 
 * @author D'zign
 */
public class Jogo {
    
    JogadaRepositorio repJogada = new JogadaRepositorio();
    JogadorRepositorio jogadorRep = new JogadorRepositorio();
    ConfigRepositorio configRep = new ConfigRepositorio();
    Configuracao configuracao = new Configuracao();
  
    ArrayList <Jogada> jogadas = new ArrayList<>();
    ArrayList <Jogador> listaCompleta = new ArrayList<>();
    Jogada auxJogada= new Jogada();
    Jogada novaJogada=null;
     
    int qntDig;int k=0;  int rodE;
    Random aleatorio = new Random();
     
/**
 * 
 * @throws IOException
 * @throws FileNotFoundException
 * @throws ClassNotFoundException 
 */
      void configJogo() throws IOException, FileNotFoundException, ClassNotFoundException{
          listaCompleta=jogadorRep.getJogadores();
         configuracao = configRep.getConfig();
         qntDig = configuracao.getQntDigitos();
         rodE = configuracao.getNumeroRodada();
         
        
    }
      /**
       * 
       * @throws IOException
       * @throws FileNotFoundException
       * @throws ClassNotFoundException 
       */
    public void waitClean() throws IOException, FileNotFoundException, ClassNotFoundException{
        espera();
        limparConsole();
    }
    /**
     * 
     * @param qtdDigitos
     * @return 
     */
    public int gerarNumeroAleatorio (int qtdDigitos){
        int numeroMax =(int) (Math.pow(10, (qtdDigitos))-1);
        int numeroAleatorio=aleatorio.nextInt(numeroMax);
        
        return numeroAleatorio;
    }/**
     * 
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    
    public void espera() throws IOException, FileNotFoundException, ClassNotFoundException{
         try {
            TimeUnit.SECONDS.sleep(configuracao.getTempoMemorizacao());
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
         //System.out.println("Já");
    }
   
   
         
    //Configuracao configuracao = configRep.getConfig();
    /**
     * 
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    public void singlePlayer() throws IOException, FileNotFoundException, ClassNotFoundException{ 
        jogadorRep.listarJogadores();
        limparConsole();
        limparConsole();
      
       Jogador jogadorSP;
       configJogo();
       limparConsole();
        int rodadas = configuracao.getNumeroRodada();
        
       
        System.out.print("Jogo Da Memoria | Single Player\nDigite o ID do Jogador: ");
        int iD=scan.nextInt();boolean passa=true;int numeroDoJogador = 0;
        jogadorSP = jogadorRep.encontrarJogador(iD);
        if(jogadorSP !=null){
                while(rodadas>0){
                int numeroGerado=gerarNumeroAleatorio(qntDig);
                System.out.println("\tRODADA: "+(++k));
                System.out.print("Numero Sorteado: "+ numeroGerado);
                waitClean();
                System.out.print("\nDigite o numero Sorteado: ");
                do{
                    try{
                       numeroDoJogador = scan.nextInt();
                         passa=false;
                        if (numeroDoJogador== numeroGerado){
                            jogadorSP.setPontuacao(jogadorSP.getPontuacao()+1);
                            auxJogada.setIsCerto(true);
                            limparConsole();
                        }
                       
                    }catch(InputMismatchException e){
                        System.err.println("Ups! Nao Digitou um numero... ");
                        System.out.print("\nDigite o numero Sorteado: ");
                        scan.nextLine();
                    
                    }
                }while(passa);
                
                int idAux =jogadorSP.getIdJogador(); int numAux= numeroGerado; 
                int numDigitado= numeroDoJogador; boolean vBool = auxJogada.isIsCerto();
                int point = jogadorSP.getPontuacao();
               novaJogada = new Jogada(idAux, numAux, numDigitado,vBool,point);
                jogadas.add(novaJogada);
                rodadas-=1;
            }
            repJogada.registarJogadas(jogadas);
            System.out.print(jogadorSP.toString());
        }else{
            System.err.print("Jogador Inexistente!\n");
        }
    }
    /**
     * 
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */ 
    public void multiPlayer() throws IOException, FileNotFoundException, ClassNotFoundException{
        configJogo();
        jogadorRep.listarJogadores();
        limparConsole();
        limparConsole();
        configuracao = configRep.getConfig();
        int rodadas = configuracao.getNumeroRodada();
        ArrayList<Jogador>jogadoresON =new ArrayList<>();
        
        int tam = configuracao.getQntJogador();int i=0;
        System.out.println("Jogo Da Memoria | Multi Player");
        // Ciclo para Add Jogadores existentes no Jogo!!!!
        while(i<tam){
            System.out.print("Digite o ID do Jogador: ");
            int idJ=scan.nextInt();
            Jogador auxJ= jogadorRep.encontrarJogador(idJ);
            if (auxJ!=null){
                jogadoresON.add(auxJ);
                i++;
                System.out.println("Jogador Adicionado ao Jogo!");
            }else{
                System.err.println("Jogador Inexistente!");
            }
        }
        System.out.println("START | Jogo de Memoria!\n");
        for(int j=0; j<rodadas; j++){// Ciclo de Rodada!
            System.out.println("\tRODADA: "+(j+1));
            int numGerado=gerarNumeroAleatorio(qntDig); int num=0;
            boolean passa = true;
            System.out.print("Numero Sorteado: "+ numGerado);
            waitClean();
            for (Jogador jg : jogadoresON) {
                limparConsole();
                System.out.print("Jogador "+jg.getIdJogador()+" | Digita o numero Sorteado: ");
               do{
                   try{
                    num = scan.nextInt();
                    passa=false;
                    if (num== numGerado){
                        jg.setPontuacao(jg.getPontuacao()+1);
                        auxJogada.setIsCerto(true);
                    }
               }catch(InputMismatchException e){
                   System.err.println("Ups! Nao Digitou um numero... ");
                   scan.nextLine();
               } 
               }while(passa);
                
                int idAux = jg.getIdJogador(); int numAux= numGerado; 
                int numDigitado= num; boolean vBool = auxJogada.isIsCerto();
                int point = jg.getPontuacao();
                novaJogada = new Jogada(idAux, numAux, numDigitado,vBool,point);
               jogadas.add(novaJogada);
            }
        }
        Collections.sort(jogadoresON); limparConsole(); limparConsole();
        seEmpate (jogadoresON, jogadas);
         repJogada.registarJogadas(jogadas);
    }
    void seEmpate (ArrayList<Jogador>l, ArrayList<Jogada>j) throws IOException, FileNotFoundException, ClassNotFoundException{
       ArrayList<Jogada>empJogadas =j;
        ArrayList<Jogador>empJogadores =l;
        Jogador jWinner = Collections.min(empJogadores); //Temos O Vencedor
        
        int qntEmpat=0;
        for (int pJog= 0; pJog<empJogadores.size(); pJog++) {
            if(empJogadores.get(pJog).getPontuacao()==jWinner.getPontuacao()){
                qntEmpat++;
            } 
            if(qntEmpat>1){
                
                repJogada.registarJogadas(jogadas);
                limparConsole(); limparConsole();
                repJogada.getEstatistica(empJogadores);//Mostra a Estatistica!
            }else{
                limparConsole(); limparConsole();
                System.out.println("====DESEMPATE======");
                //for(int a=0;a<qntEmpat;a++)
                Collections.sort(l);
                int a=0;int numeroGerado=gerarNumeroAleatorio(qntDig);
                System.out.println("RODADA: "+(++rodE));
                System.out.print("Numero Sorteado: "+ numeroGerado);
                waitClean();
                while(a<qntEmpat){
                    limparConsole();
                    System.out.print("Jogador "+empJogadores.get(a).getIdJogador()+" | Digita o numero Sorteado: ");
                    int num = scan.nextInt();
                
                    if (num== numeroGerado){
                        empJogadores.get(a).setPontuacao(empJogadores.get(a).getPontuacao()+1);
                        auxJogada.setIsCerto(true);
                    }
                    //Variaveis para inicializar cada Jogada
                    int idAux = empJogadores.get(a).getIdJogador(); int numAux= numeroGerado; 
                    int numDigitado= num; boolean vBool = auxJogada.isIsCerto();
                    int point = empJogadores.get(a).getPontuacao();
                    novaJogada = new Jogada(idAux, numAux, numDigitado,vBool,point);
                    empJogadas.add(novaJogada);
                    ++a;
                }
                Collections.sort(empJogadores); limparConsole(); limparConsole();
                seEmpate (empJogadores, j);
            }
        }
    }
}
   
