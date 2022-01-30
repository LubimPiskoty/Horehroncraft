package com.parobci.horehroncraft.item;

import com.parobci.horehroncraft.HorehronCraft;
import com.parobci.horehroncraft.armor.ArmorMaterials;
import com.parobci.horehroncraft.entity.EntityList;
import com.parobci.horehroncraft.item.custom.Cepin;
import com.parobci.horehroncraft.item.custom.RomakSpawnEgg;

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

    public static final RegistryObject<Item> KLUC = ITEMS.register("kluc", 
            () -> new Item(HOREHRONCRAFT_TAB));


                // FOOD
    public static final RegistryObject<Item> PIZZA = ITEMS.register("pizza", 
            () -> new Item(HOREHRONCRAFT_TAB.food(FoodList.PIZZA)));

    public static final RegistryObject<Item> KEL = ITEMS.register("kel",
            () -> new Item(HOREHRONCRAFT_TAB.food(FoodList.KEL)));

    public static final RegistryObject<Item> OVOS = ITEMS.register("ovos",
            () -> new Item(HOREHRONCRAFT_TAB.food(FoodList.OVOS)));

    public static final RegistryObject<Item> VAZELINA = ITEMS.register("vazelina",
            () -> new Item(HOREHRONCRAFT_TAB.food(FoodList.VAZELINA)));


                // ARMOR
    public static final RegistryObject<Item> DREVAKY = ITEMS.register("drevaky",
            () -> new ArmorItem(ArmorMaterials.DREVAKY, EquipmentSlotType.FEET, HOREHRONCRAFT_TAB));

                // Tools
                //TODO: Adjust harverst level
    public static final RegistryObject<Item> CEPIN = ITEMS.register("cepin",
            () -> new Cepin(2.5f, -3f, ToolMaterials.CEPIN, HOREHRONCRAFT_TAB));       

                //Spawn Eggs
    // public static final RegistryObject<Item> ROMAK_SPAWN_EGG = ITEMS.register("romak_spawn_egg", 
    //         () -> new RomakSpawnEgg(EntityList.ROMAK, 0x5B402B,0x302F28, HOREHRONCRAFT_TAB.stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}