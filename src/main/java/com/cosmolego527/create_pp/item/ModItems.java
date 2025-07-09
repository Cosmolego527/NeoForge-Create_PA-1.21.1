package com.cosmolego527.create_pp.item;

import com.cosmolego527.create_pp.CreatePP;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreatePP.MOD_ID);

    public static final DeferredItem<Item> AUTOMATON_PROCESSOR = ITEMS.register("automaton_processor",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
