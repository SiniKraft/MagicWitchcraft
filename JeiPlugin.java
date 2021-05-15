package fr.sinikraft.magicwitchcraft;

import fr.sinikraft.magicwitchcraft.item.MysteriousOrbItem;
import fr.sinikraft.magicwitchcraft.item.MagicalOrbItem;
import fr.sinikraft.magicwitchcraft.item.MagicalWheatItem;
import fr.sinikraft.magicwitchcraft.item.MagicalBerriesItem;
import fr.sinikraft.magicwitchcraft.item.SpectralOrbItem;
import fr.sinikraft.magicwitchcraft.item.RechargeableEnergyCrystalItem;
import fr.sinikraft.magicwitchcraft.item.EnergyCrystalItem;
import fr.sinikraft.magicwitchcraft.item.EnergyCrystalEmptyItem;
import fr.sinikraft.magicwitchcraft.item.SpectralOrbItem;
import fr.sinikraft.magicwitchcraft.item.SolidTrollWaterItem;
import fr.sinikraft.magicwitchcraft.item.SolidMagicalSapItem;
import fr.sinikraft.magicwitchcraft.item.MagicalBottleFilled0Item;
import fr.sinikraft.magicwitchcraft.item.FloatBootsItem;
import fr.sinikraft.magicwitchcraft.block.TNTMineBlock;
import fr.sinikraft.magicwitchcraft.block.InvisibleMineBlock;
import fr.sinikraft.magicwitchcraft.block.MagicalCompressorBlock;
import fr.sinikraft.magicwitchcraft.block.MysteriousLogBlock;
import fr.sinikraft.magicwitchcraft.block.MysteriousExtractorBlock;
import fr.sinikraft.magicwitchcraft.block.AtomicFusionerBlock;
import fr.sinikraft.magicwitchcraft.block.SpectralPowerInfuserBlock;
import fr.sinikraft.magicwitchcraft.gui.MysteriousExtractorGUIGuiWindow;
import fr.sinikraft.magicwitchcraft.gui.MagicalCompressorGUIGuiWindow;
import fr.sinikraft.magicwitchcraft.gui.AtomicFusionerGUIGuiWindow;
import fr.sinikraft.magicwitchcraft.gui.SpectralPowerInterfaceGuiWindow;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IGuiHandlerRegistration;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.List;



