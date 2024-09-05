package tv.mapper.mapperbase.world.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import tv.mapper.mapperbase.MapperBase;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public enum BaseArmorMaterial implements ArmorMaterial
{
    STEEL("steel", 25, new int[] {3, 6, 7, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, () ->
    {
        return Ingredient.of(BaseItems.STEEL_INGOT.get());
    });

    private static final int[] MAX_DAMAGE_ARRAY = new int[] {13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final LazyLoadedValue<Ingredient> repairMaterial;

    private BaseArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountsIn, int enchantabilityIn, SoundEvent equipSoundIn, float p_i48533_8_, Supplier<Ingredient> repairMaterialSupplier)
    {
        this.name = nameIn;
        this.maxDamageFactor = maxDamageFactorIn;
        this.damageReductionAmountArray = damageReductionAmountsIn;
        this.enchantability = enchantabilityIn;
        this.soundEvent = equipSoundIn;
        this.toughness = p_i48533_8_;
        this.repairMaterial = new LazyLoadedValue<>(repairMaterialSupplier);
    }


    public int getDurabilityForSlot(EquipmentSlot slotIn)
    {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    public int getDefenseForSlot(EquipmentSlot slotIn)
    {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return MAX_DAMAGE_ARRAY[type.getSlot().getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.damageReductionAmountArray[type.getSlot().getIndex()];
    }

    public int getEnchantmentValue()
    {
        return this.enchantability;
    }

    public SoundEvent getEquipSound()
    {
        return this.soundEvent;
    }

    public Ingredient getRepairIngredient()
    {
        return this.repairMaterial.get();
    }

    public String getName()
    {
        return MapperBase.MODID + ":" + this.name;
    }

    public float getToughness()
    {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance()
    {
        return 0;
    }
}