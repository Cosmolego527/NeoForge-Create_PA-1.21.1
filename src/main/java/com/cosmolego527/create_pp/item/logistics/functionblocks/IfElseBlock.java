package com.cosmolego527.create_pp.item.logistics.functionblocks;

import com.cosmolego527.create_pp.item.logistics.functionblocks.abstractblocks.FuncBlock;
import com.cosmolego527.create_pp.item.logistics.functionblocks.abstractblocks.ActiveFuncBlock;

import java.util.List;


public class IfElseBlock extends ActiveFuncBlock {
    private FuncBlock condition;
    private List<FuncBlock> thenActions;
    private List<FuncBlock> elseActions;

    public IfElseBlock(FuncBlock condition, List<FuncBlock> thenAction, List<FuncBlock> elseAction){
        this.condition = condition;
        this.thenActions = thenAction;
        this.elseActions = elseAction;
    }
    public IfElseBlock(){
        this.condition = null;
        this.thenActions = null;
        this.elseActions = null;
    }


    @Override
    public void execute(FunctionContext context) {
        boolean conditionResult = ((BoolFuncBlock)condition).getValue();

        if(conditionResult){
            for (FuncBlock block : thenActions) block.execute(context);
        }
        else if(elseActions != null)
            for (FuncBlock block : elseActions) block.execute(context);
    }

    public FuncBlock getCondition() {
        return condition;
    }

    public List<FuncBlock> getThenAction() {
        return thenActions;
    }

    public List<FuncBlock> getElseAction() {
        return elseActions;
    }
}

