package Monde;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;


import javax.imageio.ImageIO;

public class Terrain {
	private static int[][][] terrain;
	private int dx;
	private int dy;
	private  BufferedImage image;
	private static boolean pluie=false;
	
	public Terrain(int dx,int dy) {
		try {
			image = ImageIO.read(new File("Bruit_de_Perlin220x220.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		this.dx=dx;
		this.dy=dy;
		terrain = new int[dy][dx][3];
		int taille_y=((int) (Math.random()*(Math.max(0,image.getHeight()-dy))));
		int taille_x=((int) (Math.random()*(Math.max(0,image.getHeight()-dx))));
		
		for (int i=taille_y;i<Math.min(taille_y+dy,image.getHeight());i++) {
			for (int j=taille_x;j<Math.min(taille_x+dx,image.getWidth());j++) {
				Color c = new Color(image.getRGB(i, j));
				int couleur = c.getRed();
				terrain[i-taille_y][j-taille_x][0]=couleur; //Altitude
				terrain[i-taille_y][j-taille_x][1]=couleur; //Type de terrain(biome)
				terrain[i-taille_y][j-taille_x][2]=0; //Si il y a un arbre ou pas
			}
		}
	}
	
	public static int[][][] getTerrain() {
		return terrain;
	}
	public void eruption() {
		for(int i = 0; i < dy ; i++) {
			for(int j = 0; j < dx; j++) {
				if(Terrain.getTerrain()[i][j][0] >=(Terrain.sommetVolcan() - 1)) { //Pour un haut altitude
					Terrain.getTerrain()[i][j][1] =  Terrain.SolLave(); //Pour debuter ecoulement de lave 
				}	
			}
		}
	}
	
	public void propagation_lave() {
		for(int i = 0; i < dy ; i++) {
			for(int j = 0; j < dx; j++) {
				if (((Terrain.getTerrain()[i][j][0] <= Terrain.getTerrain()[(i-1+dy)%dy][j][0] && (Terrain.getTerrain()[(i-1+dy)%dy][j][1] == Terrain.SolLave())) 
				|| (Terrain.getTerrain()[i][j][0] <= Terrain.getTerrain()[(i+1+dy)%dy][j][0] && (Terrain.getTerrain()[(i+1+dy)%dy][j][1] == Terrain.SolLave())
				|| (Terrain.getTerrain()[i][j][0] <= Terrain.getTerrain()[i][(j+1+dx)%dx][0] && (Terrain.getTerrain()[i][(j+1+dx)%dx][1] == Terrain.SolLave()))
				|| (Terrain.getTerrain()[i][j][0] <= Terrain.getTerrain()[i][(j-1+dx)%dx][0] && (Terrain.getTerrain()[i][(j-1+dx)%dx][1] == Terrain.SolLave()))))) { //si l'altitude est moins élevé
					
					if(Math.random() <= 0.1) {
						Terrain.getTerrain()[i][j][1] = Terrain.SolLave(); //coulé de lave
					}
				}
			}
		}
	}
	public void partir_lave() {
		for(int i = 0; i < dy ; i++) {
			for(int j = 0; j < dx; j++) {
				if(Terrain.getTerrain()[i][j][1] == Terrain.SolLave() && Terrain.getTerrain()[i][j][0] <= Terrain.contourRoche()) {
					Terrain.getTerrain()[i][j][1]=Terrain.getTerre(); //devient de la terre
				}
				else {
					if(Terrain.getTerrain()[i][j][1] == 255 && Terrain.getTerrain()[i][j][0] >= Terrain.contourRoche()) {	
						Terrain.getTerrain()[i][j][1]=Terrain.getTerrain()[i][j][0]; //la lave redevient solide
					}
				}
			}
		}
	}
	public void MonteEau(boolean pluie) {  //L'eau monte lorsqu'il pleut
		if(pluie) {
			for(int i = 0; i < dy ; i++) {
				for(int j = 0; j < dx; j++) {
					if((Terrain.getTerrain()[i][j][1] < Terrain.contourRoche()) && ((Terrain.getTerrain()[(i+1+dy)%dy][j][1]<Terrain.getEau()) 
							|| Terrain.getTerrain()[(i-1+dy)%dy][j][1]<Terrain.getEau() 
							|| Terrain.getTerrain()[i][(j+1+dx)%dx][1]<Terrain.getEau() 
							|| Terrain.getTerrain()[i][(j-1+dx)%dx][1]<Terrain.getEau()) && (Terrain.getTerrain()[i][j][1]>=Terrain.getEau())) {
						if(Math.random() < 0.2) {
							Terrain.getTerrain()[i][j][1]=Terrain.getTerrain()[i][j][1]-5;
						}
					}
				}
			}
		}
	}
	public void evaporeLave(boolean pluie) { //La lave s'evapore lorsqu'il pleut
		if(pluie) {
			for(int i = 0; i < dy ; i++) {
				for(int j = 0; j < dx; j++) {
					if((Terrain.getTerrain()[i][j][1] == Terrain.SolLave()) && ((Terrain.getTerrain()[(i+1+dy)%dy][j][1]!=Terrain.SolLave()) 
							|| Terrain.getTerrain()[(i-1+dy)%dy][j][1]!=Terrain.SolLave() 
							|| Terrain.getTerrain()[i][(j+1+dx)%dx][1]!=Terrain.SolLave() 
							|| Terrain.getTerrain()[i][(j-1+dx)%dx][1]!=Terrain.SolLave())) {
						if(Math.random() < 0.05) {
							Terrain.getTerrain()[i][j][1]=Terrain.getTerrain()[i][j][0]; 
						}
					}
				}
			}
		}
	}
	public void herbePoussant(){
		for(int i = 0; i < dy ; i++) {
			for(int j = 0; j < dx; j++) {
				if(Terrain.getTerrain()[i][j][1] >= Terrain.getTerre() - 2 && Terrain.getTerrain()[i][j][1] <= Terrain.getTerre() + 2) {
					if(Math.random() < 0.05) {
						Terrain.getTerrain()[i][j][1]=grass(); 
					}
				}
			}
		}
	}
	public void evaporeEau(boolean ete) {
		if(ete) {
			for(int i = 0; i < dy ; i++) {
				for(int j = 0; j < dx; j++) {
					if((Terrain.getTerrain()[i][j][1]<Terrain.getEau()) && ((Terrain.getTerrain()[(i+1+dy)%dy][j][1]>=Terrain.getEau()) 
							|| Terrain.getTerrain()[(i-1+dy)%dy][j][1]>=Terrain.getEau() 
							|| Terrain.getTerrain()[i][(j+1+dx)%dx][1]>=Terrain.getEau() 
							|| Terrain.getTerrain()[i][(j-1+dx)%dx][1]>=Terrain.getEau()) && (Terrain.getTerrain()[i][j][0]>=Terrain.getEau() - 10)) {
						if(Math.random() < 0.05) {
							Terrain.getTerrain()[i][j][1]=grass(); 
						}
					}
				}
			}
		}
	}
	public static boolean getPluie() {
		return pluie;
	}
	public void setPluie(boolean pluie) {
		Terrain.pluie=pluie;
	}
	public static int getTerre() {
		return 207;
	}
	public static int getEau() {
		return 180;
	}
	public static int contourRoche() {
		return 239;
	}
	public static int sommetVolcan() {
		return 248;
	}
	public static int SolLave() {
		return 255;
	}
	public static int grass(){
		return 220;
	}
}