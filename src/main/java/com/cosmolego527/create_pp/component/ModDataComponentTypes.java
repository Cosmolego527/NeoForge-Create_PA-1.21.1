package com.cosmolego527.create_pp.component;


import com.cosmolego527.create_pp.CreatePP;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, CreatePP.MOD_ID);

    public static final DataComponentType<CustomData> VOID_FUNCTION_DATA = register(
            "void_function_data",
            builder -> builder.persistent(CustomData.CODEC).networkSynchronized(CustomData.STREAM_CODEC)
    );

    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        DataComponentType<T> type = builder.apply(DataComponentType.builder()).build();
        DATA_COMPONENT_TYPES.register(name, () -> type);
        return type;
    }

    @ApiStatus.Internal
    public static void register(IEventBus modEventBus) {
        DATA_COMPONENT_TYPES.register(modEventBus);
    }
}
