package pack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class App {
	private static boolean isInteger(String s) {
	    boolean isValid = true;
	    try{ Integer.parseInt(s); }
	    catch(NumberFormatException nfe){ isValid = false; }
	    return isValid;
	}
	
	public static boolean verif(String name) {
		File folder = new File("C:/Users/DHIA/Desktop/Players/");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    if(listOfFiles[i].getName().equals(name+".txt")) {
		    	return true;
		    }
		  }
		}
		return false;
	}
	
	public static boolean login(char type, String name) throws IOException {
		if(type=='o') {
			if(!verif(name)) {
				System.out.println("this account does not exist !!");
				System.out.println("Please check the name account!!");
				return false;
			}
		}
		else if(type=='n') {
			if(verif(name)) {
				System.out.println("this name is already used !!");
				System.out.println("Chose an other name !!");
				return false;
			}
		}
		return true;
		
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Welcome to our game !! ");
		String name;
		char type;
		while(true) {
			System.out.println("if are you an old player print 'o', if if are you an new player print 'n' ");
			Scanner in = new Scanner(System.in); 
			String ch = in.nextLine();
			System.out.println();
			System.out.println("                     --------------------------------------------------------------------       ");
			System.out.println();
			
			if (ch.equals("o") || ch.equals("n")) {
				System.out.println("enter your name !");
				name = in.nextLine();
				type = ch.charAt(0);
				if(login(type,name)) {
					break;
				}
			}
		}
		player play=new player(type,name);
		play.create_player();
		while(true) {
			System.out.println();
			System.out.println("                     --------------------------------------------------------------------       ");
			System.out.println();
			int n=3;
			partie p=new partie();
				BufferedReader reader = new BufferedReader(new FileReader("C:/Users/DHIA/Desktop/Players/"+play.getName()+".txt"));
				File logFile=new File("C:/Users/DHIA/Desktop/Players/"+play.getName()+".txt");
				if(logFile.length() != 0) {
				System.out.println("Do you want to continue one of these games ? if yes type its numbre else type anything else :)");
				String line = reader.readLine();
				int count=0;
				while (line != null) {
					count++;
					System.out.println(count+") ");
					partie p1=new partie();
					p1.setGame(line);
					p1.affichePartie();
					line = reader.readLine();
				}
				reader.close();
				Scanner in = new Scanner(System.in); 
				String ch = in.nextLine();
				if(isInteger(ch)) {
					if(Integer.parseInt(ch)<=count&&Integer.parseInt(ch)>=1) {
						reader = new BufferedReader(new FileReader("C:/Users/DHIA/Desktop/Players/"+play.getName()+".txt"));
						BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/DHIA/Desktop/Players/"+"secret.txt"));
						for(int i=0;i<Integer.parseInt(ch)-1;i++) {
							line = reader.readLine();
							writer.write(line);
							writer.newLine();
						}
							line = reader.readLine();
							p.setGame(line);
							if(Integer.parseInt(ch)!=count) {
								for(int i=Integer.parseInt(ch);i<count;i++) {
									line = reader.readLine();
									writer.write(line);
									writer.newLine();
								}
							}
							writer.close();
							reader.close();
							File file = new File("C:/Users/DHIA/Desktop/Players/"+play.getName()+".txt");
							file.delete();
							file = new File("C:/Users/DHIA/Desktop/Players/"+play.getName()+".txt");
							File file1=new File("C:/Users/DHIA/Desktop/Players/secret.txt");
					        file1.renameTo(file);
					}
					else {
						while(true) {
							System.out.println("Please chose difficulty n to make a 'n x n' taquin, n must be bigger than 2 ");
							 in = new Scanner(System.in); 
							 ch = in.nextLine();
							if(isInteger(ch)) {
								n=Integer.parseInt(ch);
								if(n>2) {
									p.setTaille(n);
									p.setMatrice(p.creerPartie(n));
									p.setTime();
									p.setNbreCoups();
									
									break;
								}
							}
							System.out.println("Invalide Input !!");
						}
					}
				}
				else {
					while(true) {
						System.out.println("Please chose difficulty n to make a 'n x n' taquin, n must be bigger than 2 ");
						 in = new Scanner(System.in); 
						 ch = in.nextLine();
						if(isInteger(ch)) {
							n=Integer.parseInt(ch);
							if(n>2) {
								p.setTaille(n);
								p.setMatrice(p.creerPartie(n));
								p.setTime();
								p.setNbreCoups();
								break;
							}
						}
						System.out.println("Invalide Input !!");
					}
				}
				}
				else {
					while(true) {
						System.out.println("Please chose difficulty n to make a 'n x n' taquin, n must be bigger than 2 ");
						Scanner in = new Scanner(System.in); 
						String ch = in.nextLine();
						if(isInteger(ch)) {
							n=Integer.parseInt(ch);
							if(n>2) {
								p.setTaille(n);
								p.setMatrice(p.creerPartie(n));
								p.setTime();
								p.setNbreCoups();
								break;
							}
						}
						System.out.println("Invalide Input !!");
					}
				}
		System.out.println();
		System.out.println("                     --------------------------------------------------------------------       ");
		System.out.println();
		System.out.println(" Hello "+play.getName());
		System.out.println("the Rules :");
		System.out.println("1) if you want to move the piece on the top of the empty square write 't'.");
		System.out.println("2) if you want to move the piece on the bottom of the empty square write 'b'.");
		System.out.println("3) if you want to move the piece on the left of the empty square write 'l'.");
		System.out.println("4) if you want to move the piece on the right of the empty square write 'r'.");
		System.out.println("5) if you want to leave without saving the game press 'x'.");
		System.out.println("6) if you want to leave and save the game press 's'.");
		while(!p.verif()) {		
			System.out.println();
			System.out.println("                     --------------------------------------------------------------------       ");
			System.out.println();
			p.affichePartie();
			p.possible();
			Scanner in = new Scanner(System.in); 
			String dep = in.nextLine();
			if(dep.equals("x"))
				break;
			else if(dep.equals("s")) {
				play.add_game(p.getGame());
				break;
			}
			else
				p.deplacer(dep);
		}
		if(p.verif())
			System.out.println("Congratulations !!! "); 
		System.out.println();
		System.out.println("                     --------------------------------------------------------------------       ");
		System.out.println();
		System.out.println("if you want to leave the game press 'x' or if you want to try the puzzle of Sam Loyd before you leave press 's' or if you want to start an other game type anything else :).");
		Scanner in = new Scanner(System.in); 
		String dep = in.nextLine();
		if(dep.equals("x"))
			break;
		System.out.println();
		System.out.println("                     --------------------------------------------------------------------       ");
		System.out.println();
		if(dep.contentEquals("s")) {
			System.out.println("Welcome to the SamLoyd Puzzle >:) !!");
			System.out.println("it is very simple you just need to solve this simple Taquin.");
			System.out.println("The Rules here are the same.");
			System.out.println("If you want to quit press 'q' and we'll give you the awnser.");
			System.out.println("Enjoy :) !!");
			partie s=new partie();
			String samloyd="1%2%3%4%5%6%8%7%0%0%0";
			s.setGame(samloyd);
			while(!s.verif()) {		
				System.out.println();
				System.out.println("                     --------------------------------------------------------------------       ");
				System.out.println();
				s.affichePartie();
				s.possible();
				in = new Scanner(System.in); 
				dep = in.nextLine();
				if(dep.equals("x"))
					break;
				if(dep.equals("q")) {
					System.out.println();
					System.out.println("                     --------------------------------------------------------------------       ");
					System.out.println();
					System.out.println("Actually no body is able to find the solution because it's mathemathicly impossible !! I hope you had fun trying it :D !!");
					break;
				}
				else
					s.deplacer(dep);
					
			}
			break;
		}
		}
		System.out.println("Bye Bye !! visit us later for more fun :).");
		}
}
