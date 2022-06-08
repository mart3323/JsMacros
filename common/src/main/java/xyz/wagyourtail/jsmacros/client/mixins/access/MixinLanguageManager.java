package xyz.wagyourtail.jsmacros.client.mixins.access;


import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.resource.ResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.wagyourtail.jsmacros.client.JsMacrosJsonLangFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

@Mixin(LanguageManager.class)
public class MixinLanguageManager {
    
    @Shadow private String field_6652;

    @Shadow @Final protected static TranslationStorage field_6650;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resource/language/TranslationStorage;method_5945(Lnet/minecraft/resource/ResourceManager;Ljava/util/List;)V", shift = At.Shift.AFTER), method = "reload")
    public void onLanguageLoad(ResourceManager resourceManager, CallbackInfo ci) {
        Map<String, String> translations = new LinkedHashMap<>();
        List<String> langs = Lists.newArrayList("en_US");
        if (!field_6652.equals("en_US")) langs.add(field_6652);
        for (String lang : langs) {
            Set<String> res = JsMacrosJsonLangFile.getLangResources(lang);
            for (String r : res) {
                JsonObject ts = null;
                try (Reader reader = new InputStreamReader(JsMacrosJsonLangFile.class.getResourceAsStream(r))) {
                    ts = new JsonParser().parse(reader).getAsJsonObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ts.entrySet().forEach((e) -> translations.putIfAbsent(e.getKey(), e.getValue().getAsString()));
            }
        }
        translations.forEach(((MixinLocale)field_6650).getProperties()::putIfAbsent);
    }
}
