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
	public boolean setProvider(int type) {
		if(type == 1) this.dp = new SQLite();
		else if(type == 2) this.dp = new MySQL();
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
	}
}
