package xyz.wagyourtail.jsmacros.client.mixins.access;

import net.minecraft.client.resource.language.TranslationStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(TranslationStorage.class)
public interface MixinLocale {
    @Accessor(value = "translations")
    Map<String, String> getProperties();
}
