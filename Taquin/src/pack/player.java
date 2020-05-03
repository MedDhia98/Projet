package pack;

import java.io.File;
import java.io.IOException;

public class player {
	public char type;
	public String name;
	
	public player(char type,String name) {
		this.type=type;
		this.name=name;
	}
	
	public void create_player() throws IOException{
		File myObj = new File("C:/Users/DHIA/Desktop/Players/"+this.name+".txt");
		myObj.createNewFile();
	}
	
	public boolean verif() {
		File folder = new File("C:/Users/DHIA/Desktop/Players/");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    if(listOfFiles[i].getName().equals(this.name+".txt")) {
		    	return true;
		    }
		  }
		}
		return false;
	}
	
	public boolean login() throws IOException {
		if(this.type=='o') {
			if(!this.verif()) {
				System.out.println("this account does not exist !!");
				System.out.println("Please check the name account!!");
				return false;
			}
		}
		else if(this.type=='n') {
			if(this.verif()) {
				System.out.println("this name is already used !!");
				System.out.println("Chose an other name !!");
				return false;
			}
			else {
				this.create_player();
			}
		}
		System.out.println("Let's get started !!");
		return true;
		
	}
	
	
	public int wins;
	public int num_games;
}
