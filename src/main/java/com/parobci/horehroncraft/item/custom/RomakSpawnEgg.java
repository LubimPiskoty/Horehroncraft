package com.parobci.horehroncraft.item.custom;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.base.Supplier;
import com.google.common.collect.Maps;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class RomakSpawnEgg extends ForgeSpawnEggItem {
    private static final List<ForgeSpawnEggItem> MOD_EGGS = new ArrayList<>();
    private static final Map<EntityType<?>, ForgeSpawnEggItem> TYPE_MAP = new IdentityHashMap<>();
    public final Supplier<? extends EntityType<?>> typeSupplier;

    public RomakSpawnEgg(Supplier<? extends EntityType<?>> type, int backgroundColor, int highlightColor, Properties props)
    {
        super((Supplier<? extends EntityType<?>>) null, backgroundColor, highlightColor, props);
        this.typeSupplier = type;

        MOD_EGGS.add(this);
    }

    @Override
    public EntityType<?> getType(@Nullable CompoundNBT tag) {
        EntityType<?> type = super.getType(tag);
        return type != null ? type : typeSupplier.get();
    }

    @Nullable
    public DefaultDispenseItemBehavior createDispenseBehavior() {
        return DEFAULT_DISPENSE_BEHAVIOR;
    }

    private static final DefaultDispenseItemBehavior DEFAULT_DISPENSE_BEHAVIOR = new DefaultDispenseItemBehavior() {
        protected ItemStack execute(IBlockSource source, ItemStack stack) {
            Direction face = source.getBlockState().getValue(DispenserBlock.FACING);
            EntityType<?> type = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());

            // FORGE: fix potential crash
            try {
                type.spawn(source.getLevel(), stack, null, source.getPos().relative(face), SpawnReason.DISPENSER,
                        face != Direction.UP, false);
            } catch (Exception exception) {
                DefaultDispenseItemBehavior.LOGGER.error("Error while dispensing spawn egg from dispenser at {}",
                        source.getPos(), exception);
                return ItemStack.EMPTY;
            }

            stack.shrink(1);
            return stack;
        }
    };
}
