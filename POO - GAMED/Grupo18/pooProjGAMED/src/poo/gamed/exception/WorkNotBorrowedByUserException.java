package poo.gamed.exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dzign
 */
@SuppressWarnings("serial")
public class WorkNotBorrowedByUserException extends Exception{
    public String exSMS;
    public WorkNotBorrowedByUserException() {
       exSMS = "Utente Não Requisitou Esta Obra!!!";
    }

}
