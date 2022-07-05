package service;

import dao.NoItemInventoryException;
import dao.VendingMachineDAO;
import dao.VendingMachineDAOException;
import dto.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class VendingMachineDaoStubImpl implements VendingMachineDAO
{
    private Map<String, Item> items = new HashMap<>();

    public VendingMachineDaoStubImpl()
    {
        Item validItem = new Item("A1", "CHOCOLATE BAR", BigDecimal.valueOf(1.10), 5);
        Item invalidItem = new Item("A2", "CRISPS", BigDecimal.valueOf(1.0), 0);

        items.put(validItem.getId(), validItem);
        items.put(invalidItem.getId(), invalidItem);
    }

    @Override
    public Map<String, Item> getAllItems() throws VendingMachineDAOException
    {
        return items;
    }

    @Override
    public Item vendItem(Item item) throws VendingMachineDAOException
    {

        for(Item mapItem : items.values())
        {
            if(mapItem.getId().equals(item.getId()))
            {
                return mapItem;
            }
        }

        return null;
    }

    @Override
    public Item getItem(String id) throws VendingMachineDAOException, NoItemInventoryException
    {
        for(Item mapItem : items.values())
        {
            if(mapItem.getId().equals(id))
            {
                return mapItem;
            }
        }

        return null;
    }
}
