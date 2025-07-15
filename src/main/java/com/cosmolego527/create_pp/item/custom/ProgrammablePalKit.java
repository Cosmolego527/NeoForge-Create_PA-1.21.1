package com.cosmolego527.create_pp.item.custom;

import com.cosmolego527.create_pp.CreatePP;
import com.cosmolego527.create_pp.entity.ModEntities;
import com.cosmolego527.create_pp.entity.ProgrammablePalStyles;
import com.cosmolego527.create_pp.entity.ProgrammablePalVariant;
import com.cosmolego527.create_pp.entity.custom.ProgrammablePalEntity;
import com.sun.jna.platform.win32.Variant;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Direction;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
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
        Vec3 point = context.getClickLocation();
        float h = 0.625f;
        float r = 0.875f;

        if (context.getClickedFace() == Direction.DOWN)
            point = point.subtract(0, h+.25f,0);
        else if(context.getClickedFace().getAxis().isHorizontal())
            point = point.add(Vec3.atLowerCornerOf(context.getClickedFace().getNormal()).scale(r));
        AABB scanBB = new AABB(point, point).inflate(r,0,r).expandTowards(0,h,0);
        Level world = context.getLevel();
        ProgrammablePalEntity entity = new ProgrammablePalEntity(world, point.x, point.y,point.z);
        ItemStack itemInHand = context.getItemInHand();
        entity.setItemStack(itemInHand.copy());
        world.addFreshEntity(entity);
        itemInHand.shrink(1);
        return InteractionResult.SUCCESS;
    }
}
