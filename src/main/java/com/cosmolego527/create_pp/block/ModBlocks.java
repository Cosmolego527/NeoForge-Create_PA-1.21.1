package com.cosmolego527.create_pp.block;

import com.cosmolego527.create_pp.CreatePP;
import com.cosmolego527.create_pp.block.Custom.WrenchableBlock;
import com.cosmolego527.create_pp.block.Custom.WrenchableSlabBlock;
import com.cosmolego527.create_pp.block.Custom.WrenchableStairBlock;
import com.cosmolego527.create_pp.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.swing.*;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreatePP.MOD_ID);


    public static final DeferredBlock<Block> FACTORY_FLOOR = registerBlock("factory_floor",
            () -> new WrenchableBlock(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<StairBlock> FACTORY_FLOOR_STAIRS = registerBlock("factory_floor_stairs",
            () -> new WrenchableStairBlock(ModBlocks.FACTORY_FLOOR.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.HEAVY_CORE)));
    public static final DeferredBlock<SlabBlock> FACTORY_FLOOR_SLAB = registerBlock("factory_floor_slab",
            () -> new WrenchableSlabBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.HEAVY_CORE)));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block );
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
