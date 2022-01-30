package com.parobci.horehroncraft.item;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodList {
    public static final Food PIZZA = (new Food.Builder()).nutrition(12).saturationMod(1.5f).build();
    public static final Food KEL = (new Food.Builder()).nutrition(1).saturationMod(0.2f).build();
    public static final Food OVOS = (new Food.Builder()).nutrition(2).saturationMod(0.4f).fast().build();

    public static final Food VAZELINA = (new Food.Builder()).effect(
        () -> new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 2), 1f).effect(
        () -> new EffectInstance(Effects.CONFUSION, 260, 1), 1f).nutrition(0).alwaysEat().build();
}
