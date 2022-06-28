package service;

/**
 * Exception Class which handles if a user has insufficient funds
 */
public class InsufficientFundsException extends Exception
{
    public InsufficientFundsException(String message)
    {
        super(message);
        System.out.println(message);
    }

}
