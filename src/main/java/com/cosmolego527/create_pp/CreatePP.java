package com.cosmolego527.create_pp;

import com.cosmolego527.create_pp.block.ModBlocks;
import com.cosmolego527.create_pp.component.ModDataComponentTypes;
import com.cosmolego527.create_pp.entity.ModEntities;
import com.cosmolego527.create_pp.item.ModItems;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModLoadingContext;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CreatePP.MOD_ID)
public class CreatePP {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "create_programmablepals";

    public static final String NAME = "Create: Programmable Pals";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(CreatePP.MOD_ID)
            .defaultCreativeTab("create_create_programmablepals_tab",
                    t -> {
                        t.icon(() -> ModItems.AUTOMATON_PROCESSOR.get().getDefaultInstance());
                        t.title(Component.translatable("itemGroup.create_create_programmablepals"));
                    }).build()
            .setTooltipModifierFactory(item ->
                    new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                            .andThen(TooltipModifier.mapNull(KineticStats.create(item)))
            );


    public CreatePP(IEventBus eventBus, ModContainer modContainer) {
        onCtor(eventBus, modContainer);
    }

    private void onCtor(IEventBus modEventBus, ModContainer modContainer) {
        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        REGISTRATE.registerEventListeners(modEventBus);

        ModBlocks.register();
        ModItems.register();
        //ModBlockEntities.register();
        ModMenuTypes.register();
        //ModPackets.register();
        //ModConfigs.register(modLoadingContext, modContainer);
        ModEntities.register();
        //ModDisplaySources.register();
        ModDataComponentTypes.register(modEventBus);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}