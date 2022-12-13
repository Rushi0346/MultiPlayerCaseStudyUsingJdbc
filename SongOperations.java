package com.jspiders.multiplayercasestudy.casestudy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.jspiders.multiplayercasestudy.main.SongApp;

public class SongOperation {
	
	private static SongApp songApp;
	private static Scanner scanner=new Scanner(System.in);
	private static PreparedStatement preparedStatement;
	private static int result;
	private static ResultSet resultSet;
	
	
	public SongOperation(SongApp obj)
	{
		this.songApp=obj;
	}
	//Default no argument constructor
	public SongOperation()
	{
		
	}
	
	public void createSong() throws SQLException
	{
		System.out.println("=========TO create song enter below details===========");
		System.out.println("Enter the song ID: ");
		int id=scanner.nextInt();
		System.out.println("Enter the Song Name:\n ");
		String name=scanner.nextLine();
		System.out.println("Enter the Artist Name: \n");
		String artist=scanner.nextLine();
		System.out.println("Enter the song duration: \n");
		double duration=scanner.nextDouble();
		System.out.println("Enter the albulm name: \n");
		String album=scanner.nextLine();
		
		preparedStatement=songApp.getConnection().prepareStatement(songApp.getProperties().getProperty("createquery"));
		
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, name);
		preparedStatement.setString(3, artist);
		preparedStatement.setString(4, album);
		preparedStatement.setDouble(5, duration);
		
		int result=preparedStatement.executeUpdate();
		System.out.println(name +" is created succesfully");
		
	}
	
//	public void createPlayList() throws SQLException
//	{
//		System.out.println("Enter the name for new PlayList: \n");
//		String playListName=scanner.nextLine();
//		
//		preparedStatement = songApp.getConnection().prepareStatement(songApp.getProperties().getProperty("findPlayList"));
//		
//		int result1 =preparedStatement.executeUpdate();
//		
//		if(result1==0)
//		{
//			preparedStatement = songApp.getConnection().prepareStatement(songApp.getProperties().getProperty("createPlayList"));
//			preparedStatement.setString(1, playListName);
//			result=preparedStatement.executeUpdate();
//		}
//		else
//		{
//			System.out.println("Playlisyt is already present.");
//		}
//		
//		
//		
//		
//	}
//	public void addSong() throws SQLException
//	{
//		System.out.println("Enter the playlist name: \n");
//		String playlistname=scanner.nextLine();
//		
//		System.out.println("enter the song name to add into the playlist: \n");
//		String songName=scanner.nextLine();
//		
//		
//		
//		System.out.println("");
//		
//		preparedStatement = SongApp.getConnection().prepareStatement(songApp.getProperties().getProperty("addSong"));
//		
//	}
	
	public void removeSong() throws SQLException
	{
		preparedStatement = songApp.getConnection().prepareStatement(songApp.getProperties().getProperty("removeSong"));
		
		System.out.println("Enter the song name which you want to remove: \n");
		String name=scanner.nextLine();
		preparedStatement.setString(1, name);
		
		result=preparedStatement.executeUpdate();
		System.out.println("Song succesfully removed from the List");
	}
	public void updateSongDetails() throws SQLException
	{
		System.out.println("******************You can only update album name:************************** \n");
		preparedStatement = songApp.getConnection().prepareStatement(songApp.getProperties().getProperty("updateSongDetails"));
		System.out.println("Enter the song Id to update details: ");
		int id=scanner.nextInt();
		System.out.println("Enter the new album name: \n");
		String albumName=scanner.next();
		
		preparedStatement.setString(1, albumName);
		preparedStatement.setInt(2, id);
		result=preparedStatement.executeUpdate();
		System.out.println("Song details updated");
		System.out.println("Do you want to see (y/n)");
		char ch=scanner.next().charAt(0);
		if(ch=='y')
		{
			this.displaySong();
		}
		System.out.println("**************************Thank you for an update****************************");
	}
	
	public void displaySong() throws SQLException
	{
		resultSet=preparedStatement.executeQuery(songApp.getProperties().getProperty("displaySong"));
		while(resultSet.next())
		{
			System.out.println(resultSet.getString(1) + "|" + resultSet.getString(2) + "|" + resultSet.getString(3) + "|" + resultSet.getString(4) + "|" + resultSet.getString(5));
		}
	}

}
