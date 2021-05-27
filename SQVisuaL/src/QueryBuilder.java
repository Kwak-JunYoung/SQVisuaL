import java.util.ArrayList;

abstract class QueryBuilder {
	ArrayList<Constraint> con;

	abstract String buildQuery();

	abstract void addParam(Constraint c);
}
