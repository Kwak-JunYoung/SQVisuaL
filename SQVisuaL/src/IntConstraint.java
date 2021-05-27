class IntConstraint extends Constraint {

	public IntConstraint(String table, String attr, String type, int compareTo) {
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

}
