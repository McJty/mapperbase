package tv.mapper.mapperbase.world;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import tv.mapper.mapperbase.MapperBase;
import tv.mapper.mapperbase.config.BaseConfig.ClientConfig;

public class BaseOreGenerator
{
    private static boolean generate;

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void registerOreGen(BiomeLoadingEvent event)
    {
        for(CustomOre ore : OreList.ores)
        {
            if(ore.canGenerate())
            {
                String biome = event.getName().toString();

                if(ore.hasWhitelist())
                {
                    if(ore.getBiomeList().contains(biome))
                    {
                        generate = true;
                        if(ClientConfig.ENABLE_GEN_LOGS.get())
                            MapperBase.LOGGER.info(ore.name + " whitelisted for biome " + biome + " in the config.");
                    }
                    else
                        generate = false;
                }
                else
                {
                    if(ore.getBiomeList().contains(biome))
                    {
                        generate = false;
                        if(ClientConfig.ENABLE_GEN_LOGS.get())
                            MapperBase.LOGGER.info(ore.name + " blacklisted for biome " + biome + " in the config.");
                    }
                    else
                        generate = true;
                }

                if(generate)
                {
                    event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ore.getFeature());
                }
            }
        }
    }

    public static List<PlacementModifier> orePlacement(PlacementModifier amount, PlacementModifier modifier)
    {
        return List.of(amount, InSquarePlacement.spread(), modifier, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int amount, PlacementModifier modifier)
    {
        return orePlacement(CountPlacement.of(amount), modifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int amount, PlacementModifier modifier)
    {
        return orePlacement(RarityFilter.onAverageOnceEvery(amount), modifier);
    }
}