package com.cosmolego527.create_pp.entity;

import com.cosmolego527.create_pp.CreatePP;
import com.cosmolego527.create_pp.entity.custom.ProgrammablePalEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, CreatePP.MOD_ID);

    public static final Supplier<EntityType<ProgrammablePalEntity>> PROGRAMMABLE_PAL =
            ENTITY_TYPES.register("programmable_pal", () -> EntityType.Builder.of(ProgrammablePalEntity::new, MobCategory.CREATURE)
                    .sized(0.625f, 0.875f).build("programmable_pal"));



    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }


}
