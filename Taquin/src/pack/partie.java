package pack;
import java.util.ArrayList; 
import java.util.List; 
import java.util.Random;
import java.util.Scanner; 

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
		System.out.print("+");
		for(int i=0;i<this.taille;i++) {
			System.out.print("---------------+");	
		}
		System.out.println();
		for(int i=0;i<this.taille;i++) {
			System.out.print("|");
			for(int j=0;j<this.taille;j++) {
				if(this.matrice[i][j]!=0){
					System.out.print("\t"+this.matrice[i][j]+"\t"+"|");
				}
				else
				System.out.print("\t\t|");
			}
			System.out.println();
			System.out.print("+");
			for(int k=0;k<this.taille;k++) {
				System.out.print("---------------+");	
			}
			System.out.println();
		}
	}
	
	public void deplacer() {
		if(this.caseVide[0]==this.taille-1 && this.caseVide[1]==this.taille-1)
			System.out.println("which square would you like to move the one on the 'left' or on 'top'");
		else if(this.caseVide[0]==0 && this.caseVide[1]==0)
			System.out.println("which square would you like to move the one on the 'right' or on 'bottom'");
		else if(this.caseVide[0]==this.taille-1 && this.caseVide[1]==0)
			System.out.println("which square would you like to move the one on the 'right' or on 'top'");
		else if(this.caseVide[0]==0 && this.caseVide[1]==this.taille-1)
			System.out.println("which square would you like to move the one on the 'left' or on 'bottom'");
		else if(this.caseVide[0]==this.taille-1)
			System.out.println("which square would you like to move the one on the 'right' or on the 'left' or on 'top'");
		else if(this.caseVide[1]==this.taille-1)
			System.out.println("which square would you like to move the one on the 'left' or on 'top' or on 'bottom'");
		else if(this.caseVide[0]==0)
			System.out.println("which square would you like to move the one on the 'left' or on the 'right' or on 'top'");
		else if(this.caseVide[1]==0)
			System.out.println("which square would you like to move the one on the 'right' or on 'top' or on 'bottom'");
		else
			System.out.println("which square would you like to move the one on the 'left' or on the 'right' or on 'top' or on 'bottom'");
		Scanner in = new Scanner(System.in); 
		String dep = in.nextLine(); 
		int a;
		if(dep.equals("u")&&this.caseVide[0]!=0) {
			a=this.matrice[this.caseVide[0]][this.caseVide[1]];
			this.matrice[this.caseVide[0]][this.caseVide[1]]=this.matrice[this.caseVide[0]-1][this.caseVide[1]];
			this.matrice[this.caseVide[0]-1][this.caseVide[1]]=a; 
			this.caseVide[0]-=1;
		}
		else if(dep.equals("b")&&this.caseVide[0]!=this.taille-1) {
			a=this.matrice[this.caseVide[0]][this.caseVide[1]];
			this.matrice[this.caseVide[0]][this.caseVide[1]]=this.matrice[this.caseVide[0]+1][this.caseVide[1]];
			this.matrice[this.caseVide[0]+1][this.caseVide[1]]=a; 
			this.caseVide[0]+=1;
		}
		else if(dep.equals("r")&&this.caseVide[1]!=this.taille-1) {
			a=this.matrice[this.caseVide[0]][this.caseVide[1]];
			this.matrice[this.caseVide[0]][this.caseVide[1]]=this.matrice[this.caseVide[0]][this.caseVide[1]+1];
			this.matrice[this.caseVide[0]][this.caseVide[1]+1]=a; 
			this.caseVide[1]+=1;
		}
		else if(dep.equals("l")&&this.caseVide[1]!=0){
			a=this.matrice[this.caseVide[0]][this.caseVide[1]];
			this.matrice[this.caseVide[0]][this.caseVide[1]]=this.matrice[this.caseVide[0]][this.caseVide[1]-1];
			this.matrice[this.caseVide[0]][this.caseVide[1]-1]=a; 
			this.caseVide[1]-=1;
		}
		else {
			System.out.println("Invalid input");
		}
		
	}
	public boolean verif() {
		for(int i=0;i<this.taille;i++) {
			for(int j=0;j<this.taille;j++) {
				if(i*j==4) return true;
				else if((3*i)+j+1!=this.matrice[i][j])
					return false;
			}
		}
		return(true);
	}
}
