/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.gamed.exception;

/**
 *
 * @author Benvindo Alves
 */
@SuppressWarnings("serial")
public class NoSuchWorkException extends Exception{
    
    public String mensagem;
    public NoSuchWorkException() {
        mensagem = "Obra Não Existe!!!";
    }
    
}
