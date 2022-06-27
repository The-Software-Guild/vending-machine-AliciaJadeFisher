package ui;

import dto.Coins;
import dto.Item;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineView
{
    public UserIO io;

    public VendingMachineView(UserIO io)
    {
        this.io = io;
    }

    public void printMainMenu(Map<String, Item> items)
    {
        io.print("-----------------------");
        for(String item: items.keySet())
        {
            Item currentItem = items.get(item);
            System.out.print(currentItem.getName() + " - " +  currentItem.getPrice());

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

    public BigDecimal getAmount()
    {
        double money = io.readDouble("How much money do you have?: ");
        return new BigDecimal(String.valueOf(money));
    }

    public String getItemChoice()
    {
        return io.readString("Which item would you like to buy? (Name of Item): ").toUpperCase();
    }

    public void displayChange(Map<Coins, Integer> change)
    {
        io.print("------- Change --------");
        io.print("Quarters: " + change.get(Coins.TWENTYFIVE));
        io.print("Dimes: " + change.get(Coins.TEN));
        io.print("Nickels: " + change.get(Coins.FIVE));
        io.print("Pennies: " + change.get(Coins.ONE));
    }

    public void displayMainBanner()
    {
        io.print("================= VENDING MACHINE =================");
    }

    public void displayEndBanner()
    {

        io.print("===================================================");
    }
}
