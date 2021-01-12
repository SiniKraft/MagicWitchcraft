
package fr.sinikraft.magicwitchcraft.entity;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.World;
import net.minecraft.world.BossInfo;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.block.BlockState;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;

import fr.sinikraft.magicwitchcraft.procedures.DangerousDragonEntityDiesProcedure;
import fr.sinikraft.magicwitchcraft.itemgroup.MagicWitchCraftItemGroup;
import fr.sinikraft.magicwitchcraft.block.MysteriousLootBlock;
import fr.sinikraft.magicwitchcraft.MagicWitchcraftModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@MagicWitchcraftModElements.ModElement.Tag
public class DangerousDragonEntity extends MagicWitchcraftModElements.ModElement {
	public static EntityType entity = null;
	@ObjectHolder("magic_witchcraft:entitybulletdangerousdragon")
	public static final EntityType arrow = null;
	public DangerousDragonEntity(MagicWitchcraftModElements instance) {
		super(instance, 25);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(500).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(8.2f, 0.75f)).build("dangerousdragon")
						.setRegistryName("dangerousdragon");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -16777216, -10027009, new Item.Properties().group(MagicWitchCraftItemGroup.tab))
				.setRegistryName("dangerousdragon_spawn_egg"));
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
				.size(0.5f, 0.5f)).build("entitybulletdangerousdragon").setRegistryName("entitybulletdangerousdragon"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Model_dangerous_dragon(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magic_witchcraft:textures/dangerous_dragon.png");
				}
			};
		});
		RenderingRegistry.registerEntityRenderingHandler(arrow,
				renderManager -> new SpriteRenderer(renderManager, Minecraft.getInstance().getItemRenderer()));
	}
	public static class CustomEntity extends MonsterEntity implements IRangedAttackMob {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 25;
			setNoAI(false);
			enablePersistence();
			this.moveController = new FlyingMovementController(this, 10, true);
			this.navigator = new FlyingPathNavigator(this, this.world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 0.8, 20) {
				@Override
				protected Vec3d getPosition() {
					Random random = CustomEntity.this.getRNG();
					double dir_x = CustomEntity.this.getPosX() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = CustomEntity.this.getPosY() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = CustomEntity.this.getPosZ() + ((random.nextFloat() * 2 - 1) * 16);
					return new Vec3d(dir_x, dir_y, dir_z);
				}
			});
			this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(3, new SwimGoal(this));
			this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, (float) 0.8));
			this.goalSelector.addGoal(5, new Goal() {
				{
					this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
				}
				public boolean shouldExecute() {
					if (CustomEntity.this.getAttackTarget() != null && !CustomEntity.this.getMoveHelper().isUpdating()) {
						return true;
					} else {
						return false;
					}
				}

				@Override
				public boolean shouldContinueExecuting() {
					return CustomEntity.this.getMoveHelper().isUpdating() && CustomEntity.this.getAttackTarget() != null
							&& CustomEntity.this.getAttackTarget().isAlive();
				}

				@Override
				public void startExecuting() {
					LivingEntity livingentity = CustomEntity.this.getAttackTarget();
					Vec3d vec3d = livingentity.getEyePosition(1);
					CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
				}

				@Override
				public void tick() {
					LivingEntity livingentity = CustomEntity.this.getAttackTarget();
					if (CustomEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
						CustomEntity.this.attackEntityAsMob(livingentity);
					} else {
						double d0 = CustomEntity.this.getDistanceSq(livingentity);
						if (d0 < 300) {
							Vec3d vec3d = livingentity.getEyePosition(1);
							CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
						}
					}
				}
			});
			this.targetSelector.addGoal(6, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(7, new AvoidEntityGoal(this, DangerousWitchEntity.CustomEntity.class, (float) 24, 1, 1.2));
			this.goalSelector.addGoal(8, new FollowMobGoal(this, (float) 1, 10, 5));
			this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, (float) 32));
			this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 20, 10) {
				@Override
				public boolean shouldContinueExecuting() {
					return this.shouldExecute();
				}
			});
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(MysteriousLootBlock.block, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ender_dragon.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ender_dragon.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ender_dragon.death"));
		}

		@Override
		public boolean onLivingFall(float l, float d) {
			return false;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void onDeath(DamageSource source) {
			super.onDeath(source);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity sourceentity = source.getTrueSource();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				DangerousDragonEntityDiesProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public boolean processInteract(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			boolean retval = true;
			super.processInteract(sourceentity, hand);
			sourceentity.startRiding(this);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			return retval;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15);
			if (this.getAttribute(SharedMonsterAttributes.FLYING_SPEED) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
			this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.6);
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) {
			ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, this, this.world);
			double d0 = target.getPosY() + (double) target.getEyeHeight() - 1.1;
			double d1 = target.getPosX() - this.getPosX();
			double d3 = target.getPosZ() - this.getPosZ();
			entityarrow.shoot(d1, d0 - entityarrow.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.6F, 12.0F);
			world.addEntity(entityarrow);
		}

		@Override
		public boolean isNonBoss() {
			return false;
		}
		private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS);
		@Override
		public void addTrackingPlayer(ServerPlayerEntity player) {
			super.addTrackingPlayer(player);
			this.bossInfo.addPlayer(player);
		}

		@Override
		public void removeTrackingPlayer(ServerPlayerEntity player) {
			super.removeTrackingPlayer(player);
			this.bossInfo.removePlayer(player);
		}

		@Override
		public void updateAITasks() {
			super.updateAITasks();
			this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		}

		@Override
		public void travel(Vec3d dir) {
			Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
			if (this.isBeingRidden()) {
				this.rotationYaw = entity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch = entity.rotationPitch * 0.5F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.jumpMovementFactor = this.getAIMoveSpeed() * 0.15F;
				this.renderYawOffset = entity.rotationYaw;
				this.rotationYawHead = entity.rotationYaw;
				this.stepHeight = 1.0F;
				if (entity instanceof LivingEntity) {
					this.setAIMoveSpeed((float) this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
					float forward = ((LivingEntity) entity).moveForward;
					float strafe = ((LivingEntity) entity).moveStrafing;
					super.travel(new Vec3d(strafe, 0, forward));
				}
				this.prevLimbSwingAmount = this.limbSwingAmount;
				double d1 = this.getPosX() - this.prevPosX;
				double d0 = this.getPosZ() - this.prevPosZ;
				float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
				if (f1 > 1.0F)
					f1 = 1.0F;
				this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
				this.limbSwing += this.limbSwingAmount;
				return;
			}
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.travel(dir);
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}

		public void livingTick() {
			super.livingTick();
			this.setNoGravity(true);
		}
	}

	@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
	private static class ArrowCustomEntity extends AbstractArrowEntity implements IRendersAsItem {
		public ArrowCustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			super(arrow, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, World world) {
			super(type, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, double x, double y, double z, World world) {
			super(type, x, y, z, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, LivingEntity entity, World world) {
			super(type, entity, world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack getItem() {
			return new ItemStack(Items.FIRE_CHARGE, (int) (1));
		}

		@Override
		protected ItemStack getArrowStack() {
			return new ItemStack(Items.FIRE_CHARGE, (int) (1));
		}
	}

	public static class Model_dangerous_dragon extends EntityModel<Entity> {
		private final ModelRenderer neck;
		private final ModelRenderer neck2;
		private final ModelRenderer neck3;
		private final ModelRenderer neck4;
		private final ModelRenderer neck5;
		private final ModelRenderer head;
		private final ModelRenderer jaw;
		private final ModelRenderer body;
		private final ModelRenderer wing;
		private final ModelRenderer wingtip;
		private final ModelRenderer wing1;
		private final ModelRenderer wingtip1;
		private final ModelRenderer rearleg;
		private final ModelRenderer rearlegtip;
		private final ModelRenderer rearfoot;
		private final ModelRenderer rearleg1;
		private final ModelRenderer rearlegtip1;
		private final ModelRenderer rearfoot1;
		private final ModelRenderer frontleg;
		private final ModelRenderer frontlegtip;
		private final ModelRenderer frontfoot;
		private final ModelRenderer frontleg1;
		private final ModelRenderer frontlegtip1;
		private final ModelRenderer frontfoot1;
		private final ModelRenderer tail;
		private final ModelRenderer tail2;
		private final ModelRenderer tail3;
		private final ModelRenderer tail4;
		private final ModelRenderer tail5;
		private final ModelRenderer tail6;
		private final ModelRenderer tail7;
		private final ModelRenderer tail8;
		private final ModelRenderer tail9;
		private final ModelRenderer tail10;
		private final ModelRenderer tail11;
		private final ModelRenderer tail12;
		public Model_dangerous_dragon() {
			textureWidth = 256;
			textureHeight = 256;
			neck = new ModelRenderer(this);
			neck.setRotationPoint(0.0F, 17.0F, -8.0F);
			addBoxHelper(neck, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(neck, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false);
			neck2 = new ModelRenderer(this);
			neck2.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck.addChild(neck2);
			addBoxHelper(neck2, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(neck2, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false);
			neck3 = new ModelRenderer(this);
			neck3.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck2.addChild(neck3);
			addBoxHelper(neck3, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(neck3, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false);
			neck4 = new ModelRenderer(this);
			neck4.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck3.addChild(neck4);
			addBoxHelper(neck4, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(neck4, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false);
			neck5 = new ModelRenderer(this);
			neck5.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck4.addChild(neck5);
			addBoxHelper(neck5, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(neck5, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck5.addChild(head);
			addBoxHelper(head, 176, 44, -6.0F, -1.0F, -30.0F, 12, 5, 16, 0.0F, false);
			addBoxHelper(head, 112, 30, -8.0F, -8.0F, -16.0F, 16, 16, 16, 0.0F, false);
			addBoxHelper(head, 0, 0, -5.0F, -12.0F, -10.0F, 2, 4, 6, 0.0F, true);
			addBoxHelper(head, 112, 0, -5.0F, -3.0F, -28.0F, 2, 2, 4, 0.0F, true);
			addBoxHelper(head, 0, 0, 3.0F, -12.0F, -10.0F, 2, 4, 6, 0.0F, false);
			addBoxHelper(head, 112, 0, 3.0F, -3.0F, -28.0F, 2, 2, 4, 0.0F, false);
			jaw = new ModelRenderer(this);
			jaw.setRotationPoint(0.0F, 4.0F, -13.0F);
			head.addChild(jaw);
			setRotationAngle(jaw, 0.2618F, 0.0F, 0.0F);
			addBoxHelper(jaw, 176, 65, -6.0F, 0.0F, -17.0F, 12, 4, 16, 0.0F, false);
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 4.0F, 8.0F);
			addBoxHelper(body, 0, 0, -12.0F, 0.0F, -16.0F, 24, 24, 64, 0.0F, false);
			addBoxHelper(body, 220, 53, -1.0F, -6.0F, -10.0F, 2, 6, 12, 0.0F, false);
			addBoxHelper(body, 220, 53, -1.0F, -6.0F, 10.0F, 2, 6, 12, 0.0F, false);
			addBoxHelper(body, 220, 53, -1.0F, -6.0F, 30.0F, 2, 6, 12, 0.0F, false);
			wing = new ModelRenderer(this);
			wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
			addBoxHelper(wing, 112, 88, -56.0F, -4.0F, -4.0F, 56, 8, 8, 0.0F, false);
			addBoxHelper(wing, -56, 88, -56.0F, 0.0F, 2.0F, 56, 0, 56, 0.01F, false);
			wingtip = new ModelRenderer(this);
			wingtip.setRotationPoint(-56.0F, 0.0F, -2.0F);
			wing.addChild(wingtip);
			addBoxHelper(wingtip, 112, 136, -56.0F, -2.0F, 0.0F, 56, 4, 4, 0.0F, false);
			addBoxHelper(wingtip, -56, 144, -56.0F, 0.0F, 4.0F, 56, 0, 56, 0.01F, false);
			wing1 = new ModelRenderer(this);
			wing1.setRotationPoint(12.0F, 5.0F, 2.0F);
			addBoxHelper(wing1, 112, 88, 0.0F, -4.0F, -4.0F, 56, 8, 8, 0.0F, true);
			addBoxHelper(wing1, -56, 88, 0.0F, 0.0F, 2.0F, 56, 0, 56, 0.01F, true);
			wingtip1 = new ModelRenderer(this);
			wingtip1.setRotationPoint(56.0F, 0.0F, -2.0F);
			wing1.addChild(wingtip1);
			addBoxHelper(wingtip1, 112, 136, 0.0F, -2.0F, 0.0F, 56, 4, 4, 0.0F, true);
			addBoxHelper(wingtip1, -56, 144, 0.0F, 0.0F, 4.0F, 56, 0, 56, 0.01F, true);
			rearleg = new ModelRenderer(this);
			rearleg.setRotationPoint(-16.0F, 16.0F, 42.0F);
			setRotationAngle(rearleg, 1.5708F, 0.0F, 0.0F);
			addBoxHelper(rearleg, 0, 0, -8.0F, -4.0F, -8.0F, 16, 32, 16, 0.0F, false);
			rearlegtip = new ModelRenderer(this);
			rearlegtip.setRotationPoint(0.0F, 28.0F, 1.0F);
			rearleg.addChild(rearlegtip);
			addBoxHelper(rearlegtip, 196, 0, -6.0F, 0.0F, -7.0F, 12, 32, 12, 0.0F, false);
			rearfoot = new ModelRenderer(this);
			rearfoot.setRotationPoint(0.0F, 32.0F, -2.0F);
			rearlegtip.addChild(rearfoot);
			setRotationAngle(rearfoot, 1.5708F, 0.0F, 0.0F);
			addBoxHelper(rearfoot, 112, 0, -9.0F, 0.0F, -20.0F, 18, 6, 24, 0.0F, false);
			rearleg1 = new ModelRenderer(this);
			rearleg1.setRotationPoint(16.0F, 16.0F, 42.0F);
			setRotationAngle(rearleg1, 1.5708F, 0.0F, 0.0F);
			addBoxHelper(rearleg1, 0, 0, -8.0F, -4.0F, -8.0F, 16, 32, 16, 0.0F, true);
			rearlegtip1 = new ModelRenderer(this);
			rearlegtip1.setRotationPoint(0.0F, 28.0F, 1.0F);
			rearleg1.addChild(rearlegtip1);
			addBoxHelper(rearlegtip1, 196, 0, -6.0F, 0.0F, -7.0F, 12, 32, 12, 0.0F, true);
			rearfoot1 = new ModelRenderer(this);
			rearfoot1.setRotationPoint(0.0F, 32.0F, -2.0F);
			rearlegtip1.addChild(rearfoot1);
			setRotationAngle(rearfoot1, 1.5708F, 0.0F, 0.0F);
			addBoxHelper(rearfoot1, 112, 0, -9.0F, 0.0F, -23.0F, 18, 6, 24, 0.0F, true);
			frontleg = new ModelRenderer(this);
			frontleg.setRotationPoint(-12.0F, 20.0F, 2.0F);
			setRotationAngle(frontleg, 1.1345F, 0.0F, 0.0F);
			addBoxHelper(frontleg, 112, 104, -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, false);
			frontlegtip = new ModelRenderer(this);
			frontlegtip.setRotationPoint(0.0F, 20.0F, 0.0F);
			frontleg.addChild(frontlegtip);
			setRotationAngle(frontlegtip, -0.3491F, 0.0F, 0.0F);
			addBoxHelper(frontlegtip, 226, 138, -3.0F, -1.0F, -3.0F, 6, 24, 6, 0.0F, false);
			frontfoot = new ModelRenderer(this);
			frontfoot.setRotationPoint(0.0F, 22.0F, 0.0F);
			frontlegtip.addChild(frontfoot);
			setRotationAngle(frontfoot, 0.7854F, 0.0F, 0.0F);
			addBoxHelper(frontfoot, 144, 104, -4.0F, 0.0F, -12.0F, 8, 4, 16, 0.0F, false);
			frontleg1 = new ModelRenderer(this);
			frontleg1.setRotationPoint(12.0F, 20.0F, 2.0F);
			setRotationAngle(frontleg1, 1.1345F, 0.0F, 0.0F);
			addBoxHelper(frontleg1, 112, 104, -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, true);
			frontlegtip1 = new ModelRenderer(this);
			frontlegtip1.setRotationPoint(0.0F, 20.0F, 0.0F);
			frontleg1.addChild(frontlegtip1);
			setRotationAngle(frontlegtip1, -0.3491F, 0.0F, 0.0F);
			addBoxHelper(frontlegtip1, 226, 138, -3.0F, -1.0F, -3.0F, 6, 24, 6, 0.0F, true);
			frontfoot1 = new ModelRenderer(this);
			frontfoot1.setRotationPoint(0.0F, 22.0F, 0.0F);
			frontlegtip1.addChild(frontfoot1);
			setRotationAngle(frontfoot1, 0.7854F, 0.0F, 0.0F);
			addBoxHelper(frontfoot1, 144, 104, -4.0F, 0.0F, -12.0F, 8, 4, 16, 0.0F, true);
			tail = new ModelRenderer(this);
			tail.setRotationPoint(0.0F, 10.0F, 58.0F);
			addBoxHelper(tail, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(tail, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false);
			tail2 = new ModelRenderer(this);
			tail2.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail.addChild(tail2);
			addBoxHelper(tail2, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(tail2, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false);
			tail3 = new ModelRenderer(this);
			tail3.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail2.addChild(tail3);
			addBoxHelper(tail3, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(tail3, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false);
			tail4 = new ModelRenderer(this);
			tail4.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail3.addChild(tail4);
			addBoxHelper(tail4, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(tail4, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false);
			tail5 = new ModelRenderer(this);
			tail5.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail4.addChild(tail5);
			addBoxHelper(tail5, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(tail5, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false);
			tail6 = new ModelRenderer(this);
			tail6.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail5.addChild(tail6);
			addBoxHelper(tail6, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(tail6, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false);
			tail7 = new ModelRenderer(this);
			tail7.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail6.addChild(tail7);
			addBoxHelper(tail7, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(tail7, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false);
			tail8 = new ModelRenderer(this);
			tail8.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail7.addChild(tail8);
			addBoxHelper(tail8, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(tail8, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false);
			tail9 = new ModelRenderer(this);
			tail9.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail8.addChild(tail9);
			addBoxHelper(tail9, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(tail9, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false);
			tail10 = new ModelRenderer(this);
			tail10.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail9.addChild(tail10);
			addBoxHelper(tail10, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(tail10, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false);
			tail11 = new ModelRenderer(this);
			tail11.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail10.addChild(tail11);
			addBoxHelper(tail11, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(tail11, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false);
			tail12 = new ModelRenderer(this);
			tail12.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail11.addChild(tail12);
			addBoxHelper(tail12, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false);
			addBoxHelper(tail12, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			neck.render(ms, vb, i1, i2, f1, f2, f3, f4);
			body.render(ms, vb, i1, i2, f1, f2, f3, f4);
			wing.render(ms, vb, i1, i2, f1, f2, f3, f4);
			wing1.render(ms, vb, i1, i2, f1, f2, f3, f4);
			rearleg.render(ms, vb, i1, i2, f1, f2, f3, f4);
			rearleg1.render(ms, vb, i1, i2, f1, f2, f3, f4);
			frontleg.render(ms, vb, i1, i2, f1, f2, f3, f4);
			frontleg1.render(ms, vb, i1, i2, f1, f2, f3, f4);
			tail.render(ms, vb, i1, i2, f1, f2, f3, f4);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.jaw.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.wing1.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.wing.rotateAngleZ = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
	@OnlyIn(Dist.CLIENT)
	public static void addBoxHelper(ModelRenderer renderer, int texU, int texV, float x, float y, float z, int dx, int dy, int dz, float delta) {
		addBoxHelper(renderer, texU, texV, x, y, z, dx, dy, dz, delta, renderer.mirror);
	}

	@OnlyIn(Dist.CLIENT)
	public static void addBoxHelper(ModelRenderer renderer, int texU, int texV, float x, float y, float z, int dx, int dy, int dz, float delta,
			boolean mirror) {
		renderer.mirror = mirror;
		renderer.addBox("", x, y, z, dx, dy, dz, delta, texU, texV);
	}
}
