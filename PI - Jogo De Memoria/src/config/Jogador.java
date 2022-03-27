
package config;

import java.io.Serializable;

/**
 *
 * @author D'zign
 */
public class Jogador implements Serializable, Comparable<Jogador>{
    private static int idGerador = 0;
    private  int idJogador;
    private String nome;
    private String genero;
    private int pontuacao=0;
    
   
    
    //Construtores da Classe Jogador
    public Jogador(String nome, String genero) {
        this.idJogador = (idGerador+1);
        this.nome = nome;
        this.genero = genero;
        this.pontuacao = pontuacao;
        idGerador++; 
    }
    
    public Jogador() {}
    
    
   //Metodos Sets e Gets
   public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int codJogador) {
        this.idJogador = codJogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    /**
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "\nJOGADOR "+ this.getIdJogador() + 
                "\n\tNome: " + this.getNome() + 
                "\n\tGenero: " +this.getGenero()+
                "\n\tPontuacao:" + this.getPontuacao()+"\n";
    }
    /**
     * 
     * @param j
     * @return 
     */
    @Override
    public int compareTo(Jogador j) {//Comparacao pontuacao do maior ao Menor
        return (j.getPontuacao()- this.getPontuacao());
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
