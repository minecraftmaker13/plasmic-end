
package net.mcreator.;

import net.minecraftforge.client.model.generators.BlockStateProvider;

import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.block.state.predicate.BlockPredicate;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.critereon.BlockPredicate;

import net.mcreator.plasmicend.world.features.plants.NovaPlantFeature;
import net.mcreator.plasmicend.init.EndsBotanyModBlocks;

import java.util.Set;
import java.util.List;

import com.mojang.serialization.Codec;

import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
public class NovaPlantFeature extends RandomPatchFeature {
	public static NovaPlantFeature FEATURE = null;
	public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> CONFIGURED_FEATURE = null;
	public static Holder<PlacedFeature> PLACED_FEATURE = null;
	public static Feature<?> feature() {
		FEATURE = new NovaPlantFeature();
		CONFIGURED_FEATURE = FeatureUtils.register("ends_botany:nova_plant", FEATURE,
				FeatureUtils.simpleRandomPatchConfiguration(64, PlacementUtils.filtered(
								Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(2, 4),
										BlockStateProvider.simple(EndsBotanyModBlocks.NOVA_PLANT.get().defaultBlockState())),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
								BlockPredicate.wouldSurvive(EndsBotanyModBlocks.NOVA_PLANT.get().defaultBlockState(), BlockPos.ZERO))
				))
		);
		PLACED_FEATURE = PlacementUtils.register("ends_botany:nova_plant", CONFIGURED_FEATURE,
				List.of(
			CountPlacement.of(5),
			RarityFilter.onAverageOnceEvery(32),
			InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE
			,
			 BiomeFilter.biome()
		));
		return FEATURE;
	}
	public static Holder<PlacedFeature> placedFeature() {
		return PLACED_FEATURE;
	}
	public static final Set<ResourceLocation> GENERATE_BIOMES =
	null;
	private final Set<ResourceKey<Level>> generate_dimensions = Set.of(
				Level.END
	);
	public NovaPlantFeature() {
		super(RandomPatchConfiguration.CODEC);
	}
	public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
		WorldGenLevel world = context.level();
		if (!generate_dimensions.contains(world.getLevel().dimension()))
			return false;
		return super.place(context);
	}
}
