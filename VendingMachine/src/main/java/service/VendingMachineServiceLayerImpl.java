package service;

import dao.*;
import dto.Coins;
import dto.Item;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Class which handles all the business logic for the program
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer
{
    VendingMachineDAO dao;
    private VendingMachineAuditDao auditDao;

    /**
     * Main constructor which sets the VendingMachineDao and VendingMachineAuditDao implementations
     * @param dao - program data access object
     * @param auditDao - program audit data access object
     */
    public VendingMachineServiceLayerImpl(VendingMachineDAO dao, VendingMachineAuditDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    /**
     * Method which gets all items using the dao
     * @return a Map of items
     * @throws VendingMachineDAOException
     */
    @Override
    public Map<String, Item> getAllItems() throws VendingMachineDAOException
    {
        return dao.getAllItems();
    }

    /**
     * Method which gets a single item using the dao
     * @param name - name of item
     * @return specified item
     * @throws VendingMachineDAOException
     * @throws NoItemInventoryException
     */
    @Override
    public Item getItem(String name) throws VendingMachineDAOException, NoItemInventoryException
    {
        return dao.getItem(name);
    }

    /**
     * Method which handles the vending of an item
     *
     * @param item   - name of item to vend
     * @param amount - user's amount of money
     * @return
     * @throws VendingMachineDAOException
     * @throws InsufficientFundsException
     * @throws NoItemInventoryException
     */
    @Override
    public Map<Coins, Integer> vendItem(Item item, BigDecimal amount) throws VendingMachineDAOException, InsufficientFundsException, NoItemInventoryException
    {
        // Gets the item and price of item
        BigDecimal price = item.getPrice();

        // Compares the item's price to the user's amount of money
        int compareAmount = amount.compareTo(price);

        try
        {
            // Checks if the user has insufficient funds, if it does then throws the relevant error
            if(compareAmount < 0)
            {
                throw new InsufficientFundsException("-_- Insufficient funds, cannot vend this item: " + amount);
            }

            // Decrements the qty of vended item
            item.setQty(item.getQty() - 1);
            dao.vendItem(item);

            // Calls the method get the user's change
            Change change = new Change(amount, price);
            return change.getChange();
        }
        catch (InsufficientFundsException e)
        {
            // Exits the system if they have insufficient funds
            System.exit(1);
        }

        return null;
    }
}
