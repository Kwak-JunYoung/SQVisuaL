import java.util.ArrayList;

public class SQVisuaL {
	private DataProvider dp;
	public SQVisuaL() {
		this.dp = null;
	}
	public DataProvider getProvider() {
		return this.dp;
	}
	public boolean isConnected() {
		return (this.dp != null && this.dp.isOpen());
	}
	public boolean setProvider(String type) {
		if(type.equals("SQLite")) this.dp = new SQLite();
		else if(type.equals("MySQL")) this.dp = new MySQL();
		return this.dp != null;
	}
	public String getDPType() {
		return this.dp.getClass().getSimpleName();
	}
	public boolean connect() {
		this.dp.connect();
		return this.dp.isOpen();
	}
	public void setCred(ArrayList<String> cred) {
		if(this.dp instanceof SQLite) ((SQLite) this.dp).setPath(cred.get(0));
		else if(this.dp instanceof MySQL) {
			((MySQL) this.dp).setHost(cred.get(0));
			((MySQL) this.dp).setPort(Integer.valueOf(cred.get(1)));
			((MySQL) this.dp).setDB(cred.get(2));
			((MySQL) this.dp).setUsername(cred.get(3));
			((MySQL) this.dp).setPassword(cred.get(4));
		}
	}
}
