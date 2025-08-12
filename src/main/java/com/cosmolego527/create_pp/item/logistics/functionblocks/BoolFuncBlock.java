package com.cosmolego527.create_pp.item.logistics.functionblocks;

import com.cosmolego527.create_pp.item.logistics.functionblocks.abstractblocks.FuncBlock;

public class BoolFuncBlock extends FuncBlock {
    private boolean value;

    public BoolFuncBlock(boolean value){
        this.value = value;
    }

    public boolean getValue(){
        return value;
    }

    @Override
    public void execute(FunctionContext context) {
    }
}
