package tv.mapper.mapperbase.block;

import net.minecraft.world.level.block.WallBlock;

public class CustomWallBlock extends WallBlock implements ToolType
{
    private ToolTiers tier;
    private ToolTypes tool;

    public CustomWallBlock(Properties properties)
    {
        super(properties);
    }

    public CustomWallBlock(Properties properties, ToolTypes tool)
    {
        super(properties);
        this.tool = tool;
        this.tier = ToolTiers.WOOD;
    }

    public CustomWallBlock(Properties properties, ToolTypes tool, ToolTiers tier)
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