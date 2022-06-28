package dao;

/**
 * Exception Class which handles an item being out of stock
 */
public class NoItemInventoryException extends Exception
{
    public NoItemInventoryException(String message)
    {
        System.out.println(message);
    }
}
