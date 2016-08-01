package com.bitrubio.prototipoebitrubio;

/**
 * Created by Orion on 29/06/2016.
 */
public class Item{
    public final String text;
    public final int icon;
    public Item(String text, Integer icon) {
        this.text = text;
        this.icon = icon;
    }
    @Override
    public String toString() {
        return text;
    }
}