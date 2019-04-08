package Mobs;

import java.util.ArrayList;

import Monde.Monde;

public abstract class M {
	protected int x;
	protected int y;
	protected int sens;
	protected int nb_pomme_manger;
	protected boolean evolution;
	protected boolean mort;


	protected int step;
	protected  int pv;
	
	public M(int x, int y) {
		this.x=x;
		this.y=y;
		this.sens=(int)(Math.random()*4);
		step =0;
		nb_pomme_manger = 0;
		evolution = false;
		mort = false;
		
	}
	public abstract void move(int dx, int dy);
	
	public abstract void manger_pomme(Pomme apple);
	public abstract void evoluer();
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getStep() {
		return step;
	}
	

	public void setStep() {
		this.step += 1;
	}	

	public int getPv() {
		return pv;
	}
	public int getNb_pomme_manger() {
		return nb_pomme_manger;
	}
	public boolean getEvolution() {
		return evolution;
	}
	public int getSens() {
		return this.sens;
	}
	
	public static void reproduction() {
		ArrayList<Object> carte = Monde.getcarte_Ag();
		int taille = carte.size();
		if(taille > (Monde.getDx() * Monde.getDy()) / 20) {
			return ;
		}
		for (int i=0; i<taille; i++) {
			if (carte.get(i) instanceof M && ((M) carte.get(i)).getStep() >100) {
				for (int j=0;j<taille ;j++) {
					if (!(carte.get(j).equals(carte.get(i))) 
							&& carte.get(j).getClass().equals(carte.get(i).getClass()) 
							&& ((M) carte.get(j)).getStep() >20 
							&& ((M)carte.get(j)).getX() == ((M)carte.get(i)).getX() 
							&& ((M)carte.get(j)).getY() == ((M)carte.get(i)).getY() ) {
						
						if (carte.get(i) instanceof M1) {
							((M1) carte.get(i)).step=0;
							((M1) carte.get(j)).step=0;
							carte.add(new M1(((M)carte.get(j)).getX(), ((M)carte.get(j)).getY()));
							break ;
						}
						if (carte.get(i) instanceof M2) {
							((M2) carte.get(i)).step=0;
							((M2) carte.get(j)).step=0;
							carte.add(new M2(((M)carte.get(j)).getX(), ((M)carte.get(j)).getY()));
							break ;
						}
					}
				}
			}
		}
		
	}
	public void setMort(boolean mort) {
		this.mort = mort;
	}
	public boolean getMort() {
		return this.mort;
	}
	public abstract void setSens();
	
	
}