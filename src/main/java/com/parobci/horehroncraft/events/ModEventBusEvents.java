package com.parobci.horehroncraft.events;

import javax.annotation.Nonnull;

import com.parobci.horehroncraft.HorehronCraft;
import com.parobci.horehroncraft.entity.EntityList;
import com.parobci.horehroncraft.entity.custom.MestanEntity;
import com.parobci.horehroncraft.entity.custom.RomakEntity;
import com.parobci.horehroncraft.entity.render.MestanRenderer;
import com.parobci.horehroncraft.entity.render.RomakRenderer;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.loot.LootTables;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HorehronCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event){
        RenderingRegistry.registerEntityRenderingHandler(EntityList.ROMAK.get(), RomakRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityList.MESTAN_0.get(), MestanRenderer::new);
    }

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityList.ROMAK.get(), RomakEntity.setAttributes().build());
        event.put(EntityList.MESTAN_0.get(), MestanEntity.setAttributes().build());
    }

    @SubscribeEvent
    public static void registerModifierSerialzers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event){
        event.getRegistry().registerAll();
    }
}
