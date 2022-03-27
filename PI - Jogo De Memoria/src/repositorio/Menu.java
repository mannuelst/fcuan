
package repositorio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *clase Menu:
 *  aqui temos os menus todos.
 * Estes chamam todas as funcoes das classes no pacote Repositorio
 *      
 * 
 *@author D'zign
 */
public class Menu {
    public static Scanner scan = new Scanner(System.in);
    static JogadorRepositorio repJogador = new JogadorRepositorio();
    static  ConfigRepositorio c = new ConfigRepositorio();
    static JogadaRepositorio repJogada = new JogadaRepositorio();
    static Jogo modoJogo= new Jogo();
//    static Configuracao modoJogo=c.getConfig();
    /**
     * 
     * @throws InterruptedException 
     */
    public static void fecharPrograma() throws InterruptedException{
        System.out.print("Fechando o Programa");
        TimeUnit.SECONDS.sleep(1);
        System.out.print(".");
        TimeUnit.SECONDS.sleep(1);
        System.out.print(".");
        TimeUnit.SECONDS.sleep(1);
        System.out.print(".\n");
        System.exit(0);
    } 
    
       static void limparConsole(){
        System.out.println(System.lineSeparator().repeat(10));
        }
    /**
     * 
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     * @throws NullPointerException 
     */
    public static void menuPrincipal() throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException, NullPointerException{
        
        while(true){
            System.out.print("""
                             ===== JOGO DE MEMORIA =====
                             \t1 - Jogar
                             \t2 - Configuracao (Jogador/Jogo)
                             \t3 - Sair
                             Escolha uma Opcao: """);
            String opt=scan.next();
            switch (opt) {
                case "1" -> menuJogar();
               //Implementar Funcao
                case "2" ->  menuConfiguracao();
               //Implementar Funcao
                case "3" -> {
                    fecharPrograma();
               }
                default -> {
                        //limparConsole();
                        System.err.println("Opcao Invalida!");
                        menuPrincipal();
               }
            } 
        } 
    }
    /**
     * 
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InterruptedException 
     */
      public static void menuConfiguracao() throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException{
          limparConsole();
        System.out.print("""
                         CONFIGURACAO
                         \t1 - Configuracao do Jogador
                         \t2 - Configuracao do Jogo
                         \t3 - Historico do Ultima Jogo
                         \t4 - Menu Principal
                         \t0 - Sair
                         Escolha uma Opcao: """);
         String opt=scan.next();
            switch (opt) {
                case "1" -> {
                    System.out.println("Configuracao do Jogador");
                    
                    menuJogador();
                }
                case "2" -> {
                    System.out.println("Configuracao do Jogo!");
                    configMenu();
                }
                case "3" -> {
                    System.out.println("Historico do Ultimo Jogo!");
                   repJogada.showLastJogadas();
                }
                case "4" -> {
                   menuPrincipal();
               }case "0" -> {
                     fecharPrograma();
               }
                default -> {
                        //limparConsole();
                        System.err.println("Opcao Invalida!");
                       menuConfiguracao();
               }
         }
    }
/**
 * 
 * @throws IOException
 * @throws FileNotFoundException
 * @throws ClassNotFoundException
 * @throws InterruptedException
 * @throws NullPointerException 
 */
     public static void menuJogar() throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException, NullPointerException{
        limparConsole();
        System.out.print("""
                         JOGAR
                         \t1 - Single Player
                         \t2 - Multi-Players
                         \t3 - Menu Inicial
                         \t0 - Sair
                         Escolha uma Opcao: """);
         String opt=scan.next();
            switch (opt) {
                case "1" -> {
                    modoJogo.singlePlayer() ;
                }
               //Implementar Funcao
                case "2" -> modoJogo.multiPlayer();
               //Implementar Funcao
                case "3" -> {
                    menuPrincipal();
               }case "0" -> {
                     fecharPrograma();
               }
                default -> {
                        System.err.println("Opcao Invalida!");
                       menuJogar();
               }
            }
    }
  /**
   * 
   * @throws IOException
   * @throws FileNotFoundException
   * @throws ClassNotFoundException
   * @throws InterruptedException 
   */
    public static void configMenu() throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException{
        limparConsole();
        System.out.print("""
                         CONFIGURACAO DO JOGO
                         \t1 - Ver Configuracao
                         \t2 - Configurar o Jogo
                         \t3 - Menu Inicial
                         \t0 - Sair
                         Escolha uma Opcao: """);
            String opt=scan.next();
        switch (opt) {
            case "1" ->c.getConfig();
            case "2" ->c.guardarConfig();
            case "3" ->menuPrincipal();    
            case "0" ->fecharPrograma();
            default -> {
                        System.err.println("Opcao Invalida!");
                        configMenu();
            }
        }       
    }
    /**
     * 
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InterruptedException 
     */
    public static void menuJogador() throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException{
        limparConsole();
        System.out.print("""
                         CONFIGURACAO DO JOGADOR
                         \t1 - Registar Jogador
                         \t2 - Ver Jogador
                         \t3 - Listar Jogadores
                         \t4 - Menu Inicial
                         \t0 - Sair
                         Escolha uma Opcao: """);
            String opt=scan.next();
            switch (opt) {
                case "1" -> {
                   
                   repJogador.criarJogador();       
                }
                case "2" -> {
                    repJogador.funLocalizarJogador();
                    
                }
                case "3" -> {
                   
                    System.out.println("Listar Jogadores!");
                   repJogador.listarJogadores();
                   }
                case "4" -> {
                    menuPrincipal();
               }case "0" -> {
                    fecharPrograma();
               }
                default -> {
                        
                        System.err.println("Opcao Invalida!");
                       configMenu();
               }
            } 
    }
    /**
     * 
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InterruptedException 
     */
    public static void menuVerJogador() throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException{
        limparConsole();
        System.out.print("""
                         CONFIGURACAO DO JOGADOR
                         \t1 - Editar Jogador
                         \t2 - Excluir Jogador
                         \t3 - Menu Inicial
                         \t0 - Sair
                         Escolha uma Opcao: """);
        String opt=scan.next();
        switch (opt) {
            case "1" ->System.out.println("Editar Jogador");
            case "2" ->  System.out.println("Excluir Jogador");
            case "3" -> menuPrincipal();
            case "0" ->fecharPrograma();
            default -> {
                System.err.println("Opcao Invalida!");
                menuVerJogador();
            }
        }
    }
}
