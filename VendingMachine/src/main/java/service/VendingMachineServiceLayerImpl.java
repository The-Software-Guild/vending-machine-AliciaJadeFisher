package service;

import dao.*;
import dto.Coins;
import dto.Item;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer
{
    VendingMachineDAO dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDAO dao, VendingMachineAuditDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public Map<String, Item> getAllItems() throws VendingMachineDAOException
    {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String id) throws VendingMachineDAOException, NoItemInventoryException
    {
        Item item = dao.getItem(id);

        try{
            if(item.getQty() == 0)
            {
                throw new NoItemInventoryException("-_- This item is out of stock: " + item.getName());
            }
        } catch (NoItemInventoryException e){
            return null;
        }

        return item;
    }

    @Override
    public Map<Coins, Integer> vendItem(Item item, BigDecimal amount) throws VendingMachineDAOException, VendingMachinePersistenceException
    {
        if(item == null)
        {
            return null;
        }

        BigDecimal price = item.getPrice();

        int compareAmount = amount.compareTo(price);

        try
        {
            if(compareAmount < 0)
            {
                throw new InsufficientFundsException("-_- Insufficient funds, cannot vend this item: " + amount);
            }

            item.setQty(item.getQty() - 1);
            dao.vendItem(item);
            auditDao.writeAuditEntry(item.toString());

            Change change = new Change(amount, price);
            return change.getChange();
        }
        catch (InsufficientFundsException e)
        {
            return null;
        }
    }
}