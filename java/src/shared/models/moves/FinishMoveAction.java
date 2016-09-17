package shared.models.moves;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javax.annotation.Generated;
import shared.definitions.PlayerIndex;

@Generated("net.kupiakos")
public class FinishMoveAction {

    @SerializedName("type")
    @Expose(deserialize = false)
    private final String type = "FinishTurn";

    @SerializedName("playerIndex")
    @Expose
    private PlayerIndex playerIndex;


    // CUSTOM CODE
    // END CUSTOM CODE

    /**
     * No args constructor for use in serialization
     */
    public FinishMoveAction() {
    }

    /**
      * @param type The type
      * @param playerIndex Who's sending this command (0-3)
     */
    public FinishMoveAction(PlayerIndex playerIndex) {
            this.playerIndex = playerIndex;
    }

    /**
     * @return The type
     */
    public final String getType() { return type; }

    /**
     * @return Who's sending this command (0-3)
     */
    public PlayerIndex getPlayerIndex() { return playerIndex; }

    /**
     * @param playerIndex Who's sending this command (0-3)
     */
    public void setPlayerIndex(PlayerIndex playerIndex) { this.playerIndex = playerIndex; }

    public FinishMoveAction withPlayerIndex(PlayerIndex playerIndex) {
        setPlayerIndex(playerIndex);
        return this;
    }

    @Override
    public String toString() {
        return "FinishMoveAction [" +
            "type=" + type +
            ", playerIndex=" + playerIndex +
            "]";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof FinishMoveAction) {
            return equals((FinishMoveAction)other);
        }
        return false;
    }

    public boolean equals(FinishMoveAction other) {
        return (
            type == other.type &&
            playerIndex == other.playerIndex
        );
    }
}
