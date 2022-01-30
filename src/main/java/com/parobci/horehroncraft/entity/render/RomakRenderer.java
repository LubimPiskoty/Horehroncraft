package com.parobci.horehroncraft.entity.render;

import com.parobci.horehroncraft.HorehronCraft;
import com.parobci.horehroncraft.entity.custom.RomakEntity;
import com.parobci.horehroncraft.entity.model.RomakModel;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RomakRenderer extends MobRenderer<RomakEntity, RomakModel<RomakEntity>> {

    protected static final ResourceLocation TEXTURE = 
            new ResourceLocation(HorehronCraft.MOD_ID, "textures/entity/romak.png");

    public RomakRenderer(EntityRendererManager rendererManagerIn) {
        super(rendererManagerIn, new RomakModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RomakEntity entity) {
        return TEXTURE;
    }
    
}
