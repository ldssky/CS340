package shared.models.games;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;
import shared.definitions.CatanColor;
import shared.models.GameAction;

import javax.annotation.Generated;
import java.util.Objects;

@Generated("net.kupiakos")
public class JoinGameRequest extends GameAction {

    @SerializedName("color")
    @Expose
    private CatanColor color;

    @SerializedName("id")
    @Expose
    private int id;


    // CUSTOM CODE
    // END CUSTOM CODE

    /**
     * No args constructor for use in serialization
     */
    public JoinGameRequest() {
    }

    /**
     * @param color What color you want to join (or rejoin) as.
     * @param id    The ID of the game to join
     */
    public JoinGameRequest(CatanColor color, int id) {
        this.color = color;
        this.id = id;
    }

    /**
     * @return What color you want to join (or rejoin) as.
     */
    public CatanColor getColor() {
        return color;
    }

    /**
     * @param color What color you want to join (or rejoin) as.
     */
    public void setColor(@NotNull CatanColor color) {
        this.color = color;
    }

    public JoinGameRequest withColor(@NotNull CatanColor color) {
        setColor(color);
        return this;
    }

    /**
     * @return The ID of the game to join
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The ID of the game to join
     */
    public void setId(int id) {
        this.id = id;
    }

    public JoinGameRequest withId(int id) {
        setId(id);
        return this;
    }

    @Override
    public String toString() {
        return "JoinGameRequest [" +
                "color=" + color +
                ", id=" + id +
                "]";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof JoinGameRequest) {
            return equals((JoinGameRequest) other);
        }
        return false;
    }

    public boolean equals(JoinGameRequest other) {
        return (
                Objects.equals(color, other.color) &&
                        id == other.id
        );
    }

    /**
     * Run on the server. Will add the player specified in the cookie to the game specified in this action, with the specified
     * color.
     */
    @Override
    public void execute() {

    }
}
