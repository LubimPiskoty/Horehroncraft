package com.parobci.horehroncraft.entity.custom;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.parobci.horehroncraft.potion.PotionList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class RomakEntity extends ZombieEntity {

    public static final List<Effect> CHOROBY = Arrays.asList(PotionList.KIAHNE_EFFECT.get(),
            PotionList.TUBERKULOZA_EFFECT.get());

    // TODO: Add more Iron items
    public static List<Item> IRON_ITEMS =
    Arrays.asList(Items.IRON_AXE,Items.IRON_BARS,Items.IRON_BLOCK,Items.IRON_BOOTS,Items.IRON_CHESTPLATE,Items.IRON_DOOR,Items.IRON_HELMET,Items.IRON_HOE,Items.IRON_HORSE_ARMOR,Items.IRON_INGOT,Items.IRON_LEGGINGS,Items.IRON_NUGGET,Items.IRON_ORE,Items.IRON_PICKAXE,Items.IRON_SHOVEL,Items.IRON_SWORD,Items.IRON_TRAPDOOR);

    public static final Predicate<LivingEntity> HAS_IRON_PREDICATE = (entity) -> {
        Iterable<ItemStack> armor = entity.getArmorSlots();

        if (IRON_ITEMS.contains(entity.getMainHandItem().getItem()) || IRON_ITEMS
                .contains(entity.getOffhandItem().getItem())) {
            return true;
        }

        for (ItemStack item : armor) {
            if (IRON_ITEMS.contains(item.getItem())) {
                return true;
            }
        }
        if (entity.getMainHandItem().getDisplayName().toString().contains("Iron"))
            return true;
        return false;
    };

    public RomakEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.23F)
                .add(Attributes.ATTACK_DAMAGE, 1.8D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 16.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglinEntity.class));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(1,
                new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 1, true, false, HAS_IRON_PREDICATE));
    }

    protected boolean isSunSensitive() {
        return false;
    }

    protected int getExperienceReward(PlayerEntity player) {
        return 5 + this.level.random.nextInt(5);
    }

    public boolean doHurtTarget(Entity entityIn) {
        boolean flag = super.doHurtTarget(entityIn);
        if (flag && entityIn instanceof LivingEntity) {
            Effect choroba = CHOROBY.get(entityIn.level.random.nextInt(CHOROBY.size()));
            if (entityIn.level.random.nextInt(10) == 1) {
                ((LivingEntity) entityIn).addEffect(new EffectInstance(choroba, 160));
            }
        }

        return flag;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }

}