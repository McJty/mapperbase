package tv.mapper.mapperbase.data.gen;

import java.util.function.Consumer;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import tv.mapper.mapperbase.MapperBase;
import tv.mapper.mapperbase.data.BaseTags;
import tv.mapper.mapperbase.world.item.BaseItems;
import tv.mapper.mapperbase.world.level.block.BaseBlocks;

public class BaseRecipes extends RecipeProvider
{
    public BaseRecipes(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(BaseItems.BOLT.get()).pattern("x").pattern("x").define('x', BaseTags.ForgeItems.NUGGETS_STEEL).unlockedBy("has_steel_nugget", has(BaseTags.ForgeItems.NUGGETS_STEEL)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseItems.FLATTER_HAMMER.get()).pattern("i").pattern("/").define('i', BaseTags.ForgeItems.INGOTS_STEEL).define('/', Items.STICK).unlockedBy("has_steel_ingot", has(BaseTags.ForgeItems.INGOTS_STEEL)).unlockedBy("has_stick", has(Items.STICK)).save(consumer);

        // Steel blocks
        ShapedRecipeBuilder.shaped(BaseBlocks.STEEL_SLAB.get(), 6).pattern("iii").define('i', BaseTags.ForgeItems.STORAGE_BLOCKS_STEEL).unlockedBy("has_steel_block", has(BaseTags.ForgeItems.STORAGE_BLOCKS_STEEL)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseBlocks.STEEL_BLOCK.get()).pattern("i").pattern("i").define('i', BaseBlocks.STEEL_SLAB.get()).unlockedBy("has_steel_slab", has(BaseBlocks.STEEL_SLAB.get())).save(consumer, "mapperbase:steel_block_from_slabs");
        ShapedRecipeBuilder.shaped(BaseBlocks.STEEL_STAIRS.get(), 4).define('#', BaseTags.ForgeItems.STORAGE_BLOCKS_STEEL).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_steel_block", has(BaseTags.ForgeItems.STORAGE_BLOCKS_STEEL)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseBlocks.STEEL_WALL.get(), 6).define('#', BaseTags.ForgeItems.STORAGE_BLOCKS_STEEL).pattern("###").pattern("###").unlockedBy("has_steel_block", has(BaseTags.ForgeItems.STORAGE_BLOCKS_STEEL)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseBlocks.STEEL_PRESSURE_PLATE.get()).define('#', BaseTags.ForgeItems.STORAGE_BLOCKS_STEEL).pattern("##").unlockedBy("has_steel_block", has(BaseTags.ForgeItems.STORAGE_BLOCKS_STEEL)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseBlocks.STEEL_FENCE.get(), 4).pattern("nsn").pattern("isi").define('n', BaseItems.BOLT.get()).define('i', BaseTags.ForgeItems.INGOTS_STEEL).define('s', BaseTags.ForgeItems.RODS_STEEL).unlockedBy("has_steel_ingot", has(BaseTags.ForgeItems.INGOTS_STEEL)).unlockedBy("has_steel_rod", has(BaseTags.ForgeItems.RODS_STEEL)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseBlocks.STEEL_FENCE_GATE.get(), 4).pattern("nin").pattern("sis").define('n', BaseItems.BOLT.get()).define('i', BaseTags.ForgeItems.INGOTS_STEEL).define('s', BaseTags.ForgeItems.RODS_STEEL).unlockedBy("has_steel_ingot", has(BaseTags.ForgeItems.INGOTS_STEEL)).unlockedBy("has_steel_rod", has(BaseTags.ForgeItems.RODS_STEEL)).save(consumer);

        // Rods
        ShapedRecipeBuilder.shaped(BaseItems.IRON_ROD.get(), 4).pattern("x").pattern("x").define('x', Tags.Items.INGOTS_IRON).unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseItems.STEEL_ROD.get(), 4).pattern("x").pattern("x").define('x', BaseTags.ForgeItems.INGOTS_STEEL).unlockedBy("has_steel_ingot", has(BaseTags.ForgeItems.INGOTS_STEEL)).save(consumer);

