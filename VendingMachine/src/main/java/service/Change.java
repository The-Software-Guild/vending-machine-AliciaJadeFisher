package service;

import dto.Coins;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Change
{
    BigDecimal amount, price;
    Coins coins = Coins.TWENTYFIVE;

    public Change(BigDecimal amount, BigDecimal price)
    {
        this.amount = amount.multiply(BigDecimal.valueOf(100));;
        this.price = price.multiply(BigDecimal.valueOf(100));
    }

    public Map<Coins,Integer> getChange()
    {
        Map<Coins, Integer> changeQty = new HashMap<>();
        changeQty.put(Coins.TWENTYFIVE, 0);
        changeQty.put(Coins.TEN, 0);
        changeQty.put(Coins.FIVE, 0);
        changeQty.put(Coins.ONE, 0);

        BigDecimal change = amount.subtract(price);
        int compareChange = change.compareTo(BigDecimal.valueOf(0));

        while(true)
        {
            switch (coins)
            {
                case TWENTYFIVE:
                    if(change.compareTo(BigDecimal.valueOf(25)) >= 0)
                    {
                        change = change.subtract(new BigDecimal(25));
                        int count = changeQty.get(Coins.TWENTYFIVE) + 1;
                        changeQty.put(Coins.TWENTYFIVE, count);
                    }
                    else
                    {
                        coins = Coins.TEN;
                    }
                    break;
                case TEN:
                    if(change.compareTo(BigDecimal.valueOf(10)) >= 0)
                    {
                        change = change.subtract(new BigDecimal(10));
                        int count = changeQty.get(Coins.TEN) + 1;
                        changeQty.put(Coins.TEN, count);
                    }
                    else
                    {
                        coins = Coins.FIVE;
                    }
                    break;
                case FIVE:
                    if(change.compareTo(BigDecimal.valueOf(5)) >= 0)
                    {
                        change = change.subtract(new BigDecimal(55));
                        int count = changeQty.get(Coins.FIVE) + 1;
                        changeQty.put(Coins.FIVE, count);
                    }
                    else
                    {
                        coins = Coins.ONE;
                    }
                    break;
                case ONE:
                    if(change.compareTo(BigDecimal.valueOf(1)) >= 0)
                    {
                        change = change.subtract(new BigDecimal(1));
                        int count = changeQty.get(Coins.ONE) + 1;
                        changeQty.put(Coins.ONE, count);
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
