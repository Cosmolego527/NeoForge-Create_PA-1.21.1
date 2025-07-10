package com.cosmolego527.create_pp.item;

import com.cosmolego527.create_pp.CreatePP;
import com.cosmolego527.create_pp.block.ModBlocks;
import com.simibubi.create.Create;
import net.minecraft.client.gui.components.tabs.Tab;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreatePP.MOD_ID);

    public static final Supplier<CreativeModeTab>   CREATE_PP_TAB = CREATIVE_MODE_TAB.register("create_pp_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.AUTOMATON_PROCESSOR.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Create.ID, "palettes"))
                    .title(Component.translatable("creativetab.create_programmablepals.create_pp_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.AUTOMATON_PROCESSOR);

                        output.accept(ModBlocks.FACTORY_FLOOR);
                        output.accept(ModBlocks.FACTORY_FLOOR_STAIRS);
                        output.accept(ModBlocks.FACTORY_FLOOR_SLAB);
                    }).build());



    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
