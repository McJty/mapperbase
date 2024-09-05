package tv.mapper.mapperbase.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BaseTags {
    public static class Blocks {
        @SuppressWarnings("unused")
        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(name));
        }
    }

    public static class Items {
        @SuppressWarnings("unused")
        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, new ResourceLocation(name));

        }
    }

    public static class ForgeBlocks {
        public static final TagKey<Block> PRESSURE_PLATES = tag("pressure_plates");

        public static final TagKey<Block> STORAGE_BLOCKS_STEEL = tag("storage_blocks/steel");
        public static final TagKey<Block> FENCES_STEEL = tag("fences/steel");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation("forge", name));
            // return BlockTags.bind(new ResourceLocation("forge", name).toString());
        }
    }

    public static class ForgeItems {

        public static final TagKey<Item> PLATES = tag("plates");
        public static final TagKey<Item> PLATES_IRON = tag("plates/iron");
        public static final TagKey<Item> PLATES_STEEL = tag("plates/steel");
        public static final TagKey<Item> FENCES_STEEL = tag("fences/steel");

        public static final TagKey<Item> RODS_IRON = tag("rods/iron");
        public static final TagKey<Item> RODS_STEEL = tag("rods/steel");

        public static final TagKey<Item> INGOTS_STEEL = tag("ingots/steel");
        public static final TagKey<Item> NUGGETS_STEEL = tag("nuggets/steel");
        public static final TagKey<Item> STORAGE_BLOCKS_STEEL = tag("storage_blocks/steel");

        public static final TagKey<Item> PRESSURE_PLATES = tag("pressure_plates");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, new ResourceLocation("forge", name));

            //   return ItemTags.bind(new ResourceLocation("forge", name).toString());
        }
    }
}