package com.parobci.horehroncraft.world.gen;

import java.util.Arrays;
import java.util.List;

import com.parobci.horehroncraft.entity.EntityList;

import org.apache.http.config.Registry;

import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModEntityGeneration {

    public static void onEntitySpawn(final BiomeLoadingEvent event){
        addEntityToAllBiomes(event.getSpawns(), EntityList.ROMAK.get(), 30, 2, 4);
    }

    private static void addEntityToAllBiomeExceptThese(BiomeLoadingEvent event, EntityType<?> type, int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes){
        boolean isBiomeSelected = Arrays.stream(biomes).map(RegistryKey::location).map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

        if (!isBiomeSelected) {
            addEntityToAllBiomes(event.getSpawns(), type, weight, minCount, maxCount);
        }
    }

    private static void addEntityToSpecificBiomes(BiomeLoadingEvent event, EntityType<?> type, int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes){
        boolean isBiomeSelected = Arrays.stream(biomes).map(RegistryKey::location).map(Object::toString)
                .anyMatch(s -> s.equals(event.getName().toString()));

        if (isBiomeSelected) {
            addEntityToAllBiomes(event.getSpawns(), type, weight, minCount, maxCount);
        }
    }

    private static void addEntityToAllBiomes(MobSpawnInfoBuilder spawns, EntityType<?> type, int weight, int minCount, int maxCount){
        List<MobSpawnInfo.Spawners> base = spawns.getSpawner(type.getCategory());
        base.add(new MobSpawnInfo.Spawners(type, weight, minCount, maxCount));
    }
}
