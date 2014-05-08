package at.mehlox.guildwars.rest.deserialization;

import java.io.IOException;
import java.util.Locale;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import at.mehlox.guildwars.rest.entities.base.ItemBase.ItemType;

public class ItemTypeDeserializer extends JsonDeserializer<ItemType> {

	@Override
	public ItemType deserialize(JsonParser parser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {

		ItemType t;

		try {
			final String jsonValue = parser.getText().toUpperCase(
					Locale.getDefault());
			t = ItemType.valueOf(jsonValue);
		} catch (Exception e) {
			t = ItemType.UNKNOWN;
		}

		return t;

	}
}
