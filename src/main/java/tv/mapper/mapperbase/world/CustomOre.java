package tv.mapper.mapperbase.world;

import java.util.ArrayList;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class CustomOre
{
    public String name;

    private boolean allowGeneration;
    private boolean whitelisteMode;

    private ArrayList<String> biomeList;

    private Holder<PlacedFeature> placedFeature;

    public CustomOre(String name, Holder<PlacedFeature> feature, boolean generate, boolean whitelist, ArrayList<String> biomeList)
    {
        this.name = name;
        this.allowGeneration = generate;
        this.whitelisteMode = whitelist;
        this.biomeList = biomeList;
        this.placedFeature = feature;
    }

    public boolean canGenerate()
    {
        return this.allowGeneration;
    }

    public boolean hasWhitelist()
    {
        return this.whitelisteMode;
    }

    public ArrayList<String> getBiomeList()
    {
        return this.biomeList;
    }

    public Holder<PlacedFeature> getFeature()
    {
        return this.placedFeature;
    }
}
