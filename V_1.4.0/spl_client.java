import java.io.*;
import java.net.*;
import java.util.*;


class MyThread1 extends Thread
{
	Socket sock;
	public PrintWriter pwrite;
	public multi_server parent;

	public void wrapper(OutputStream ostrm)
	{
		this.pwrite = new PrintWriter(ostrm,true);
		this.start();
	}
	
	
	public void run()
	{
		try
		{
			String user_name;
			BufferedReader sendRead = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Your name: ");
			user_name = sendRead.readLine();
			System.out.flush();
			//OutputStream ostream = sock.getOutputStream();
			//this.pwrite = new PrintWriter(ostream, true);
			//InputStream istream = sock.getInputStream();
			//BufferedReader receiveread = new BufferedReader(new InputStreamReader(istream));
			//for(int i = 0, i < 3; i++)
			//{
			//	Socket sock = sersock.accept();
			//	BufferedReader sendRead = new BufferedReader(new InputStreamReader(System.in));
			//	OutputStream ostream = sock.getOutputStream();
			//	PrintWriter pwrite = new PrintWriter(ostream, true);
			//	InputStream istream = sock.getInputStream();
			//	BufferedReader receiveread = new BufferedReader(new InputStreamReader(istream));
			//}

			System.out.println("\nStart to chat:\n\n");
			String r_msg, s_msg;
			while(true)
			{
				//if((r_msg = receiveread.readLine()) != null)
				//{
				//	System.out.println(">>>> " + r_msg);
				//}
	
				//System.out.print("You: ");
				s_msg = sendRead.readLine();
				pwrite.println(user_name + ": " + s_msg);
				System.out.flush();
			}
		}
		catch(Exception e)
		{
			System.out.println("\n\nDISCONNECTED!!!!!!\n\n");
		}
	}
}


public class spl_client
{
	public static void main(String args[]) throws Exception
	{
		//Socket sock = new Socket("172.16.42.8", 9999);
		Socket sock = new Socket("localhost", 9999);
		BufferedReader sendRead = new BufferedReader(new InputStreamReader(System.in));
		OutputStream ostream = sock.getOutputStream();
		//PrintWriter pwrite = new PrintWriter(ostream,true);
		InputStream istream = sock.getInputStream();
		BufferedReader receiveread = new BufferedReader(new InputStreamReader(istream));
		System.out.println("\n\nClient ready for chatting.\n\n");
		
		MyThread1 t = new MyThread1();
		//t.pwrite = this.pwrite;
		t.wrapper(ostream);
		
		
		String r_msg, s_msg;
		while(true)
		{
			//System.out.print("You: ");
			//s_msg = sendRead.readLine();
			//pwrite.println(s_msg);
			//System.out.flush();

			if((r_msg = receiveread.readLine()) != null)
			{
				System.out.println("\n" + r_msg + "\n");
			}
		}
	}
}