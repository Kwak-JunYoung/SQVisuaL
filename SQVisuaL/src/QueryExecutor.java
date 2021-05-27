//This is a temporary code, and is not in use.
import java.sql.ResultSet;

public class QueryExecutor implements Runnable{
	private String q;
	private SQVisuaL sql;
	public ResultSet r;
	public QueryExecutor(String q, SQVisuaL sql) {
		this.sql = sql;
		this.q = q;
	}
	@Override
	public void run() {
		this.r = this.sql.getProvider().query(this.q);
	}
}