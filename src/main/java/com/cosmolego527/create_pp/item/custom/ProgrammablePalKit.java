package com.cosmolego527.create_pp.item.custom;

import com.cosmolego527.create_pp.entity.ModEntities;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import java.util.Properties;
import java.util.function.Supplier;

public class ProgrammablePalKit extends DeferredSpawnEggItem {

    public ProgrammablePalKit(Properties properties) {
        super(ModEntities.PROGRAMMABLE_PAL, 0xFFFFFF, 0xFFFFFF, properties);
    }
    public static ProgrammablePalKit Kit(Properties prop) {return new ProgrammablePalKit(prop);}
}
