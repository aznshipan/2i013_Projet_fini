package Mobs;

public class Arbre{
	private boolean enfeu;
	private boolean brulé;
	private boolean grille;
	private int x;
	private int y;
	private String S;
	private int residu;

	public Arbre(int x, int y) {
		this.x=x;
		this.y=y;
		S="T";
		enfeu=false;
		brulé=false;
		grille=false;
		residu = 0;
	}
	
	public boolean isEnfeu() {
		return enfeu;
	}
	public void setEnfeu(boolean enfeu) {
		this.enfeu = enfeu;
		
	}
	
	public boolean isBrulé() {
		return brulé;
	}

	public void setBrulé(boolean brulé) {
		this.brulé = brulé;
	}

	public String getS() {
		return S;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public void setGrille(boolean grille) {
		this.grille=grille;
	}
	public boolean getGrille() {
		return this.grille;
	}
	public int getResidu() {
		return this.residu;
	}
	public void IncrementResidu() {
		this.residu++;
	}
}