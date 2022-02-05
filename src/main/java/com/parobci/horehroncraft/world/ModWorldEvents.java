package com.parobci.horehroncraft.world;

import com.parobci.horehroncraft.HorehronCraft;
import com.parobci.horehroncraft.world.gen.ModEntityGeneration;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HorehronCraft.MOD_ID)
public class ModWorldEvents {
    
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event){
        ModEntityGeneration.onEntitySpawn(event);
    }
}
