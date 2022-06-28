package dao;

/**
 * Exception Class which handles data persistence exceptions
 */
public class VendingMachinePersistenceException extends Exception
{
    public VendingMachinePersistenceException(String message)
    {
        super(message);
    }

    public VendingMachinePersistenceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
