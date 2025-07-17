package com.cosmolego527.create_pp.entity.custom;

import com.cosmolego527.create_pp.entity.ModEntities;
import com.cosmolego527.create_pp.entity.ProgrammablePalStyles;
import com.cosmolego527.create_pp.entity.ProgrammablePalVariant;
import com.cosmolego527.create_pp.item.ModItems;
import com.cosmolego527.create_pp.item.custom.ProgrammablePalKit;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.logistics.box.PackageItem;
import io.netty.channel.nio.AbstractNioMessageChannel;
import net.createmod.catnip.math.VecHelper;
import net.createmod.ponder.api.level.PonderLevel;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProgrammablePalEntity extends PathfinderMob implements IEntityWithComplexSpawn {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public ItemStack itemStack;

    public int insertionDelay;

    public Vec3 clientPosition, vec2 = Vec3.ZERO, vec3 = Vec3.ZERO;

    private static final EntityDataAccessor<Integer> DOME_COLOR =
            SynchedEntityData.defineId(ProgrammablePalEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK =
            SynchedEntityData.defineId(ProgrammablePalEntity.class, EntityDataSerializers.ITEM_STACK);


    public ProgrammablePalEntity(EntityType<? extends PathfinderMob> entityTypeIn, Level level) {
        super(entityTypeIn, level);
        insertionDelay = 30;
    }
    public ProgrammablePalEntity(Level worldIn, BlockPos pos) {
        this(ModEntities.PROGRAMMABLE_PAL_ENTITY.get(), worldIn);
        this.setPos(pos.getCenter());
        this.refreshDimensions();
    }

    public static EntityType.Builder<?> build(EntityType.Builder<?> builder){
        @SuppressWarnings("unchecked")
        EntityType.Builder<ProgrammablePalEntity> palBuilder = (EntityType.Builder<ProgrammablePalEntity>) builder;
        return palBuilder.sized(1,1);
    }

    public void setItem(ItemStack item){
        this.itemStack = item;
        refreshDimensions();
    }

    public ItemStack getItem(){
        return this.itemStack;
    }

    public void setItemStack(ItemStack itemStack){
        if(itemStack == null) return;
        this.entityData.set(DATA_ITEM_STACK, itemStack);
    }
    public ItemStack getItemStack(){
        return this.entityData.get(DATA_ITEM_STACK);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new TemptGoal(this,1.0, stack -> stack.is(AllItems.ANDESITE_ALLOY), false));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 10f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

    }

    @Override
    public @Nullable ItemStack getPickedResult(HitResult target) {
        return itemStack.copy();
    }

    public static AttributeSupplier.Builder createPalAttributes(){
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }


    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 100;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }



    @Override
    public void tick() {
        if (level() instanceof PonderLevel) {
            setDeltaMovement(getDeltaMovement().add(0, -0.06, 0));
            if (position().y < 0.125)
                discard();
        }

        super.tick();
    }

    /* VARIANT */
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DOME_COLOR, 0);
        builder.define(DATA_ITEM_STACK, ItemStack.EMPTY);
    }

    private int getTypeVariant() {
        return this.entityData.get(DOME_COLOR);
    }

    public ProgrammablePalVariant getVariant() {
        return ProgrammablePalVariant.byId(this.getTypeVariant() & 255);
    }

    public void setVariant(ProgrammablePalStyles.PPalStyle style) {
        this.entityData.set(DOME_COLOR, style.Variant().getId() & 255);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getTypeVariant());
        compound.put("item", itemStack.saveOptional(level().registryAccess()));
    }



    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(DOME_COLOR, compound.getInt("Variant"));
        itemStack = ItemStack.parseOptional(level().registryAccess(), compound.getCompound("item"));
    }



    @Override
    public void writeSpawnData(RegistryFriendlyByteBuf buffer) {
        ItemStack.STREAM_CODEC.encode(buffer, getItem());
        Vec3 motion = getDeltaMovement();
        buffer.writeFloat((float) motion.x);
        buffer.writeFloat((float) motion.y);
        buffer.writeFloat((float) motion.z);
    }

    @Override
    public void readSpawnData(RegistryFriendlyByteBuf additionalData) {
        setItem(ItemStack.STREAM_CODEC.decode(additionalData));
        setDeltaMovement(
                additionalData.readFloat(),
                additionalData.readFloat(),
                additionalData.readFloat());
    }

}
