package dao;

import dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class VendingMachineDAOFileImpl implements VendingMachineDAO
{
    private Map<String, Item> items = new HashMap<>();
    private static final String ITEM_FILE = "VendingMachine.txt";
    private static final String DELIMITER = "::";

    @Override
    public Map<String, Item> getAllItems() throws VendingMachineDAOException
    {
        readItems();
        return items;
    }

    @Override
    public Item vendItem(Item item) throws VendingMachineDAOException
    {
        readItems();
        Item vendedItem = items.put(item.getName(), item);
        writeItems();
        return vendedItem;
    }

    @Override
    public Item getItem(String id) throws VendingMachineDAOException, NoItemInventoryException
    {
        readItems();
        try
        {
            Item item = items.get(id);

            if(item.getQty() == 0)
            {
                throw new NoItemInventoryException("-_- This item is out of stock: " + id);
            }
            else
            {
                return item;
            }
        }
        catch (NoItemInventoryException | NoSuchElementException e)
        {
            return null;
        }
    }
    public void readItems() throws VendingMachineDAOException
    {
        Scanner reader;

        try
        {
            reader = new Scanner(new BufferedReader(new FileReader(ITEM_FILE)));
        }
        catch (FileNotFoundException e)
        {
            throw new VendingMachineDAOException("-_- Could not load item data into memory", e);
        }

        while(reader.hasNextLine())
        {
            String currentLine = reader.nextLine();
            Item currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getId(), currentItem);
        }

        reader.close();
    }

    public void writeItems() throws VendingMachineDAOException
    {
        PrintWriter writer;

        try
        {
            writer = new PrintWriter(new FileWriter(ITEM_FILE));
        }
        catch (IOException e)
        {
            throw new VendingMachineDAOException("-_- Could not save item data", e);
        }

        for(Item item : items.values())
        {
            String fileText = marshallItem(item);
            writer.println(fileText);
            writer.flush();
        }

        writer.close();
    }

    public Item unmarshallItem(String itemText)
    {
        String[] itemParts = itemText.split(DELIMITER);
        return new Item(itemParts[0], itemParts[1], new BigDecimal(itemParts[2].toString()),
                Integer.parseInt(itemParts[3]));
    }


    public String marshallItem(Item item)
    {
        return item.getId() + DELIMITER + item.getName() + DELIMITER + item.getPrice() + DELIMITER + item.getQty();
    }
}
