package com.parobci.horehroncraft.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class Kiahne {
    
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
            return duration % 10 == 0;
        }

    }
}
