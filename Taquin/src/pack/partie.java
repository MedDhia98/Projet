package pack;
import java.util.ArrayList; 
import java.util.List; 
import java.util.Random;

public class partie {
	public int taille;
	public char type;
	public int[]	caseVide = new int[2];
	public int[][] matrice=null;
	
	public partie(int taille) {
		this.taille=taille;
		this.matrice=creerPartie(this.taille);
	}
	
	public int getRandomElement(List<Integer> list) 
    { 
        Random rand = new Random(); 
        return list.get(rand.nextInt(list.size())); 
    }
	
	public void permut(int a,int b)
	{
		int c;
		c=a;
		a=b;
		b=c;
	}
	
	
	public int niveauMelange(int[][] tab) {
		int niveauMelange=0;
		for(int i=0;i<this.taille;i++) {
			for(int j=0;j<this.taille;j++) {
				if(tab[i][j]!=0) {
					int l=j;
					int k=i;
					while(l!=this.taille)
					{
						if(tab[i][j]>tab[k][l] && tab[k][l]!=0) {
							niveauMelange++;
						}
						l++;
					}
					if(k!=this.taille-1)
						for(k=i+1;k<this.taille;k++) {
							for(l=0;l<this.taille;l++) {
								if(tab[i][j]>tab[k][l] && tab[k][l]!=0) {
									niveauMelange++;
								}
							}
						}
					}	
				}
			}
		return niveauMelange ;
	}
	
	public int[][] creerPartie(int taille) {
		int tab[][] = new int [taille][taille];
		List<Integer> list = new ArrayList<>();
		for(int i=0;i<taille*taille;i++) {
			list.add(i);
		}
		int n;
		for(int i=0;i<taille;i++) {	
			for(int j=0;j<taille;j++) {
				n=getRandomElement(list);
				if(n==0) {
					this.caseVide[0]=i;
					this.caseVide[1]=j;
				}
				tab[i][j]= n;
				list.remove(new Integer(n));
			}
		}
		/*else {
			List<Integer> list = new ArrayList<>();
			for(int i=0;i<taille*taille;i++) {
				list.add(i);
			}
			for(int i=0;i<taille;i++) {
				for(int j=0;j<taille;j++) {
					int n=getRandomElement(list);
					tab[i][j]=(char)(n+97);
					list.remove(n);
				}
			}
		}*/
		if(niveauMelange(tab)%2==0) {
			System.out.println("cas0");
			return tab;
		}
		else {
			if(tab[taille-1][taille-1]==0) {
				System.out.println("cas3");
				int a;
				a=tab[taille-1][taille-2];
				tab[taille-1][taille-2]=tab[taille-1][taille-3];
				tab[taille-1][taille-3]=a;
			}
			else if(tab[taille-1][taille-2]==0) {
				System.out.println("cas2");
				int a;
				a=tab[taille-1][taille-3];
				tab[taille-1][taille-3]=tab[taille-1][taille-1];
				tab[taille-1][taille-1]=a;
			}
			else {
				System.out.println("cas1");
				int a;
				a=tab[taille-1][taille-2];
				tab[taille-1][taille-2]=tab[taille-1][taille-1];
				tab[taille-1][taille-1]=a;
			}
			return tab;
		}
	}
	
	
	public void affichePartie() throws Exception
	{
		for(int i=0;i<this.taille;i++) {
			for(int j=0;j<this.taille;j++) {
				if(this.matrice[i][j]!=0){
					System.out.print("\t"+this.matrice[i][j]+"\t");
				}
				else
				System.out.print("\t"+"\t");
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
}
