package net.glowstone.entity.ai.path.ground;

import ca.momoperes.lobster.RelationshipProvider;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.BlockVector;

public class GroundRelationshipNodeProvider implements RelationshipProvider<GroundRelationshipNode> {

    private final World world;
    private final BlockVector start;
    private final BlockVector end;

    public GroundRelationshipNodeProvider(World world, BlockVector start, BlockVector end) {
        this.world = world;
        this.start = start;
        this.end = end;
    }

    @Override
    public Collection<GroundRelationshipNode> getRelationships(GroundRelationshipNode node) {
        BlockVector block = node == null ? start : node.getNeighbor();
        // now we need to find all of the neighbors of this node, and evaluate
        // the cost of their relationship.
        Set<GroundRelationshipNode> nodes = new HashSet<>();

        // direct neighbors (same level)
        BlockVector clone = new BlockVector(block.getBlockX() - 1, block.getBlockY(), block.getBlockZ() - 1);
        for (int i = 0; i < 9; i++) {
            if (i == 4) {
                continue; // ignore self
            }
            int x = i % 3;
            int z = i / 3;
            clone.setX(clone.getBlockX() + x);
            clone.setZ(clone.getBlockZ() + z);
            Material blockType = getBlockType(clone);
            if (blockType.isSolid()) {
                continue;
            }
            double relCost = (i == 1 || i == 3 || i == 5 || i == 7) ? 1.0 : Math.sqrt(2);
            nodes.add(new GroundRelationshipNode(relCost, block, clone, end));
        }
        System.out.println("nodes for " + block + ": " + nodes);
        return nodes;
    }

    @Override
    public boolean confirmTermination(GroundRelationshipNode node) {
        if (node == null) {
            return false;
        }
        return node.getBlock().equals(end);
    }

    private Material getBlockType(BlockVector vector) {
        return Material.getMaterial(world.getBlockTypeIdAt(
                vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()));
    }
}
