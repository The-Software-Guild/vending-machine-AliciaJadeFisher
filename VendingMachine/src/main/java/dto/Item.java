package dto;

import java.math.BigDecimal;

/**
 * Class which represents a single vending machine item
 */
public class Item
{
    // Attributes of Item
    private String name;
    private BigDecimal price;
    private int qty;

    /**
     * Main constructor
     * @param name - name of item
     * @param price - price of item
     * @param qty - current quantity
     */
    public Item(String name, BigDecimal price, int qty)
    {
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    /**
     * Retrieves the item's name
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Retrieves the item's price
     * @return price
     */
    public BigDecimal getPrice()
    {
        return price;
    }

    /**
     * Retrieves the item's quantity
     * @return qty
     */
    public int getQty()
    {
        return qty;
    }

    /**
     * Sets the item's quantity
     * @param qty - new quantity
     */
    public void setQty(int qty)
    {
        this.qty = qty;
    }
}
