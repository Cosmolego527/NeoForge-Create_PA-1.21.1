package com.cosmolego527.create_pp.item.custom;

import com.cosmolego527.create_pp.CreatePP;
import com.cosmolego527.create_pp.entity.ModEntities;
import com.cosmolego527.create_pp.entity.ProgrammablePalStyles;
import com.cosmolego527.create_pp.entity.ProgrammablePalVariant;
import com.cosmolego527.create_pp.entity.custom.ProgrammablePalEntity;
import com.simibubi.create.AllEntityTypes;
import com.sun.jna.platform.win32.Variant;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import org.jetbrains.annotations.Nullable;

import java.util.Properties;
import java.util.function.Supplier;

public class ProgrammablePalKit extends Item {

    public ProgrammablePalStyles.PPalStyle Style;

    public ProgrammablePalKit(Properties properties, ProgrammablePalStyles.PPalStyle style) {
        super(properties);
        this.Style = style;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.success(itemStack);
    }

    @Override
    public String getDescriptionId() {
        return "item." + CreatePP.MOD_ID + ".programmable_pal_kit";
    }


    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();

        float h = 0.875f, r = 0.625f/2f;

        BlockPos block = context.getClickedPos();
        Vec3 point = context.getClickLocation();
        Direction direction = context.getClickedFace();
        if(direction.getAxis().isHorizontal()) point = point.add(Vec3.atLowerCornerOf(direction.getNormal()).scale(r));
        var blockPos = block.relative(direction);
        var pos = blockPos.getBottomCenter();
        if (!level.getBlockState(blockPos).isAir()) {
            context.getPlayer().displayClientMessage(Component.literal("Programmable Pal requires more space"), true);
            return super.useOn(context);
        }


        ProgrammablePalEntity entity = new ProgrammablePalEntity(level, pos);
        ItemStack itemInHand = context.getItemInHand();
        var item = itemInHand.copy();
        item.setCount(1);
        entity.setItem(item);
        entity.setVariant(Style);
        level.addFreshEntity(entity);
        itemInHand.shrink(1);
        return InteractionResult.SUCCESS;
    }


    public static boolean isPal(ItemStack stack) {
        return stack.getItem() instanceof ProgrammablePalKit;
    }
}
