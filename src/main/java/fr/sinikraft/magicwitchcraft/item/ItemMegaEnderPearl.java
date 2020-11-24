
package fr.sinikraft.magicwitchcraft.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ActionResult;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.EnumAction;
import net.minecraft.init.Items;
import net.minecraft.init.Enchantments;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.Minecraft;

import fr.sinikraft.magicwitchcraft.procedure.ProcedureMegaEnderPearlRangedItemUsed;
import fr.sinikraft.magicwitchcraft.procedure.ProcedureMegaEnderPearlBulletHitsBlock;
import fr.sinikraft.magicwitchcraft.creativetab.TabMagicWitchCraft;
import fr.sinikraft.magicwitchcraft.ElementsMagicWitchcraft;

@ElementsMagicWitchcraft.ModElement.Tag
public class ItemMegaEnderPearl extends ElementsMagicWitchcraft.ModElement {
	@GameRegistry.ObjectHolder("magic_witchcraft:megaenderpearl")
	public static final Item block = null;
	public static final int ENTITYID = 8;
	public ItemMegaEnderPearl(ElementsMagicWitchcraft instance) {
		super(instance, 46);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new RangedItem());
		elements.entities.add(() -> EntityEntryBuilder.create().entity(EntityArrowCustom.class)
				.id(new ResourceLocation("magic_witchcraft", "entitybulletmegaenderpearl"), ENTITYID).name("entitybulletmegaenderpearl")
				.tracker(64, 1, true).build());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("magic_witchcraft:megaenderpearl", "inventory"));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowCustom.class, renderManager -> {
			return new RenderSnowball(renderManager, new ItemStack(Items.ENDER_EYE, (int) (1)).getItem(), Minecraft.getMinecraft().getRenderItem());
		});
	}
	public static class RangedItem extends Item {
		public RangedItem() {
			super();
			setMaxDamage(32);
			setFull3D();
			setUnlocalizedName("megaenderpearl");
			setRegistryName("megaenderpearl");
			maxStackSize = 1;
			setCreativeTab(TabMagicWitchCraft.tab);
		}

		@Override
		public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityLivingBase entityLivingBase, int timeLeft) {
			if (!world.isRemote && entityLivingBase instanceof EntityPlayerMP) {
				EntityPlayerMP entity = (EntityPlayerMP) entityLivingBase;
				int slotID = -1;
				for (int i = 0; i < entity.inventory.mainInventory.size(); i++) {
					ItemStack stack = entity.inventory.mainInventory.get(i);
					if (stack != null && stack.getItem() == new ItemStack(ItemEnergyCrystal.block, (int) (1)).getItem()
							&& stack.getMetadata() == new ItemStack(ItemEnergyCrystal.block, (int) (1)).getMetadata()) {
						slotID = i;
						break;
					}
				}
				if (entity.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemstack) > 0
						|| slotID != -1) {
					float power = 5f;
					EntityArrowCustom entityarrow = new EntityArrowCustom(world, entity);
					entityarrow.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 2, 0);
					entityarrow.setSilent(true);
					entityarrow.setIsCritical(true);
					entityarrow.setDamage(0);
					entityarrow.setKnockbackStrength(0);
					itemstack.damageItem(1, entity);
					int x = (int) entity.posX;
					int y = (int) entity.posY;
					int z = (int) entity.posZ;
					world.playSound((EntityPlayer) null, (double) x, (double) y, (double) z,
							(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
									.getObject(new ResourceLocation(("entity.enderpearl.throw"))),
							SoundCategory.NEUTRAL, 1, 1f / (itemRand.nextFloat() * 0.5f + 1f) + (power / 2));
					if (entity.capabilities.isCreativeMode) {
						entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
					} else {
						if (new ItemStack(ItemEnergyCrystal.block, (int) (1)).isItemStackDamageable()) {
							ItemStack stack = entity.inventory.getStackInSlot(slotID);
							if (stack.attemptDamageItem(1, itemRand, entity)) {
								stack.shrink(1);
								stack.setItemDamage(0);
							}
						} else {
							entity.inventory.clearMatchingItems(new ItemStack(ItemEnergyCrystal.block, (int) (1)).getItem(), -1, 1, null);
						}
					}
					if (!world.isRemote)
						world.spawnEntity(entityarrow);
					{
						java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
						$_dependencies.put("entity", entity);
						ProcedureMegaEnderPearlRangedItemUsed.executeProcedure($_dependencies);
					}
				}
			}
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entity, EnumHand hand) {
			entity.setActiveHand(hand);
			return new ActionResult(EnumActionResult.SUCCESS, entity.getHeldItem(hand));
		}

		@Override
		public EnumAction getItemUseAction(ItemStack itemstack) {
			return EnumAction.BOW;
		}

		@Override
		public int getMaxItemUseDuration(ItemStack itemstack) {
			return 72000;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
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

		@Override
		public void onCollideWithPlayer(EntityPlayer entity) {
			super.onCollideWithPlayer(entity);
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			World world = this.world;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				ProcedureMegaEnderPearlBulletHitsBlock.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void arrowHit(EntityLivingBase entity) {
			super.arrowHit(entity);
			entity.setArrowCountInEntity(entity.getArrowCountInEntity() - 1);
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			World world = this.world;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				ProcedureMegaEnderPearlBulletHitsBlock.executeProcedure($_dependencies);
			}
		}

		@Override
		public void onUpdate() {
			super.onUpdate();
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			World world = this.world;
			Entity entity = (Entity) shootingEntity;
			if (this.inGround) {
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					ProcedureMegaEnderPearlBulletHitsBlock.executeProcedure($_dependencies);
				}
				this.world.removeEntity(this);
			}
		}
	}
}
