package service;
public class InsufficientFundsException extends Exception
{
    public InsufficientFundsException(String message)
    {
        super(message);
        System.out.println(message);
    }

}
