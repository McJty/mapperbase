package tv.mapper.mapperbase.util;

import java.util.ArrayList;

import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import tv.mapper.mapperbase.MapperBase;

public class ConfigChecker
{
    // Check if config is correct
    public static void checkConfig(ArrayList<String> biomeList, String modid)
    {
        // Get all registered biomes
        ArrayList<String> biome_verify = new ArrayList<>();
        for(Biome biome : ForgeRegistries.BIOMES)
        {
            biome_verify.add( ForgeRegistries.BIOMES.getKey(biome).toString());
        }

        // Check if biomes listed in config exist
        for(String name : biomeList)
            if(!biome_verify.contains(name))
                MapperBase.LOGGER.warn("Invalid biome '" + name + "' in " + modid + " ore black/whitelist, please check the entries!");
    }
}