package com.parobci.horehroncraft.item.custom;

import java.util.function.Supplier;

import com.parobci.horehroncraft.HorehronCraft;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum ArmorMaterials implements IArmorMaterial {
    
    DREVAKY("drevaky", 7, new int[] {2, 2, 2, 2}, 12, SoundEvents.ARMOR_EQUIP_LEATHER, 2.0f, 4.0f, () -> {
        return Ingredient.of(Items.OAK_WOOD.getItem());
    }),;

    private static final int[] HEALTH_PER_SLOT = new int[] { 13, 15, 16, 11 };
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

   private ArmorMaterials(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEffect, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
      this.name = name;
      this.durabilityMultiplier = maxDamageFactor;
      this.slotProtections = damageReductionAmountArray;
      this.enchantmentValue = enchantability;
      this.sound = soundEffect;
      this.toughness = toughness;
      this.knockbackResistance = knockbackResistance;
      this.repairIngredient = new LazyValue<>(repairIngredient);
   }

    public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
        return HEALTH_PER_SLOT[p_200896_1_.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {
        return this.slotProtections[p_200902_1_.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return HorehronCraft.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

}
