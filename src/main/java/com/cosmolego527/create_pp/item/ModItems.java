package com.cosmolego527.create_pp.item;

import com.cosmolego527.create_pp.CreatePP;
import com.cosmolego527.create_pp.entity.ModEntities;
import com.cosmolego527.create_pp.entity.ProgrammablePalStyles;
import com.cosmolego527.create_pp.entity.ProgrammablePalVariant;
import com.cosmolego527.create_pp.item.custom.ProgrammablePalKit;
import com.cosmolego527.create_pp.item.logistics.functions.FunctionTapeItem;
import com.cosmolego527.create_pp.sound.ModSounds;
import com.cosmolego527.create_pp.util.CPP_BuilderTransformers;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final ItemEntry<Item> AUTOMATON_PROCESSOR =
            CreatePP.REGISTRATE.item("automaton_processor", Item::new)
                    .register();

    public static final ItemEntry<FunctionTapeItem> VOID_FUNCTION_TAPE =
            CreatePP.REGISTRATE.item("void_function_tape", FunctionTapeItem::voidFuncItem)
                    .register(),
            BOOL_FUNCTION_TAPE =
            CreatePP.REGISTRATE.item("bool_function_tape", FunctionTapeItem::boolFuncItem)
                    .register(),
            STRING_FUNCTION_TAPE =
            CreatePP.REGISTRATE.item("string_function_tape", FunctionTapeItem::stringFuncItem)
                    .register(),
            INT_FUNCTION_TAPE =
            CreatePP.REGISTRATE.item("int_function_tape", FunctionTapeItem::intFuncItem)
                    .register(),
            FLOAT_FUNCTION_TAPE =
            CreatePP.REGISTRATE.item("float_function_tape", FunctionTapeItem::intFuncItem)
                    .register();


    public static final ItemEntry<Item> CONCLUSES_MUSIC_DISC = CreatePP.REGISTRATE.item("concluses_music_disc", Item::new)
            .lang("Concluses Music Disc")
            .properties(p -> p.jukeboxPlayable(ModSounds.CONCLUSES_KEY).stacksTo(1))
            .register();
    public static final ItemEntry<Item> SESULCNOC_MUSIC_DISC = CreatePP.REGISTRATE.item("sesulcnoc_music_disc", Item::new)
            .lang("sesulcnoC Music Disc")
            .properties(p -> p.jukeboxPlayable(ModSounds.SESULCNOC_KEY).stacksTo(1))
            .register();

//
//    public static final ItemEntry<ProgrammablePalKit> PROGRAMMABLE_PAL_BOX = CreatePP.REGISTRATE.item("programmable_pal_box", ProgrammablePalKit::new)
//            .lang("Programmable Pal Kit")
//            .register();

    static{
        boolean created = false;
        for(ProgrammablePalStyles.PPalStyle style : ProgrammablePalStyles.STYLES){

            ItemBuilder<ProgrammablePalKit, CreateRegistrate> programmablePalKit = CPP_BuilderTransformers.programmablePalItem(style);

            if (created)
                programmablePalKit.setData(ProviderType.LANG, NonNullBiConsumer.noop());

            created |= style.made();
            programmablePalKit.register();
        }
    }

//    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreatePP.MOD_ID);
//
//    public static final DeferredItem<Item> AUTOMATON_PROCESSOR = ITEMS.register("automaton_processor",
//            () -> new Item(new Item.Properties()));
//
//
//    public static final DeferredItem<Item> COLORED_TAPE_VOID = ITEMS.register("colored_tape_void",
//            () -> new FunctionTapeItem(FunctionTapeItem.FunctionType.VOID, new Item.Properties()));
//    public static final DeferredItem<Item> COLORED_TAPE_FLOAT = ITEMS.register("colored_tape_float",
//            () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> COLORED_TAPE_BOOL = ITEMS.register("colored_tape_bool",
//            () -> new FunctionTapeItem(FunctionTapeItem.FunctionType.BOOL, new Item.Properties()));
//    public static final DeferredItem<Item> COLORED_TAPE_INT = ITEMS.register("colored_tape_int",
//            () -> new FunctionTapeItem(FunctionTapeItem.FunctionType.INT, new Item.Properties()));
//    public static final DeferredItem<Item> COLORED_TAPE_STRING = ITEMS.register("colored_tape_string",
//            () -> new FunctionTapeItem(FunctionTapeItem.FunctionType.STRING, new Item.Properties()));
//
//    //public static final ItemEntry<FunctionTapeItem> COLORED_TAPE_VOID = REGISTRATE.item("colored_tape_void", FunctionTapeItem::voidFuncItem)
//    //        .lang("Void Function Tape")
//    //        .register();
//
//    public static final DeferredItem<Item> CONCLUSES_MUSIC_DISC = ITEMS.register("concluses_music_disc",
//            ()-> new Item(new Item.Properties().jukeboxPlayable(ModSounds.CONSLUSES_KEY).stacksTo(1)));
//    public static final DeferredItem<Item> SESULCNOC_MUSIC_DISC = ITEMS.register("sesulcnoc_music_disc",
//            ()-> new Item(new Item.Properties().jukeboxPlayable(ModSounds.SESULCNOC_KEY).stacksTo(1)));

//    public static final DeferredItem<Item> PROGRAMMABLE_PAL_BOX = ITEMS.register("programmable_pal_box",
//            ()-> new DeferredSpawnEggItem(ModEntities.PROGRAMMABLE_PAL, 0xFFFFFF, 0xFFFFFF,
//                    new Item.Properties()));


    public static void register() {

    }
}
