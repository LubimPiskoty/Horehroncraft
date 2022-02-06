package com.parobci.horehroncraft.entity.custom;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.parobci.horehroncraft.potion.PotionList;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.server.ServerWorld;

public class RomakEntity extends ZombieEntity {

    public static final List<Effect> CHOROBY = Arrays.asList(PotionList.KIAHNE_EFFECT.get(),
            PotionList.TUBERKULOZA_EFFECT.get(), PotionList.CHOLERA_EFFECT.get());

    public static List<Item> IRON_ITEMS = Arrays.asList(Items.IRON_AXE, Items.IRON_BARS, Items.IRON_BLOCK,
            Items.IRON_BOOTS, Items.IRON_CHESTPLATE, Items.IRON_DOOR, Items.IRON_HELMET, Items.IRON_HOE,
            Items.IRON_HORSE_ARMOR, Items.IRON_INGOT, Items.IRON_LEGGINGS, Items.IRON_NUGGET, Items.IRON_ORE,
            Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_SWORD, Items.IRON_TRAPDOOR);

    public static final Predicate<LivingEntity> HAS_IRON_PREDICATE = (entity) -> {
        Iterable<ItemStack> armor = entity.getArmorSlots();

        if (IRON_ITEMS.contains(entity.getMainHandItem().getItem()) || IRON_ITEMS
                .contains(entity.getOffhandItem().getItem())) {
            return true;
        }

        for (ItemStack item : armor) {
            if (IRON_ITEMS.contains(item.getItem())) {
                return true;
            }
        }
        if (entity.getMainHandItem().getDisplayName().toString().contains("Iron"))
            return true;
        return false;
    };

