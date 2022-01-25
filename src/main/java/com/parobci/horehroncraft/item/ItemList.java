package com.parobci.horehroncraft.item;

import com.parobci.horehroncraft.HorehronCraft;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemList {
    public static final DeferredRegister<Item> ITEMS =  
        DeferredRegister.create(ForgeRegistries.ITEMS, HorehronCraft.MOD_ID);

    public static final Item.Properties HOREHRONCRAFT_TAB = new Item.Properties().tab(ModItemGroup.HOREHRONCRAFT_GROUP);

    public static final RegistryObject<Item> TEMNA_HMOTA = ITEMS.register("koncentrovana_temna_hmota", 
            () -> new Item(HOREHRONCRAFT_TAB));

    public static final RegistryObject<Item> PIZZA = ITEMS.register("pizza", 
            () -> new Item(HOREHRONCRAFT_TAB.food(FoodList.PIZZA)));

    public static final RegistryObject<Item> KEL = ITEMS.register("kel",
            () -> new Item(HOREHRONCRAFT_TAB.food(FoodList.KEL)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}