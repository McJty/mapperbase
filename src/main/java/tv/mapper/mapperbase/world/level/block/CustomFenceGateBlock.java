package tv.mapper.mapperbase.world.level.block;

import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.properties.WoodType;

public class CustomFenceGateBlock extends FenceGateBlock implements ToolManager
{
    private ToolTiers tier;
    private ToolTypes tool;

    public CustomFenceGateBlock(Properties properties, WoodType type, ToolTypes tool)
    {
        super(properties, type);
        this.tool = tool;
        this.tier = ToolTiers.WOOD;
    }

    public CustomFenceGateBlock(Properties properties, WoodType type, ToolTypes tool, ToolTiers tier)
    {
        super(properties, type);
        this.tool = tool;
        this.tier = tier;
    }

    @Override
    public ToolTiers getTier()
    {
        return this.tier;
    }

    @Override
    public ToolTypes getTool()
    {
        return this.tool;
    }
}