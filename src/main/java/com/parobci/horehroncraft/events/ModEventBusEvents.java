package com.parobci.horehroncraft.events;

import com.parobci.horehroncraft.HorehronCraft;
import com.parobci.horehroncraft.entity.EntityList;
import com.parobci.horehroncraft.entity.custom.RomakEntity;
import com.parobci.horehroncraft.entity.render.RomakRenderer;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
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
    }

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityList.ROMAK.get(), RomakEntity.createAttributes().build());
    }
}
