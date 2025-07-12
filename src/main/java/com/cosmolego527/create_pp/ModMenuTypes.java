package com.cosmolego527.create_pp;

import com.cosmolego527.create_pp.datagen.CreatePPRegistrate;
import com.cosmolego527.create_pp.item.logistics.functions.VoidFunctionMenu;
import com.cosmolego527.create_pp.item.logistics.functions.VoidFunctionScreen;
import com.simibubi.create.Create;
import com.simibubi.create.api.registrate.CreateRegistrateRegistrationCallback;
import com.tterrag.registrate.builders.MenuBuilder;
import com.tterrag.registrate.util.entry.MenuEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;


public class ModMenuTypes {
    public static final MenuEntry<VoidFunctionMenu> VOID_FUNCTION =
            register("void_function", VoidFunctionMenu::new, () -> VoidFunctionScreen::new);

    private static <C extends AbstractContainerMenu, S extends Screen & MenuAccess<C>> MenuEntry<C> register (
            String name, MenuBuilder.ForgeMenuFactory<C> factory, NonNullSupplier<MenuBuilder.ScreenFactory<C, S>> screenFactory){
        return CreatePP.registrate()
                .menu(name, factory, screenFactory)
                .register();
    }


    public static void register(){}
}
