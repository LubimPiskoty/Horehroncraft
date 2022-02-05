package com.parobci.horehroncraft.entity.render;

import com.parobci.horehroncraft.HorehronCraft;
import com.parobci.horehroncraft.entity.custom.MestanEntity;
import com.parobci.horehroncraft.entity.model.MestanModel;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MestanRenderer extends MobRenderer<MestanEntity, MestanModel<MestanEntity>> {

    protected static final ResourceLocation TEXTURE = 
            new ResourceLocation(HorehronCraft.MOD_ID, "textures/entity/mestan0.png");

    public MestanRenderer(EntityRendererManager rendererManagerIn) {
        super(rendererManagerIn, new MestanModel<>(), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(MestanEntity entity) {
        return TEXTURE;
    }
    
}
