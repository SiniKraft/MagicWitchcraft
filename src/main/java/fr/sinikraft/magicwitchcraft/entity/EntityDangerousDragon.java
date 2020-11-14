
package fr.sinikraft.magicwitchcraft.entity;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.BossInfo;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumHand;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.Minecraft;
import net.minecraft.block.state.IBlockState;

import java.util.Random;
import java.util.Iterator;
import java.util.ArrayList;

import fr.sinikraft.magicwitchcraft.procedure.ProcedureDangerousDragonEntityDies;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class EntityDangerousDragon extends ElementsMagicWitchcraft.ModElement {
	public static final int ENTITYID = 4;
	public static final int ENTITYID_RANGED = 5;
	public EntityDangerousDragon(ElementsMagicWitchcraft instance) {
		super(instance, 53);
	}

	@Override
	public void initElements() {
		elements.entities.add(
				() -> EntityEntryBuilder.create().entity(EntityCustom.class).id(new ResourceLocation("magic_witchcraft", "dangerousdragon"), ENTITYID)
						.name("dangerousdragon").tracker(500, 3, true).egg(-16777216, -10027009).build());
		elements.entities.add(() -> EntityEntryBuilder.create().entity(EntityArrowCustom.class)
				.id(new ResourceLocation("magic_witchcraft", "entitybulletdangerousdragon"), ENTITYID_RANGED).name("entitybulletdangerousdragon")
				.tracker(64, 1, true).build());
	}

	private Biome[] allbiomes(net.minecraft.util.registry.RegistryNamespaced<ResourceLocation, Biome> in) {
		Iterator<Biome> itr = in.iterator();
		ArrayList<Biome> ls = new ArrayList<Biome>();
		while (itr.hasNext())
			ls.add(itr.next());
		return ls.toArray(new Biome[ls.size()]);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityCustom.class, renderManager -> {
			return new RenderLiving(renderManager, new Model_dangerous_dragon(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magic_witchcraft:textures/dangerous_dragon.png");
				}
			};
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowCustom.class, renderManager -> {
			return new RenderSnowball<EntityArrowCustom>(renderManager, null, Minecraft.getMinecraft().getRenderItem()) {
				public ItemStack getStackToRender(EntityArrowCustom entity) {
					return new ItemStack(Items.FIRE_CHARGE, (int) (1));
				}
			};
		});
	}
	public static class EntityCustom extends EntityMob implements IRangedAttackMob {
		public EntityCustom(World world) {
			super(world);
			setSize(8.2f, 0.75f);
			experienceValue = 0;
			this.isImmuneToFire = false;
			setNoAI(!true);
			enablePersistence();
			this.navigator = new PathNavigateFlying(this, this.world);
			this.moveHelper = new EntityFlyHelper(this);
		}

		@Override
		protected void initEntityAI() {
			super.initEntityAI();
			this.tasks.addTask(1, new EntityAIWander(this, 0.8, 20) {
				@Override
				protected Vec3d getPosition() {
					Random random = EntityCustom.this.getRNG();
					double dir_x = EntityCustom.this.posX + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = EntityCustom.this.posY + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = EntityCustom.this.posZ + ((random.nextFloat() * 2 - 1) * 16);
					return new Vec3d(dir_x, dir_y, dir_z);
				}
			});
			this.tasks.addTask(2, new EntityAILookIdle(this));
			this.tasks.addTask(3, new EntityAISwimming(this));
			this.tasks.addTask(4, new EntityAILeapAtTarget(this, (float) 0.8));
			this.tasks.addTask(5, new EntityAIBase() {
				{
					this.setMutexBits(1);
				}
				public boolean shouldExecute() {
					if (EntityCustom.this.getAttackTarget() != null && !EntityCustom.this.getMoveHelper().isUpdating()) {
						return true;
					} else {
						return false;
					}
				}

				@Override
				public boolean shouldContinueExecuting() {
					return EntityCustom.this.getMoveHelper().isUpdating() && EntityCustom.this.getAttackTarget() != null
							&& EntityCustom.this.getAttackTarget().isEntityAlive();
				}

				@Override
				public void startExecuting() {
					EntityLivingBase livingentity = EntityCustom.this.getAttackTarget();
					Vec3d vec3d = livingentity.getPositionEyes(1);
					EntityCustom.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
				}

				@Override
				public void updateTask() {
					EntityLivingBase livingentity = EntityCustom.this.getAttackTarget();
					double d0 = EntityCustom.this.getDistanceSq(livingentity);
					if (d0 <= getAttackReachSq(livingentity)) {
						EntityCustom.this.attackEntityAsMob(livingentity);
					} else if (d0 < 300) {
						Vec3d vec3d = livingentity.getPositionEyes(1);
						EntityCustom.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
					}
				}

				protected double getAttackReachSq(EntityLivingBase attackTarget) {
					return EntityCustom.this.width * 1.5 * EntityCustom.this.height * 1.5 + attackTarget.height;
				}
			});
			this.targetTasks.addTask(6, new EntityAIHurtByTarget(this, false));
			this.tasks.addTask(1, new EntityAIAttackRanged(this, 1.25D, 20, 10.0F));
		}

		@Override
		public EnumCreatureAttribute getCreatureAttribute() {
			return EnumCreatureAttribute.UNDEFINED;
		}

		@Override
		protected boolean canDespawn() {
			return false;
		}

		@Override
		protected Item getDropItem() {
			return null;
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("entity.enderdragon.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.enderdragon.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.enderdragon.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public void fall(float l, float d) {
		}

		@Override
		public void onDeath(DamageSource source) {
			super.onDeath(source);
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			Entity entity = this;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureDangerousDragonEntityDies.executeProcedure($_dependencies);
			}
		}

		@Override
		public boolean processInteract(EntityPlayer entity, EnumHand hand) {
			super.processInteract(entity, hand);
			entity.startRiding(this);
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			ItemStack itemstack = entity.getHeldItem(hand);
			return true;
		}

		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
			if (this.getEntityAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
			if (this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
			if (this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200D);
			if (this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15D);
			this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
			this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.6);
		}

		@Override
		public void setSwingingArms(boolean swingingArms) {
		}

		public void attackEntityWithRangedAttack(EntityLivingBase target, float flval) {
			EntityArrowCustom entityarrow = new EntityArrowCustom(this.world, this);
			double d0 = target.posY + (double) target.getEyeHeight() - 1.1;
			double d1 = target.posX - this.posX;
			double d3 = target.posZ - this.posZ;
			entityarrow.shoot(d1, d0 - entityarrow.posY + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.6F, 12.0F);
			this.world.spawnEntity(entityarrow);
		}

		@Override
		public boolean isNonBoss() {
			return false;
		}
		private final BossInfoServer bossInfo = new BossInfoServer(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS);
		@Override
		public void addTrackingPlayer(EntityPlayerMP player) {
			super.addTrackingPlayer(player);
			this.bossInfo.addPlayer(player);
		}

		@Override
		public void removeTrackingPlayer(EntityPlayerMP player) {
			super.removeTrackingPlayer(player);
			this.bossInfo.removePlayer(player);
		}

		@Override
		public void onUpdate() {
			super.onUpdate();
			this.setNoGravity(true);
			this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		}

		@Override
		public void travel(float ti, float tj, float tk) {
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
				if (entity instanceof EntityLivingBase) {
					this.setAIMoveSpeed((float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
					float forward = ((EntityLivingBase) entity).moveForward;
					float strafe = ((EntityLivingBase) entity).moveStrafing;
					super.travel(strafe, 0, forward);
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
			super.travel(ti, tj, tk);
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}
	}

	public static class EntityArrowCustom extends EntityTippedArrow {
		public EntityArrowCustom(World a) {
			super(a);
		}

		public EntityArrowCustom(World worldIn, double x, double y, double z) {
			super(worldIn, x, y, z);
		}

		public EntityArrowCustom(World worldIn, EntityLivingBase shooter) {
			super(worldIn, shooter);
		}
	}

	public static class Model_dangerous_dragon extends ModelBase {
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
			neck.cubeList.add(new ModelBox(neck, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
			neck.cubeList.add(new ModelBox(neck, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));
			neck2 = new ModelRenderer(this);
			neck2.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck.addChild(neck2);
			neck2.cubeList.add(new ModelBox(neck2, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
			neck2.cubeList.add(new ModelBox(neck2, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));
			neck3 = new ModelRenderer(this);
			neck3.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck2.addChild(neck3);
			neck3.cubeList.add(new ModelBox(neck3, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
			neck3.cubeList.add(new ModelBox(neck3, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));
			neck4 = new ModelRenderer(this);
			neck4.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck3.addChild(neck4);
			neck4.cubeList.add(new ModelBox(neck4, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
			neck4.cubeList.add(new ModelBox(neck4, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));
			neck5 = new ModelRenderer(this);
			neck5.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck4.addChild(neck5);
			neck5.cubeList.add(new ModelBox(neck5, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
			neck5.cubeList.add(new ModelBox(neck5, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 0.0F, -10.0F);
			neck5.addChild(head);
			head.cubeList.add(new ModelBox(head, 176, 44, -6.0F, -1.0F, -30.0F, 12, 5, 16, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 112, 30, -8.0F, -8.0F, -16.0F, 16, 16, 16, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 0, 0, -5.0F, -12.0F, -10.0F, 2, 4, 6, 0.0F, true));
			head.cubeList.add(new ModelBox(head, 112, 0, -5.0F, -3.0F, -28.0F, 2, 2, 4, 0.0F, true));
			head.cubeList.add(new ModelBox(head, 0, 0, 3.0F, -12.0F, -10.0F, 2, 4, 6, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 112, 0, 3.0F, -3.0F, -28.0F, 2, 2, 4, 0.0F, false));
			jaw = new ModelRenderer(this);
			jaw.setRotationPoint(0.0F, 4.0F, -13.0F);
			head.addChild(jaw);
			setRotationAngle(jaw, 0.2618F, 0.0F, 0.0F);
			jaw.cubeList.add(new ModelBox(jaw, 176, 65, -6.0F, 0.0F, -17.0F, 12, 4, 16, 0.0F, false));
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 4.0F, 8.0F);
			body.cubeList.add(new ModelBox(body, 0, 0, -12.0F, 0.0F, -16.0F, 24, 24, 64, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 220, 53, -1.0F, -6.0F, -10.0F, 2, 6, 12, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 220, 53, -1.0F, -6.0F, 10.0F, 2, 6, 12, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 220, 53, -1.0F, -6.0F, 30.0F, 2, 6, 12, 0.0F, false));
			wing = new ModelRenderer(this);
			wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
			wing.cubeList.add(new ModelBox(wing, 112, 88, -56.0F, -4.0F, -4.0F, 56, 8, 8, 0.0F, false));
			wing.cubeList.add(new ModelBox(wing, -56, 88, -56.0F, 0.0F, 2.0F, 56, 0, 56, 0.01F, false));
			wingtip = new ModelRenderer(this);
			wingtip.setRotationPoint(-56.0F, 0.0F, -2.0F);
			wing.addChild(wingtip);
			wingtip.cubeList.add(new ModelBox(wingtip, 112, 136, -56.0F, -2.0F, 0.0F, 56, 4, 4, 0.0F, false));
			wingtip.cubeList.add(new ModelBox(wingtip, -56, 144, -56.0F, 0.0F, 4.0F, 56, 0, 56, 0.01F, false));
			wing1 = new ModelRenderer(this);
			wing1.setRotationPoint(12.0F, 5.0F, 2.0F);
			wing1.cubeList.add(new ModelBox(wing1, 112, 88, 0.0F, -4.0F, -4.0F, 56, 8, 8, 0.0F, true));
			wing1.cubeList.add(new ModelBox(wing1, -56, 88, 0.0F, 0.0F, 2.0F, 56, 0, 56, 0.01F, true));
			wingtip1 = new ModelRenderer(this);
			wingtip1.setRotationPoint(56.0F, 0.0F, -2.0F);
			wing1.addChild(wingtip1);
			wingtip1.cubeList.add(new ModelBox(wingtip1, 112, 136, 0.0F, -2.0F, 0.0F, 56, 4, 4, 0.0F, true));
			wingtip1.cubeList.add(new ModelBox(wingtip1, -56, 144, 0.0F, 0.0F, 4.0F, 56, 0, 56, 0.01F, true));
			rearleg = new ModelRenderer(this);
			rearleg.setRotationPoint(-16.0F, 16.0F, 42.0F);
			setRotationAngle(rearleg, 1.5708F, 0.0F, 0.0F);
			rearleg.cubeList.add(new ModelBox(rearleg, 0, 0, -8.0F, -4.0F, -8.0F, 16, 32, 16, 0.0F, false));
			rearlegtip = new ModelRenderer(this);
			rearlegtip.setRotationPoint(0.0F, 28.0F, 1.0F);
			rearleg.addChild(rearlegtip);
			rearlegtip.cubeList.add(new ModelBox(rearlegtip, 196, 0, -6.0F, 0.0F, -7.0F, 12, 32, 12, 0.0F, false));
			rearfoot = new ModelRenderer(this);
			rearfoot.setRotationPoint(0.0F, 32.0F, -2.0F);
			rearlegtip.addChild(rearfoot);
			setRotationAngle(rearfoot, 1.5708F, 0.0F, 0.0F);
			rearfoot.cubeList.add(new ModelBox(rearfoot, 112, 0, -9.0F, 0.0F, -20.0F, 18, 6, 24, 0.0F, false));
			rearleg1 = new ModelRenderer(this);
			rearleg1.setRotationPoint(16.0F, 16.0F, 42.0F);
			setRotationAngle(rearleg1, 1.5708F, 0.0F, 0.0F);
			rearleg1.cubeList.add(new ModelBox(rearleg1, 0, 0, -8.0F, -4.0F, -8.0F, 16, 32, 16, 0.0F, true));
			rearlegtip1 = new ModelRenderer(this);
			rearlegtip1.setRotationPoint(0.0F, 28.0F, 1.0F);
			rearleg1.addChild(rearlegtip1);
			rearlegtip1.cubeList.add(new ModelBox(rearlegtip1, 196, 0, -6.0F, 0.0F, -7.0F, 12, 32, 12, 0.0F, true));
			rearfoot1 = new ModelRenderer(this);
			rearfoot1.setRotationPoint(0.0F, 32.0F, -2.0F);
			rearlegtip1.addChild(rearfoot1);
			setRotationAngle(rearfoot1, 1.5708F, 0.0F, 0.0F);
			rearfoot1.cubeList.add(new ModelBox(rearfoot1, 112, 0, -9.0F, 0.0F, -23.0F, 18, 6, 24, 0.0F, true));
			frontleg = new ModelRenderer(this);
			frontleg.setRotationPoint(-12.0F, 20.0F, 2.0F);
			setRotationAngle(frontleg, 1.1345F, 0.0F, 0.0F);
			frontleg.cubeList.add(new ModelBox(frontleg, 112, 104, -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, false));
			frontlegtip = new ModelRenderer(this);
			frontlegtip.setRotationPoint(0.0F, 20.0F, 0.0F);
			frontleg.addChild(frontlegtip);
			setRotationAngle(frontlegtip, -0.3491F, 0.0F, 0.0F);
			frontlegtip.cubeList.add(new ModelBox(frontlegtip, 226, 138, -3.0F, -1.0F, -3.0F, 6, 24, 6, 0.0F, false));
			frontfoot = new ModelRenderer(this);
			frontfoot.setRotationPoint(0.0F, 22.0F, 0.0F);
			frontlegtip.addChild(frontfoot);
			setRotationAngle(frontfoot, 0.7854F, 0.0F, 0.0F);
			frontfoot.cubeList.add(new ModelBox(frontfoot, 144, 104, -4.0F, 0.0F, -12.0F, 8, 4, 16, 0.0F, false));
			frontleg1 = new ModelRenderer(this);
			frontleg1.setRotationPoint(12.0F, 20.0F, 2.0F);
			setRotationAngle(frontleg1, 1.1345F, 0.0F, 0.0F);
			frontleg1.cubeList.add(new ModelBox(frontleg1, 112, 104, -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, true));
			frontlegtip1 = new ModelRenderer(this);
			frontlegtip1.setRotationPoint(0.0F, 20.0F, 0.0F);
			frontleg1.addChild(frontlegtip1);
			setRotationAngle(frontlegtip1, -0.3491F, 0.0F, 0.0F);
			frontlegtip1.cubeList.add(new ModelBox(frontlegtip1, 226, 138, -3.0F, -1.0F, -3.0F, 6, 24, 6, 0.0F, true));
			frontfoot1 = new ModelRenderer(this);
			frontfoot1.setRotationPoint(0.0F, 22.0F, 0.0F);
			frontlegtip1.addChild(frontfoot1);
			setRotationAngle(frontfoot1, 0.7854F, 0.0F, 0.0F);
			frontfoot1.cubeList.add(new ModelBox(frontfoot1, 144, 104, -4.0F, 0.0F, -12.0F, 8, 4, 16, 0.0F, true));
			tail = new ModelRenderer(this);
			tail.setRotationPoint(0.0F, 10.0F, 58.0F);
			tail.cubeList.add(new ModelBox(tail, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail.cubeList.add(new ModelBox(tail, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail2 = new ModelRenderer(this);
			tail2.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail.addChild(tail2);
			tail2.cubeList.add(new ModelBox(tail2, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail2.cubeList.add(new ModelBox(tail2, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail3 = new ModelRenderer(this);
			tail3.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail2.addChild(tail3);
			tail3.cubeList.add(new ModelBox(tail3, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail3.cubeList.add(new ModelBox(tail3, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail4 = new ModelRenderer(this);
			tail4.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail3.addChild(tail4);
			tail4.cubeList.add(new ModelBox(tail4, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail4.cubeList.add(new ModelBox(tail4, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail5 = new ModelRenderer(this);
			tail5.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail4.addChild(tail5);
			tail5.cubeList.add(new ModelBox(tail5, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail5.cubeList.add(new ModelBox(tail5, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail6 = new ModelRenderer(this);
			tail6.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail5.addChild(tail6);
			tail6.cubeList.add(new ModelBox(tail6, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail6.cubeList.add(new ModelBox(tail6, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail7 = new ModelRenderer(this);
			tail7.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail6.addChild(tail7);
			tail7.cubeList.add(new ModelBox(tail7, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail7.cubeList.add(new ModelBox(tail7, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail8 = new ModelRenderer(this);
			tail8.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail7.addChild(tail8);
			tail8.cubeList.add(new ModelBox(tail8, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail8.cubeList.add(new ModelBox(tail8, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail9 = new ModelRenderer(this);
			tail9.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail8.addChild(tail9);
			tail9.cubeList.add(new ModelBox(tail9, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail9.cubeList.add(new ModelBox(tail9, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail10 = new ModelRenderer(this);
			tail10.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail9.addChild(tail10);
			tail10.cubeList.add(new ModelBox(tail10, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail10.cubeList.add(new ModelBox(tail10, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail11 = new ModelRenderer(this);
			tail11.setRotationPoint(0.0F, 0.0F, 10.0F);
			tail10.addChild(tail11);
			tail11.cubeList.add(new ModelBox(tail11, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
			tail11.cubeList.add(new ModelBox(tail11, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
			tail12 = new ModelRenderer(this);
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

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
			super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.jaw.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.wing1.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.wing.rotateAngleZ = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}
