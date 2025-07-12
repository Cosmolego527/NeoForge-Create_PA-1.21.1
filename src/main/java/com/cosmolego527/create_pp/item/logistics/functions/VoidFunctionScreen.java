package com.cosmolego527.create_pp.item.logistics.functions;

import com.simibubi.create.content.logistics.filter.FilterScreenPacket;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.gui.widget.IconButton;
import com.simibubi.create.foundation.utility.CreateLang;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Inventory;

import java.util.Arrays;
import java.util.List;

public class VoidFunctionScreen extends AbstractFunctionScreen<VoidFunctionMenu> {

    private static final String PREFIX = "gui.void_function.";

    private Component respectDataN = CreateLang.translateDirect(PREFIX + "respect_data");
    private Component respectDataDESC = CreateLang.translateDirect(PREFIX + "respect_data.description");
    private Component ignoreDataN = CreateLang.translateDirect(PREFIX + "ignore_data");
    private Component ignoreDataDESC = CreateLang.translateDirect(PREFIX + "ignore_data.description");

    private IconButton respectNBT, ignoreNBT;

    public VoidFunctionScreen(VoidFunctionMenu menu, Inventory inv, Component title){
        super(menu, inv, title, AllGuiTextures.FILTER);
    }

    @Override
    protected void init() {
        setWindowOffset(-11,5);
        super.init();
        int x = leftPos;
        int y = topPos;

        respectNBT = new IconButton(x + 60, y + 75, AllIcons.I_RESPECT_NBT);
        respectNBT.withCallback(() -> {
            menu.respectNBT = true;
            sendOptionUpdate(FilterScreenPacket.Option.RESPECT_DATA);
        });
        respectNBT.setToolTip(respectDataN);
        ignoreNBT = new IconButton(x + 60, y + 75, AllIcons.I_IGNORE_NBT);
        ignoreNBT.withCallback(() -> {
            menu.respectNBT = false;
            sendOptionUpdate(FilterScreenPacket.Option.IGNORE_DATA);
        });
        ignoreNBT.setToolTip(ignoreDataN);
        addRenderableWidgets(respectNBT, ignoreNBT);

        handleIndicators();
    }

    @Override
    protected List<IconButton> getTooltipButtons() {
        return Arrays.asList(respectNBT, ignoreNBT);
    }

    @Override
    protected List<MutableComponent> getTooltipDescriptions() {
        return Arrays.asList(respectDataDESC.plainCopy(), ignoreDataDESC.plainCopy());
    }

    @Override
    protected boolean isButtonEnabled(IconButton button) {
        if (button == respectNBT)
            return !menu.respectNBT;
        if (button == ignoreNBT)
            return menu.respectNBT;
        return true;
    }
}
