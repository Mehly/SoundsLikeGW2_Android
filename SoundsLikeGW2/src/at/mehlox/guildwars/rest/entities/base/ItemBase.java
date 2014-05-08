package at.mehlox.guildwars.rest.entities.base;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import at.mehlox.guildwars.rest.deserialization.ItemRarityDeserializer;
import at.mehlox.guildwars.rest.deserialization.ItemTypeDeserializer;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@JsonIgnoreProperties(ignoreUnknown = true)
@DatabaseTable(tableName = ItemBase.TABLE_NAME)
public class ItemBase {
	
	public static final String TABLE_NAME = "ITEM_BASE";
	public static final String COLUMN_NAME_ID = "COLUMN_NAME_ID";
	public static final String COLUMN_NAME_ITEM_NAME = "COLUMN_NAME_ITEM_NAME";
	public static final String COLUMN_NAME_ITEM_TYPE = "COLUMN_NAME_ITEM_TYPE";
	public static final String COLUMN_NAME_DESCRIPTION = "COLUMN_NAME_DESCRIPTION";
	
	@DatabaseField(version = true)
	private Date modificationDate;	
	@DatabaseField(id = true, columnName = COLUMN_NAME_ID)
	@JsonProperty("item_id")
	private int mItemId;
	
	@DatabaseField(canBeNull = false, columnName = COLUMN_NAME_ITEM_NAME)
	@JsonProperty("name")
	private String mName;
	
	@DatabaseField(canBeNull = true, columnName = COLUMN_NAME_DESCRIPTION)
	@JsonProperty("description")
	private String mDescription;
	
	@DatabaseField(canBeNull = false, columnName = COLUMN_NAME_ITEM_TYPE)
	@JsonProperty("type")
	private ItemType mType;
	
	@JsonProperty("level")
	private int mRequiredLevel;
	
	@JsonProperty("rarity")
	private ItemRarity mRarity;
	
	@JsonDeserialize(using = ItemRarityDeserializer.class)
	public static enum ItemRarity {
		LEGENDARY("Legendary"),
		ASCENDED("Ascended"),
		EXOTIC("Exotic"),
		RARE("Rare"),
		MASTERWORK("Masterwork"),
		FINE("Fine"),
		JUNK("Junk"),
		UNKNOWN("Unkown");
		
		private ItemRarity(String rarity) {
			this.rarity = rarity;
		}
		
		String rarity;
		
		@Override
		public String toString() {
			return rarity;
		}
	}

	@JsonDeserialize(using = ItemTypeDeserializer.class)
	public static enum ItemType {
		ARMOR("Armor"),
		BACK("Back"),
		BAG("Bag"),
		CONSUMABLE("Consumable"),
		CONTAINER("Container"),
		CRAFTINGMATERIAL("CraftingMaterial"),
		GATHERING("Gathering"),
		GIZMO("Gizmo"),
		MINIPET("MiniPet"),
		TOOL("Tool"),
		TRINKET("Trinket"),
		TROPHY("Trophy"),
		UPGRADECOMPONENT("UpgradeComponent"),
		WEAPON("Weapon"),
		UNKNOWN("Unkown");

		private ItemType(String value) {
			this.value = value;
		}
		
		String value;

		@Override
		public String toString() {
			return value;
		}
		
		

	}

}
