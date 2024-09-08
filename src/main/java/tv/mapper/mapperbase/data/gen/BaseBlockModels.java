package tv.mapper.mapperbase.data.gen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BaseBlockModels extends BlockModelProvider
{
    public BaseBlockModels(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper)
    {
        super(generator.getPackOutput(), modid, existingFileHelper);
    }

    @Override
    public String getName()
    {
        return "Mapper Base Block Models";
    }

    @Override
    protected void registerModels()
    {
        buildWall("steel", modLoc("block/steel_block"));
        getBuilder("steel_pressure_plate").parent(getExistingFile(mcLoc("block/pressure_plate_up"))).texture("texture", modLoc("block/steel_block"));
        getBuilder("steel_pressure_plate_down").parent(getExistingFile(mcLoc("block/pressure_plate_down"))).texture("texture", modLoc("block/steel_block"));
        getBuilder("steel_fence_inventory").parent(getExistingFile(mcLoc("block/fence_inventory"))).texture("texture", modLoc("block/steel_block"));
    }

    protected void buildWall(String name, ResourceLocation texture)
    {
        getBuilder(name + "_wall_inventory").parent(getExistingFile(mcLoc("block/wall_inventory"))).texture("wall", texture);
        getBuilder(name + "_wall_post").parent(getExistingFile(mcLoc("block/template_wall_post"))).texture("wall", texture);
        getBuilder(name + "_wall_side").parent(getExistingFile(mcLoc("block/template_wall_side"))).texture("wall", texture);
        getBuilder(name + "_wall_side_tall").parent(getExistingFile(mcLoc("block/template_wall_side_tall"))).texture("wall", texture);
    }

    protected void buildFence(String name, ResourceLocation texture)
    {
        getBuilder(name + "_fence_inventory").parent(getExistingFile(mcLoc("block/fence_inventory"))).texture("texture", texture);
    }

    protected void buildPressure(String name, ResourceLocation texture)
    {
        getBuilder(name + "_pressure_plate").parent(getExistingFile(mcLoc("block/pressure_plate_up"))).texture("texture", texture);
        getBuilder(name + "_pressure_plate_down").parent(getExistingFile(mcLoc("block/pressure_plate_down"))).texture("texture", texture);
    }

    protected void buildButton(String name, ResourceLocation texture)
    {
        getBuilder(name + "_button_inventory").parent(getExistingFile(mcLoc("block/button_inventory"))).texture("texture", texture);
        getBuilder(name + "_button_pressed").parent(getExistingFile(mcLoc("block/button_pressed"))).texture("texture", texture);
        getBuilder(name + "_button").parent(getExistingFile(mcLoc("block/button"))).texture("texture", texture);
    }

    protected void buildRooftilesStairs(String name)
    {
        getBuilder(name + "_stairs").parent(getExistingFile(modLoc("block/rooftiles_stairs"))).texture("top", new ResourceLocation(modid, "block/" + name)).texture("bottom", new ResourceLocation(modid, "block/" + name)).texture("side", new ResourceLocation(modid, "block/" + name)).texture("particle", new ResourceLocation(modid, "block/" + name));
        getBuilder(name + "_stairs_inner").parent(getExistingFile(modLoc("block/rooftiles_inner_stairs"))).texture("top", new ResourceLocation(modid, "block/" + name + "_inner")).texture("bottom", new ResourceLocation(modid, "block/" + name + "_inner")).texture("side", new ResourceLocation(modid, "block/" + name)).texture("particle", new ResourceLocation(modid, "block/" + name));
        getBuilder(name + "_stairs_outer").parent(getExistingFile(modLoc("block/rooftiles_outer_stairs"))).texture("top", new ResourceLocation(modid, "block/" + name + "_outer")).texture("bottom", new ResourceLocation(modid, "block/" + name + "_outer")).texture("side", new ResourceLocation(modid, "block/" + name)).texture("particle", new ResourceLocation(modid, "block/" + name));
    }
}