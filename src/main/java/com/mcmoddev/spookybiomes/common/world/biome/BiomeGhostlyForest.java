package com.mcmoddev.spookybiomes.common.world.biome;

import com.mcmoddev.proxyslib.world.biome.MistyBiome;
import com.mcmoddev.spookybiomes.common.entity.EntityTheForgottenWarlock;
import com.mcmoddev.spookybiomes.common.world.gen.features.WorldGenGhostlyTree;
import com.mcmoddev.spookybiomes.init.ConfigHandler;
import net.minecraft.block.BlockTallGrass.EnumType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.Nonnull;
import java.util.Random;

public class BiomeGhostlyForest extends MistyBiome {

    public static final WorldGenGhostlyTree TREE_GEN = new WorldGenGhostlyTree(true);
    public static final WorldGenGhostlyTree NATURAL_GEN = new WorldGenGhostlyTree(false);

    public BiomeGhostlyForest() {
        super(new BiomeProperties("Ghostly Forest")
                .setTemperature(0.25F).setRainfall(0.9F)
                .setBaseHeight(0.05F).setHeightVariation(0.35F));
        setRegistryName("ghostly_forest");
        spawnableCreatureList.clear();
        spawnableCreatureList.add(new SpawnListEntry(
                EntityBat.class, 2, 2, 2));
        spawnableMonsterList.add(new SpawnListEntry(
                EntityEnderman.class, 4, 1, 3));
        spawnableMonsterList.add(new SpawnListEntry(
                EntityTheForgottenWarlock.class, 5, 1, 2));
        decorator.treesPerChunk = 6;
        decorator.grassPerChunk = 6;
        decorator.generateFalls = true;
        mistColor = 0xedfffd;
        if (ConfigHandler.misc.disableBiomeMist) {
            mistDensity = 1.0F;
        } else {
            mistDensity = 0.05F;
        }
    }

    @Nonnull
    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return (rand.nextInt(10) == 0 ? NATURAL_GEN : TREE_GEN);
    }

    @Nonnull
    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(4) == 0 ? new WorldGenTallGrass(EnumType.GRASS)
                : new WorldGenTallGrass(EnumType.FERN);
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos) {
        return 0xdbe6e5;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0xdbe6e5;
    }

    @Override
    public int getWaterColorMultiplier() {
        return 0x0a2a72;
    }

    @Override
    public float getSpawningChance() {
        return 0.1F;
    }
}