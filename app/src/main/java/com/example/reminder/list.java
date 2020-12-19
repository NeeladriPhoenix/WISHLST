package com.example.reminder;

public class list {

    String item;
    String quantity;
    String price;

    public list() {

    }

    public list(String it, String qt, String pr) {

        this.item= it;
        this.quantity= qt;
        this.price= pr;
    }



    public String getItem() {
        return item;
    }



    public String getQuantity() {
        return quantity;
    }



    public String getPrice() {
        return price;
    }

    public void setQuantity(String mquantity) {
        this.quantity = mquantity;
    }

    public void setPrice(String mprice) {
        this.price = mprice;
    }

    public void setItem(String mitem) {
        this.item = mitem;
    }


}