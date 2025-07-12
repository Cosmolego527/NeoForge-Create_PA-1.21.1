package com.cosmolego527.create_pp.entity.client;

import com.cosmolego527.create_pp.CreatePP;
import com.cosmolego527.create_pp.entity.ProgrammablePalVariant;
import com.cosmolego527.create_pp.entity.custom.ProgrammablePalEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class ProgrammablePalRenderer extends MobRenderer<ProgrammablePalEntity, ProgramablePalModel<ProgrammablePalEntity>> {

    private static final ResourceLocation PAL_NAMED_COSMO = ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/special/programmablepal_named_cosmo.png");
    private static final ResourceLocation RAINBOW_PAL = ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/special/programablepaltexturerainbow.png");

    private static final Map<ProgrammablePalVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(ProgrammablePalVariant.class), map -> {
                map.put(ProgrammablePalVariant.WHITE, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed01.png"));
                map.put(ProgrammablePalVariant.LIGHTGRAY, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed02.png"));
                map.put(ProgrammablePalVariant.GRAY, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed03.png"));
                map.put(ProgrammablePalVariant.BLACK, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed04.png"));
                map.put(ProgrammablePalVariant.RED, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed05.png"));
                map.put(ProgrammablePalVariant.ORANGE, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed06.png"));
                map.put(ProgrammablePalVariant.YELLOW, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed07.png"));
                map.put(ProgrammablePalVariant.LIME, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed08.png"));
                map.put(ProgrammablePalVariant.GREEN, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed09.png"));
                map.put(ProgrammablePalVariant.LIGHTBLUE, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed10.png"));
                map.put(ProgrammablePalVariant.CYAN, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed11.png"));
                map.put(ProgrammablePalVariant.BLUE, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed12.png"));
                map.put(ProgrammablePalVariant.PURPLE, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed13.png"));
                map.put(ProgrammablePalVariant.MAGENTA, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed14.png"));
                map.put(ProgrammablePalVariant.PINK, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed15.png"));
                map.put(ProgrammablePalVariant.BROWN, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/dyed_visor/programmablepalstexturedyed16.png"));
                map.put(ProgrammablePalVariant.DEFAULT, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, "textures/entity/programmable_pal/default_green.png"));
            });


    public ProgrammablePalRenderer(EntityRendererProvider.Context context) {
        super(context, new ProgramablePalModel<>(context.bakeLayer(ProgramablePalModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(ProgrammablePalEntity entity) {
        String s = ChatFormatting.stripFormatting(entity.getName().getString());
        if("Cosmo".equals(s)){return PAL_NAMED_COSMO;}
        //if("_neko".equals(s)){return RAINBOW_PAL;}
        return LOCATION_BY_VARIANT.get(entity.getVariant());
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
