import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//初始化服务器socket，端口用9598
			ServerSocket server = new ServerSocket(9598);
			Socket client = null;
			System.out.println("服务器已启动...");
			while (true) {
				//等待客户端请求
				client = server.accept();
				System.out.println("接受请求...");

				// 分析请求的文件名
				InputStream in = client.getInputStream();
				BufferedReader br1 = new BufferedReader(new InputStreamReader(
						in));
				String httpMessage = br1.readLine();
				String[] content = httpMessage.split(" ");
				System.out.println("code:" + content[0] + ",filename"
						+ content[1] + "httpversion:" + content[2]); // 输出协议
				String fileName = content[1];//第二个为请求的文件名

				// 发送
				PrintStream ps = new PrintStream(client.getOutputStream());
				BufferedReader br2 = new BufferedReader(new InputStreamReader(
						new FileInputStream("D:\\" + fileName)));//html文件应在D盘根目录
				String html = "";
				while (html != null) {
					html = br2.readLine();
					if (html != null) {
						ps.println(html);
					}
				}
				ps.close();

			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
