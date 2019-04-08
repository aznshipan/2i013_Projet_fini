package Mobs;

import Monde.Monde;

public class Pomme{
	private int x;
	private int y;
	private int step;
	private boolean estPourrie;
	private static int delaiP=30;
	public Pomme(int x, int y) {
		this.x = x;
		this.y = y;
		step=0;
		estPourrie = false;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void pourrir() {
		if(step >= delaiP && estPourrie==false)
			estPourrie=true;
	}
	public static void duree() {
		for (int i=0;i<Monde.getcarte_P().size();i++) {
			(((Pomme) Monde.getcarte_P().get(i)).step)++;;
		}
	}
	public int getStep() {
		return step;
	}
	public boolean isEstPourrie() { //true si la pomme est pourrie
		return estPourrie;
	}
	public static void delete() { //si la pomme est pourri, et Step >= 50, la pomme disparait
		for(int i = 0; i < Monde.getcarte_P().size(); i++) {
			if (((Pomme)Monde.getcarte_P().get(i)).isEstPourrie() && ((Pomme)Monde.getcarte_P().get(i)).getStep() >= delaiP+delaiP/2)
				Monde.getcarte_P().remove(Monde.getcarte_P().get(i));
			}
		}
	
}
