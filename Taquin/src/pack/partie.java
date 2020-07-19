package pack;
import java.util.ArrayList; 
import java.util.List; 
import java.util.Random;
import java.util.Scanner; 

public class partie {
	public int nbreCoups;
	public long time;
	public int taille;
	public char type;
	public int[]	caseVide = new int[2];
	public int[][] matrice=null;

	public partie(){
	}
	
	public int[][] getMatrice() {
		return matrice;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public void setMatrice(int[][] matrice) {
		this.matrice = matrice;
	}
	
	public String affichetime(long time) {
		long heure = time/3600000 ;
		long minute = (time-heure*3600000)/60000;
		long seconde = (time-heure*3600000-minute*60000)/1000;
		long milli = time % 100 ;
		String s="heures : "+heure+"; minutes : "+minute+"; secondes : "+seconde+"; millisecondes : "+milli+" ;";
		return s;
	}
	

	public void setNbreCoups() {
		this.nbreCoups = 0;
	}
	


	public void setTime() {
		this.time = System.currentTimeMillis();
	}
	
	public void setGame(String S) {
		String[] tab = S.split("%");
		this.taille=(int) Math.sqrt(tab.length-2);
		int[][] matrice=new int[this.taille][this.taille];
		for(int i=0;i<this.taille;i++) {
			for(int j=0;j<this.taille;j++) {
				if(Integer.parseInt(tab[this.taille*i+j])==0) {
					this.caseVide[0]=i;
					this.caseVide[1]=j;
				}
				matrice[i][j]=Integer.parseInt(tab[this.taille*i+j]);
			}
		}
		long tempsPasse = Long.parseLong(tab[tab.length-2]);
		this.time=System.currentTimeMillis()-tempsPasse;
		this.nbreCoups=Integer.parseInt(tab[tab.length-1]);
		this.matrice=matrice;
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
		if(niveauMelange(tab)%2==0) {
			return tab;
		}
		else {
			if(tab[taille-1][taille-1]==0) {
				int a;
				a=tab[taille-1][taille-2];
				tab[taille-1][taille-2]=tab[taille-1][taille-3];
				tab[taille-1][taille-3]=a;
			}
			else if(tab[taille-1][taille-2]==0) {
				int a;
				a=tab[taille-1][taille-3];
				tab[taille-1][taille-3]=tab[taille-1][taille-1];
				tab[taille-1][taille-1]=a;
			}
			else {
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
		System.out.println();
		System.out.print("time spent this game : " );
		System.out.println(affichetime(System.currentTimeMillis()- this.time));
		System.out.print("number of moves this game : ");
		System.out.println(this.nbreCoups);
		System.out.println();
	}
	
	public void deplacer(String dep) {
		int a;
		if(dep.equals("t")&&this.caseVide[0]!=0) {
			a=this.matrice[this.caseVide[0]][this.caseVide[1]];
			this.matrice[this.caseVide[0]][this.caseVide[1]]=this.matrice[this.caseVide[0]-1][this.caseVide[1]];
			this.matrice[this.caseVide[0]-1][this.caseVide[1]]=a; 
			this.caseVide[0]-=1;
			this.nbreCoups++;
		}
		else if(dep.equals("b")&&this.caseVide[0]!=this.taille-1) {
			a=this.matrice[this.caseVide[0]][this.caseVide[1]];
			this.matrice[this.caseVide[0]][this.caseVide[1]]=this.matrice[this.caseVide[0]+1][this.caseVide[1]];
			this.matrice[this.caseVide[0]+1][this.caseVide[1]]=a; 
			this.caseVide[0]+=1;
			this.nbreCoups++;
		}
		else if(dep.equals("r")&&this.caseVide[1]!=this.taille-1) {
			a=this.matrice[this.caseVide[0]][this.caseVide[1]];
			this.matrice[this.caseVide[0]][this.caseVide[1]]=this.matrice[this.caseVide[0]][this.caseVide[1]+1];
			this.matrice[this.caseVide[0]][this.caseVide[1]+1]=a; 
			this.caseVide[1]+=1;
			this.nbreCoups++;
		}
		else if(dep.equals("l")&&this.caseVide[1]!=0){
			a=this.matrice[this.caseVide[0]][this.caseVide[1]];
			this.matrice[this.caseVide[0]][this.caseVide[1]]=this.matrice[this.caseVide[0]][this.caseVide[1]-1];
			this.matrice[this.caseVide[0]][this.caseVide[1]-1]=a; 
			this.caseVide[1]-=1;
			this.nbreCoups++;
		}
		else {
			System.out.println("Invalid input");
		}	
	}
	
	public void possible() {
		if(this.caseVide[0]==this.taille-1 && this.caseVide[1]==this.taille-1)
			System.out.println("which square would you like to move the one on the 'left' or on the 'top'");
		else if(this.caseVide[0]==0 && this.caseVide[1]==0)
			System.out.println("which square would you like to move the one on the 'right' or on the 'bottom'");
		else if(this.caseVide[0]==this.taille-1 && this.caseVide[1]==0)
			System.out.println("which square would you like to move the one on the 'right' or on the 'top'");
		else if(this.caseVide[0]==0 && this.caseVide[1]==this.taille-1)
			System.out.println("which square would you like to move the one on the 'left' or on the 'bottom'");
		else if(this.caseVide[0]==this.taille-1)
			System.out.println("which square would you like to move the one on the 'right' or on the 'left' or on the 'top'");
		else if(this.caseVide[1]==this.taille-1)
			System.out.println("which square would you like to move the one on the 'left' or on 'top' or on the 'bottom'");
		else if(this.caseVide[0]==0)
			System.out.println("which square would you like to move the one on the 'left' or on the 'right' or on the 'bottom'");
		else if(this.caseVide[1]==0)
			System.out.println("which square would you like to move the one on the 'right' or on 'top' or on the 'bottom'");
		else
			System.out.println("which square would you like to move the one on the 'left' or on the 'right' or on the 'top' or on the 'bottom'");
	}
	public boolean verif() {
		for(int i=0;i<this.taille;i++) {
			for(int j=0;j<this.taille;j++) {
				if((i==this.taille-1)&&(j==this.taille-1)) return true;
				if((this.taille*i)+j+1!=this.matrice[i][j])
					return false;
			}
		}
		return(true);
	}
	
	public String getGame() {
		String s="";
		for(int i=0;i<this.taille;i++) {
			for(int j=0;j<this.taille;j++) {
				s="%"+s;
				s=this.matrice[i][j]+s;
			}
		}
		s+=(System.currentTimeMillis()-this.time);
		s+="%";
		s+=this.nbreCoups;
		s+="%";
		return s;
	}
	
	public void scoreboard() {
		
	}
}
