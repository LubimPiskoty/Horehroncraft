package com.parobci.horehroncraft.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.parobci.horehroncraft.entity.custom.RomakEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class RomakModel<T extends RomakEntity> extends EntityModel<T> {
    private final ModelRenderer left_arm;
    private final ModelRenderer right_arm;
    private final ModelRenderer right_leg;
    private final ModelRenderer left_leg;
    private final ModelRenderer head;
    private final ModelRenderer body;

    public RomakModel() {
		texWidth = 32;
		texHeight = 32;

		left_arm = new ModelRenderer(this);
		left_arm.setPos(-6.5F, 9.0F, 0.0F);
		left_arm.texOffs(18, 0).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 8.0F, 4.0F, 0.0F, true);

		right_arm = new ModelRenderer(this);
		right_arm.setPos(6.5F, 9.0F, 0.0F);
		right_arm.texOffs(18, 0).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 8.0F, 4.0F, 0.0F, true);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-3.0F, 17.0F, 0.0F);
		right_leg.texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(3.0F, 17.0F, 0.0F);
		left_leg.texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, true);                                        

		head = new ModelRenderer(this);
		head.setPos(0.0F, 9.0F, -0.5F);
		head.texOffs(9, 12).addBox(-2.0F, -4.0F, -1.5F, 4.0F, 2.0F, 4.0F, 1.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 13.0F, 0.0F);
		body.texOffs(3, 18).addBox(-5.0F, -6.0F, -2.0F, 10.0F, 10.0F, 4.0F, 0.0F, false);
	}

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
            float headPitch) {
                
        ModelHelper.animateZombieArms(this.left_arm, this.right_arm, entity.isAggressive(), this.attackTime,
                ageInTicks);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);

        this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay,
            float red, float green, float blue, float alpha) {
        left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}