@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin {
	public static IJeiHelpers jeiHelper;
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation("magic_witchcraft", "default");
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        jeiHelper = registration.getJeiHelpers();
        registration.addRecipeCategories(new MysteriousExtractorBlockJeiCategory(jeiHelper.getGuiHelper()));
        registration.addRecipeCategories(new MagicalCompressorBlockJeiCategory(jeiHelper.getGuiHelper()));
        registration.addRecipeCategories(new AtomicFusionerBlockJeiCategory(jeiHelper.getGuiHelper()));
        registration.addRecipeCategories(new SpectralPowerInfuserBlockJeiCategory(jeiHelper.getGuiHelper()));
    }
    
	private List<MysteriousExtractorBlockJeiCategory.MysteriousExtractorBlockRecipeWrapper> generateMysteriousExtractorBlockRecipes() {
		List<MysteriousExtractorBlockJeiCategory.MysteriousExtractorBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(MysteriousLogBlock.block));
        outputs.add(new ItemStack(MysteriousOrbItem.block));
		recipes.add(new MysteriousExtractorBlockJeiCategory.MysteriousExtractorBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Mysterious Extractor Recipe 1
	}
	private List<MysteriousExtractorBlockJeiCategory.MysteriousExtractorBlockRecipeWrapper> generateMysteriousExtractorBlockRecipes2() {
		List<MysteriousExtractorBlockJeiCategory.MysteriousExtractorBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(MysteriousOrbItem.block));
        outputs.add(new ItemStack(MagicalOrbItem.block));
		recipes.add(new MysteriousExtractorBlockJeiCategory.MysteriousExtractorBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Mysterious Extractor Recipe 2
	}
	private List<MagicalCompressorBlockJeiCategory.MagicalCompressorBlockRecipeWrapper> generateMagicalCompressorBlockRecipes() {
		List<MagicalCompressorBlockJeiCategory.MagicalCompressorBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(MagicalWheatItem.block));
        outputs.add(new ItemStack(MagicalOrbItem.block));
		recipes.add(new MagicalCompressorBlockJeiCategory.MagicalCompressorBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Magical Compressor Recipe 1
	}
	private List<MagicalCompressorBlockJeiCategory.MagicalCompressorBlockRecipeWrapper> generateMagicalCompressorBlockRecipes2() {
		List<MagicalCompressorBlockJeiCategory.MagicalCompressorBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(MagicalBerriesItem.block));
        outputs.add(new ItemStack(MagicalOrbItem.block));
		recipes.add(new MagicalCompressorBlockJeiCategory.MagicalCompressorBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Magical Compressor Recipe 2
	}
	private List<AtomicFusionerBlockJeiCategory.AtomicFusionerBlockRecipeWrapper> generateAtomicFusionerBlockRecipes() {
		List<AtomicFusionerBlockJeiCategory.AtomicFusionerBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(EnergyCrystalItem.block));
        inputs.add(new ItemStack(MagicalOrbItem.block));
        inputs.add(new ItemStack(Items.DIAMOND));
        outputs.add(new ItemStack(SpectralOrbItem.block));
        outputs.add(new ItemStack(EnergyCrystalEmptyItem.block));
		recipes.add(new AtomicFusionerBlockJeiCategory.AtomicFusionerBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Atomic Fusioner Recipe 1
	}
	private List<AtomicFusionerBlockJeiCategory.AtomicFusionerBlockRecipeWrapper> generateAtomicFusionerBlockRecipes2() {
		List<AtomicFusionerBlockJeiCategory.AtomicFusionerBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(RechargeableEnergyCrystalItem.block));
        inputs.add(new ItemStack(MagicalOrbItem.block));
        inputs.add(new ItemStack(Items.DIAMOND));
        outputs.add(new ItemStack(SpectralOrbItem.block));
        outputs.add(new ItemStack(EnergyCrystalEmptyItem.block));
		recipes.add(new AtomicFusionerBlockJeiCategory.AtomicFusionerBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Atomic Fusioner Recipe 2
	}
	private List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> generateSpectralPowerInfuserBlockRecipes() {
		List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(EnergyCrystalItem.block));
        inputs.add(new ItemStack(SpectralOrbItem.block));
        inputs.add(new ItemStack(Blocks.TNT));
        outputs.add(new ItemStack(InvisibleMineBlock.block));
        outputs.add(new ItemStack(EnergyCrystalEmptyItem.block));
		recipes.add(new SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Spectral Power Infuser Recipe 1
	}
	private List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> generateSpectralPowerInfuserBlockRecipes2() {
		List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(EnergyCrystalItem.block));
        inputs.add(new ItemStack(SpectralOrbItem.block));
        inputs.add(new ItemStack(Items.WATER_BUCKET));
        outputs.add(new ItemStack(SolidTrollWaterItem.block));
        outputs.add(new ItemStack(EnergyCrystalEmptyItem.block));
		recipes.add(new SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Spectral Power Infuser Recipe 2
	}
	private List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> generateSpectralPowerInfuserBlockRecipes3() {
		List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(EnergyCrystalItem.block));
        inputs.add(new ItemStack(SpectralOrbItem.block));
        inputs.add(new ItemStack(Items.DIAMOND_BOOTS));
        outputs.add(new ItemStack(FloatBootsItem.boots));
        outputs.add(new ItemStack(EnergyCrystalEmptyItem.block));
		recipes.add(new SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Spectral Power Infuser Recipe 3
	}
	private List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> generateSpectralPowerInfuserBlockRecipes4() {
		List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(EnergyCrystalItem.block));
        inputs.add(new ItemStack(SpectralOrbItem.block));
        inputs.add(new ItemStack(MagicalBottleFilled0Item.block));
        outputs.add(new ItemStack(SolidMagicalSapItem.block));
        outputs.add(new ItemStack(EnergyCrystalEmptyItem.block));
		recipes.add(new SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Spectral Power Infuser Recipe 4
	}
	private List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> generateSpectralPowerInfuserBlockRecipes5() {
		List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(RechargeableEnergyCrystalItem.block));
        inputs.add(new ItemStack(SpectralOrbItem.block));
        inputs.add(new ItemStack(Blocks.TNT));
        outputs.add(new ItemStack(InvisibleMineBlock.block));
        outputs.add(new ItemStack(EnergyCrystalEmptyItem.block));
		recipes.add(new SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Spectral Power Infuser Recipe 5
	}
	private List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> generateSpectralPowerInfuserBlockRecipes6() {
		List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(RechargeableEnergyCrystalItem.block));
        inputs.add(new ItemStack(SpectralOrbItem.block));
        inputs.add(new ItemStack(Items.WATER_BUCKET));
        outputs.add(new ItemStack(SolidTrollWaterItem.block));
        outputs.add(new ItemStack(EnergyCrystalEmptyItem.block));
		recipes.add(new SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Spectral Power Infuser Recipe 6
	}
	private List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> generateSpectralPowerInfuserBlockRecipes7() {
		List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(RechargeableEnergyCrystalItem.block));
        inputs.add(new ItemStack(SpectralOrbItem.block));
        inputs.add(new ItemStack(Items.DIAMOND_BOOTS));
        outputs.add(new ItemStack(FloatBootsItem.boots));
        outputs.add(new ItemStack(EnergyCrystalEmptyItem.block));
		recipes.add(new SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Spectral Power Infuser Recipe 7
	}
	private List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> generateSpectralPowerInfuserBlockRecipes8() {
		List<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        inputs.add(new ItemStack(RechargeableEnergyCrystalItem.block));
        inputs.add(new ItemStack(SpectralOrbItem.block));
        inputs.add(new ItemStack(MagicalBottleFilled0Item.block));
        outputs.add(new ItemStack(SolidMagicalSapItem.block));
        outputs.add(new ItemStack(EnergyCrystalEmptyItem.block));
		recipes.add(new SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper(inputs, outputs));
		return recipes;  // Spectral Power Infuser Recipe 8
	}
	
	
	@Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(generateMysteriousExtractorBlockRecipes(), MysteriousExtractorBlockJeiCategory.Uid);
        registration.addRecipes(generateMysteriousExtractorBlockRecipes2(), MysteriousExtractorBlockJeiCategory.Uid);
        registration.addRecipes(generateMagicalCompressorBlockRecipes(), MagicalCompressorBlockJeiCategory.Uid);
        registration.addRecipes(generateMagicalCompressorBlockRecipes2(), MagicalCompressorBlockJeiCategory.Uid);
        registration.addRecipes(generateAtomicFusionerBlockRecipes(), AtomicFusionerBlockJeiCategory.Uid);
        registration.addRecipes(generateAtomicFusionerBlockRecipes2(), AtomicFusionerBlockJeiCategory.Uid);
        registration.addRecipes(generateSpectralPowerInfuserBlockRecipes(), SpectralPowerInfuserBlockJeiCategory.Uid);
        registration.addRecipes(generateSpectralPowerInfuserBlockRecipes2(), SpectralPowerInfuserBlockJeiCategory.Uid);
        registration.addRecipes(generateSpectralPowerInfuserBlockRecipes3(), SpectralPowerInfuserBlockJeiCategory.Uid);
        registration.addRecipes(generateSpectralPowerInfuserBlockRecipes4(), SpectralPowerInfuserBlockJeiCategory.Uid);
        registration.addRecipes(generateSpectralPowerInfuserBlockRecipes5(), SpectralPowerInfuserBlockJeiCategory.Uid);
        registration.addRecipes(generateSpectralPowerInfuserBlockRecipes6(), SpectralPowerInfuserBlockJeiCategory.Uid);
        registration.addRecipes(generateSpectralPowerInfuserBlockRecipes7(), SpectralPowerInfuserBlockJeiCategory.Uid);
        registration.addRecipes(generateSpectralPowerInfuserBlockRecipes8(), SpectralPowerInfuserBlockJeiCategory.Uid);

        // Before, add recipes, after, add descriptions :
		IIngredientType<ItemStack> itemType = VanillaTypes.ITEM;

		registration.addIngredientInfo(new ItemStack(EnergyCrystalItem.block), itemType, (new TranslationTextComponent("jei.plugin.descr.energycrystal").getString()));
    }
	@Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {  // Allows Jei to bind output items to guis
        registration.addRecipeCatalyst(new ItemStack(MysteriousExtractorBlock.block),MysteriousExtractorBlockJeiCategory.Uid);
        registration.addRecipeCatalyst(new ItemStack(MagicalCompressorBlock.block),MagicalCompressorBlockJeiCategory.Uid);
        registration.addRecipeCatalyst(new ItemStack(AtomicFusionerBlock.block),AtomicFusionerBlockJeiCategory.Uid);
        registration.addRecipeCatalyst(new ItemStack(SpectralPowerInfuserBlock.block),SpectralPowerInfuserBlockJeiCategory.Uid);
    }
    public static class MysteriousExtractorBlockJeiCategory implements IRecipeCategory<MysteriousExtractorBlockJeiCategory.MysteriousExtractorBlockRecipeWrapper> {
        private static ResourceLocation Uid = new ResourceLocation("magic_witchcraft", "mysteriousextractorblockcategory");
        private static final int input1 = 0; // THE NUMBER = SLOTID
        private static final int output1 = 1; // THE NUMBER = SLOTID
        // ...
        private final String title;
        private final IDrawable background;
        
        public MysteriousExtractorBlockJeiCategory(IGuiHelper guiHelper) {
        	
        	this.title = (String) (new TranslationTextComponent("jei.plugin.category.mysteriousextraction.title").getString());
            
            this.background = guiHelper.createDrawable(new ResourceLocation("magic_witchcraft", "textures/jei/mysterious_extractor_gui.png"), 0, 0, 176, 82);
        }
        @Override
        public ResourceLocation getUid() {
            return Uid;
        }
        @Override
        public Class<? extends MysteriousExtractorBlockRecipeWrapper> getRecipeClass() {
            return MysteriousExtractorBlockJeiCategory.MysteriousExtractorBlockRecipeWrapper.class;
        }
        @Override
        public String getTitle() {
            return title;
        }
 
        @Override
        public IDrawable getBackground() {
            return background;
        }
 
        @Override
        public IDrawable getIcon() {
            return null;
        }
         @Override
        public void setIngredients(MysteriousExtractorBlockRecipeWrapper recipeWrapper, IIngredients iIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, recipeWrapper.getInput());
            iIngredients.setOutputs(VanillaTypes.ITEM, recipeWrapper.getOutput());
            // ...
        }
 
        
        public void draw(MysteriousExtractorBlockRecipeWrapper recipe, double mouseX, double mouseY) {
            FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
            ItemStack recipeOutput = (ItemStack) recipe.output.get(0);
        }
        @Override
        public void setRecipe(IRecipeLayout iRecipeLayout, MysteriousExtractorBlockRecipeWrapper recipeWrapper, IIngredients iIngredients) {
            IGuiItemStackGroup stacks = iRecipeLayout.getItemStacks();
            stacks.init(input1, true, 42, 29);
            stacks.set(input1, iIngredients.getInputs(VanillaTypes.ITEM).get(0));
            // ,,,
            
            stacks.init(output1, false, 114, 29);
            stacks.set(output1, iIngredients.getOutputs(VanillaTypes.ITEM).get(0));
            // ...
        }
        public static class MysteriousExtractorBlockRecipeWrapper {
            private ArrayList input;
            private ArrayList output;
 
            public MysteriousExtractorBlockRecipeWrapper(ArrayList input, ArrayList output) {
                this.input = input;
                this.output = output;
            }
 
 
            public ArrayList getInput() {
                return input;
            }
 
            public ArrayList getOutput() {
                return output;
            }
        }
    }
    public static class MagicalCompressorBlockJeiCategory implements IRecipeCategory<MagicalCompressorBlockJeiCategory.MagicalCompressorBlockRecipeWrapper> {
        private static ResourceLocation Uid = new ResourceLocation("magic_witchcraft", "magicalcompressorblockcategory");
        private static final int input1 = 0; // THE NUMBER = SLOTID
        private static final int output1 = 1; // THE NUMBER = SLOTID
        // ...
        private final String title;
        private final IDrawable background;
        
        public MagicalCompressorBlockJeiCategory(IGuiHelper guiHelper) {
        	
        	this.title = (String) (new TranslationTextComponent("jei.plugin.category.magicalcompression.title").getString());
            
            this.background = guiHelper.createDrawable(new ResourceLocation("magic_witchcraft", "textures/jei/magical_compressor_gui.png"), 0, 0, 176, 82);
        }
        @Override
        public ResourceLocation getUid() {
            return Uid;
        }
        @Override
        public Class<? extends MagicalCompressorBlockRecipeWrapper> getRecipeClass() {
            return MagicalCompressorBlockJeiCategory.MagicalCompressorBlockRecipeWrapper.class;
        }
        @Override
        public String getTitle() {
            return title;
        }
 
        @Override
        public IDrawable getBackground() {
            return background;
        }
 
        @Override
        public IDrawable getIcon() {
            return null;
        }
         @Override
        public void setIngredients(MagicalCompressorBlockRecipeWrapper recipeWrapper, IIngredients iIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, recipeWrapper.getInput());
            iIngredients.setOutputs(VanillaTypes.ITEM, recipeWrapper.getOutput());
            // ...
        }
 
        
        public void draw(MagicalCompressorBlockRecipeWrapper recipe, double mouseX, double mouseY) {
            FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
            ItemStack recipeOutput = (ItemStack) recipe.output.get(0);
        }
        @Override
        public void setRecipe(IRecipeLayout iRecipeLayout, MagicalCompressorBlockRecipeWrapper recipeWrapper, IIngredients iIngredients) {
            IGuiItemStackGroup stacks = iRecipeLayout.getItemStacks();
            stacks.init(input1, true, 51, 29);
            stacks.set(input1, iIngredients.getInputs(VanillaTypes.ITEM).get(0));
            // ,,,
            
            stacks.init(output1, false, 114, 29);
            stacks.set(output1, iIngredients.getOutputs(VanillaTypes.ITEM).get(0));
            // ...
        }
        public static class MagicalCompressorBlockRecipeWrapper {
            private ArrayList input;
            private ArrayList output;
 
            public MagicalCompressorBlockRecipeWrapper(ArrayList input, ArrayList output) {
                this.input = input;
                this.output = output;
            }
 
 
            public ArrayList getInput() {
                return input;
            }
 
            public ArrayList getOutput() {
                return output;
            }
        }
    }
    public static class AtomicFusionerBlockJeiCategory implements IRecipeCategory<AtomicFusionerBlockJeiCategory.AtomicFusionerBlockRecipeWrapper> {
        private static ResourceLocation Uid = new ResourceLocation("magic_witchcraft", "atomicfusionerblockcategory");
        private static final int input1 = 0; // THE NUMBER = SLOTID
        private static final int input2 = 1;
        private static final int input3 = 2;
        private static final int output1 = 3; // THE NUMBER = SLOTID
        private static final int output2 = 4;
        // ...
        private final String title;
        private final IDrawable background;
        
        public AtomicFusionerBlockJeiCategory(IGuiHelper guiHelper) {
        	
        	this.title = (String) (new TranslationTextComponent("jei.plugin.category.atomicfusion.title").getString());
            
            this.background = guiHelper.createDrawable(new ResourceLocation("magic_witchcraft", "textures/jei/atomic_fusioner_gui.png"), 0, 0, 176, 77);
        }
        @Override
        public ResourceLocation getUid() {
            return Uid;
        }
        @Override
        public Class<? extends AtomicFusionerBlockRecipeWrapper> getRecipeClass() {
            return AtomicFusionerBlockJeiCategory.AtomicFusionerBlockRecipeWrapper.class;
        }
        @Override
        public String getTitle() {
            return title;
        }
 
        @Override
        public IDrawable getBackground() {
            return background;
        }
 
        @Override
        public IDrawable getIcon() {
            return null;
        }
         @Override
        public void setIngredients(AtomicFusionerBlockRecipeWrapper recipeWrapper, IIngredients iIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, recipeWrapper.getInput());
            iIngredients.setOutputs(VanillaTypes.ITEM, recipeWrapper.getOutput());
            // ...
        }
 
        
        public void draw(AtomicFusionerBlockRecipeWrapper recipe, double mouseX, double mouseY) {
            FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
            ItemStack recipeOutput = (ItemStack) recipe.output.get(0);
        }
        @Override
        public void setRecipe(IRecipeLayout iRecipeLayout, AtomicFusionerBlockRecipeWrapper recipeWrapper, IIngredients iIngredients) {
            IGuiItemStackGroup stacks = iRecipeLayout.getItemStacks();
            stacks.init(input1, true, 6, 29);
            stacks.set(input1, iIngredients.getInputs(VanillaTypes.ITEM).get(0));
            stacks.init(input2, true, 42, 11);
            stacks.set(input2, iIngredients.getInputs(VanillaTypes.ITEM).get(1));
            stacks.init(input3, true, 42, 47);
            stacks.set(input3, iIngredients.getInputs(VanillaTypes.ITEM).get(2));
            // ,,,
            
            stacks.init(output1, false, 114, 29);
            stacks.set(output1, iIngredients.getOutputs(VanillaTypes.ITEM).get(0));
            stacks.init(output2, false, 150, 29);
            stacks.set(output2, iIngredients.getOutputs(VanillaTypes.ITEM).get(1));
            // ...
        }
        public static class AtomicFusionerBlockRecipeWrapper {
            private ArrayList input;
            private ArrayList output;
 
            public AtomicFusionerBlockRecipeWrapper(ArrayList input, ArrayList output) {
                this.input = input;
                this.output = output;
            }
 
 
            public ArrayList getInput() {
                return input;
            }
 
            public ArrayList getOutput() {
                return output;
            }
        }
    }
    public static class SpectralPowerInfuserBlockJeiCategory implements IRecipeCategory<SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper> {
        private static ResourceLocation Uid = new ResourceLocation("magic_witchcraft", "spectralpowerinfuserblockcategory");
        private static final int input1 = 0; // THE NUMBER = SLOTID
        private static final int input2 = 1;
        private static final int input3 = 2;
        private static final int output1 = 3; // THE NUMBER = SLOTID
        private static final int output2 = 4;
        // ...
        private final String title;
        private final IDrawable background;
        
        public SpectralPowerInfuserBlockJeiCategory(IGuiHelper guiHelper) {
        	
        	this.title = (String) (new TranslationTextComponent("jei.plugin.category.spectralpowerinfusion.title").getString());
            
            this.background = guiHelper.createDrawable(new ResourceLocation("magic_witchcraft", "textures/jei/spectral_power_infuser_gui.png"), 0, 0, 176, 77);
        }
        @Override
        public ResourceLocation getUid() {
            return Uid;
        }
        @Override
        public Class<? extends SpectralPowerInfuserBlockRecipeWrapper> getRecipeClass() {
            return SpectralPowerInfuserBlockJeiCategory.SpectralPowerInfuserBlockRecipeWrapper.class;
        }
        @Override
        public String getTitle() {
            return title;
        }
 
        @Override
        public IDrawable getBackground() {
            return background;
        }
 
        @Override
        public IDrawable getIcon() {
            return null;
        }
         @Override
        public void setIngredients(SpectralPowerInfuserBlockRecipeWrapper recipeWrapper, IIngredients iIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, recipeWrapper.getInput());
            iIngredients.setOutputs(VanillaTypes.ITEM, recipeWrapper.getOutput());
            // ...
        }
 
        
        public void draw(SpectralPowerInfuserBlockRecipeWrapper recipe, double mouseX, double mouseY) {
            FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
            ItemStack recipeOutput = (ItemStack) recipe.output.get(0);
        }
        @Override
        public void setRecipe(IRecipeLayout iRecipeLayout, SpectralPowerInfuserBlockRecipeWrapper recipeWrapper, IIngredients iIngredients) {
            IGuiItemStackGroup stacks = iRecipeLayout.getItemStacks();
            stacks.init(input1, true, 6, 29);
            stacks.set(input1, iIngredients.getInputs(VanillaTypes.ITEM).get(0));
            stacks.init(input2, true, 42, 11);
            stacks.set(input2, iIngredients.getInputs(VanillaTypes.ITEM).get(1));
            stacks.init(input3, true, 42, 47);
            stacks.set(input3, iIngredients.getInputs(VanillaTypes.ITEM).get(2));
            // ,,,
            
            stacks.init(output1, false, 114, 29);
            stacks.set(output1, iIngredients.getOutputs(VanillaTypes.ITEM).get(0));
            stacks.init(output2, false, 150, 29);
            stacks.set(output2, iIngredients.getOutputs(VanillaTypes.ITEM).get(1));
            // ...
        }
        public static class SpectralPowerInfuserBlockRecipeWrapper {
            private ArrayList input;
            private ArrayList output;
 
            public SpectralPowerInfuserBlockRecipeWrapper(ArrayList input, ArrayList output) {
                this.input = input;
                this.output = output;
            }
 
 
            public ArrayList getInput() {
                return input;
            }
 
            public ArrayList getOutput() {
                return output;
            }
        }
    }
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(MysteriousExtractorGUIGuiWindow.class, 78, 29, 30, 20, MysteriousExtractorBlockJeiCategory.Uid);
		registration.addRecipeClickArea(MagicalCompressorGUIGuiWindow.class, 78, 29, 30, 20, MagicalCompressorBlockJeiCategory.Uid);
		registration.addRecipeClickArea(AtomicFusionerGUIGuiWindow.class, 78, 29, 30, 20, AtomicFusionerBlockJeiCategory.Uid);
		registration.addRecipeClickArea(SpectralPowerInterfaceGuiWindow.class, 78, 29, 30, 20, SpectralPowerInfuserBlockJeiCategory.Uid);
	}
}