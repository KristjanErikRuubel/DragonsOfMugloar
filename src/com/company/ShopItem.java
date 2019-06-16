package com.company;

import java.util.ArrayList;

public class ShopItem {

    public String id;
    public String name;
    public Integer cost;

    public ShopItem(String id, String name, Integer cost){
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public static String shopForItems(ArrayList<ShopItem> ShopItems, Integer gold){
        String itemId = "";
        Integer currentItemValue = 0;
        ArrayList<ShopItem> affordableItemList = new ArrayList<>();

        for (ShopItem Item: ShopItems) {
            if(Item.cost <= gold){
                affordableItemList.add(Item);
            }
        }
        for (ShopItem affordableShopItem :affordableItemList) {
            if (affordableShopItem.cost >  currentItemValue){
                itemId = affordableShopItem.id;
            }

        }

        return itemId;
    }
}
