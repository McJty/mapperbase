package tv.mapper.mapperbase.data.gen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import tv.mapper.mapperbase.world.item.BaseItems;
import tv.mapper.mapperbase.world.level.block.BaseBlocks;

public class BaseLang extends LanguageProvider
{
    private String lang;

    public BaseLang(DataGenerator generator, String modid, String lang)
    {
        super(generator.getPackOutput(), modid, lang);
        this.lang = lang;
    }

    @Override
    protected void addTranslations()
    {
        switch(lang)
        {
            default:
            case "en_us":
                add(BaseBlocks.STEEL_BLOCK.get(), "Steel Block");
                add(BaseBlocks.STEEL_SLAB.get(), "Steel Slab");
                add(BaseBlocks.STEEL_STAIRS.get(), "Steel Stairs");
                add(BaseBlocks.STEEL_WALL.get(), "Steel Wall");
                add(BaseBlocks.STEEL_PRESSURE_PLATE.get(), "Steel Pressure Plate");
                add(BaseBlocks.STEEL_FENCE.get(), "Steel Fence");
                add(BaseBlocks.STEEL_FENCE_GATE.get(), "Steel Fence Gate");

                add(BaseItems.FERRITE.get(), "Ferrite");
                add(BaseItems.RAW_STEEL.get(), "Raw Steel");
                add(BaseItems.BOLT.get(), "Bolt");
                add(BaseItems.FLATTER_HAMMER.get(), "Flatter Hammer");
                add(BaseItems.IRON_PLATE.get(), "Iron Plate");
                add(BaseItems.IRON_ROD.get(), "Iron Rod");
                add(BaseItems.STEEL_INGOT.get(), "Steel Ingot");
                add(BaseItems.STEEL_NUGGET.get(), "Steel Nugget");
                add(BaseItems.STEEL_PLATE.get(), "Steel Plate");
                add(BaseItems.STEEL_ROD.get(), "Steel Rod");

                add(BaseItems.STEEL_AXE.get(), "Steel Axe");
                add(BaseItems.STEEL_PICKAXE.get(), "Steel Pickaxe");
                add(BaseItems.STEEL_SHOVEL.get(), "Steel Shovel");
                add(BaseItems.STEEL_HOE.get(), "Steel Hoe");
                add(BaseItems.STEEL_SWORD.get(), "Steel Sword");

                add(BaseItems.STEEL_HELMET.get(), "Steel Helmet");
                add(BaseItems.STEEL_CHESTPLATE.get(), "Steel Chestplate");
                add(BaseItems.STEEL_LEGGINGS.get(), "Steel Leggings");
                add(BaseItems.STEEL_BOOTS.get(), "Steel Boots");

                add(BaseItems.STEEL_HORSE_ARMOR.get(), "Steel Horse Armor");

                add("itemGroup.mapperbase_group", "Mapper Base");
                break;
            case "fr_fr":
                add(BaseBlocks.STEEL_BLOCK.get(), "Bloc d'acier");
                add(BaseBlocks.STEEL_SLAB.get(), "Dalle en acier");
                add(BaseBlocks.STEEL_STAIRS.get(), "Escalier en acier");
                add(BaseBlocks.STEEL_WALL.get(), "Muret en acier");
                add(BaseBlocks.STEEL_PRESSURE_PLATE.get(), "Plaque de pression en acier");
                add(BaseBlocks.STEEL_FENCE.get(), "Barrière en acier");
                add(BaseBlocks.STEEL_FENCE_GATE.get(), "Portillon en acier");

                add(BaseItems.BOLT.get(), "Boulon");
                add(BaseItems.FERRITE.get(), "Ferrite");
                add(BaseItems.RAW_STEEL.get(), "Acier brut");
                add(BaseItems.FLATTER_HAMMER.get(), "Masse");
                add(BaseItems.IRON_PLATE.get(), "Plaque de fer");
                add(BaseItems.IRON_ROD.get(), "Barre de fer");
                add(BaseItems.STEEL_INGOT.get(), "Lingot d'acier");
                add(BaseItems.STEEL_NUGGET.get(), "Pépite d'acier");
                add(BaseItems.STEEL_PLATE.get(), "Plaque d'acier");
                add(BaseItems.STEEL_ROD.get(), "Barre d'acier");

                add(BaseItems.STEEL_AXE.get(), "Hache en acier");
                add(BaseItems.STEEL_PICKAXE.get(), "Pioche en acier");
                add(BaseItems.STEEL_SHOVEL.get(), "Pelle en acier");
                add(BaseItems.STEEL_HOE.get(), "Houe en acier");
                add(BaseItems.STEEL_SWORD.get(), "Epee en acier");

                add(BaseItems.STEEL_HELMET.get(), "Casque en acier");
                add(BaseItems.STEEL_CHESTPLATE.get(), "Plastron en acier");
                add(BaseItems.STEEL_LEGGINGS.get(), "Jambières en acier");
                add(BaseItems.STEEL_BOOTS.get(), "Bottes en acier");

                add(BaseItems.STEEL_HORSE_ARMOR.get(), "Armure en acier pour cheval");

                add("itemGroup.mapperbase_group", "Mapper Base");
                break;
        }
    }
}