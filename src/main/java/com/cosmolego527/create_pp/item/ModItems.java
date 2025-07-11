package com.cosmolego527.create_pp.item;

import com.cosmolego527.create_pp.CreatePP;
import com.cosmolego527.create_pp.entity.ModEntities;
import com.cosmolego527.create_pp.sound.ModSounds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreatePP.MOD_ID);

    public static final DeferredItem<Item> AUTOMATON_PROCESSOR = ITEMS.register("automaton_processor",
            () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> COLORED_TAPE_VOID = ITEMS.register("colored_tape_void",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COLORED_TAPE_ = ITEMS.register("colored_tape_",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COLORED_TAPE_BOOL = ITEMS.register("colored_tape_bool",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COLORED_TAPE_INT = ITEMS.register("colored_tape_int",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COLORED_TAPE_STRING = ITEMS.register("colored_tape_string",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CONCLUSES_MUSIC_DISC = ITEMS.register("concluses_music_disc",
            ()-> new Item(new Item.Properties().jukeboxPlayable(ModSounds.CONSLUSES_KEY).stacksTo(1)));
    public static final DeferredItem<Item> SESULCNOC_MUSIC_DISC = ITEMS.register("sesulcnoc_music_disc",
            ()-> new Item(new Item.Properties().jukeboxPlayable(ModSounds.SESULCNOC_KEY).stacksTo(1)));




    public static final DeferredItem<Item> PROGRAMMABLE_PAL_BOX = ITEMS.register("programmable_pal_box",
            ()-> new DeferredSpawnEggItem(ModEntities.PROGRAMMABLE_PAL, 0x5b5b5b, 0x744700,
                    new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
