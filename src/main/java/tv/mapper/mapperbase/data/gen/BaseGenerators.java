package tv.mapper.mapperbase.data.gen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tv.mapper.mapperbase.MapperBase;

import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BaseGenerators
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true,new BaseRecipes(generator));
        generator.addProvider(true,new LootTableProvider(generator.getPackOutput(), Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(() -> new BaseLootTables(MapperBase.MODID), LootContextParamSets.BLOCK))));
        generator.addProvider(true,new BaseBlockStates(generator, MapperBase.MODID, existingFileHelper));
        generator.addProvider(true,new BaseBlockModels(generator, MapperBase.MODID, existingFileHelper));
        generator.addProvider(true,new BaseItemModels(generator, MapperBase.MODID, existingFileHelper));

        BaseBlockTags baseBlockTags = new BaseBlockTags(generator.getPackOutput(), event.getLookupProvider(), MapperBase.MODID, existingFileHelper);

        generator.addProvider(true,baseBlockTags);
        generator.addProvider(true,new BaseItemTags(generator.getPackOutput(), event.getLookupProvider(), baseBlockTags, existingFileHelper));

        generator.addProvider(true,new BaseLang(generator, MapperBase.MODID, "en_us"));
        generator.addProvider(true,new BaseLang(generator, MapperBase.MODID, "fr_fr"));
    }
}