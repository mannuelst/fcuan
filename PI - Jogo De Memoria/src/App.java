
import java.io.FileNotFoundException;
import java.io.IOException;
import static repositorio.Menu.menuPrincipal;

/**
 *Sobre:
 *  Este projecto consiste em um Jogo de Memoria
 *  Onde é gerado um numero, e depois de um tempo (segundos)
 *  Tentas acertar o numero gerado!
 * 
 * BS: 
 *  Antes de Rodar este programa, deves mudar o directorio dos ficheiros dentro da pasta 'file'
 *  De acordo a localizacao dos mesmos na sua maquina, para evitar erros como 'FileNotFoundException'
 * 
 * @author 
 * @author 
 * @author Manuel dos Santos
 * @see repositorio
 * 
 */
public class App {
     public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException, NullPointerException {
         menuPrincipal(); 
    }
 }
