package com.cosmolego527.create_pp.item.logistics.functions;

import com.cosmolego527.create_pp.ModMenuTypes;
import com.simibubi.create.AllDataComponents;
import com.simibubi.create.AllMenuTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class VoidFunctionMenu extends AbstractFunctionMenu {
    boolean respectNBT;

    public VoidFunctionMenu(MenuType<?> type, int id, Inventory inv, RegistryFriendlyByteBuf extraData){
        super(type, id, inv, extraData);
    }
    public VoidFunctionMenu(MenuType<?> type, int id, Inventory inv, ItemStack stack){
        super(type, id, inv, stack);
    }

    public static VoidFunctionMenu create(int id, Inventory inv, ItemStack stack){
        return new VoidFunctionMenu(ModMenuTypes.VOID_FUNCTION.get(), id, inv,stack);
    }

    @Override
    protected int getPlayerInventoryXOffset() {
        return 38;
    }

    @Override
    protected int getPlayerInventoryYOffset() {
        return 121;
    }

    @Override
    protected void addFilterSlots() {
        int x = 23;
        int y = 25;
        for (int row = 0; row < 2; ++row)
            for (int col = 0; col < 5; ++col)
                this.addSlot(new SlotItemHandler(ghostInventory, col + row * 5, x + col * 18, y + row * 18));
    }

    @Override
    protected ItemStackHandler createGhostInventory() {
        return FunctionTapeItem.getFilterItems(contentHolder);
    }

    @Override
    protected void initAndReadInventory(ItemStack filterItem) {
        super.initAndReadInventory(filterItem);
        respectNBT = filterItem.getOrDefault(AllDataComponents.FILTER_ITEMS_RESPECT_NBT, false);
    }

    @Override
    protected void saveData(ItemStack filterItem) {
        super.saveData(filterItem);
        filterItem.set(AllDataComponents.FILTER_ITEMS_RESPECT_NBT, respectNBT);

        if (respectNBT)
            return;
        for (int i = 0; i < ghostInventory.getSlots(); i++)
            if (!ghostInventory.getStackInSlot(i).isEmpty())
                return;
        filterItem.remove(AllDataComponents.FILTER_ITEMS_RESPECT_NBT);
    }
}
