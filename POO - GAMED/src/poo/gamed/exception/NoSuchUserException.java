/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.gamed.exception;

/**
 *
 * @author FRIDSON FIRMINO
 */
@SuppressWarnings("serial")
public class NoSuchUserException extends Exception{
    public String mensagem;
    public NoSuchUserException() {
        mensagem = "Utente Não Existe";
    }
    
}
