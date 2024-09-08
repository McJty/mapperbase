package tv.mapper.mapperbase.data.gen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BaseItemModels extends ItemModelProvider
{
    public BaseItemModels(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper)
    {
        super(generator.getPackOutput(), modid, existingFileHelper);
    }

    @Override
    public String getName()
    {
        return "Mapper Base Item Models";
    }

    @Override
    protected void registerModels()
    {
        getBuilder("flatter_hammer").parent(new UncheckedModelFile("item/handheld")).texture("layer0", modLoc("item/flatter_hammer"));
        getBuilder("steel_nugget").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/steel_nugget"));
        getBuilder("steel_ingot").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/steel_ingot"));
        getBuilder("steel_plate").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/steel_plate"));
        getBuilder("steel_rod").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/steel_rod"));
        getBuilder("ferrite").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/ferrite"));
        getBuilder("raw_steel").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/raw_steel"));

        getBuilder("iron_plate").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/iron_plate"));
        getBuilder("iron_rod").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/iron_rod"));

        getBuilder("bolt").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/bolt"));

        getBuilder("steel_block").parent(new UncheckedModelFile(modid + ":block/steel_block"));

        registerBlockBasics("steel", false, true, true, true, true, true);

        getBuilder("steel_fence_gate").parent(new UncheckedModelFile(modid + ":block/steel_fence_gate"));

        getBuilder("steel_axe").parent(new UncheckedModelFile("item/handheld")).texture("layer0", modLoc("item/steel_axe"));
        getBuilder("steel_pickaxe").parent(new UncheckedModelFile("item/handheld")).texture("layer0", modLoc("item/steel_pickaxe"));
        getBuilder("steel_shovel").parent(new UncheckedModelFile("item/handheld")).texture("layer0", modLoc("item/steel_shovel"));
        getBuilder("steel_hoe").parent(new UncheckedModelFile("item/handheld")).texture("layer0", modLoc("item/steel_hoe"));
        getBuilder("steel_sword").parent(new UncheckedModelFile("item/handheld")).texture("layer0", modLoc("item/steel_sword"));

        getBuilder("steel_helmet").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/steel_helmet"));
        getBuilder("steel_chestplate").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/steel_chestplate"));
        getBuilder("steel_leggings").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/steel_leggings"));
        getBuilder("steel_boots").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/steel_boots"));

        getBuilder("steel_horse_armor").parent(new UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/steel_horse_armor"));
    }

    protected void registerBlockBasics(String name, boolean block, boolean slab, boolean stairs, boolean wall, boolean pressure, boolean fence)
    {
        if(block)
            getBuilder(name).parent(new UncheckedModelFile(modid + ":block/" + name));
        if(slab)
            getBuilder(name + "_slab").parent(new UncheckedModelFile(modid + ":block/" + name + "_slab"));
        if(stairs)
            getBuilder(name + "_stairs").parent(new UncheckedModelFile(modid + ":block/" + name + "_stairs"));
        if(wall)
            getBuilder(name + "_wall").parent(new UncheckedModelFile(modid + ":block/" + name + "_wall_inventory"));
        if(pressure)
            getBuilder(name + "_pressure_plate").parent(new UncheckedModelFile(modid + ":block/" + name + "_pressure_plate"));
        if(fence)
            getBuilder(name + "_fence").parent(new UncheckedModelFile(modid + ":block/" + name + "_fence_inventory"));
    }
}