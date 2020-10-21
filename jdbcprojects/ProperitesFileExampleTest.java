package jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ProperitesFileExampleTest {

	public static void main(String[] args) {
		//locating input file and read from stream
		try(InputStream is=new FileInputStream("src/com/nt/commons/demo.properties"))
		{
		//creating the properties object
		Properties prop=new Properties();
		prop.load(is);
		System.out.println("name:::"+prop.getProperty("student.name"));
		System.out.println("address"+ prop.getProperty("sdf"));
		System.out.println("age::"+ prop.getProperty("student.age"));
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}

}
