package com.parobci.horehroncraft.item;

import com.parobci.horehroncraft.HorehronCraft;
import com.parobci.horehroncraft.item.custom.Cepin;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemList {
    public static final DeferredRegister<Item> ITEMS =  
        DeferredRegister.create(ForgeRegistries.ITEMS, HorehronCraft.MOD_ID);

    public static final Item.Properties HOREHRONCRAFT_TAB = new Item.Properties().tab(ModItemGroup.HOREHRONCRAFT_GROUP);

                // ITEMS
    public static final RegistryObject<Item> TEMNA_HMOTA = ITEMS.register("koncentrovana_temna_hmota", 
            () -> new Item(HOREHRONCRAFT_TAB));


                // FOOD
    public static final RegistryObject<Item> PIZZA = ITEMS.register("pizza", 
            () -> new Item(HOREHRONCRAFT_TAB.food(FoodList.PIZZA)));

    public static final RegistryObject<Item> KEL = ITEMS.register("kel",
            () -> new Item(HOREHRONCRAFT_TAB.food(FoodList.KEL)));

    public static final RegistryObject<Item> OVOS = ITEMS.register("ovos",
            () -> new Item(HOREHRONCRAFT_TAB.food(FoodList.OVOS)));


                // ARMOR
    public static final RegistryObject<Item> DREVAKY = ITEMS.register("drevaky",
            () -> new ArmorItem(ArmorMaterials.DREVAKY, EquipmentSlotType.FEET, HOREHRONCRAFT_TAB));

                // Tools
                //TODO: Adjust harverst level
    public static final RegistryObject<Item> CEPIN = ITEMS.register("cepin",
            () -> new Cepin(2.5f, -3f, ToolMaterials.CEPIN, HOREHRONCRAFT_TAB));       

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}