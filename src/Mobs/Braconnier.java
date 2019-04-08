package Mobs;

import Monde.Monde;
import Monde.Terrain;

public class Braconnier {
	private int x;
	private int y;
	private int sens;
	public Braconnier(int x,int y) {
		this.x=x;
		this.y=y;
		sens=0;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void move(int dx, int dy) {

		if (this.sens == 0) {
			this.x=(this.x-1+dx)%dx;
		}
		if (this.sens == 1) {
			this.x=(this.x+1+dx)%dx;
		}
		if (this.sens == 2) {
			this.y=(this.y+1+dy)%dy;
		}
		if (this.sens == 3) {
			this.y=(this.y-1+dy)%dy;
		}
		chasser();
	}
	
	public void setSens() {
		if((Terrain.getTerrain()[(y-1+Monde.getDy())%Monde.getDy()][x][2] == 1 || Terrain.getTerrain()[(y-1+Monde.getDy())%Monde.getDy()][x][1] >= Terrain.contourRoche()) 
				&& (Terrain.getTerrain()[(y+1+Monde.getDy())%Monde.getDy()][x][2] == 1 || Terrain.getTerrain()[(y+1+Monde.getDy())%Monde.getDy()][x][1] >= Terrain.contourRoche()) 
				&& (Terrain.getTerrain()[y][(x+1+Monde.getDy())%Monde.getDx()][2] == 1 || Terrain.getTerrain()[y][(x+1+Monde.getDx())%Monde.getDx()][1] >= Terrain.contourRoche()) 
				&& (Terrain.getTerrain()[y][(x-1+Monde.getDy())%Monde.getDx()][2] == 1 || Terrain.getTerrain()[y][(x-1+Monde.getDx())%Monde.getDx()][1] >= Terrain.contourRoche())) {
			Monde.getcarte_Ag().remove(this);
			return;
		}
		double d=Integer.MAX_VALUE;
		for (int i=0;i<Monde.getcarte_Ag().size();i++) {
			if (Monde.getcarte_Ag().get(i) instanceof M1 || Monde.getcarte_Ag().get(i) instanceof M2) {
				int delta_x=this.x-((M) Monde.getcarte_Ag().get(i)).getX();
				int delta_y=this.y-((M) Monde.getcarte_Ag().get(i)).getY();
				double delta=Math.sqrt(delta_x*delta_x+delta_y*delta_y);
				
				if (delta<d) {
					if (delta_x<0 && delta_y>0) {
						if (Math.abs(delta_x)>=Math.abs(delta_y)) {
							this.sens=1;
						}else {
							this.sens=3;
						}
					}
					if (delta_x>0 && delta_y<0) {
						if (Math.abs(delta_x)>=Math.abs(delta_y)) {
							this.sens=0;
						}else {
							this.sens=2;
						}
					}
					if (delta_x<0 && delta_y<0) {
						if (Math.abs(delta_x)>=Math.abs(delta_y)) {
							this.sens=1;
						}else {
							this.sens=2;
						}
					}
					if (delta_x>0 && delta_y>0) {
						if (Math.abs(delta_x)>=Math.abs(delta_y)) {
							this.sens=0;
						}else {
							this.sens=3;
						}
					}
					d=delta;
				}
				
			}
		}
		
		
		boolean obstacle;
		do {
			obstacle=false;
			if (this.sens == 0) {
				if(Terrain.getTerrain()[y][(x-1+Monde.getDx())%Monde.getDx()][1] >= Terrain.contourRoche() || Terrain.getTerrain()[y][(x-1+Monde.getDx())%Monde.getDx()][2] == 1) {
					obstacle=true;
				}
			}
			if (this.sens == 1) {
				if(Terrain.getTerrain()[y][(x+1+Monde.getDx())%Monde.getDx()][1] >= Terrain.contourRoche() || Terrain.getTerrain()[y][(x+1+Monde.getDx())%Monde.getDx()][2] == 1) {
					obstacle=true;
				}
			}
			if (this.sens == 2) {
				if(Terrain.getTerrain()[(y+1+Monde.getDy())%Monde.getDy()][x][1] >= Terrain.contourRoche() || Terrain.getTerrain()[(y+1+Monde.getDy())%Monde.getDy()][x][2] == 1) {
					obstacle=true;
				}
			}
			if (this.sens == 3) {
				if(Terrain.getTerrain()[(y-1+Monde.getDy())%Monde.getDy()][x][1] >= Terrain.contourRoche() || Terrain.getTerrain()[(y-1+Monde.getDy())%Monde.getDy()][x][2]==1) {
					obstacle=true;
				}
			
		}
			if (obstacle==true) {				
				this.sens = (int)(Math.random()*4);
			}
		}while(obstacle);
	}
	
	public int getSens() {
		return this.sens;
	}
	public static void chasser() {
		for (int c=0;c<Monde.getcarte_Ag().size();c++) {

			if (Monde.getcarte_Ag().get(c) instanceof Braconnier) {
				for(int i = ((Braconnier)Monde.getcarte_Ag().get(c)).getX() - 1; i <= ((Braconnier)Monde.getcarte_Ag().get(c)).getX() + 1; i++) { //Parcours voisin selon voisinage de Moore
					for(int j = ((Braconnier)Monde.getcarte_Ag().get(c)).getY() - 1; j <= ((Braconnier)Monde.getcarte_Ag().get(c)).getY() + 1; j++) {
						for(int m=0; m < Monde.getcarte_Ag().size();m++) {
							if (Monde.getcarte_Ag().get(m) instanceof M1 && ((M1) Monde.getcarte_Ag().get(m)).getX() == i && ((M1) Monde.getcarte_Ag().get(m)).getY() == j) {// si M1 voisin
								if (Math.random() <0.6)
									((M1)Monde.getcarte_Ag().get(m)).setMort(true);
									return ;
							}
							if (Monde.getcarte_Ag().get(m) instanceof M2 && ((M2) Monde.getcarte_Ag().get(m)).getX() == i && ((M2) Monde.getcarte_Ag().get(m)).getY() == j) {// si M2 voisin
								if (Math.random() <0.6)
									((M2)Monde.getcarte_Ag().get(m)).setMort(true);
									return ;
							}
						}
					}
				}
			}
		}
	}
}