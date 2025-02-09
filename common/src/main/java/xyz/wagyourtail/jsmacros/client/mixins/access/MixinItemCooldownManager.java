package xyz.wagyourtail.jsmacros.client.mixins.access;

import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xyz.wagyourtail.jsmacros.client.access.IItemCooldownEntry;
import xyz.wagyourtail.jsmacros.client.access.IItemCooldownManager;

import java.util.Map;

@Mixin(ItemCooldownManager.class)
public class MixinItemCooldownManager implements IItemCooldownManager {

    @Shadow
    @Final
    private Map<Item, IItemCooldownEntry> entries;


    @Shadow private int tick;

    @Override
    public Map<Item, IItemCooldownEntry> getCooldownItems() {
        return entries;
    }

    @Override
    public int getManagerTicks() {
        return tick;
    }

}