        // Steel processing
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Tags.Items.INGOTS_IRON), BaseItems.FERRITE.get(), 0.5f, 800).unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON)).save(consumer);
        ShapelessRecipeBuilder.shapeless(BaseItems.RAW_STEEL.get()).requires(BaseItems.FERRITE.get()).requires(BaseItems.FERRITE.get()).requires(BaseItems.FERRITE.get()).unlockedBy("has_ferrite", has(BaseItems.FERRITE.get())).save(consumer);
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(BaseItems.RAW_STEEL.get()), BaseItems.STEEL_INGOT.get(), 0.5f, 800).unlockedBy("has_raw_steel", has(BaseItems.RAW_STEEL.get())).save(consumer, MapperBase.MODID + ":steel_ingot_from_raw_steel");

        // Metal plates
        ShapelessRecipeBuilder.shapeless(BaseItems.IRON_PLATE.get()).requires(Tags.Items.INGOTS_IRON).requires(BaseItems.FLATTER_HAMMER.get()).unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON)).save(consumer);
        ShapelessRecipeBuilder.shapeless(BaseItems.STEEL_PLATE.get()).requires(BaseTags.ForgeItems.INGOTS_STEEL).requires(BaseItems.FLATTER_HAMMER.get()).unlockedBy("has_steel_ingot", has(BaseTags.ForgeItems.INGOTS_STEEL)).save(consumer);

        // Nugget to ingot and vice-versa
        ShapedRecipeBuilder.shaped(BaseItems.STEEL_INGOT.get()).pattern("xxx").pattern("xxx").pattern("xxx").define('x', BaseTags.ForgeItems.NUGGETS_STEEL).unlockedBy("has_steel_nugget", has(BaseTags.ForgeItems.NUGGETS_STEEL)).group("steel_ingot").save(consumer);
        ShapelessRecipeBuilder.shapeless(BaseItems.STEEL_NUGGET.get(), 9).requires(BaseTags.ForgeItems.INGOTS_STEEL).unlockedBy("has_steel_ingot", has(BaseTags.ForgeItems.INGOTS_STEEL)).save(consumer, MapperBase.MODID + ":steel_nugget_from_ingot");

        // Ingot to block and vice-versa
        ShapedRecipeBuilder.shaped(BaseBlocks.STEEL_BLOCK.get()).pattern("xxx").pattern("xxx").pattern("xxx").define('x', BaseTags.ForgeItems.INGOTS_STEEL).unlockedBy("has_steel_ingot", has(BaseTags.ForgeItems.INGOTS_STEEL)).save(consumer);
        ShapelessRecipeBuilder.shapeless(BaseItems.STEEL_INGOT.get(), 9).requires(BaseTags.ForgeItems.STORAGE_BLOCKS_STEEL).unlockedBy("has_steel_block", has(BaseTags.ForgeItems.STORAGE_BLOCKS_STEEL)).group("steel_ingot").save(consumer, MapperBase.MODID + ":steel_ingot_from_block");

        // Tools
        ShapedRecipeBuilder.shaped(BaseItems.STEEL_AXE.get()).define('#', Items.STICK).define('X', BaseTags.ForgeItems.INGOTS_STEEL).pattern("XX").pattern("X#").pattern(" #").unlockedBy("has_steel_ingot", has(BaseTags.ForgeItems.INGOTS_STEEL)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseItems.STEEL_PICKAXE.get()).define('#', Items.STICK).define('X', BaseTags.ForgeItems.INGOTS_STEEL).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_steel_ingot", has(BaseTags.ForgeItems.INGOTS_STEEL)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseItems.STEEL_SHOVEL.get()).define('#', Items.STICK).define('X', BaseTags.ForgeItems.INGOTS_STEEL).pattern("X").pattern("#").pattern("#").unlockedBy("has_steel_ingot", has(BaseTags.ForgeItems.INGOTS_STEEL)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseItems.STEEL_SWORD.get()).define('#', Items.STICK).define('X', BaseTags.ForgeItems.INGOTS_STEEL).pattern("X").pattern("X").pattern("#").unlockedBy("has_steel_ingot", has(BaseTags.ForgeItems.INGOTS_STEEL)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseItems.STEEL_HOE.get()).define('#', Items.STICK).define('X', BaseTags.ForgeItems.INGOTS_STEEL).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_steel_ingot", has(BaseTags.ForgeItems.INGOTS_STEEL)).save(consumer);

        ShapedRecipeBuilder.shaped(BaseItems.STEEL_BOOTS.get()).define('X', BaseTags.ForgeItems.INGOTS_STEEL).pattern("X X").pattern("X X").unlockedBy("has_steel", has(BaseTags.ForgeItems.INGOTS_STEEL)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseItems.STEEL_CHESTPLATE.get()).define('X', BaseTags.ForgeItems.INGOTS_STEEL).pattern("X X").pattern("XXX").pattern("XXX").unlockedBy("has_steel", has(BaseTags.ForgeItems.INGOTS_STEEL)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseItems.STEEL_HELMET.get()).define('X', BaseTags.ForgeItems.INGOTS_STEEL).pattern("XXX").pattern("X X").unlockedBy("has_steel", has(BaseTags.ForgeItems.INGOTS_STEEL)).save(consumer);
        ShapedRecipeBuilder.shaped(BaseItems.STEEL_LEGGINGS.get()).define('X', BaseTags.ForgeItems.INGOTS_STEEL).pattern("XXX").pattern("X X").pattern("X X").unlockedBy("has_steel", has(BaseTags.ForgeItems.INGOTS_STEEL)).save(consumer);

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(BaseItems.STEEL_PICKAXE.get(), BaseItems.STEEL_SHOVEL.get(), BaseItems.STEEL_AXE.get(), BaseItems.STEEL_HOE.get(), BaseItems.STEEL_SWORD.get(), BaseItems.STEEL_HELMET.get(), BaseItems.STEEL_CHESTPLATE.get(), BaseItems.STEEL_LEGGINGS.get(), BaseItems.STEEL_BOOTS.get(), BaseItems.STEEL_HORSE_ARMOR.get()), BaseItems.STEEL_NUGGET.get(), 0.1f, 130).unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET)).unlockedBy("has_steel_pickaxe", has(BaseItems.STEEL_PICKAXE.get())).unlockedBy("has_steel_shovel", has(BaseItems.STEEL_SHOVEL.get())).unlockedBy("has_steel_axe", has(BaseItems.STEEL_AXE.get())).unlockedBy("has_steel_hoe", has(BaseItems.STEEL_HOE.get())).unlockedBy("has_steel_sword", has(BaseItems.STEEL_SWORD.get())).unlockedBy("has_steel_helmet", has(BaseItems.STEEL_HELMET.get())).unlockedBy("has_steel_chestplate", has(BaseItems.STEEL_CHESTPLATE.get())).unlockedBy("has_steel_leggings", has(BaseItems.STEEL_LEGGINGS.get())).unlockedBy("has_steel_boots", has(BaseItems.STEEL_BOOTS.get())).unlockedBy("has_steel_horse_armor", has(BaseItems.STEEL_HORSE_ARMOR.get())).save(consumer, MapperBase.MODID + ":steel_nugget_from_recycling");
    }

    protected void basicRecipes(Consumer<FinishedRecipe> consumer, Block base, Block slab, Block stairs, Block wall, Block pressure, Block button, Block fence, Block fence_gate)
    {
        String base_name = base.getDescriptionId();

        if(slab != null)
            SingleItemRecipeBuilder.stonecutting(Ingredient.of(base), slab, 2).unlockedBy("has_" + base_name, has(base)).save(consumer, base_name + "_slab_from_" + base_name + "_stonecutting");
        if(stairs != null)
            SingleItemRecipeBuilder.stonecutting(Ingredient.of(base), stairs).unlockedBy("has_" + base_name, has(base)).save(consumer, base_name + "_stairs_from_" + base_name + "_stonecutting");
        if(wall != null)
            SingleItemRecipeBuilder.stonecutting(Ingredient.of(base), wall).unlockedBy("has_" + base_name, has(base)).save(consumer, base_name + "_wall_from_" + base_name + "_stonecutting");
        if(pressure != null)
            SingleItemRecipeBuilder.stonecutting(Ingredient.of(base), pressure, 2).unlockedBy("has_" + base_name, has(base)).save(consumer, base_name + "_pressure_plate_from_" + base_name + "_stonecutting");
        if(button != null)
            SingleItemRecipeBuilder.stonecutting(Ingredient.of(base), button, 2).unlockedBy("has_" + base_name, has(base)).save(consumer, base_name + "_button_plate_from_" + base_name + "_stonecutting");
        if(fence != null)
            SingleItemRecipeBuilder.stonecutting(Ingredient.of(base), fence).unlockedBy("has_" + base_name, has(base)).save(consumer, base_name + "_fence_plate_from_" + base_name + "_stonecutting");
        if(fence_gate != null)
            SingleItemRecipeBuilder.stonecutting(Ingredient.of(base), fence_gate).unlockedBy("has_" + base_name, has(base)).save(consumer, base_name + "_fence_gate_plate_from_" + base_name + "_stonecutting");
    }
}