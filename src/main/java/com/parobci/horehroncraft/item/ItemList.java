package com.parobci.horehroncraft.item;

import com.parobci.horehroncraft.HorehronCraft;
import com.parobci.horehroncraft.armor.ArmorMaterials;
import com.parobci.horehroncraft.entity.EntityList;
import com.parobci.horehroncraft.item.custom.Cep;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemList {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            HorehronCraft.MOD_ID);

    // ITEMS
    public static final RegistryObject<Item> TEMNA_HMOTA = ITEMS.register("koncentrovana_temna_hmota",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HOREHRONCRAFT_GROUP)));

    public static final RegistryObject<Item> KLUC = ITEMS.register("kluc",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HOREHRONCRAFT_GROUP).stacksTo(1)));

    // FOOD
    public static final RegistryObject<Item> PIZZA = ITEMS.register("pizza",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HOREHRONCRAFT_GROUP).food(FoodList.PIZZA)));

    public static final RegistryObject<Item> KEL = ITEMS.register("kel",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HOREHRONCRAFT_GROUP).food(FoodList.KEL)));

    public static final RegistryObject<Item> OVOS = ITEMS.register("ovos",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HOREHRONCRAFT_GROUP).food(FoodList.OVOS)));

    public static final RegistryObject<Item> VAZELINA = ITEMS.register("vazelina",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HOREHRONCRAFT_GROUP).food(FoodList.VAZELINA)));

    // ARMOR
    public static final RegistryObject<Item> DREVAKY = ITEMS.register("drevaky",
            () -> new ArmorItem(ArmorMaterials.DREVAKY, EquipmentSlotType.FEET,
                    new Item.Properties().tab(ModItemGroup.HOREHRONCRAFT_GROUP)));

    // Tools
    // TODO: Adjust harverst level
    public static final RegistryObject<Item> CEP = ITEMS.register("cep",
            () -> new Cep(2.5f, -3f, ToolMaterials.CEP,
                    new Item.Properties().tab(ModItemGroup.HOREHRONCRAFT_GROUP)));

    // Spawn Eggs
    public static final RegistryObject<ForgeSpawnEggItem> ROMAK_SPAWN_EGG = ITEMS.register("romak_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityList.ROMAK, 0x5B402B, 0x302F28,
                    new Item.Properties().tab(ModItemGroup.HOREHRONCRAFT_GROUP)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}