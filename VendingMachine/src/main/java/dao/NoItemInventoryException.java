package dao;
public class NoItemInventoryException extends Exception
{
    public NoItemInventoryException(String message)
    {
        System.out.println(message);
    }
}
