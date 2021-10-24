package io.github.metalturtle18.watermod;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("watermod")
public class WaterMod {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MOD_ID = "watermod";

    public WaterMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("Welcome to Water Mod!");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            final var registry = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

            // Register tools here because I don't feel like making a new class
            registry.register(
                    "water_pickaxe",
                    () -> new PickaxeItem(
                            Tiers.WOOD,
                            1,
                            -2.8F,
                            new Item.Properties()
                                    .tab(CreativeModeTab.TAB_TOOLS)
                                    .durability(1)
                    )
            );

            registry.register(
                    "water_axe",
                    () -> new AxeItem(
                            Tiers.WOOD,
                            1,
                            -2.8F,
                            new Item.Properties()
                                    .tab(CreativeModeTab.TAB_TOOLS)
                                    .durability(1)
                    )
            );

            registry.register(
                    "water_shovel",
                    () -> new ShovelItem(
                            Tiers.WOOD,
                            1,
                            -2.8F,
                            new Item.Properties()
                                    .tab(CreativeModeTab.TAB_TOOLS)
                                    .durability(1)
                    )
            );

            registry.register(
                    "water_hoe",
                    () -> new HoeItem(
                            Tiers.WOOD,
                            1,
                            -2.8F,
                            new Item.Properties()
                                    .tab(CreativeModeTab.TAB_TOOLS)
                                    .durability(1)
                    )
            );

            registry.register(
                    "water_sword",
                    () -> new SwordItem(
                            Tiers.WOOD,
                            1,
                            -2.8F,
                            new Item.Properties()
                                    .tab(CreativeModeTab.TAB_COMBAT)
                                    .durability(1)
                    )
            );

            registry.register(FMLJavaModLoadingContext.get().getModEventBus());
            LOGGER.info("Water Mod registered tools");
        }
    }
}
