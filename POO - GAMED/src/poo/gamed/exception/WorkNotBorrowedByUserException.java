package poo.gamed.exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Benvindo Alves
 */
@SuppressWarnings("serial")
public class WorkNotBorrowedByUserException extends Exception{
    public String mensagem;
    public WorkNotBorrowedByUserException() {
        mensagem = "Utente Não Requisitou Esta Obra!!!";
    }

}
