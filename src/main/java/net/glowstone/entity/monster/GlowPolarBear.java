package net.glowstone.entity.monster;

import com.flowpowered.network.Message;
import net.glowstone.entity.GlowAgeable;
import net.glowstone.entity.GlowPlayer;
import net.glowstone.entity.meta.MetadataIndex;
import net.glowstone.net.message.play.player.InteractEntityMessage;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PolarBear;

import java.util.List;

public class GlowPolarBear extends GlowAgeable implements PolarBear {

    private boolean standing = false;

    public GlowPolarBear(Location loc) {
        super(loc, EntityType.POLAR_BEAR, 30);
        setSize(1.3f, 1.4f);
    }

    @Override
    public boolean isStanding() {
        return standing;
    }

    @Override
    public void setStanding(boolean standing) {
        this.standing = standing;
        metadata.set(MetadataIndex.POLARBEAR_STANDING, standing);
    }

    @Override
    public List<Message> createUpdateMessage() {
        metadata.set(MetadataIndex.POLARBEAR_STANDING, standing);
        return super.createUpdateMessage();
    }

    @Override
    public boolean entityInteract(GlowPlayer player, InteractEntityMessage message) {
        setStanding(true);
        return super.entityInteract(player, message);
    }
}
