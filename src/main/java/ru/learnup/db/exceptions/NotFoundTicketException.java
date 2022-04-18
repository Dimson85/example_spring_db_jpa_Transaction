package ru.learnup.db.exceptions;

public class NotFoundTicketException extends Exception{
    public NotFoundTicketException(String message) {
        super(message);
    }
}
