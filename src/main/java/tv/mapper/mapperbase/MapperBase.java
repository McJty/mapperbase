package tv.mapper.mapperbase;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.mapper.mapperbase.config.BaseConfig;
import tv.mapper.mapperbase.world.BaseOreGenerator;
import tv.mapper.mapperbase.world.item.BaseItems;
import tv.mapper.mapperbase.world.level.block.BaseBlocks;

@Mod(MapperBase.MODID)
public class MapperBase
{
    public static final String MODID = "mapperbase";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public MapperBase()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, BaseConfig.CLIENT_CONFIG);

        BaseBlocks.init();
        BaseItems.init();
        TABS.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::serverSetup);

        MinecraftForge.EVENT_BUS.register(new BaseOreGenerator());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Mapper Base setup started! Thank you for using my mods!");
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {}

    private void serverSetup(final FMLDedicatedServerSetupEvent event)
    {}

    public static RegistryObject<CreativeModeTab> TAB = TABS.register("mapperbase_group", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.mapperbase_group"))
            .icon(() -> new ItemStack(BaseItems.STEEL_INGOT.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .displayItems((featureFlags, output) -> {
                BaseItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
            })
            .build());

}