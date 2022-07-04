package dto;

import java.math.BigDecimal;

public class Item
{
    private String id,name;
    private BigDecimal price;
    private int qty;

    public Item(String id,String name, BigDecimal price, int qty)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public String getId(){return id;}

    public String getName()
    {
        return name;
    }

    public BigDecimal getPrice()
    {
        return price;
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
