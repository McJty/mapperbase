package tv.mapper.mapperbase.data.gen;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import tv.mapper.mapperbase.world.level.block.CustomDoorBlock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BaseLootTableProvider extends VanillaBlockLoot {

    private final String modid;
    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();

    private static final Set<Item> IMMUNE_TO_EXPLOSIONS = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(ImmutableSet.toImmutableSet());

    private static final LootItemCondition.Builder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));

    protected abstract void addTables();

    public BaseLootTableProvider(String modid) {
        this.modid = modid;
    }

    protected static <T extends FunctionUserBuilder<T>> T withExplosionDecay(ItemLike p_218552_0_, FunctionUserBuilder<T> p_218552_1_) {
        return (T) (!IMMUNE_TO_EXPLOSIONS.contains(p_218552_0_.asItem()) ? p_218552_1_.apply(ApplyExplosionDecay.explosionDecay()) : p_218552_1_.unwrap());
    }

    protected static <T extends ConditionUserBuilder<T>> T withSurvivesExplosion(ItemLike p_218560_0_, ConditionUserBuilder<T> p_218560_1_) {
        return (T) (!IMMUNE_TO_EXPLOSIONS.contains(p_218560_0_.asItem()) ? p_218560_1_.when(ExplosionCondition.survivesExplosion()) : p_218560_1_.unwrap());
    }

    protected LootTable.Builder createStandardTable(String modid, Block block) {
        String name = block.getDescriptionId().replace(modid + ":", "");
        LootPool.Builder builder = LootPool.lootPool().name(name).setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block)).when(ExplosionCondition.survivesExplosion());
        return LootTable.lootTable().withPool(builder);
    }

    protected LootTable.Builder createSlabTable(String modid, Block block) {
        String name = block.getDescriptionId().replace(modid + ":", "");
        LootPool.Builder builder = LootPool.lootPool().name(name).setRolls(ConstantValue.exactly(1)).add(withExplosionDecay(block, LootItem.lootTableItem(block).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))));
        return LootTable.lootTable().withPool(builder);
    }

    protected LootTable.Builder createDoorTable(String modid, Block block) {
        String name = block.getDescriptionId().replace(modid + ":", "");
        LootPool.Builder builder = LootPool.lootPool().name(name).setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CustomDoorBlock.HALF, DoubleBlockHalf.LOWER)))).when(ExplosionCondition.survivesExplosion());
        return LootTable.lootTable().withPool(builder);
    }

    protected LootTable.Builder createBedTable(String modid, Block block) {
        String name = block.getDescriptionId().replace(modid + ":", "");
        LootPool.Builder builder = LootPool.lootPool().name(name).setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BedBlock.PART, BedPart.HEAD)))).when(ExplosionCondition.survivesExplosion());
        return LootTable.lootTable().withPool(builder);
    }

    protected LootTable.Builder createChestTable(String name, Block block) {
        LootPool.Builder builder = LootPool.lootPool().name(name).setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block).apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))).when(ExplosionCondition.survivesExplosion());
        return LootTable.lootTable().withPool(builder);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected LootTable.Builder createSilkTable(String modid, Block block, Block loot) {
        String name = block.getDescriptionId().replace(modid + ":", "");
        LootPool.Builder builder = LootPool.lootPool().name(name).setRolls(ConstantValue.exactly(1)).add(((LootPoolSingletonContainer.Builder) LootItem.lootTableItem(block).when(SILK_TOUCH)).otherwise(withSurvivesExplosion(block, LootItem.lootTableItem(loot))));
        return LootTable.lootTable().withPool(builder);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected LootTable.Builder createSilkTable(String modid, Block block, Item loot) {
        String name = block.getDescriptionId().replace(modid + ":", "");
        LootPool.Builder builder = LootPool.lootPool().name(name).setRolls(ConstantValue.exactly(1)).add(((LootPoolSingletonContainer.Builder) LootItem.lootTableItem(block).when(SILK_TOUCH)).otherwise(withSurvivesExplosion(block, LootItem.lootTableItem(loot))));
        return LootTable.lootTable().withPool(builder);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected LootTable.Builder createSilkTable(String modid, Block block, Item loot, int min, int max, int fortune) {
        String name = block.getDescriptionId().replace(modid + ":", "");
        LootPool.Builder builder = LootPool.lootPool().name(name).setRolls(ConstantValue.exactly(1)).add(((LootPoolSingletonContainer.Builder) LootItem.lootTableItem(block).when(SILK_TOUCH)).otherwise(withSurvivesExplosion(block, LootItem.lootTableItem(loot).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, fortune)))));
        return LootTable.lootTable().withPool(builder);
    }

    @Override
    protected void generate() {
        addTables();
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getEntries().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(modid))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}