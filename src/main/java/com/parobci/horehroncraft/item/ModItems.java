package com.parobci.horehroncraft.item;

import com.parobci.horehroncraft.HorehronCraft;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =  
        DeferredRegister.create(ForgeRegistries.ITEMS, HorehronCraft.MOD_ID);


    public static final RegistryObject<Item> TEMNA_HMOTA = ITEMS.register("koncentrovana_temna_hmota", 
        () -> new Item(new Item.Properties().tab(ModItemGroup.HOREHRONCRAFT_GROUP)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}