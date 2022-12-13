package com.jspiders.multiplayercasestudy.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.jspiders.multiplayercasestudy.multiplayer.SongOperations;

public class SongApp {
	
	private static Connection connection;
	private static FileReader fileReader;
	private static String filePath="C:\\Users\\Rushikesh\\OneDrive\\Desktop\\WEJM4\\multiplayercasestudy\\resources\\db_info.properties";
	private static Properties properties;
	public void getConn() throws IOException, ClassNotFoundException, SQLException {
		fileReader= new FileReader(filePath);
		Properties properties=new Properties();
		properties.load(fileReader);
		
		//Loading driver class
		Class.forName(properties.getProperty("driverPath"));
		
		connection = DriverManager.getConnection(properties.getProperty("dburl"),properties);
		System.out.println("Connection succesfull fo db");
	}
	
		
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		SongApp.connection = connection;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		SongApp.properties = properties;
	}

	public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException
	{
		SongApp obj=new SongApp();
		obj.getConn();
		SongOperations obj1=new SongOperations(obj);
		int ch;
		char str;
		while(true)
		{
			System.out.println("**********************Main Menu***********************");
			System.out.println("1.Create Song");
			System.out.println("2.Remove Song");
			System.out.println("3.update Song details");
			System.out.println("4.Delete Song");
			System.out.println("5.display Song list");
			System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
			Scanner sc=new Scanner(System.in);
			System.out.println("enter your choice");
			ch=sc.nextInt();
			switch(ch)
			{
				case 1: {
					try {
						obj1.createSong();
						System.out.println("========================================");
					} catch (SQLException e) {
						System.out.println("Table is already present.please give another name");
						System.out.println("========================================");
					}
					break;
				}
				case 2:{
					try {
						obj1.removeSong();
						System.out.println("========================================");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 3:{
					try {
						obj1.updateSongDetails();
						System.out.println("========================================");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 4:{
					try {
						obj1.displaySong();
						System.out.println("========================================");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				default:
					System.out.println("");
			}
			System.out.println("Do you want continue(y/n): ");
			str = sc.next().charAt(0);
			if(str=='n')
			{
				System.exit(0);
			}
		}
	}

}
