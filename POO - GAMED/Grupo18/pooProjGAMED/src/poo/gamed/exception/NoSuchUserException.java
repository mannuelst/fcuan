/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.gamed.exception;

/**
 *
 * @author Dzign
 */
@SuppressWarnings("serial")
public class NoSuchUserException extends Exception{
    public String exSMS;
    public NoSuchUserException() {
       exSMS = "Utente Inexistente!";
    }
    
}
