package tv.mapper.mapperbase.data.gen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import tv.mapper.mapperbase.MapperBase;
import tv.mapper.mapperbase.data.BaseTags;
import tv.mapper.mapperbase.world.item.BaseItems;

import java.util.concurrent.CompletableFuture;

public class BaseItemTags extends ItemTagsProvider
{
    public BaseItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> generatorIn, BaseBlockTags blockTagProvider, ExistingFileHelper existingFileHelper)
    {
        super(packOutput, generatorIn, blockTagProvider.contentsGetter(), MapperBase.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.FENCES, ItemTags.FENCES);
        copy(Tags.Blocks.FENCES, Tags.Items.FENCES);
        copy(BaseTags.ForgeBlocks.FENCES_STEEL, BaseTags.ForgeItems.FENCES_STEEL);
        copy(Tags.Blocks.FENCE_GATES, Tags.Items.FENCE_GATES);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
        copy(BaseTags.ForgeBlocks.STORAGE_BLOCKS_STEEL, BaseTags.ForgeItems.STORAGE_BLOCKS_STEEL);
        copy(BaseTags.ForgeBlocks.PRESSURE_PLATES, BaseTags.ForgeItems.PRESSURE_PLATES);

        tag(BaseTags.ForgeItems.INGOTS_STEEL).add(BaseItems.STEEL_INGOT.get());
        tag(BaseTags.ForgeItems.NUGGETS_STEEL).add(BaseItems.STEEL_NUGGET.get());
        tag(BaseTags.ForgeItems.PLATES_STEEL).add(BaseItems.STEEL_PLATE.get());
        tag(BaseTags.ForgeItems.RODS_STEEL).add(BaseItems.STEEL_ROD.get());

        tag(Tags.Items.NUGGETS).add(BaseItems.STEEL_NUGGET.get());
        tag(Tags.Items.RODS).add(BaseItems.STEEL_ROD.get());
        tag(Tags.Items.INGOTS).add(BaseItems.STEEL_INGOT.get());

        tag(BaseTags.ForgeItems.PLATES_IRON).add(BaseItems.IRON_PLATE.get());
        tag(BaseTags.ForgeItems.RODS_IRON).add(BaseItems.IRON_ROD.get());

        tag(BaseTags.ForgeItems.PLATES).add(BaseItems.IRON_PLATE.get());
        tag(BaseTags.ForgeItems.PLATES).add(BaseItems.STEEL_PLATE.get());

        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(BaseItems.STEEL_INGOT.get());
    }
}