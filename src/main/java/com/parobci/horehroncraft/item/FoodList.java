package com.parobci.horehroncraft.item;

import net.minecraft.item.Food;

public class FoodList {
    public static final Food PIZZA = (new Food.Builder()).nutrition(12).saturationMod(1.5f).build();
    public static final Food KEL = (new Food.Builder()).nutrition(1).saturationMod(0.2f).build();
}
