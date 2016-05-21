package net.glowstone.entity.monster;

import com.flowpowered.network.Message;
import net.glowstone.entity.meta.MetadataIndex;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;

import java.util.List;

public class GlowSkeleton extends GlowMonster implements Skeleton {
    private SkeletonType skeletonType;

    public GlowSkeleton(Location loc) {
        this(loc, EntityType.SKELETON);
    }

    public GlowSkeleton(Location loc, EntityType type) {
        this(loc, type, SkeletonType.NORMAL);
    }

    public GlowSkeleton(Location loc, EntityType type, SkeletonType skeletonType) {
        super(loc, type, 20);
        this.skeletonType = skeletonType;
    }

    @Override
    public SkeletonType getSkeletonType() {
        return skeletonType;
    }

    @Override
    public List<Message> createUpdateMessage() {
        metadata.set(MetadataIndex.SKELETON_TYPE, getSkeletonType().getId());
        return super.createUpdateMessage();
    }

    @Override
    public void setSkeletonType(SkeletonType type) {
        skeletonType = type;
        metadata.set(MetadataIndex.SKELETON_TYPE, skeletonType.getId());
    }

    public static class GlowStray extends GlowSkeleton implements Skeleton.Stray {
        public GlowStray(Location loc) {
            this(loc, EntityType.STRAY);
        }

        public GlowStray(Location loc, EntityType type) {
            super(loc, type, SkeletonType.STRAY);
        }
    }

    public static class GlowWitherSkeleton extends GlowSkeleton implements Skeleton.WitherSkeleton {
        public GlowWitherSkeleton(Location loc) {
            this(loc, EntityType.WITHER_SKELETON);
        }

        public GlowWitherSkeleton(Location loc, EntityType type) {
            super(loc, type, SkeletonType.WITHER);
        }
    }
}
