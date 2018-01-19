package net.glowstone.entity.ai.path.ground;

import ca.momoperes.lobster.RelationshipNode;
import java.util.Objects;
import lombok.Data;
import org.bukkit.util.BlockVector;

@Data
public class GroundRelationshipNode implements RelationshipNode {
    /**
     * The cost of this relationship. In this implementation,
     * the cost is defined by the action taken to go from the block to its neighbor.
     *
     * <p>The cost of a A-to-B non-diagonal walk is 1.0 * distance(A, B)
     *
     * <p>The cost of a A-to-B diagonal walk is sqrt(2) * distance(A, B)
     *
     * <p>The cost of a jump is 1.75
     *
     * <p>The cost of a drop is 0.5 * distance(drop)
     */
    private final double relationshipCost;
    private final double heuristicCost;
    private final BlockVector block;
    private final BlockVector neighbor;
    private final BlockVector destination;

    public GroundRelationshipNode(double relationshipCost, BlockVector block, BlockVector neighbor, BlockVector destination) {
        this.relationshipCost = relationshipCost;
        this.heuristicCost = neighbor.distance(destination); // Euclidean heuristic
        this.block = block;
        this.destination = destination;
        this.neighbor = neighbor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(block, destination);
    }
}
