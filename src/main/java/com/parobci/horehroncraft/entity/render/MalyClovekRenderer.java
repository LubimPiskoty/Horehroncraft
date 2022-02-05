package com.parobci.horehroncraft.entity.render;

import com.parobci.horehroncraft.HorehronCraft;
import com.parobci.horehroncraft.entity.custom.MalyClovekEntity;
import com.parobci.horehroncraft.entity.model.MalyClovekModel;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MalyClovekRenderer extends MobRenderer<MalyClovekEntity, MalyClovekModel<MalyClovekEntity>> {

    protected static final ResourceLocation TEXTURE = 
            new ResourceLocation(HorehronCraft.MOD_ID, "textures/entity/maly_muz.png");

    public MalyClovekRenderer(EntityRendererManager rendererManagerIn) {
        super(rendererManagerIn, new MalyClovekModel<>(), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(MalyClovekEntity entity) {
        return TEXTURE;
    }
    
}
