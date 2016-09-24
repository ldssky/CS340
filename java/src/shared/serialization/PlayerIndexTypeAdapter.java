package shared.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import shared.definitions.PlayerIndex;

import java.io.IOException;

public class PlayerIndexTypeAdapter extends TypeAdapter<PlayerIndex> {
    @Override
    public void write(JsonWriter jsonWriter, PlayerIndex playerIndex) throws IOException {
        if (playerIndex == null) {
            jsonWriter.value(-1);
        } else {
            jsonWriter.value(playerIndex.index());
        }
    }

    @Override
    public PlayerIndex read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() != JsonToken.NUMBER) {
            return null;
        }
        int idx = jsonReader.nextInt();
        if (idx < 0 || idx >= PlayerIndex.MAX_PLAYERS) {
            return null;
        }
        return PlayerIndex.fromInt(idx);
    }
}