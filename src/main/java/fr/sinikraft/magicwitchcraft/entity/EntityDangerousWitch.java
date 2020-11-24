
package fr.sinikraft.magicwitchcraft.entity;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.common.DungeonHooks;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelBase;

import java.util.Iterator;
import java.util.ArrayList;

import fr.sinikraft.magicwitchcraft.procedure.ProcedureDangerousWitchEntityDies;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class EntityDangerousWitch extends ElementsMagicWitchcraft.ModElement {
	public static final int ENTITYID = 2;
	public static final int ENTITYID_RANGED = 3;
	public EntityDangerousWitch(ElementsMagicWitchcraft instance) {
		super(instance, 22);
	}

	@Override
	public void initElements() {
		elements.entities.add(
				() -> EntityEntryBuilder.create().entity(EntityCustom.class).id(new ResourceLocation("magic_witchcraft", "dangerouswitch"), ENTITYID)
						.name("dangerouswitch").tracker(256, 3, true).egg(-10092340, -65536).build());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		Biome[] spawnBiomes = allbiomes(Biome.REGISTRY);
		EntityRegistry.addSpawn(EntityCustom.class, 20, 3, 10, EnumCreatureType.MONSTER, spawnBiomes);
		DungeonHooks.addDungeonMob(new ResourceLocation("magic_witchcraft:dangerouswitch"), 180);
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
			return new RenderLiving(renderManager, new Model_witch(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("magic_witchcraft:textures/dangerous_witch.png");
				}
			};
		});
	}
	public static class EntityCustom extends EntityWitch {
		public EntityCustom(World world) {
			super(world);
			setSize(1f, 2f);
			experienceValue = 10;
			this.isImmuneToFire = false;
			setNoAI(!true);
		}

		@Override
		protected void initEntityAI() {
			super.initEntityAI();
			this.tasks.addTask(1, new EntityAIWander(this, 1));
			this.tasks.addTask(2, new EntityAILookIdle(this));
			this.tasks.addTask(3, new EntityAISwimming(this));
			this.tasks.addTask(4, new EntityAILeapAtTarget(this, (float) 0.8));
			this.tasks.addTask(5, new EntityAIPanic(this, 1.2));
			this.targetTasks.addTask(6, new EntityAIHurtByTarget(this, false));
		}

		@Override
		public EnumCreatureAttribute getCreatureAttribute() {
			return EnumCreatureAttribute.UNDEFINED;
		}

		@Override
		protected Item getDropItem() {
			return null;
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.witch.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.witch.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.witch.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof EntityPotion)
				return false;
			return super.attackEntityFrom(source, amount);
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
				ProcedureDangerousWitchEntityDies.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
			if (this.getEntityAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
			if (this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
			if (this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50D);
			if (this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3D);
		}
	}

	public static class Model_witch extends ModelBase {
		private final ModelRenderer head;
		private final ModelRenderer nose;
		private final ModelRenderer hat;
		private final ModelRenderer hat2;
		private final ModelRenderer hat3;
		private final ModelRenderer hat4;
		private final ModelRenderer body;
		private final ModelRenderer arms;
		private final ModelRenderer leg0;
		private final ModelRenderer leg1;
		public Model_witch() {
			textureWidth = 64;
			textureHeight = 128;
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 0.0F, 0.0F);
			head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F, false));
			nose = new ModelRenderer(this);
			nose.setRotationPoint(0.0F, 24.0F, 0.0F);
			head.addChild(nose);
			nose.cubeList.add(new ModelBox(nose, 24, 0, -1.0F, -27.0F, -6.0F, 2, 4, 2, 0.0F, false));
			nose.cubeList.add(new ModelBox(nose, 0, 0, 0.0F, -26.0F, -6.75F, 1, 1, 1, -0.25F, false));
			hat = new ModelRenderer(this);
			hat.setRotationPoint(-5.0F, -8.0313F, -5.0F);
			head.addChild(hat);
			hat.cubeList.add(new ModelBox(hat, 0, 64, 0.0F, -2.0187F, 0.0F, 10, 2, 10, 0.0F, false));
			hat2 = new ModelRenderer(this);
			hat2.setRotationPoint(6.75F, 0.0313F, 7.0F);
			hat.addChild(hat2);
			hat2.cubeList.add(new ModelBox(hat2, 0, 76, -5.0F, -5.5F, -5.0F, 7, 4, 7, 0.0F, false));
			hat3 = new ModelRenderer(this);
			hat3.setRotationPoint(0.0F, -3.0F, 0.0F);
			hat2.addChild(hat3);
			setRotationAngle(hat3, 0.0F, 0.0F, 0.0F);
			hat3.cubeList.add(new ModelBox(hat3, 0, 87, -3.25F, -5.5F, -3.0F, 4, 4, 4, 0.0F, false));
			hat4 = new ModelRenderer(this);
			hat4.setRotationPoint(0.0F, -3.0F, 0.0F);
			hat3.addChild(hat4);
			hat4.cubeList.add(new ModelBox(hat4, 0, 95, -1.5F, -4.0F, -1.0F, 1, 2, 1, 0.25F, false));
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 24.0F, 0.0F);
			body.cubeList.add(new ModelBox(body, 16, 20, -4.0F, -24.0F, -3.0F, 8, 12, 6, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 38, -4.0F, -24.0F, -3.0F, 8, 18, 6, 0.5F, false));
			arms = new ModelRenderer(this);
			arms.setRotationPoint(0.0F, 2.0F, 0.0F);
			setRotationAngle(arms, -0.7854F, 0.0F, 0.0F);
			arms.cubeList.add(new ModelBox(arms, 40, 38, -4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F, false));
			arms.cubeList.add(new ModelBox(arms, 44, 22, -8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false));
			arms.cubeList.add(new ModelBox(arms, 44, 22, 4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false));
			leg0 = new ModelRenderer(this);
			leg0.setRotationPoint(-2.0F, 12.0F, 0.0F);
			leg0.cubeList.add(new ModelBox(leg0, 0, 22, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));
			leg1 = new ModelRenderer(this);
			leg1.setRotationPoint(2.0F, 12.0F, 0.0F);
			leg1.cubeList.add(new ModelBox(leg1, 0, 22, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			head.render(f5);
			body.render(f5);
			arms.render(f5);
			leg0.render(f5);
			leg1.render(f5);
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
			this.leg0.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
