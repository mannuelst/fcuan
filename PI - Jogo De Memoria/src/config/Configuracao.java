/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.Serializable;



/**
 *
 * @author D'zign
 */
public class Configuracao implements Serializable {
    
    private int qntJogador;
    private int numeroRodada;
    private int qntDigitos;
    private int tempoMemorizacao;//Duracao do numero sorteadoa ate desaparecer!
    
     // Construtores

    public Configuracao(int qntJogador, int numeroRodada, int qntDigitos, int tempoMemorizacao) {
        this.qntJogador = qntJogador;
        this.numeroRodada = numeroRodada;
        this.qntDigitos = qntDigitos;
        this.tempoMemorizacao = tempoMemorizacao;
    }

    public Configuracao() {
    }
    
    
    // Metodos Getters E Setters
    public int getQntJogador() {
        return qntJogador;
    }

    public void setQntJogador(int qntJogador) {
        this.qntJogador = qntJogador;
    }
    
    public int getNumeroRodada() {
        return numeroRodada;
    }

    public void setNumeroRodada(int numeroRodada) {
        this.numeroRodada = numeroRodada;
    }
     
    public int getQntDigitos() {
        return qntDigitos;
    }

    public void setQntDigitos(int qntDigito) {
        this.qntDigitos = qntDigito;
    }
     
    //TimeUnit.SECONDS.sleep(Tempo De Espera em segundo);
    public int getTempoMemorizacao() {
        return tempoMemorizacao;
    }

    public void setTempoMemorizacao(int tempoMemorizacao) {
        this.tempoMemorizacao = tempoMemorizacao;
    }
    
   
    
   
    @Override
    public String toString() {
        return """
               CONFIGURACAO DO JOGO 
               \tQuantidade de Jogadores:""" + this.qntJogador 
                + "\n\tNumero de Rodadas: " + this.numeroRodada 
                + "\n\tQuantidade de Digitos: " + this.qntDigitos + "\n\tTemporizador: " + this.tempoMemorizacao + "segundos\n\n";
    }
    
       
       
}
