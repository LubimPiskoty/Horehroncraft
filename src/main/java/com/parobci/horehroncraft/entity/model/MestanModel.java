package com.parobci.horehroncraft.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.parobci.horehroncraft.entity.custom.MestanEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.1.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class MestanModel <T extends MestanEntity> extends EntityModel<T>{
	private final ModelRenderer hand_left;
	private final ModelRenderer hand_rigth;
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg_left;
	private final ModelRenderer leg_rigth;

	public MestanModel() {
		texWidth = 64;
		texHeight = 64;

		hand_left = new ModelRenderer(this);
		hand_left.setPos(5.5F, 7.0F, -0.5F);
		hand_left.texOffs(26, 29).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		hand_rigth = new ModelRenderer(this);
		hand_rigth.setPos(-5.5F, 7.0F, -0.5F);
		hand_rigth.texOffs(14, 29).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 6.0F, -0.5F);
		head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 10.0F, -0.5F);
		body.texOffs(0, 16).addBox(-4.0F, -4.0F, -1.5F, 8.0F, 8.0F, 3.0F, 0.0F, false);

		leg_left = new ModelRenderer(this);
		leg_left.setPos(2.0F, 14.0F, -0.5F);
		leg_left.texOffs(22, 16).addBox(-2.0F, 0.0F, -1.5F, 4.0F, 10.0F, 3.0F, 0.0F, false);

		leg_rigth = new ModelRenderer(this);
		leg_rigth.setPos(-2.0F, 14.0F, -0.5F);
		leg_rigth.texOffs(0, 27).addBox(-2.0F, 0.0F, -1.5F, 4.0F, 10.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){

		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);

		this.leg_rigth.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg_left.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.hand_left.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.hand_rigth.xRot = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		hand_left.render(matrixStack, buffer, packedLight, packedOverlay);
		hand_rigth.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		leg_left.render(matrixStack, buffer, packedLight, packedOverlay);
		leg_rigth.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}