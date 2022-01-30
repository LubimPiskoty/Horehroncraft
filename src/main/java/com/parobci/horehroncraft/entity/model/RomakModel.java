package com.parobci.horehroncraft.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.parobci.horehroncraft.entity.custom.RomakEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class RomakModel<T extends RomakEntity> extends EntityModel<T> {
    private final ModelRenderer right_arm;
    private final ModelRenderer left_arm;
    private final ModelRenderer body;
    private final ModelRenderer head;

    public RomakModel() {
		texWidth = 16;
		texHeight = 16;

		right_arm = new ModelRenderer(this);
		right_arm.setPos(-4.0F, 20.0F, 1.0F);
		right_arm.texOffs(0, 4).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setPos(1.5F, 21.5F, 0.5F);
		left_arm.texOffs(0, 6).addBox(1.5F, -2.5F, -0.5F, 6.0F, 2.0F, 2.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(0, 0).addBox(-2.0F, -4.0F, 0.0F, 4.0F, 3.0F, 2.0F, 1.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 24.0F, 0.0F);
		head.texOffs(7, 3).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	}

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);

        this.right_arm.zRot = 90f;
        this.left_arm.zRot = 90f;

        this.right_arm.yRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.left_arm.yRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay,
            float red, float green, float blue, float alpha) {
        right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}