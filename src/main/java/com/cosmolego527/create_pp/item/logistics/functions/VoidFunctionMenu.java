package com.cosmolego527.create_pp.item.logistics.functions;

import com.cosmolego527.create_pp.ModMenuTypes;
import com.simibubi.create.AllDataComponents;
import com.simibubi.create.content.trains.schedule.ScheduleMenu;
import com.simibubi.create.foundation.gui.menu.GhostItemMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class VoidFunctionMenu extends GhostItemMenu<ItemStack> {
    public boolean slotsActive = true;
    public int targetSlotsActive = 1;
    static final int slots = 2;

    public VoidFunctionMenu(MenuType<?> type, int id, Inventory inv, RegistryFriendlyByteBuf extraData) {
        super(type, id, inv, extraData);
    }

    public VoidFunctionMenu(MenuType<?> type, int id, Inventory inv, ItemStack contentHolder) {
        super(type, id, inv, contentHolder);
    }

    protected ItemStackHandler createGhostInventory() {
        return new ItemStackHandler(2);
    }

    public void clicked(int slotId, int dragType, ClickType clickTypeIn, Player player) {
        if (slotId != this.playerInventory.selected || clickTypeIn == ClickType.THROW) {
            super.clicked(slotId, dragType, clickTypeIn, player);
        }

    }

    protected boolean allowRepeats() {
        return true;
    }

    protected ItemStack createOnClient(RegistryFriendlyByteBuf extraData) {
        return (ItemStack)ItemStack.STREAM_CODEC.decode(extraData);
    }

    protected void addSlots() {
        this.addPlayerSlots(46, 140);

        for(int i = 0; i < 2; ++i) {
            this.addSlot(new VoidFunctionMenu.InactiveItemHandlerSlot(this.ghostInventory, i, i, 54 + 20 * i, 88));
        }

    }

    protected void addPlayerSlots(int x, int y) {
        for(int hotbarSlot = 0; hotbarSlot < 9; ++hotbarSlot) {
            this.addSlot(new VoidFunctionMenu.InactiveSlot(this.playerInventory, hotbarSlot, x + hotbarSlot * 18, y + 58));
        }

        for(int row = 0; row < 3; ++row) {
            for(int col = 0; col < 9; ++col) {
                this.addSlot(new VoidFunctionMenu.InactiveSlot(this.playerInventory, col + row * 9 + 9, x + col * 18, y + row * 18));
            }
        }

    }

    protected void saveData(ItemStack contentHolder) {
    }

    public boolean stillValid(Player player) {
        return this.playerInventory.getSelected() == this.contentHolder;
    }

    class InactiveSlot extends Slot {
        public InactiveSlot(Container pContainer, int pIndex, int pX, int pY) {
            super(pContainer, pIndex, pX, pY);
        }

        public boolean isActive() {
            return VoidFunctionMenu.this.slotsActive;
        }
    }

    class InactiveItemHandlerSlot extends SlotItemHandler {
        private int targetIndex;

        public InactiveItemHandlerSlot(IItemHandler itemHandler, int targetIndex, int index, int xPosition, int yPosition) {
            super(itemHandler, index, xPosition, yPosition);
            this.targetIndex = targetIndex;
        }

        public boolean isActive() {
            return VoidFunctionMenu.this.slotsActive && this.targetIndex < VoidFunctionMenu.this.targetSlotsActive;
        }
    }
}
