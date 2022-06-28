package dao;

/**
 * Exception Class which handles file access exceptions
 */
public class VendingMachineDAOException extends Exception
{
    public VendingMachineDAOException(String message)
    {
        super(message);
    }

    public VendingMachineDAOException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
