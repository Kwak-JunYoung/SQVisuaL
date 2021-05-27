
public class MetaRow {
	private String name;
	private boolean canBeNull;
	private String dataType;
	private int maxLen;
	private String key;

	public MetaRow(String name, boolean canBeNull, String dataType, int maxLen, String key) {
		this.name = name;
		this.canBeNull = canBeNull;
		this.dataType = dataType;
		this.maxLen = maxLen;
		this.key = key;
	}

	public String getName() {
		return this.name;
	}

	public boolean canBeNull() {
		return this.canBeNull;
	}

	public String getType() {
		return this.dataType;
	}

	public int getLen() {
		return this.maxLen;
	}

	public String getKeyStatus() {
		return this.key;
	}
}
//{"Target attribute", "Value", "Set to NULL", "Data type", "Max length", "Key status"};