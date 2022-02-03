package com.parobci.horehroncraft.entity;

import com.google.common.base.Supplier;
import com.parobci.horehroncraft.HorehronCraft;
import com.parobci.horehroncraft.entity.custom.RomakEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityList {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, HorehronCraft.MOD_ID);    

    public static final RegistryObject<EntityType<RomakEntity>> ROMAK = ENTITY_TYPES.register("romak", 
    () -> EntityType.Builder.<RomakEntity>of(RomakEntity::new, EntityClassification.MONSTER).sized(1.0F, 2.0F)
            .build(new ResourceLocation(HorehronCraft.MOD_ID, "romak").toString()));


    public static void register(IEventBus bus){
        ENTITY_TYPES.register(bus);
    }
}
