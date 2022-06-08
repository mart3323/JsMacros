package net.fabricmc.loader.api;

import net.minecraft.client.MinecraftClient;
import net.minecraftforge.fml.common.Loader;
import xyz.wagyourtail.jsmacros.forge.client.FakeFabricLoader;

import java.io.File;
import java.nio.file.Path;

public interface FabricLoader {


    static FabricLoader getInstance() {
        return FakeFabricLoader.instance;
    }

    default File getConfigDirectory() {
        return Loader.instance().getConfigDir();
    }

    default File getGameDirectory() {
        return MinecraftClient.getInstance().runDirectory;
    }

    default Path getGameDir() {
        return MinecraftClient.getInstance().runDirectory.toPath();
    }

    default Path getConfigDir() {
        return Loader.instance().getConfigDir().toPath();
    }

    boolean isModLoaded(String modid);
}