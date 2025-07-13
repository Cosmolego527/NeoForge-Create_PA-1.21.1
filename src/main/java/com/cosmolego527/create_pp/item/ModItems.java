package com.cosmolego527.create_pp.item;

import com.cosmolego527.create_pp.CreatePP;
import com.cosmolego527.create_pp.entity.ModEntities;
import com.cosmolego527.create_pp.item.logistics.functions.FunctionTapeItem;
import com.cosmolego527.create_pp.sound.ModSounds;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreatePP.MOD_ID);

    public static final DeferredItem<Item> AUTOMATON_PROCESSOR = ITEMS.register("automaton_processor",
            () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> COLORED_TAPE_VOID = ITEMS.register("colored_tape_void",
            () -> new FunctionTapeItem(FunctionTapeItem.FunctionType.VOID, new Item.Properties()));
    public static final DeferredItem<Item> COLORED_TAPE_FLOAT = ITEMS.register("colored_tape_float",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COLORED_TAPE_BOOL = ITEMS.register("colored_tape_bool",
            () -> new FunctionTapeItem(FunctionTapeItem.FunctionType.BOOL, new Item.Properties()));
    public static final DeferredItem<Item> COLORED_TAPE_INT = ITEMS.register("colored_tape_int",
            () -> new FunctionTapeItem(FunctionTapeItem.FunctionType.INT, new Item.Properties()));
    public static final DeferredItem<Item> COLORED_TAPE_STRING = ITEMS.register("colored_tape_string",
            () -> new FunctionTapeItem(FunctionTapeItem.FunctionType.STRING, new Item.Properties()));

    //public static final ItemEntry<FunctionTapeItem> COLORED_TAPE_VOID = REGISTRATE.item("colored_tape_void", FunctionTapeItem::voidFuncItem)
    //        .lang("Void Function Tape")
    //        .register();

    public static final DeferredItem<Item> CONCLUSES_MUSIC_DISC = ITEMS.register("concluses_music_disc",
            ()-> new Item(new Item.Properties().jukeboxPlayable(ModSounds.CONSLUSES_KEY).stacksTo(1)));
    public static final DeferredItem<Item> SESULCNOC_MUSIC_DISC = ITEMS.register("sesulcnoc_music_disc",
            ()-> new Item(new Item.Properties().jukeboxPlayable(ModSounds.SESULCNOC_KEY).stacksTo(1)));




    public static final DeferredItem<Item> PROGRAMMABLE_PAL_BOX = ITEMS.register("programmable_pal_box",
            ()-> new DeferredSpawnEggItem(ModEntities.PROGRAMMABLE_PAL, 0xFFFFFF, 0xFFFFFF,
                    new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
