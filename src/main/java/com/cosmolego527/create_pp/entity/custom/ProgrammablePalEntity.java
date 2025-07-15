package com.cosmolego527.create_pp.entity.custom;

import com.cosmolego527.create_pp.entity.ModEntities;
import com.cosmolego527.create_pp.entity.ProgrammablePalVariant;
import com.simibubi.create.AllItems;
import io.netty.channel.nio.AbstractNioMessageChannel;
import net.createmod.catnip.math.VecHelper;
import net.minecraft.Util;
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

    private Entity originalEntity;
    public ItemStack itemStack;

    public Vec3 clientPosition, vec2 = Vec3.ZERO, vec3 = Vec3.ZERO;

    private static final EntityDataAccessor<Integer> DOME_COLOR =
            SynchedEntityData.defineId(ProgrammablePalEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK =
            SynchedEntityData.defineId(ProgrammablePalEntity.class, EntityDataSerializers.ITEM_STACK);

    @SuppressWarnings("unchecked")
    public ProgrammablePalEntity(EntityType<?> entityTypeIn, Level level) {
        super((EntityType<? extends PathfinderMob>) entityTypeIn, level);
        itemStack = ItemStack.EMPTY;
        setYRot(this.random.nextFloat() * 360.0f);
        setYHeadRot(getYRot());
        yRotO = getYRot();
    }
    public ProgrammablePalEntity(Level worldIn, double x, double y, double z) {
        this(ModEntities.PROGRAMMABLE_PAL_ENTITY.get(), worldIn);
        this.setPos(x,y,z);
        this.refreshDimensions();
    }
    public static ProgrammablePalEntity fromItemStack(Level world, Vec3 position, ItemStack itemstack){
        ProgrammablePalEntity palEntity = ModEntities.PROGRAMMABLE_PAL_ENTITY.get().create(world);
        palEntity.setPos(position);
        palEntity.setItemStack(itemstack);
        return palEntity;
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
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D);
    }

    public static EntityType.Builder<?> build(EntityType.Builder<?> builder) {
        @SuppressWarnings("unchecked")
                EntityType.Builder<ProgrammablePalEntity> palBuilder = (EntityType.Builder<ProgrammablePalEntity>) builder;
        return palBuilder.sized(1,1);
    }

    @Override
    public void travel(Vec3 travelVector) {
        super.travel(travelVector);
        if (!level().isClientSide)
            return;
        if (getDeltaMovement().length() < 1/128f)
            return;
        if (tickCount >= 20)
            return;
        Vec3 motion = getDeltaMovement().scale(.5f);
        AABB bb = getBoundingBox();
        List<VoxelShape> entityStream = level().getEntityCollisions(this, bb.expandTowards(motion));
        motion = collideBoundingBox(this,motion,bb,level(),entityStream);

        Vec3 clientPos = position().add(motion);
        if (lerpSteps != 0)
            clientPos = VecHelper.lerp(Math.min(1,tickCount/20f), clientPos, new Vec3(lerpX,lerpY,lerpZ));
        if (tickCount < 5)
            setPos(clientPos.x,clientPos.y,clientPos.z);
        if (tickCount<20)
            lerpTo(clientPos.x,clientPos.y,clientPos.z,getYRot(),getXRot(),lerpSteps == 0 ? 3 : lerpSteps);
    }

    @Override
    public void lerpMotion(double x, double y, double z) {
        setDeltaMovement(getDeltaMovement().add(x,y,z).scale(0.5f));
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
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }


    /* VARIANT */
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DOME_COLOR, 0);
    }

    private int getTypeVariant() {
        return this.entityData.get(DOME_COLOR);
    }

    public ProgrammablePalVariant getVariant() {
        return ProgrammablePalVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(ProgrammablePalVariant variant) {
        this.entityData.set(DOME_COLOR, variant.getId() & 255);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getTypeVariant());
    }



    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(DOME_COLOR, compound.getInt("Variant"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        ProgrammablePalVariant variant = Util.getRandom(ProgrammablePalVariant.values(), this.random);


        this.setVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }


    @Override
    public void writeSpawnData(RegistryFriendlyByteBuf buffer) {

    }

    @Override
    public void readSpawnData(RegistryFriendlyByteBuf additionalData) {

    }
}
