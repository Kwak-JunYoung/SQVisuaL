
public class SelectQueryBuilder extends QueryBuilder {

	@Override
	String buildQuery() {
		String q = "";
		for (int i = 0; i < this.con.size() - 1; i++) {
			q += this.con.get(i).toString();
			q += " AND ";
		}
		q += this.con.get(this.con.size() - 1).toString();
		return q;
	}

	@Override
	void addParam(Constraint c) {
		con.add(c);
	}

}
