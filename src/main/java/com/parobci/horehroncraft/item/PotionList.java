package com.parobci.horehroncraft.item;

import com.parobci.horehroncraft.HorehronCraft;
import com.parobci.horehroncraft.item.custom.Kiahne;

import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionList {
    

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, HorehronCraft.MOD_ID);
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, HorehronCraft.MOD_ID);
    
    // EFFECTS
    public static final RegistryObject<Effect> KIAHNE_EFFECT = EFFECTS.register("kiahne_effect", 
            () -> new Kiahne.KiahneEffect(EffectType.HARMFUL, 0xEAEA00));
            
    // POTIONS
    public static final RegistryObject<Potion> KIAHNE_POTION = POTIONS.register("kiahne_potion", 
            () -> new Potion(new EffectInstance(KIAHNE_EFFECT.get(), 3600)));


    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
        EFFECTS.register(eventBus);
    }
}
