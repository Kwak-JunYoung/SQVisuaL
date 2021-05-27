abstract class Constraint {
	String table;
	String attr;
	String type;
	int compareTo;
	public abstract String toString();
	public abstract String getAttr();
}
