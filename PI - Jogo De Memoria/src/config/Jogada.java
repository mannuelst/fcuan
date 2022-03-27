package config;

import java.io.Serializable;
import java.util.ArrayList;
import repositorio.JogadorRepositorio;

/**
 *
 * @author D'zign
 */

public class Jogada implements Serializable{
   /* Configuracao config = new Configuracao();
    private int qtdDig = config.getQntDigitos();*/
    private static int idGeradorJogada = 0;
    private int numeroJogada;
    private int idJogadorNaJogada;//Código do Jogador
    private int numeroSorteado;
    private int numeroDigitado;
    private boolean isCerto = false;
    private int pontuacao;//variavel para somar pontuacao;
    
   
    // CONSTRUTORES

    public Jogada(int idJogadorNaJogada, int numeroSorteado, int numeroDigitado, boolean isCerto, int pontuacao) {
        this.numeroJogada = (idGeradorJogada+1);
        this.idJogadorNaJogada = idJogadorNaJogada;
        this.numeroSorteado = numeroSorteado;
        this.numeroDigitado = numeroDigitado;
        this.isCerto = isCerto;
        this.pontuacao = pontuacao;
        idGeradorJogada++;
    }

    
    public Jogada() {
    }
    
    //METODOS GETS E SETTERS
    public int getNumeroJogada() {
        return numeroJogada;
    }

    public void setNumeroJogada(int numeroJogada) {
        this.numeroJogada = numeroJogada;
    }

    public int getIdJogadorNaJogada() {
        return idJogadorNaJogada;
    }

    public void setIdJogadorNaJogada(int idJogadorNaJogada) {
        this.idJogadorNaJogada = idJogadorNaJogada;
    }

    public int getNumeroSorteado() {
        return numeroSorteado;
    }

    public void setNumeroSorteado(int numeroSorteado) {
        this.numeroSorteado = numeroSorteado;
    }

    public int getNumeroDigitado() {
        return numeroDigitado;
    }

    public void setNumeroDigitado(int numeroDigitado) {
        this.numeroDigitado = numeroDigitado;
    }

    public boolean isIsCerto() {
        return isCerto;
    }

    public void setIsCerto(boolean isCerto) {
        this.isCerto = isCerto;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public String toString() {
        return numeroJogada+"a Jogada | Numero Sorteado: "+ numeroSorteado 
                +"\n\tJogador numero:" + idJogadorNaJogada +
                "\n\t\tNumero Digitado: " +numeroDigitado + 
                "\n\t\tAcertou: "+ isCerto
                + "\n\t\tPontuacao:" + pontuacao ;
    }
    
    
   

    
    
    
       
        
    
}
