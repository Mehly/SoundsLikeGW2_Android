package at.mehlox.guildwars.rest.deserialization;

import java.io.IOException;
import java.util.Locale;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import at.mehlox.guildwars.rest.entities.base.ItemBase.ItemRarity;

public class ItemRarityDeserializer extends JsonDeserializer<ItemRarity> {

	@Override
	public ItemRarity deserialize(JsonParser parser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {

		ItemRarity ir;

		try {
			final String jsonValue = parser.getText().toUpperCase(Locale.getDefault());
			ir = ItemRarity.valueOf(jsonValue);
		} catch (Exception e) {
			ir = ItemRarity.UNKNOWN;
		}

		return ir;

	}
}
