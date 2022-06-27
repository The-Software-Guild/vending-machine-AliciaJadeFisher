package dto;

import java.math.BigDecimal;

public class Item
{
    private String name;
    private BigDecimal price;
    private int qty;

    public Item(String name, BigDecimal price, int qty)
    {
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public String getName()
    {
        return name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }
}
