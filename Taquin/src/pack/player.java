package pack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class player {
	public char type;
	private String name;
	
	public player(char type,String name) {
		this.type=type;
		this.name=name;
	}
	
	public void create_player() throws IOException{
		File myObj = new File("C:/Users/DHIA/Desktop/Players/"+this.name+".txt");
		myObj.createNewFile();
	}
	
	public void add_game(String S) throws IOException {
		FileWriter myObj = new FileWriter("C:/Users/DHIA/Desktop/Players/"+this.name+".txt",true);
		BufferedWriter bw=new BufferedWriter(myObj);
		bw.write(S);
		bw.newLine();
		bw.close();
	}
	
	
	
	public String getName() {
		return name;
	}
	



	public int wins;
	public int num_games;
}
