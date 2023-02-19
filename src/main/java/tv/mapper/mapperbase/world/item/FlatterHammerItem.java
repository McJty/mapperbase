package tv.mapper.mapperbase.world.item;

import javax.annotation.Nonnull;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FlatterHammerItem extends Item
{
    public FlatterHammerItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean hasCraftingRemainingItem()
    {
        return true;
    }

    @Override
    public boolean isRepairable(ItemStack stack)
    {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack)
    {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }

  // @Override
  // public ItemStack getContainerItem(@Nonnull ItemStack stack)
  // {
  //     ItemStack hammer = stack.copy();
  //     int damage = hammer.getDamageValue();

  //     if(damage < hammer.getMaxDamage())
  //     {
  //         hammer.setDamageValue(damage + 1);
  //         return hammer;
  //     }
  //     else
  //         return ItemStack.EMPTY;
  // }
}