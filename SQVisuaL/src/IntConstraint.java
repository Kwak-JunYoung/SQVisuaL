class IntConstraint extends Constraint {
	private double compareTo;

	public IntConstraint(String table, String attr, String type, double compareTo) {
		this.table = table;
		this.attr = attr;
		this.type = type;
		this.compareTo = compareTo;
	}

	@Override
	public String toString() {
		return this.table + "." + this.attr + " " + this.type + " " + String.valueOf(this.compareTo);
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
