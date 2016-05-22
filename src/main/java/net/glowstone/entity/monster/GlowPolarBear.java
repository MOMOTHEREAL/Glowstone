package net.glowstone.entity.monster;

import net.glowstone.entity.GlowAgeable;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PolarBear;

public class GlowPolarBear extends GlowAgeable implements PolarBear {

    public GlowPolarBear(Location loc) {
        super(loc, EntityType.POLAR_BEAR, 30);
        setSize(1.3f, 1.4f);
    }
}
