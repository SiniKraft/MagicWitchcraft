
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

import net.minecraft.world.World;
import net.minecraft.world.ServerBossInfo;
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
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
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
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Model_dangerous_dragon(), 0.5f) {
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magic_witchcraft:textures/dangerous_dragon.png");
				}
			};
		});
		RenderingRegistry.registerEntityRenderingHandler(ArrowCustomEntity.class,
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
			this.moveController = new FlyingMovementController(this);
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
					double dir_x = CustomEntity.this.posX + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = CustomEntity.this.posY + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = CustomEntity.this.posZ + ((random.nextFloat() * 2 - 1) * 16);
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
		public void fall(float l, float d) {
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
			Entity sourceentity = source.getTrueSource();
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
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
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
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
			double d0 = target.posY + (double) target.getEyeHeight() - 1.1;
			double d1 = target.posX - this.posX;
			double d3 = target.posZ - this.posZ;
			entityarrow.shoot(d1, d0 - entityarrow.posY + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.6F, 12.0F);
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
				double d1 = this.posX - this.prevPosX;
				double d0 = this.posZ - this.prevPosZ;
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
		private final RendererModel neck;
		private final RendererModel neck2;
		private final RendererModel neck3;
		private final RendererModel neck4;
		private final RendererModel neck5;
		private final RendererModel head;
		private final RendererModel jaw;
		private final RendererModel body;
		private final RendererModel wing;
		private final RendererModel wingtip;
		private final RendererModel wing1;
		private final RendererModel wingtip1;
		private final RendererModel rearleg;
		private final RendererModel rearlegtip;
		private final RendererModel rearfoot;
		private final RendererModel rearleg1;
		private final RendererModel rearlegtip1;
		private final RendererModel rearfoot1;
		private final RendererModel frontleg;
		private final RendererModel frontlegtip;
		private final RendererModel frontfoot;
		private final RendererModel frontleg1;
		private final RendererModel frontlegtip1;
		private final RendererModel frontfoot1;
		private final RendererModel tail;
		private final RendererModel tail2;
		private final RendererModel tail3;
		private final RendererModel tail4;
		private final RendererModel tail5;
		private final RendererModel tail6;
		private final RendererModel tail7;
		private final RendererModel tail8;
		private final RendererModel tail9;
		private final RendererModel tail10;
		private final RendererModel tail11;
		private final RendererModel tail12;
		public Model_dangerous_dragon() {
			textureWidth = 256;
			textureHeight = 256;
			neck = new RendererModel(this);
			neck.setRotationPoint(0.0F, 17.0F, -8.0F);
			neck.cubeList.add(new ModelBox(neck, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
			neck.cubeList.add(new ModelBox(neck, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));
			neck2 = new RendererModel(this);
			neck2.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck.addChild(neck2);
			neck2.cubeList.add(new ModelBox(neck2, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
			neck2.cubeList.add(new ModelBox(neck2, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));
			neck3 = new RendererModel(this);
			neck3.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck2.addChild(neck3);
			neck3.cubeList.add(new ModelBox(neck3, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
			neck3.cubeList.add(new ModelBox(neck3, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));
			neck4 = new RendererModel(this);
			neck4.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck3.addChild(neck4);
			neck4.cubeList.add(new ModelBox(neck4, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
			neck4.cubeList.add(new ModelBox(neck4, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));
			neck5 = new RendererModel(this);
			neck5.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck4.addChild(neck5);
			neck5.cubeList.add(new ModelBox(neck5, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
			neck5.cubeList.add(new ModelBox(neck5, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));
			head = new RendererModel(this);
			head.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck5.addChild(head);
			head.cubeList.add(new ModelBox(head, 176, 44, -6.0F, -1.0F, -30.0F, 12, 5, 16, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 112, 30, -8.0F, -8.0F, -16.0F, 16, 16, 16, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 0, 0, -5.0F, -12.0F, -10.0F, 2, 4, 6, 0.0F, true));
			head.cubeList.add(new ModelBox(head, 112, 0, -5.0F, -3.0F, -28.0F, 2, 2, 4, 0.0F, true));
			head.cubeList.add(new ModelBox(head, 0, 0, 3.0F, -12.0F, -10.0F, 2, 4, 6, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 112, 0, 3.0F, -3.0F, -28.0F, 2, 2, 4, 0.0F, false));
			jaw = new RendererModel(this);
			jaw.setRotationPoint(0.0F, 4.0F, -13.0F);
			head.addChild(jaw);
			setRotationAngle(jaw, 0.2618F, 0.0F, 0.0F);
			jaw.cubeList.add(new ModelBox(jaw, 176, 65, -6.0F, 0.0F, -17.0F, 12, 4, 16, 0.0F, false));
			body = new RendererModel(this);
			body.setRotationPoint(0.0F, 4.0F, 8.0F);
			body.cubeList.add(new ModelBox(body, 0, 0, -12.0F, 0.0F, -16.0F, 24, 24, 64, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 220, 53, -1.0F, -6.0F, -10.0F, 2, 6, 12, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 220, 53, -1.0F, -6.0F, 10.0F, 2, 6, 12, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 220, 53, -1.0F, -6.0F, 30.0F, 2, 6, 12, 0.0F, false));
			wing = new RendererModel(this);
			wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
			wing.cubeList.add(new ModelBox(wing, 112, 88, -56.0F, -4.0F, -4.0F, 56, 8, 8, 0.0F, false));
			wing.cubeList.add(new ModelBox(wing, -56, 88, -56.0F, 0.0F, 2.0F, 56, 0, 56, 0.01F, false));
			wingtip = new RendererModel(this);
			wingtip.setRotationPoint(-56.0F, 0.0F, -2.0F);
			wing.addChild(wingtip);
			wingtip.cubeList.add(new ModelBox(wingtip, 112, 136, -56.0F, -2.0F, 0.0F, 56, 4, 4, 0.0F, false));
			wingtip.cubeList.add(new ModelBox(wingtip, -56, 144, -56.0F, 0.0F, 4.0F, 56, 0, 56, 0.01F, false));
			wing1 = new RendererModel(this);
			wing1.setRotationPoint(12.0F, 5.0F, 2.0F);
			wing1.cubeList.add(new ModelBox(wing1, 112, 88, 0.0F, -4.0F, -4.0F, 56, 8, 8, 0.0F, true));
			wing1.cubeList.add(new ModelBox(wing1, -56, 88, 0.0F, 0.0F, 2.0F, 56, 0, 56, 0.01F, true));
			wingtip1 = new RendererModel(this);
			wingtip1.setRotationPoint(56.0F, 0.0F, -2.0F);
			wing1.addChild(wingtip1);
			wingtip1.cubeList.add(new ModelBox(wingtip1, 112, 136, 0.0F, -2.0F, 0.0F, 56, 4, 4, 0.0F, true));
			wingtip1.cubeList.add(new ModelBox(wingtip1, -56, 144, 0.0F, 0.0F, 4.0F, 56, 0, 56, 0.01F, true));
			rearleg = new RendererModel(this);
			rearleg.setRotationPoint(-16.0F, 16.0F, 42.0F);
			setRotationAngle(rearleg, 1.5708F, 0.0F, 0.0F);
			rearleg.cubeList.add(new ModelBox(rearleg, 0, 0, -8.0F, -4.0F, -8.0F, 16, 32, 16, 0.0F, false));
			rearlegtip = new RendererModel(this);
			rearlegtip.setRotationPoint(0.0F, 28.0F, 1.0F);
			rearleg.addChild(rearlegtip);
			rearlegtip.cubeList.add(new ModelBox(rearlegtip, 196, 0, -6.0F, 0.0F, -7.0F, 12, 32, 12, 0.0F, false));
			rearfoot = new RendererModel(this);
			rearfoot.setRotationPoint(0.0F, 32.0F, -2.0F);
			rearlegtip.addChild(rearfoot);
			setRotationAngle(rearfoot, 1.5708F, 0.0F, 0.0F);
			rearfoot.cubeList.add(new ModelBox(rearfoot, 112, 0, -9.0F, 0.0F, -20.0F, 18, 6, 24, 0.0F, false));
			rearleg1 = new RendererModel(this);
			rearleg1.setRotationPoint(16.0F, 16.0F, 42.0F);
			setRotationAngle(rearleg1, 1.5708F, 0.0F, 0.0F);
			rearleg1.cubeList.add(new ModelBox(rearleg1, 0, 0, -8.0F, -4.0F, -8.0F, 16, 32, 16, 0.0F, true));
			rearlegtip1 = new RendererModel(this);
			rearlegtip1.setRotationPoint(0.0F, 28.0F, 1.0F);
			rearleg1.addChild(rearlegtip1);
			rearlegtip1.cubeList.add(new ModelBox(rearlegtip1, 196, 0, -6.0F, 0.0F, -7.0F, 12, 32, 12, 0.0F, true));
			rearfoot1 = new RendererModel(this);
			rearfoot1.setRotationPoint(0.0F, 32.0F, -2.0F);
			rearlegtip1.addChild(rearfoot1);
			setRotationAngle(rearfoot1, 1.5708F, 0.0F, 0.0F);
			rearfoot1.cubeList.add(new ModelBox(rearfoot1, 112, 0, -9.0F, 0.0F, -23.0F, 18, 6, 24, 0.0F, true));
			frontleg = new RendererModel(this);
			frontleg.setRotationPoint(-12.0F, 20.0F, 2.0F);
			setRotationAngle(frontleg, 1.1345F, 0.0F, 0.0F);
			frontleg.cubeList.add(new ModelBox(frontleg, 112, 104, -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, false));
			frontlegtip = new RendererModel(this);
			frontlegtip.setRotationPoint(0.0F, 20.0F, 0.0F);
			frontleg.addChild(frontlegtip);
			setRotationAngle(frontlegtip, -0.3491F, 0.0F, 0.0F);
			frontlegtip.cubeList.add(new ModelBox(frontlegtip, 226, 138, -3.0F, -1.0F, -3.0F, 6, 24, 6, 0.0F, false));
			frontfoot = new RendererModel(this);
			frontfoot.setRotationPoint(0.0F, 22.0F, 0.0F);
			frontlegtip.addChild(frontfoot);
			setRotationAngle(frontfoot, 0.7854F, 0.0F, 0.0F);
			frontfoot.cubeList.add(new ModelBox(frontfoot, 144, 104, -4.0F, 0.0F, -12.0F, 8, 4, 16, 0.0F, false));
			frontleg1 = new RendererModel(this);
			frontleg1.setRotationPoint(12.0F, 20.0F, 2.0F);
			setRotationAngle(frontleg1, 1.1345F, 0.0F, 0.0F);
			frontleg1.cubeList.add(new ModelBox(frontleg1, 112, 104, -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, true));
			frontlegtip1 = new RendererModel(this);
			frontlegtip1.setRotationPoint(0.0F, 20.0F, 0.0F);
			frontleg1.addChild(frontlegtip1);
			setRotationAngle(frontlegtip1, -0.3491F, 0.0F, 0.0F);
			frontlegtip1.cubeList.add(new ModelBox(frontlegtip1, 226, 138, -3.0F, -1.0F, -3.0F, 6, 24, 6, 0.0F, true));
			frontfoot1 = new RendererModel(this);
			frontfoot1.setRotationPoint(0.0F, 22.0F, 0.0F);
			frontlegtip1.addChild(frontfoot1);
			setRotationAngle(frontfoot1, 0.7854F, 0.0F, 0.0F);
			frontfoot1.cubeList.add(new ModelBox(frontfoot1, 144, 104, -4.0F, 0.0F, -12.0F, 8, 4, 16, 0.0F, true));
			tail = new RendererModel(this);
			tail.setRotationPoint(0.0F, 10.0F, 58.0F);
			tail.cubeList.add(new ModelBox(tail, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail.cubeList.add(new ModelBox(tail, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail2 = new RendererModel(this);
			tail2.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail.addChild(tail2);
			tail2.cubeList.add(new ModelBox(tail2, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail2.cubeList.add(new ModelBox(tail2, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail3 = new RendererModel(this);
			tail3.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail2.addChild(tail3);
			tail3.cubeList.add(new ModelBox(tail3, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail3.cubeList.add(new ModelBox(tail3, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail4 = new RendererModel(this);
			tail4.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail3.addChild(tail4);
			tail4.cubeList.add(new ModelBox(tail4, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail4.cubeList.add(new ModelBox(tail4, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail5 = new RendererModel(this);
			tail5.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail4.addChild(tail5);
			tail5.cubeList.add(new ModelBox(tail5, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail5.cubeList.add(new ModelBox(tail5, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail6 = new RendererModel(this);
			tail6.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail5.addChild(tail6);
			tail6.cubeList.add(new ModelBox(tail6, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail6.cubeList.add(new ModelBox(tail6, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail7 = new RendererModel(this);
			tail7.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail6.addChild(tail7);
			tail7.cubeList.add(new ModelBox(tail7, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail7.cubeList.add(new ModelBox(tail7, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail8 = new RendererModel(this);
			tail8.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail7.addChild(tail8);
			tail8.cubeList.add(new ModelBox(tail8, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail8.cubeList.add(new ModelBox(tail8, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail9 = new RendererModel(this);
			tail9.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail8.addChild(tail9);
			tail9.cubeList.add(new ModelBox(tail9, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail9.cubeList.add(new ModelBox(tail9, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail10 = new RendererModel(this);
			tail10.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail9.addChild(tail10);
			tail10.cubeList.add(new ModelBox(tail10, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail10.cubeList.add(new ModelBox(tail10, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail11 = new RendererModel(this);
			tail11.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail10.addChild(tail11);
			tail11.cubeList.add(new ModelBox(tail11, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail11.cubeList.add(new ModelBox(tail11, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail12 = new RendererModel(this);
			tail12.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail11.addChild(tail12);
			tail12.cubeList.add(new ModelBox(tail12, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail12.cubeList.add(new ModelBox(tail12, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			neck.render(f5);
			body.render(f5);
			wing.render(f5);
			wing1.render(f5);
			rearleg.render(f5);
			rearleg1.render(f5);
			frontleg.render(f5);
			frontleg1.render(f5);
			tail.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.jaw.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.wing1.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.wing.rotateAngleZ = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}
