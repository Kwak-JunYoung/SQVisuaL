
class StringConstraint extends Constraint {
	String compareTo;

	public StringConstraint(String table, String attr, String type, String compareTo) {
		this.table = table;
		this.attr = attr;
		this.type = type;
		this.compareTo = compareTo;
	}

	@Override
	public String toString() {
		return this.table + "." + this.attr + " " + this.type + " '" + this.compareTo + "'";
	}

	@Override
	public String getAttr() {
		return this.attr;
	}

	@Override
	public String getCond() {
		return this.type + " " + this.compareTo;
	}
}
