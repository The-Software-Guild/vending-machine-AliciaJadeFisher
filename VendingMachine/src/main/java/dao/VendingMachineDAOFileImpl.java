package dao;

import dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class which handles file access
 */
public class VendingMachineDAOFileImpl implements VendingMachineDAO
{
    private Map<String, Item> items = new HashMap<>();
    private static final String ITEM_FILE = "VendingMachine.txt";
    private static final String DELIMITER = "::";

    /**
     * Method which retrieves all items from the file
     * @return Map of items
     * @throws VendingMachineDAOException
     */
    @Override
    public Map<String, Item> getAllItems() throws VendingMachineDAOException
    {
        readItems();
        return items;
    }

    /**
     * Method which handles an item being vended
     * @param item - item object
     * @return item which was vended
     * @throws VendingMachineDAOException
     */
    @Override
    public Item vendItem(Item item) throws VendingMachineDAOException
    {
        readItems();
        Item vendedItem = items.put(item.getName(), item);
        writeItems();
        return vendedItem;
    }

    /**
     * Method which gets a single item
     * @param name - name of item
     * @return specified item
     * @throws VendingMachineDAOException
     * @throws NoItemInventoryException
     */
    @Override
    public Item getItem(String name) throws VendingMachineDAOException, NoItemInventoryException
    {
        readItems();
        try
        {
            // Gets the item from the map
            Item item = items.values().stream().filter((i) -> i.getName().equals(name)).findFirst().get();

            // Checks the quantity of the item
            // If it is out of stock, then throws a NoItemInventoryException
            // If it is in stock, then returns the item
            if(item.getQty() == 0)
            {
                throw new NoItemInventoryException("-_- This item is out of stock: " + name);
            }
            else
            {
                return item;
            }
        }
        catch (NoItemInventoryException e)
        {
            return null;
        }
    }

    /**
     * Method which reads all items from the file
     * @throws VendingMachineDAOException
     */
    public void readItems() throws VendingMachineDAOException
    {
        Scanner reader;

        try
        {
            // Instantiates the reader object
            reader = new Scanner(new BufferedReader(new FileReader(ITEM_FILE)));
        }
        catch (FileNotFoundException e)
        {
            throw new VendingMachineDAOException("-_- Could not load item data into memory", e);
        }

        // Loops through each line in the file
        while(reader.hasNextLine())
        {
            // Saves each item in the file to the map
            String currentLine = reader.nextLine();
            Item currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }

        reader.close();
    }

    /**
     * Method which writes items to the file
     * @throws VendingMachineDAOException
     */
    public void writeItems() throws VendingMachineDAOException
    {
        PrintWriter writer;

        try
        {
            // Instantiates the writer object
            writer = new PrintWriter(new FileWriter(ITEM_FILE));
        }
        catch (IOException e)
        {
            throw new VendingMachineDAOException("-_- Could not save item data", e);
        }

        // Loops through the items map
        for(Item item : items.values())
        {
            // Writes each item to a single line in the file
            String fileText = marshallItem(item);
            writer.println(fileText);
            writer.flush();
        }

        writer.close();
    }

    /**
     * Method which handles the unmarshalling of a single file line
     * @param itemText - single line from file
     * @return Item object from file line
     */
    public Item unmarshallItem(String itemText)
    {
        // Splits the line based on the delimiter
        // Uses each string part to create a new item object and return it
        String[] itemParts = itemText.split(DELIMITER);
        return new Item(itemParts[0], new BigDecimal(itemParts[1].toString()), Integer.parseInt(itemParts[2]));
    }

    /**
     * Method which handles the marshalling of a single item object
     * @param item - item to be written to the file
     * @return item as a single file line
     */
    public String marshallItem(Item item)
    {
        // Splits the item's attributes with delimiters and returns the String to save to the file
        return item.getName() + DELIMITER + item.getPrice() + DELIMITER + item.getQty();
    }
}
