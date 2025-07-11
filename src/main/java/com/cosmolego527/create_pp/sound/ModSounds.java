package com.cosmolego527.create_pp.sound;

import com.cosmolego527.create_pp.CreatePP;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, CreatePP.MOD_ID);



    public static final Supplier<SoundEvent> CONCLUSES = registerSoundEvent("concluses");
    public static final ResourceKey<JukeboxSong> CONSLUSES_KEY = createSong("concluses");
    public static final Supplier<SoundEvent> SESULCNOC = registerSoundEvent("sesulcnoc");
    public static final ResourceKey<JukeboxSong> SESULCNOC_KEY = createSong("sesulcnoc");

    private static ResourceKey<JukeboxSong> createSong(String name){
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, name));
    }
    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(CreatePP.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
