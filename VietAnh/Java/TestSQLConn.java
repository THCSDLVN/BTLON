import java.util.List;

public class TestSQLConn {

	static SQLConnection c = new SQLConnection();
	public static void main(String[] args) {
		String s = "25 Pho Hue";
		c.getResInfo(s);
		System.out.println("Hello");
	}
}
