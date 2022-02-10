package com.parobci.horehroncraft.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.parobci.horehroncraft.entity.EntityList;

import org.apache.http.config.Registry;

import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.RegistryBuilder;

public class ModEntityGeneration {
    
    public static void onEntitySpawn(final BiomeLoadingEvent event){
        addEntityToAllBiomesExceptThese(event, EntityList.ROMAK.get(), 50, 3, 8, 
            //? Nether biomes
            Biomes.NETHER_WASTES, Biomes.CRIMSON_FOREST, Biomes.WARPED_FOREST, Biomes.SOUL_SAND_VALLEY, Biomes.BASALT_DELTAS, 
            //? End biomes
            Biomes.END_BARRENS, Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS, Biomes.SMALL_END_ISLANDS, Biomes.THE_END);
        
    }

    private static void addEntityToAllBiomesExceptThese(BiomeLoadingEvent event, EntityType<?> type,
            int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes) {
        // Goes through each entry in the biomes and sees if it matches the current
        // biome we are loading
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
