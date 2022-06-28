package ui;

import dto.Coins;
import dto.Item;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Class which handles all program output
 */
public class VendingMachineView
{
    public UserIO io;

    /**
     * Constructor which sets the UserIO class object
     * @param io - UserIO implementation
     */
    public VendingMachineView(UserIO io)
    {
        this.io = io;
    }

    /**
     * Method which displays the vending machine items to the user
     * @param items - Map of items and their names
     */
    public void printMainMenu(Map<String, Item> items)
    {
        io.print("-----------------------");

        // Loops through each item in the map and displays the name and price
        for(String item: items.keySet())
        {
            Item currentItem = items.get(item);
            System.out.print(currentItem.getName() + " - " +  currentItem.getPrice());

            // If the quantity of an item is 0, then it displays that that item is out of stock
            if(currentItem.getQty() == 0)
            {
                System.out.println(" (CURRENTLY OUT OF STOCK)");
            }
            else
            {
                System.out.println();
            }
        }
        io.print("0. Exit");
        io.print("-----------------------");
    }

    /**
     * Method which gets the user's amount of money input
     * @return amount - user's amount of money
     */
    public BigDecimal getAmount()
    {
        double money = io.readDouble("How much money do you have?: ");
        return new BigDecimal(String.valueOf(money));
    }

    /**
     * Method which gets an item choice from the user
     * @return name - choice of item
     */
    public String getItemChoice()
    {
        return io.readString("Which item would you like to buy? (Name of Item): ").toUpperCase();
    }

    /**
     * Method which displays the user's change
     * @param change - map of coins and their quantity
     */
    public void displayChange(Map<Coins, Integer> change)
    {
        io.print("------- Change --------");
        io.print("Quarters: " + change.get(Coins.QUARTER));
        io.print("Dimes: " + change.get(Coins.DIME));
        io.print("Nickels: " + change.get(Coins.NICKEL));
        io.print("Pennies: " + change.get(Coins.PENNY));
    }

    /**
     * Method which displays the beginning banner
     */
    public void displayMainBanner()
    {
        io.print("================= VENDING MACHINE =================");
    }

    /**
     * Method which displays the ending banner
     */
    public void displayEndBanner()
    {

        io.print("===================================================");
    }
}
