package com.parobci.horehroncraft.potion;

import java.util.Random;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;

public class PotionEffects {
    
    public static class KiahneEffect extends Effect{

        public KiahneEffect(EffectType typeIn, int liquidColorIn) {
            super(typeIn, liquidColorIn);
        }

        @Override
        public void applyEffectTick(LivingEntity entity, int amplifier) {
            if(entity.getHealth() > 1f){
                entity.hurt(DamageSource.WITHER, 0.5f);
            }
        }

        @Override
        public boolean isDurationEffectTick(int duration, int amplifier){
            return duration % 25 == 0;
        }

    }

    public static class TuberkulozaEffect extends Effect{

        public TuberkulozaEffect(EffectType typeIn, int liquidColorIn) {
            super(typeIn, liquidColorIn);
        }

        @Override
        public void applyEffectTick(LivingEntity entity, int amplifier) {
            float damage = (new Random().nextFloat()+amplifier) * 1f;
            entity.hurt(DamageSource.DROWN, damage);
        }

        @Override
        public boolean isDurationEffectTick(int duration, int amplifier){
            return duration % 40 == 0;
        }

    }

    public static class CholeraEffect extends Effect {
        
        public CholeraEffect(EffectType typeIn, int liquidColorIn){
            super(typeIn, liquidColorIn);
        }

        @Override
        public void applyEffectTick(LivingEntity entity, int amplifier) {
            float damage = (new Random().nextFloat() + amplifier) * 0.75f;
            if (entity instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) entity.getEntity();
                int reduced_food = player.getFoodData().getFoodLevel();
                reduced_food = ((reduced_food < 0) ? 0 : reduced_food - 1);
                player.getFoodData().setFoodLevel(reduced_food);
            }
            entity.hurt(DamageSource.STARVE, damage);
        }

        @Override
        public boolean isDurationEffectTick(int duration, int amplifier) {
            return duration % 60 == 0;
        }
    }
}
