package com.parobci.horehroncraft.entity.custom;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class MestanEntity extends CreatureEntity{

    public MestanEntity(EntityType<? extends CreatureEntity> type, World world) {
        super(type, world);
    }

    //TODO: Add more goals
    public static AttributeModifierMap.MutableAttribute setAttributes() {
        return CreatureEntity.createLivingAttributes()
            .add(Attributes.MAX_HEALTH, 10.0D)
            .add(Attributes.FOLLOW_RANGE, 16.0F)
            .add(Attributes.ATTACK_DAMAGE, 1.0D)
            .add(Attributes.ATTACK_SPEED, 1.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.5D)
            .add(Attributes.ARMOR, 1.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 0.9D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 4.0F, 0.55D, 0.65D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 160F, 0.7D, 0.8D));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 16.0f));
        this.goalSelector.addGoal(6, new HurtByTargetGoal(this, PlayerEntity.class).setAlertOthers());
        this.goalSelector.addGoal(6, new HurtByTargetGoal(this, RomakEntity.class).setAlertOthers());
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.BLAZE_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.PIGLIN_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.DROWNED_DEATH;
    }
    
}
