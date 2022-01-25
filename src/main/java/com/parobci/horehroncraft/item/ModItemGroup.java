package com.parobci.horehroncraft.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup HOREHRONCRAFT_GROUP = new ItemGroup("horehroncraftTab") 
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.TEMNA_HMOTA.get());
        }
    };
}
