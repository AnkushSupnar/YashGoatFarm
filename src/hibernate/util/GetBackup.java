package hibernate.util;

import java.io.IOException;

public class GetBackup {

	public GetBackup() {
		
		try {
			ProcessBuilder builder = new ProcessBuilder("D:\\Software\\backup.cmd");
			builder.start();
			System.out.println("Backup Complete");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
