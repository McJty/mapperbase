package tv.mapper.mapperbase.world.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tv.mapper.mapperbase.MapperBase;
import tv.mapper.mapperbase.world.level.block.BaseBlocks;

public class BaseItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MapperBase.MODID);

    public static final RegistryObject<Item> STEEL_ITEM = ITEMS.register("steel_block", () -> new BlockItem(BaseBlocks.STEEL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> STEEL_STAIRS_ITEM = ITEMS.register("steel_stairs", () -> new BlockItem(BaseBlocks.STEEL_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SLAB_ITEM = ITEMS.register("steel_slab", () -> new BlockItem(BaseBlocks.STEEL_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> STEEL_WALL_ITEM = ITEMS.register("steel_wall", () -> new BlockItem(BaseBlocks.STEEL_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> STEEL_PRESSURE_PLATE_ITEM = ITEMS.register("steel_pressure_plate", () -> new BlockItem(BaseBlocks.STEEL_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> STEEL_FENCE_ITEM = ITEMS.register("steel_fence", () -> new BlockItem(BaseBlocks.STEEL_FENCE.get(), new Item.Properties()));
    public static final RegistryObject<Item> STEEL_FENCE_GATE_ITEM = ITEMS.register("steel_fence_gate", () -> new BlockItem(BaseBlocks.STEEL_FENCE_GATE.get(), new Item.Properties()));

    public static final RegistryObject<Item> FERRITE = ITEMS.register("ferrite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_STEEL = ITEMS.register("raw_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_ROD = ITEMS.register("steel_rod", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_ROD = ITEMS.register("iron_rod", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOLT = ITEMS.register("bolt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<FlatterHammerItem> FLATTER_HAMMER = ITEMS.register("flatter_hammer", () -> new FlatterHammerItem(new Item.Properties().durability(128)));

    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe", () -> new AxeItem(BaseTiers.STEEL_TIER, 6.0F, -3.0F, (new Item.Properties())));
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe", () -> new PickaxeItem(BaseTiers.STEEL_TIER, 1, -2.8F, (new Item.Properties())));
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel", () -> new ShovelItem(BaseTiers.STEEL_TIER, 1.5F, -3.0F, (new Item.Properties())));
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe", () -> new HoeItem(BaseTiers.STEEL_TIER, -2, -0.0F, (new Item.Properties())));
    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword", () -> new SwordItem(BaseTiers.STEEL_TIER, 3, -2.4F, (new Item.Properties())));

    public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet", () -> new ArmorItem(BaseArmorMaterial.STEEL, ArmorItem.Type.HELMET, (new Item.Properties())));
    public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate", () -> new ArmorItem(BaseArmorMaterial.STEEL, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
    public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings", () -> new ArmorItem(BaseArmorMaterial.STEEL, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
    public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots", () -> new ArmorItem(BaseArmorMaterial.STEEL, ArmorItem.Type.BOOTS, (new Item.Properties())));

    public static final RegistryObject<Item> STEEL_HORSE_ARMOR = ITEMS.register("steel_horse_armor", () -> new HorseArmorItem(9, new ResourceLocation(MapperBase.MODID, "textures/entity/horse/armor/horse_armor_steel.png"), (new Item.Properties()).stacksTo(1)));

    public static void init()
    {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}