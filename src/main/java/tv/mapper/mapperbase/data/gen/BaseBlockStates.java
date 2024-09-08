package tv.mapper.mapperbase.data.gen;

import java.util.function.Function;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import tv.mapper.mapperbase.MapperBase;
import tv.mapper.mapperbase.world.level.block.BaseBlocks;
import tv.mapper.mapperbase.world.level.block.SlopeBlock;
import tv.mapper.mapperbase.world.level.block.UpDownBlock;

public class BaseBlockStates extends BlockStateProvider
{
    protected final String mod_id;

    public BaseBlockStates(DataGenerator gen, String modid, ExistingFileHelper exFileHelper)
    {
        super(gen.getPackOutput(), modid, exFileHelper);
        this.mod_id = modid;
    }

    @Override
    protected void registerStatesAndModels()
    {
        simpleBlock(BaseBlocks.STEEL_BLOCK.get());
        slabBlock((SlabBlock)BaseBlocks.STEEL_SLAB.get(), modLoc("block/steel_block"), modLoc("block/steel_slab_side"), modLoc("block/steel_block"), modLoc("block/steel_block"));
        stairsBlock((StairBlock)BaseBlocks.STEEL_STAIRS.get(), modLoc("block/steel_block"), modLoc("block/steel_block"), modLoc("block/steel_block"));
        newWallBlock((WallBlock)BaseBlocks.STEEL_WALL.get(), new UncheckedModelFile(MapperBase.MODID + ":block/steel_wall_post"), new UncheckedModelFile(MapperBase.MODID + ":block/steel_wall_side"), new UncheckedModelFile(MapperBase.MODID + ":block/steel_wall_side_tall"));
        pressurePlateBlock(BaseBlocks.STEEL_PRESSURE_PLATE.get(), new UncheckedModelFile(MapperBase.MODID + ":block/steel_pressure_plate"), new UncheckedModelFile(MapperBase.MODID + ":block/steel_pressure_plate_down"));
        fenceBlock((FenceBlock)BaseBlocks.STEEL_FENCE.get(), modLoc("block/steel_block"));
        fenceGateBlock(BaseBlocks.STEEL_FENCE_GATE.get(), modLoc("block/steel_block"));
    }

    protected void pressurePlateBlock(Block block, ModelFile plate, ModelFile plate_down)
    {
        getVariantBuilder(block).partialState().with(BlockStateProperties.POWERED, true).modelForState().modelFile(plate_down).addModel().partialState().with(BlockStateProperties.POWERED, false).modelForState().modelFile(plate).addModel();
    }

    protected void upDownBlock(Block block, ModelFile model)
    {
        getVariantBuilder(block).partialState().with(UpDownBlock.UPSIDE_DOWN, true).modelForState().modelFile(model).rotationX(180).addModel().partialState().with(UpDownBlock.UPSIDE_DOWN, false).modelForState().modelFile(model).addModel();
    }

    protected void allRotationBlock(Block block, ModelFile model)
    {
        getVariantBuilder(block).partialState().with(BlockStateProperties.FACING, Direction.UP).modelForState().modelFile(model).rotationX(270).uvLock(true).addModel().partialState().with(BlockStateProperties.FACING, Direction.DOWN).modelForState().modelFile(model).rotationX(90).uvLock(true).addModel().partialState().with(BlockStateProperties.FACING, Direction.NORTH).modelForState().modelFile(model).uvLock(true).addModel().partialState().with(BlockStateProperties.FACING, Direction.SOUTH).modelForState().modelFile(model).rotationY(180).uvLock(true).addModel().partialState().with(BlockStateProperties.FACING, Direction.EAST).modelForState().modelFile(model).rotationY(90).uvLock(true).addModel().partialState().with(BlockStateProperties.FACING, Direction.WEST).modelForState().modelFile(model).rotationY(270).uvLock(true).addModel();
    }

    protected void buttonBlock(Block block, ModelFile model, ModelFile pressed, int angleOffset)
    {
        getVariantBuilder(block).forAllStates(state ->
        {
            AttachFace face = state.getValue(BlockStateProperties.ATTACH_FACE);
            Direction dir = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            Boolean powered = state.getValue(BlockStateProperties.POWERED);

            return ConfiguredModel.builder().modelFile(powered ? pressed : model).rotationX(face == AttachFace.WALL ? 90 : face == AttachFace.CEILING ? 180 : 0).rotationY((((int)dir.toYRot()) + angleOffset) % 360).uvLock(face == AttachFace.WALL ? true : false).build();
        });
    }

