package tv.mapper.mapperbase.data.gen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import tv.mapper.mapperbase.MapperBase;
import tv.mapper.mapperbase.world.level.block.BaseBlocks;
import tv.mapper.mapperbase.world.level.block.SlopeBlock;

public class BaseLootTables extends BaseLootTableProvider
{
    public BaseLootTables(DataGenerator dataGeneratorIn)
    {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables()
    {
        lootTables.put(BaseBlocks.STEEL_BLOCK.get(), createStandardTable(MapperBase.MODID, BaseBlocks.STEEL_BLOCK.get()));
        lootTables.put(BaseBlocks.STEEL_SLAB.get(), createSlabTable(MapperBase.MODID, BaseBlocks.STEEL_SLAB.get()));
        lootTables.put(BaseBlocks.STEEL_STAIRS.get(), createStandardTable(MapperBase.MODID, BaseBlocks.STEEL_STAIRS.get()));
        lootTables.put(BaseBlocks.STEEL_WALL.get(), createStandardTable(MapperBase.MODID, BaseBlocks.STEEL_WALL.get()));
        lootTables.put(BaseBlocks.STEEL_PRESSURE_PLATE.get(), createStandardTable(MapperBase.MODID, BaseBlocks.STEEL_PRESSURE_PLATE.get()));
        lootTables.put(BaseBlocks.STEEL_FENCE.get(), createStandardTable(MapperBase.MODID, BaseBlocks.STEEL_FENCE.get()));
    }

    protected LootTable.Builder createSlopeTable(String modid, Block block)
    {
        String name = block.getDescriptionId().replace(modid + ":", "");

        LootPool.Builder builder = LootPool.lootPool().name(name).setRolls(ConstantValue.exactly(1)).add(withExplosionDecay(block, LootItem.lootTableItem(block).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlopeBlock.LAYERS, 2)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlopeBlock.LAYERS, 3)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlopeBlock.LAYERS, 4)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlopeBlock.LAYERS, 5)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(6)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlopeBlock.LAYERS, 6)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(7)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlopeBlock.LAYERS, 7)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(8)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlopeBlock.LAYERS, 8))))));
        return LootTable.lootTable().withPool(builder);
    }
}