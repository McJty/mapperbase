package tv.mapper.mapperbase.util;

import java.util.List;
import java.util.Map;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.tags.ITag;
import tv.mapper.mapperbase.config.BaseConfig.ClientConfig;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class TagViewer
{
    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent e)
    {
        if(!ClientConfig.ENABLE_TAG_VIEWER.get())
            return;

        if(!Screen.hasControlDown())
            return;

        List<Component> tooltips = e.getToolTip();

        IForgeRegistry<Item> itemsRegistry = ForgeRegistries.ITEMS;

        if (itemsRegistry.isEmpty() || itemsRegistry.tags() == null) {
            tooltips.add(new TextComponent("Item or tag registry is empty...").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
            return;
        }

        List<ITag<Item>> tagList = itemsRegistry.tags().stream().toList();
        List<TagKey<Item>> stackKeys = e.getItemStack().getTags().toList();

        int count = 0;

        for (ITag<Item> tag : tagList) {
            if (stackKeys.contains(tag.getKey())) {
                tooltips.add(new TextComponent("Tag: " + tag.getKey().toString()).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
                count++;
            }
        }

        if(count <= 0)
            tooltips.add(new TextComponent("No tag found...").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
    }
}