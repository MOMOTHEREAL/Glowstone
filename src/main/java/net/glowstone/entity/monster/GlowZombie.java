package net.glowstone.entity.monster;

import com.flowpowered.network.Message;
import net.glowstone.entity.meta.MetadataIndex;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Zombie;

import java.util.List;

public class GlowZombie extends GlowMonster implements Zombie {

    private int conversionTime = -1;
    private boolean canBreakDoors;
    private ZombieType type;

    public GlowZombie(Location loc) {
        this(loc, EntityType.ZOMBIE);
    }

    public GlowZombie(Location loc, EntityType type) {
        this(loc, type, ZombieType.DEFAULT);
    }

    public GlowZombie(Location loc, EntityType type, ZombieType zombieType) {
        super(loc, type, 20);
        this.type = zombieType;
    }

    @Override
    public List<Message> createSpawnMessage() {
        metadata.set(MetadataIndex.ZOMBIE_IS_CONVERTING, conversionTime > 0);
        metadata.set(MetadataIndex.ZOMBIE_IS_VILLAGER, getZombieType().getId());
        return super.createSpawnMessage();
    }

    @Override
    public boolean isBaby() {
        return metadata.getBoolean(MetadataIndex.ZOMBIE_IS_CHILD);
    }

    @Override
    public void setBaby(boolean value) {
        metadata.set(MetadataIndex.ZOMBIE_IS_CHILD, value);
    }

    @Override
    public boolean isVillager() {
        return type.getId() > 0 && type.getId() < 6;
    }

    @Override
    public void setVillager(boolean value) {
        metadata.set(MetadataIndex.ZOMBIE_IS_VILLAGER, value ? getZombieType().getId() - 1 : 0);
    }

    @Override
    public void setVillagerProfession(Profession profession) {
        setZombieType(ZombieType.get(profession.getId() + 1));
    }

    @Override
    public Profession getVillagerProfession() {
        return Profession.getProfession(getZombieType().getId() - 1);
    }

    @Override
    public void setZombieType(ZombieType type) {
        this.type = type;
        metadata.set(MetadataIndex.ZOMBIE_IS_VILLAGER, type.getId());
    }

    @Override
    public ZombieType getZombieType() {
        return type;
    }

    public int getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(int conversionTime) {
        this.conversionTime = conversionTime;
        metadata.set(MetadataIndex.ZOMBIE_IS_CONVERTING, conversionTime > 0);
    }

    public boolean isCanBreakDoors() {
        return canBreakDoors;
    }

    public void setCanBreakDoors(boolean canBreakDoors) {
        this.canBreakDoors = canBreakDoors;
    }

    public static class GlowHusk extends GlowZombie implements Zombie.Husk {

        public GlowHusk(Location loc) {
            this(loc, EntityType.HUSK);
        }

        public GlowHusk(Location loc, EntityType type) {
            super(loc, type, ZombieType.HUSK);
        }
    }
}
