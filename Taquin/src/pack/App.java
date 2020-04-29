package pack;

public class App {

	public static void main(String[] args) throws Exception {
	partie p=new partie(3);
	while(!p.verif()) {
		p.affichePartie();
		p.deplacer();	
	}
	p.affichePartie();
	System.out.println("Congratulations !!! ");
	}
	

}