    public RomakEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 120.0D)
                .add(Attributes.MAX_HEALTH, 12.0f)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.45F)
                .add(Attributes.ATTACK_DAMAGE, 4D)
                .add(Attributes.ATTACK_SPEED, 0.2D)
                .add(Attributes.ATTACK_KNOCKBACK, 1.5D)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 1)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0)
                .add(Attributes.JUMP_STRENGTH, 3);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(1, new ZombieAttackGoal(this, 1.25D, true));
        this.goalSelector.addGoal(2, new StealIronOreGoal(this, 0.7D, 3));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 16.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglinEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MestanEntity.class, false));
        this.targetSelector.addGoal(1,
                new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 5, false, false, HAS_IRON_PREDICATE));
        this.targetSelector.addGoal(2,
                new NearestAttackableTargetGoal<>(this, FoxEntity.class, 20, false, false, HAS_IRON_PREDICATE));
    }

    // TODO: Skladaneho plešina
    protected ItemStack getSkull() {
        return new ItemStack(Items.ZOMBIE_HEAD);
    }

    protected boolean isSunSensitive() {
        return false;
    }

    protected boolean convertsInWater() {
        return false;
    }

    protected int getExperienceReward(PlayerEntity player) {
        return 5 + this.level.random.nextInt(5);
    }

    public boolean doHurtTarget(Entity entityIn) {
        boolean flag = super.doHurtTarget(entityIn);
        if (flag && entityIn instanceof LivingEntity) {
            Effect choroba = CHOROBY.get(entityIn.level.random.nextInt(CHOROBY.size()));
            if (entityIn.level.random.nextInt(4) == 1) {
                ((LivingEntity) entityIn).addEffect(new EffectInstance(choroba, 200));
            }
        }

        return flag;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }

    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return size.height * 0.95f;
    }

    class StealIronOreGoal extends MoveToBlockGoal {

        private final Block blockToRemove;
        private final MobEntity removerMob;
        private int ticksSinceReachedGoal;

        public StealIronOreGoal(CreatureEntity p_i48795_2_, double p_i48795_3_, int p_i48795_5_) {
            super(p_i48795_2_, p_i48795_3_, 24, p_i48795_5_);
            this.blockToRemove = Blocks.IRON_ORE;
            this.removerMob = p_i48795_2_;
        }

        public boolean canUse() {
            if (!net.minecraftforge.common.ForgeHooks.canEntityDestroy(this.removerMob.level, this.blockPos,
                    this.removerMob)) {
                return false;
            } else if (this.nextStartTick > 0) {
                --this.nextStartTick;
                return false;
            } else if (this.tryFindBlock()) {
                this.nextStartTick = 20;
                return true;
            } else {
                this.nextStartTick = this.nextStartTick(this.mob);
                return false;
            }
        }

        private boolean tryFindBlock() {
            return this.blockPos != null && this.isValidTarget(this.mob.level, this.blockPos) ? true
                    : this.findNearestBlock();
        }

        public void stop() {
            super.stop();
            this.removerMob.fallDistance = 1.0F;
        }

        public void start() {
            super.start();
            this.ticksSinceReachedGoal = 0;
        }

        public void playDestroyProgressSound(IWorld p_203114_1_, BlockPos p_203114_2_) {
            p_203114_1_.playSound((PlayerEntity) null, p_203114_2_, SoundEvents.METAL_BREAK,
                    SoundCategory.HOSTILE, 0.5F, 0.9F + RomakEntity.this.random.nextFloat() * 0.2F);
        }

        public void playBreakSound(World p_203116_1_, BlockPos p_203116_2_) {
            p_203116_1_.playSound((PlayerEntity) null, p_203116_2_, SoundEvents.METAL_BREAK, SoundCategory.BLOCKS,
                    0.7F, 0.9F + p_203116_1_.random.nextFloat() * 0.2F);
        }

        public void tick() {
            super.tick();
            World world = this.removerMob.level;
            BlockPos blockpos = this.removerMob.blockPosition();
            BlockPos blockpos1 = this.getPosWithBlock(blockpos, world);
            Random random = this.removerMob.getRandom();
            if (this.isReachedTarget() && blockpos1 != null) {
                if (this.ticksSinceReachedGoal > 0) {
                    Vector3d vector3d = this.removerMob.getDeltaMovement();
                    this.removerMob.setDeltaMovement(vector3d.x, 0.3D, vector3d.z);
                    if (!world.isClientSide) {
                        double d0 = 0.08D;
                        ((ServerWorld) world).sendParticles(
                                new ItemParticleData(ParticleTypes.ITEM, new ItemStack(Items.IRON_NUGGET)),
                                (double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.7D,
                                (double) blockpos1.getZ() + 0.5D, 3, ((double) random.nextFloat() - 0.5D) * 0.08D,
                                ((double) random.nextFloat() - 0.5D) * 0.08D,
                                ((double) random.nextFloat() - 0.5D) * 0.08D, (double) 0.15F);
                    }
                }

                if (this.ticksSinceReachedGoal % 2 == 0) {
                    Vector3d vector3d1 = this.removerMob.getDeltaMovement();
                    this.removerMob.setDeltaMovement(vector3d1.x, -0.3D, vector3d1.z);
                    if (this.ticksSinceReachedGoal % 4 == 0) {
                        this.playDestroyProgressSound(world, this.blockPos);
                    }
                }

                if (this.ticksSinceReachedGoal > 200) {
                    world.removeBlock(blockpos1, false);
                    if (!world.isClientSide) {
                        for (int i = 0; i < 20; ++i) {
                            double d3 = random.nextGaussian() * 0.02D;
                            double d1 = random.nextGaussian() * 0.02D;
                            double d2 = random.nextGaussian() * 0.02D;
                            ((ServerWorld) world).sendParticles(ParticleTypes.POOF, (double) blockpos1.getX() + 0.5D,
                                    (double) blockpos1.getY(), (double) blockpos1.getZ() + 0.5D, 1, d3, d1, d2,
                                    (double) 0.15F);
                        }

                        this.playBreakSound(world, blockpos1);
                    }
                }

                ++this.ticksSinceReachedGoal;
            }

        }

        @Nullable
        private BlockPos getPosWithBlock(BlockPos p_203115_1_, IBlockReader p_203115_2_) {
            if (p_203115_2_.getBlockState(p_203115_1_).is(this.blockToRemove)) {
                return p_203115_1_;
            } else {
                BlockPos[] ablockpos = new BlockPos[] { p_203115_1_.below(), p_203115_1_.west(), p_203115_1_.east(),
                        p_203115_1_.north(), p_203115_1_.south(), p_203115_1_.below().below() };

                for (BlockPos blockpos : ablockpos) {
                    if (p_203115_2_.getBlockState(blockpos).is(this.blockToRemove)) {
                        return blockpos;
                    }
                }

                return null;
            }
        }

        protected boolean isValidTarget(IWorldReader p_179488_1_, BlockPos p_179488_2_) {
            IChunk ichunk = p_179488_1_.getChunk(p_179488_2_.getX() >> 4, p_179488_2_.getZ() >> 4, ChunkStatus.FULL,
                    false);
            if (ichunk == null) {
                return false;
            } else {
                return ichunk.getBlockState(p_179488_2_).is(this.blockToRemove)
                        && ichunk.getBlockState(p_179488_2_.above()).isAir()
                        && ichunk.getBlockState(p_179488_2_.above(2)).isAir();
            }
        }
    }
}