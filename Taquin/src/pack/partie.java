package pack;
import java.util.ArrayList; 
import java.util.List; 
import java.util.Random;

public class partie {
	public int taille;
	public char type;
	public partie(int taille) {
		this.taille=taille;
	}
	public int getRandomElement(List<Integer> list) 
    { 
        Random rand = new Random(); 
        return list.get(rand.nextInt(list.size())); 
    } 
	public int[][] creerPartie(int taille) {
		int tab[][] = null;
		if(type=='1') {
			List<Integer> list = new ArrayList<>();
			for(int i=0;i<taille*taille;i++) {
				list.add(i);
			}
			for(int i=0;i<taille;i++) {
				for(int j=0;j<taille;j++) {
					int n=getRandomElement(list);
					tab[i][j]= n;
					list.remove(n);
				}
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
			return tab;
		}
		else {
			if(tab[taille-1][taille-1]==0) {
				int x=tab[taille-1][taille-2];
				tab[taille-1][taille-2]=tab[taille-1][taille-3];
				tab[taille-1][taille-3]=x;
			}
			else if(tab[taille-1][taille-2]==0) {
				int x=tab[taille-1][taille-1];
				tab[taille-1][taille-1]=tab[taille-1][taille-3];
				tab[taille-1][taille-3]=x;
			}
			else {
				int x=tab[taille-1][taille-1];
				tab[taille-1][taille-1]=tab[taille-1][taille-2];
				tab[taille-1][taille-2]=x;
			}
			return tab;
		}
	}
	public int niveauMelange(int[][] tab) {
		int niveauMelange=0;
		for(int i=0;i<taille;i++) {
			for(int j=0;j<taille;j++) {
				if(tab[i][j]!=0) {
					for(int k=i;k<taille;k++) {
						for(int l=j;k<taille;l++) {
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
	
}
