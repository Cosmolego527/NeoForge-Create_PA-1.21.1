package com.cosmolego527.create_pp.datagen;

import com.cosmolego527.create_pp.CreatePP;
import com.cosmolego527.create_pp.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CreatePP.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        basicItem(ModItems.AUTOMATON_PROCESSOR.get());

        basicItem(ModItems.COLORED_TAPE_BOOL.get());
        basicItem(ModItems.COLORED_TAPE_INT.get());
        basicItem(ModItems.COLORED_TAPE_STRING.get());
        basicItem(ModItems.COLORED_TAPE_VOID.get());
        basicItem(ModItems.COLORED_TAPE_FLOAT.get());

        basicItem(ModItems.CONCLUSES_MUSIC_DISC.get());
        basicItem(ModItems.SESULCNOC_MUSIC_DISC.get());

        //withExistingParent(ModItems.PROGRAMMABLE_PAL_BOX.getId().getPath(), mcLoc("item/template_spawn_egg"));
        basicItem(ModItems.PROGRAMMABLE_PAL_BOX.get());
    }



}
