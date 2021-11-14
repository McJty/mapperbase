package tv.mapper.mapperbase.block;

import net.minecraft.world.level.block.Block;

public class CustomBlock extends Block implements ToolType
{
    private ToolTiers tier;
    private ToolTypes tool;

    public CustomBlock(Properties properties, ToolTypes tool)
    {
        super(properties);
        this.tool = tool;
        this.tier = ToolTiers.WOOD;
    }

    public CustomBlock(Properties properties, ToolTypes tool, ToolTiers tier)
    {
        super(properties);
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