package tv.mapper.mapperbase.util;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;
import net.minecraftforge.registries.tags.ITagManager;
import tv.mapper.mapperbase.config.BaseConfig.ClientConfig;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class TagViewer {
    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent e) {
        if (!ClientConfig.ENABLE_TAG_VIEWER.get())
            return;

        if (!Screen.hasControlDown())
            return;

        List<Component> tooltips = e.getToolTip();
        Item item = e.getItemStack().getItem();
        ITagManager<Item> tagmap = ForgeRegistries.ITEMS.tags();

        int count = 0;

        for (ITag<Item> entry : tagmap) {
            if (e.getItemStack().is(entry.getKey())) {
                tooltips.add(Component.literal("Tag: " + entry.getKey().toString()).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
                count++;
            }
        }

        if (count <= 0)
            tooltips.add(Component.literal("No tag found...").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
    }
}