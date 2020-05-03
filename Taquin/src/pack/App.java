package pack;

import java.util.Scanner;

public class App {
	

	public static void main(String[] args) throws Exception {
		System.out.println("Welcome to our game !! ");
		while(true) {
			System.out.println("if are you an old print 'o', if if are you an old print 'n' ");
			Scanner in = new Scanner(System.in); 
			String ch = in.nextLine();
			if (ch.equals("o") || ch.equals("n")) {
				System.out.println("enter your name !");
				String name = in.nextLine();
				char type = ch.charAt(0);
				player play=new player(type,name);
				if(play.login()) break;
				
			}
		}
		player dhia=new player('o',"dhia");
		dhia.create_player();
		dhia.verif();
		partie p=new partie(5);
		while(!p.verif()) {
			p.affichePartie();
			p.deplacer();	
		}
		p.affichePartie();
		System.out.println("Congratulations !!! ");
		}
	

}
