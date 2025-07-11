package com.cosmolego527.create_pp.entity.client;

import com.cosmolego527.create_pp.CreatePP;
import com.cosmolego527.create_pp.entity.custom.ProgrammablePalEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ProgrammablePalRenderer extends MobRenderer<ProgrammablePalEntity, ProgramablePalModel<ProgrammablePalEntity>> {

    public ProgrammablePalRenderer(EntityRendererProvider.Context context) {
        super(context, new ProgramablePalModel<>(context.bakeLayer(ProgramablePalModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(ProgrammablePalEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/default_green.png");
    }

    @Override
    public void render(ProgrammablePalEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()){
            poseStack.scale(0.5f,0.5f,0.5f);
        }
        else {
            poseStack.scale(1f,1f,1f);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
