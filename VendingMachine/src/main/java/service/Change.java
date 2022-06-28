package service;

import dto.Coins;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Class which handles the user's change
 */
public class Change
{
    BigDecimal amount, price;
    Coins coins = Coins.QUARTER;

    /**
     * Main constructor which saves the user's amount and the item's price as pennies
     * @param amount - user's amount
     * @param price - item's price
     */
    public Change(BigDecimal amount, BigDecimal price)
    {
        this.amount = amount.multiply(BigDecimal.valueOf(100));;
        this.price = price.multiply(BigDecimal.valueOf(100));
    }

    /**
     * Method which calculates a user's change
     * @return a map of coins and their quantity
     */
    public Map<Coins,Integer> getChange()
    {
        // Creates a new map and sets all coin quantities to 0
        Map<Coins, Integer> changeQty = new HashMap<>();
        changeQty.put(Coins.QUARTER, 0);
        changeQty.put(Coins.DIME, 0);
        changeQty.put(Coins.NICKEL, 0);
        changeQty.put(Coins.PENNY, 0);

        // Gets the total change
        BigDecimal change = amount.subtract(price);

        // Loop which runs until change is 0
        while(true)
        {
            // Switch statement which compares the change to each coin value
            switch (coins)
            {
                // Checks if a coin value fits into the change value
                // If it does then increments the quantity of that coin and subtracts it from change
                // If it doesn't, then sets the current coin value to the next smaller value
                case QUARTER:
                    if(change.compareTo(BigDecimal.valueOf(25)) >= 0)
                    {
                        change = change.subtract(new BigDecimal(25));
                        int count = changeQty.get(Coins.QUARTER) + 1;
                        changeQty.put(Coins.QUARTER, count);
                    }
                    else
                    {
                        coins = Coins.DIME;
                    }
                    break;
                case DIME:
                    if(change.compareTo(BigDecimal.valueOf(10)) >= 0)
                    {
                        change = change.subtract(new BigDecimal(10));
                        int count = changeQty.get(Coins.DIME) + 1;
                        changeQty.put(Coins.DIME, count);
                    }
                    else
                    {
                        coins = Coins.NICKEL;
                    }
                    break;
                case NICKEL:
                    if(change.compareTo(BigDecimal.valueOf(5)) >= 0)
                    {
                        change = change.subtract(new BigDecimal(55));
                        int count = changeQty.get(Coins.NICKEL) + 1;
                        changeQty.put(Coins.NICKEL, count);
                    }
                    else
                    {
                        coins = Coins.PENNY;
                    }
                    break;
                case PENNY:
                    if(change.compareTo(BigDecimal.valueOf(1)) >= 0)
                    {
                        change = change.subtract(new BigDecimal(1));
                        int count = changeQty.get(Coins.PENNY) + 1;
                        changeQty.put(Coins.PENNY, count);
                    }
                    else
                    {
                        return changeQty;
                    }
                    break;
            }
        }
    }
}
