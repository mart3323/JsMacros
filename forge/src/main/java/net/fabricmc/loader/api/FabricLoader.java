package net.fabricmc.loader.api;

import net.minecraftforge.fml.loading.FMLLoader;
import xyz.wagyourtail.jsmacros.forge.client.FakeFabricLoader;

import java.io.File;
import java.nio.file.Path;

public interface FabricLoader {

    static FabricLoader getInstance() {
        return FakeFabricLoader.instance;
    }

    default File getConfigDirectory() {
        return new File(FMLLoader.getGamePath().toFile(), "config");
    }

    default File getGameDirectory() {
        return FMLLoader.getGamePath().toFile();
    }

    default Path getGameDir() {
        return FMLLoader.getGamePath();
    }

    default Path getConfigDir() {
        return new File(FMLLoader.getGamePath().toFile(), "config").toPath();
    }

    boolean isModLoaded(String modid);
}