package com.cosmolego527.create_pp.item.logistics;

import com.cosmolego527.create_pp.item.logistics.functionblocks.BoolFuncBlock;
import com.cosmolego527.create_pp.item.logistics.functionblocks.IfElseBlock;
import com.cosmolego527.create_pp.item.logistics.functionblocks.abstractblocks.FuncBlock;
import com.simibubi.create.foundation.gui.widget.IconButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class FuncBlockEditorScreen extends Screen {
    private List<FuncBlock> funcBlocks = new ArrayList<>();
    private Button addIfBlockButton;
    private Button addActionBlockButton;

    public FuncBlockEditorScreen(){
        super(Component.translatable("Block-Based Programming Editor"));
    }

    @Override
    protected void init() {


    }
}