    /**
     * Creates a blockstate file for blocks that have 4 orientations depending of cardinal (north, south etc). e.g. chairs, suspended stairs...
     */
    protected void orientableBlock(Block block, ModelFile model, int angleOffset)
    {
        orientableBlock(block, $ -> model, angleOffset);
    }

    protected void orientableBlock(Block block, Function<BlockState, ModelFile> modelFunc, int angleOffset)
    {
        getVariantBuilder(block).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(modelFunc.apply(state)).rotationY(((int)state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + angleOffset) % 360).build(), BlockStateProperties.WATERLOGGED);
    }

    protected void rooftilesStairsBlock(StairBlock block, ResourceLocation texture)
    {
        String baseName = block.getDescriptionId();
        ModelFile stairs = models().stairs(baseName, texture, texture, texture);
        ModelFile stairsInner = models().stairsInner(baseName + "_inner", texture, texture, texture);
        ModelFile stairsOuter = models().stairsOuter(baseName + "_outer", texture, texture, texture);

        getVariantBuilder(block).forAllStatesExcept(state ->
        {
            Direction facing = state.getValue(StairBlock.FACING);
            Half half = state.getValue(StairBlock.HALF);
            StairsShape shape = state.getValue(StairBlock.SHAPE);
            int yRot = (int)facing.getClockWise().toYRot(); // Stairs model is rotated 90 degrees clockwise for some reason
            if(shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT)
            {
                yRot += 270; // Left facing stairs are rotated 90 degrees clockwise
            }
            if(shape != StairsShape.STRAIGHT && half == Half.TOP)
            {
                yRot += 90; // Top stairs are rotated 90 degrees clockwise
            }
            yRot %= 360;
            return ConfiguredModel.builder().modelFile(shape == StairsShape.STRAIGHT ? stairs : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? stairsInner : stairsOuter).rotationX(half == Half.BOTTOM ? 0 : 180).rotationY(yRot).uvLock(false).build();
        }, StairBlock.WATERLOGGED);
    }

    protected void newWallBlock(WallBlock block, ModelFile post, ModelFile side, ModelFile side_tall)
    {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);

        builder.part().modelFile(post).addModel().condition(WallBlock.UP, true).end();

        builder.part().modelFile(side).addModel().condition(WallBlock.NORTH_WALL, WallSide.LOW).end();
        builder.part().modelFile(side).rotationY(90).uvLock(true).addModel().condition(WallBlock.EAST_WALL, WallSide.LOW).end();
        builder.part().modelFile(side).rotationY(180).uvLock(true).addModel().condition(WallBlock.SOUTH_WALL, WallSide.LOW).end();
        builder.part().modelFile(side).rotationY(270).uvLock(true).addModel().condition(WallBlock.WEST_WALL, WallSide.LOW).end();

        builder.part().modelFile(side_tall).addModel().condition(WallBlock.NORTH_WALL, WallSide.TALL).end();
        builder.part().modelFile(side_tall).rotationY(90).uvLock(true).addModel().condition(WallBlock.EAST_WALL, WallSide.TALL).end();
        builder.part().modelFile(side_tall).rotationY(180).uvLock(true).addModel().condition(WallBlock.SOUTH_WALL, WallSide.TALL).end();
        builder.part().modelFile(side_tall).rotationY(270).uvLock(true).addModel().condition(WallBlock.WEST_WALL, WallSide.TALL).end();
    }

    protected void slopeBlock(Block block, String name, String modid)
    {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        String modelName = "";
        for(int i = 1; i < 9; i++)
        {
            modelName = i == 8 ? modid + ":block/" + name : modid + ":block/" + name + "_slope_" + i * 2;
            builder.partialState().with(SlopeBlock.LAYERS, i).modelForState().modelFile(new UncheckedModelFile(modelName)).addModel();
        }
    }

    protected void slopeBlock(Block block, String name, String modid, Block fullBlock)
    {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        String modelName = "";
        for(int i = 1; i < 9; i++)
        {
            modelName = i == 8 ? fullBlock.getDescriptionId() + ":block/" + fullBlock.getDescriptionId() : modid + ":block/" + name + "_slope_" + i * 2;
            builder.partialState().with(SlopeBlock.LAYERS, i).modelForState().modelFile(new UncheckedModelFile(modelName)).addModel();
        }
    }

    protected String getModId()
    {
        return this.mod_id;
    }
}