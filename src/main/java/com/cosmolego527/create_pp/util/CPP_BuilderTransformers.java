package com.cosmolego527.create_pp.util;

import com.cosmolego527.create_pp.CreatePP;
import com.cosmolego527.create_pp.entity.ProgrammablePalStyles.*;
import com.cosmolego527.create_pp.entity.ProgrammablePalVariant;
import com.cosmolego527.create_pp.item.custom.ProgrammablePalKit;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.ItemBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;


public class CPP_BuilderTransformers {

    public static ItemBuilder<ProgrammablePalKit, CreateRegistrate> programmablePalItem(PPalStyle style){
        return CreatePP.registrate().item(style.getItemId().getPath(),
                properties -> new ProgrammablePalKit(properties, style))
                .model((c,p)-> {
                    p.withExistingParent(c.getName(), p.modLoc("item/" + style.type()));
                })
                .lang("Programmable Pal");
    }
}
