abstract class Constraint {
	String table;
	String attr;
	String type;

	public abstract String toString();

	public abstract String getAttr();
	
	public abstract String getCond();
}
