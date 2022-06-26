
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.endsbotany.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import net.mcreator.endsbotany.block.NovaPlantBlock;
import net.mcreator.endsbotany.EndsBotanyMod;

public class EndsBotanyModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, EndsBotanyMod.MODID);
	public static final RegistryObject<Block> NOVA_PLANT = REGISTRY.register("nova_plant", () -> new NovaPlantBlock());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			NovaPlantBlock.registerRenderLayer();
		}
	}
}
