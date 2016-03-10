package net.glowstone.entity.monster;

import net.glowstone.entity.meta.MetadataIndex;
import org.bukkit.Location;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;

public class GlowBlaze extends GlowMonster implements Blaze {

    public GlowBlaze(Location loc) {
        super(loc, EntityType.BLAZE, 20);
    }

    public boolean isOnFire() {
        return metadata.getBoolean(MetadataIndex.BLAZE_ON_FIRE);
    }

    public void setOnFire(boolean onFire) {
        metadata.set(MetadataIndex.BLAZE_ON_FIRE, onFire);
    }
}
