package Sprite;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Mobs.Braconnier;
import Mobs.M;
import Mobs.M1;
import Mobs.M2;
import Mobs.Pomme;
import Monde.Monde;
import Monde.Terrain;

public class SpriteDemo extends JPanel implements KeyListener,MouseWheelListener{

	private JFrame frame;
	
	public static int dx;
	public static int dy;
	private int x;
	private int y;
	private static int spriteLength = 40;
	private static int pas = 0;
	private static int marcher = 0;
	private static int cpt_pas = 0;
	private static int step;
	private static int cycle_volcan=0;
	private static int cycle_pluie=0;
	private int a1;
	private int a2;
	private int wx;
	private int wy;
	private Image waterSprite;
	private Image grassSprite;
	private Image tSprite;
	private Image arbrecrame;
	private Image terreSprite;
	private Image[] PokemonFeuMove;
	private Image[] PokemonFeuEvolueMove;
	private Image[] PokemonEauMove;
	private Image[] PokemonEauEvolueMove;
	private Image Apple;
	private Image ApplePourri;
	private Image[] Chasseur;
	private Image[] ChasseurSurEau;
	private Image TombeRIP;
	private Image reliefgauche;
	private Image reliefdroit;
	private Image reliefbas;
	private Image reliefhaut;
	private Image smoke;
	
	private Image Flamme;
	private Image rochevolcan;
	private Image Lave;
	private Image pluie;
	public int vitesse;
	private Image grassSpriteH;
	private Image grassSpriteA;
	private Image tSpriteH;
	private Image tSpriteA;
	private Image nuitF;
	private Image soleilF;
	private static int jour;
	private long time_init;
	private long laps;
	private boolean duree;
	private static boolean arbreEnCroissance = false;


	public SpriteDemo()
	{
		try
		{
			smoke = Toolkit.getDefaultToolkit().createImage("smoke.gif");
			reliefhaut = ImageIO.read(new File("reliefhaut.png"));
			reliefbas = ImageIO.read(new File("reliefbas.png"));
			reliefdroit = ImageIO.read(new File("reliefdroit.png"));
			reliefgauche = ImageIO.read(new File("reliefgauche.png"));
			TombeRIP = ImageIO.read(new File("tete-de-mort.png"));
			waterSprite = ImageIO.read(new File("water.png"));
			grassSprite = ImageIO.read(new File("herbeP.png"));
			grassSpriteH = ImageIO.read(new File("herbePN.png"));
			grassSpriteA = ImageIO.read(new File("herbePA.png"));
			tSprite = ImageIO.read(new File("test1.png"));
			tSpriteH = ImageIO.read(new File("test1N.png"));
			tSpriteA = ImageIO.read(new File("test1A.png"));
			arbrecrame = ImageIO.read(new File("test1crame.png"));
			terreSprite = ImageIO.read(new File("terre.png"));
			Apple = ImageIO.read(new File("pomme.png"));
			ApplePourri = ImageIO.read(new File("pommeP.png"));
			Flamme = Toolkit.getDefaultToolkit().createImage("Flamme.gif");
			rochevolcan = ImageIO.read(new File("roche1.png"));
			Lave = ImageIO.read(new File("lava.png"));
			pluie = Toolkit.getDefaultToolkit().createImage("Pluie.gif");
			nuitF=ImageIO.read(new File("NuitF.png"));
			soleilF=ImageIO.read(new File("SoleilF.png"));
			time_init= (System.nanoTime()/1000000000);
			jour=0;
			duree=false;
			
			Chasseur = new Image[4]; //Chasseur
			Chasseur[0] = Toolkit.getDefaultToolkit().createImage("character_walkleft.gif");
			Chasseur[1] = Toolkit.getDefaultToolkit().createImage("character_walkright.gif");
			Chasseur[2] = Toolkit.getDefaultToolkit().createImage("character_walkup.gif");
			Chasseur[3] = Toolkit.getDefaultToolkit().createImage("character_walkdown.gif");
			
			ChasseurSurEau = new Image[4]; //Chasseur sur eau
			ChasseurSurEau[0] = ImageIO.read(new File("chasseursureaugauche.png"));
			ChasseurSurEau[1] = ImageIO.read(new File("chasseursureaudroite.png"));
			ChasseurSurEau[2] = ImageIO.read(new File("chasseursureaubas.png"));
			ChasseurSurEau[3] = ImageIO.read(new File("chasseursureauhaut.png"));
			
			PokemonFeuMove = new Image[4]; // Hericendre 
			PokemonFeuMove[0] = Toolkit.getDefaultToolkit().createImage("Hericendrewalkleft.gif"); //Va a gauche
			PokemonFeuMove[1] = Toolkit.getDefaultToolkit().createImage("Hericendrewalkright.gif"); //Va a droite
			PokemonFeuMove[2] = Toolkit.getDefaultToolkit().createImage("Hericendrewalkdown.gif"); //Va en bas
			PokemonFeuMove[3] = Toolkit.getDefaultToolkit().createImage("Hericendrewalkup.gif"); //Va en haut
			
			PokemonFeuEvolueMove = new Image[4]; // Feurisson 
			PokemonFeuEvolueMove[0] = Toolkit.getDefaultToolkit().createImage("Feurissonwalkleft.gif"); //Va a gauche
			PokemonFeuEvolueMove[1] = Toolkit.getDefaultToolkit().createImage("Feurissonwalkright.gif"); //Va a droite
			PokemonFeuEvolueMove[2] = Toolkit.getDefaultToolkit().createImage("Feurissonwalkdown.gif"); //Va en bas
			PokemonFeuEvolueMove[3] = Toolkit.getDefaultToolkit().createImage("Feurissonwalkup.gif"); //Va en haut
			
			PokemonEauMove = new Image[4]; // Carapuce 
			PokemonEauMove[0] = Toolkit.getDefaultToolkit().createImage("Carapucewalkleft.gif"); //Va a gauche
			PokemonEauMove[1] = Toolkit.getDefaultToolkit().createImage("Carapucewalkright.gif"); //Va a droite
			PokemonEauMove[2] = Toolkit.getDefaultToolkit().createImage("Carapucewalkdown.gif"); //Va en bas
			PokemonEauMove[3] = Toolkit.getDefaultToolkit().createImage("Carapucewalkup.gif"); //Va en haut
			
			PokemonEauEvolueMove = new Image[4]; // Carabaffe 
			PokemonEauEvolueMove[0] = Toolkit.getDefaultToolkit().createImage("Carabaffewalkleft.gif"); //Va a gauche
			PokemonEauEvolueMove[1] = Toolkit.getDefaultToolkit().createImage("Carabaffewalkright.gif"); //Va a droite
			PokemonEauEvolueMove[2] = Toolkit.getDefaultToolkit().createImage("Carabaffewalkdown.gif"); //Va en bas
			PokemonEauEvolueMove[3] = Toolkit.getDefaultToolkit().createImage("Carabaffewalkup.gif"); //Va en haut

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		frame = new JFrame("World of Sprite");
		frame.add(this);
		x=dx*spriteLength;
		y=dy*spriteLength;
		frame.setSize(x,y+37);
		frame.setVisible(true);
		vitesse=30;
		a1=0;
		a2=0;
		wx=dx;
		wy=dy;
	}

	public void paint(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		for ( int i = a1 ; i < Math.min(dx*spriteLength, wx-a1) ; i++ ) {
			for ( int j = a2 ; j < Math.min(dy*spriteLength,wy-a2) ; j++ ) {
					try{
						if (jour%12 < 3){
							arbreEnCroissance = true;
							g2.drawImage(grassSprite,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
						}
						if (jour%12 >=3 && jour%12<6){
							arbreEnCroissance = false;
							g2.drawImage(grassSprite,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
						}
						if (jour%12>=6 && jour%12<9){
							arbreEnCroissance = false;
							g2.drawImage(grassSpriteA,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
						}
						if (jour%12>=9){
							arbreEnCroissance = false;
							g2.drawImage(grassSpriteH,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
						}
					if (Terrain.getTerrain()[j][i][1] >= (Terrain.getTerre() - 2) && Terrain.getTerrain()[j][i][1] <= (Terrain.getTerre()+2)) {
						g2.drawImage(terreSprite,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
					}
					if (Terrain.getTerrain()[j][i][1] < Terrain.getEau()) {
						g2.drawImage(waterSprite,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
					}
					if(Terrain.getTerrain()[j][i][1] >= Terrain.getEau() && Terrain.getTerrain()[j][i][1] < Terrain.contourRoche()) {
						if(Terrain.getTerrain()[(j+1+dy)%dy][i][0]/20 > Terrain.getTerrain()[j][i][0]/20) {
							g2.drawImage(reliefhaut,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
						}
						if(Terrain.getTerrain()[(j-1+dy)%dy][i][0]/20 > Terrain.getTerrain()[j][i][0]/20) {
							g2.drawImage(reliefbas,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
						}
						if(Terrain.getTerrain()[j][(i+1+dx)%dx][0]/20 < Terrain.getTerrain()[j][i][0]/20) {
							g2.drawImage(reliefgauche,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
						}
						if(Terrain.getTerrain()[j][(i+1+dx)%dx][0]/20 > Terrain.getTerrain()[j][i][0]/20) {
							g2.drawImage(reliefdroit,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
						}
					}
					
					for (int a=0;a<Monde.getcarte_Ab().size();a++) {
						if (Monde.getcarte_Ab().get(a).getX()==i && Monde.getcarte_Ab().get(a).getY()==j) {
							if (Monde.getcarte_Ab().get(a).isEnfeu()) {
								g2.drawImage(arbrecrame,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
								g2.drawImage(Flamme,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
							}
							else {
								if (Monde.getcarte_Ab().get(a).getGrille()) {
									g2.drawImage(arbrecrame,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}else {
									if (jour%12 < 6)
										g2.drawImage(tSprite,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
									if (jour%12>=6 && jour%12<9)
										g2.drawImage(tSpriteA,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
									if (jour%12>=9)
										g2.drawImage(tSpriteH,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
							}
						}
					}
					
					if (Terrain.getTerrain()[j][i][0] >= Terrain.contourRoche() && Terrain.getTerrain()[j][i][0] < Terrain.sommetVolcan()) {
						if(Terrain.getTerrain()[j][i][0] < (Terrain.sommetVolcan()-7)) {
							if(Terrain.getTerrain()[j][i][0] == Terrain.contourRoche()) {
								g2.drawImage(rochevolcan,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
							}
							else {
								g2.drawImage(rochevolcan,spriteLength*(i-a1)-25,spriteLength*(j-a2)-10,spriteLength+25,spriteLength+10, frame);
							}
						}else {
							g2.drawImage(rochevolcan,spriteLength*(i-a1)-10,spriteLength*(j-a2)-25,spriteLength+10,spriteLength+25, frame);
						}
					}
					}catch(Exception E) {
						
					}
					for (int p=0;p<Monde.getcarte_P().size();p++) {
						try{
						if (Monde.getcarte_P().get(p).getX()==i && Monde.getcarte_P().get(p).getY()==j) {
							if (Monde.getcarte_P().get(p).isEstPourrie() == false)
								g2.drawImage(Apple,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength-10,spriteLength-10, frame);
							if (Monde.getcarte_P().get(p).isEstPourrie() == true)
								g2.drawImage(ApplePourri,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength-10,spriteLength-10, frame);		
						}}catch(Exception E) {
							
						}
					}
			}
		}
		for ( int i = a1 ; i < wx ; i++ ) {
			for ( int j = a2 ; j < wy ; j++ ) {
				try {
					if(Terrain.getTerrain()[j][i][0] >= (Terrain.contourRoche()) && Terrain.getTerrain()[j][i][0] < (Terrain.sommetVolcan())) {
						g2.drawImage(rochevolcan,spriteLength*(i-a1)-35,spriteLength*(j-a2)-15,spriteLength+35,spriteLength+15, frame);
					}
					
				}catch(Exception e) {}
			}
		}
		for ( int i = a1 ; i < wx ; i++ ) {
			for ( int j = a2 ; j < wy ; j++ ) {
				try {
					if(Terrain.getTerrain()[j][i][0] >= (Terrain.contourRoche() + 3) && Terrain.getTerrain()[j][i][0] < (Terrain.sommetVolcan())) {
						g2.drawImage(rochevolcan,spriteLength*(i-a1)-35,spriteLength*(j-a2)-15,spriteLength+35,spriteLength+15, frame);
					}
					
				}catch(Exception e) {}
			}
		}
		for ( int i = a1 ; i < wx ; i++ ) {
			for ( int j = a2 ; j < wy ; j++ ) {
				try {
					if(Terrain.getTerrain()[j][i][0] >= (Terrain.contourRoche() + 5) && Terrain.getTerrain()[j][i][0] <= (Terrain.sommetVolcan())) {
						g2.drawImage(rochevolcan,spriteLength*(i-a1)-35,spriteLength*(j-a2)-15,spriteLength+35,spriteLength+15, frame);
					}
					
				}catch(Exception e) {}
			}
		}
		for ( int i = a1 ; i < wx ; i++ ) {
			for ( int j = a2 ; j < wy ; j++ ) {
				try {
					if(Terrain.getTerrain()[j][i][1] == Terrain.SolLave()) {
						g2.drawImage(Lave,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
					}
					
				}catch(Exception e) {}
			}
		}
		for ( int i = a1 ; i < wx ; i++ ) {
			for ( int j = a2 ; j < wy ; j++ ) {
				try {
					if(Terrain.getTerrain()[j][i][1] == Terrain.SolLave()) {
						if(Terrain.getPluie() && ((Terrain.getTerrain()[(i+1+dy)%dy][j][1]!=Terrain.SolLave()) 
								|| Terrain.getTerrain()[(i-1+dy)%dy][j][1]!=Terrain.SolLave() 
								|| Terrain.getTerrain()[i][(j+1+dx)%dx][1]!=Terrain.SolLave()
								|| Terrain.getTerrain()[i][(j-1+dx)%dx][1]!=Terrain.SolLave())){
							g2.drawImage(smoke,spriteLength*(i-a1)-17,spriteLength*(j-a2)-50,spriteLength+35,spriteLength+50, frame);
						}
					}
					
				}catch(Exception e) {}
			}
		}
		for ( int i = a1 ; i < wx ; i++ ) {
			for ( int j = a2 ; j < wy ; j++ ){
					ArrayList<Object> array_m=Monde.getcarte_Ag();
					for (int m=0;m<array_m.size();m++) {
						try {
						if ((array_m.get(m) instanceof M1) && (((M1)array_m.get(m)).getX()==i && ((M1)array_m.get(m)).getY()==j)) {
							M1 Hericendre = (M1)(array_m.get(m));
							if(Hericendre.getMort()) {
								g2.drawImage(TombeRIP,spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
							}else {
							if(Hericendre.getEvolution() == false) {
								if ( Hericendre.getSens() == 0 ) { //va a gauche
									g2.drawImage(PokemonFeuMove[0],spriteLength*(i-a1) - SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 1 ) { //va a droite
									g2.drawImage(PokemonFeuMove[1],spriteLength*(i-a1) + SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 2 ) { //va en bas
									g2.drawImage(PokemonFeuMove[2],spriteLength*(i-a1) ,spriteLength*(j-a2) + SpriteDemo.marcher,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 3 ) { //va en haut
									g2.drawImage(PokemonFeuMove[3],spriteLength*(i-a1) ,spriteLength*(j-a2) - SpriteDemo.marcher,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 4 ) { //reste sur place
									g2.drawImage(PokemonFeuMove[0],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 5 ) { //reste sur place
									g2.drawImage(PokemonFeuMove[1],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 6 ) { //reste sur place
									g2.drawImage(PokemonFeuMove[2],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 7 ) { //reste sur place
									g2.drawImage(PokemonFeuMove[3],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
							}
							if(Hericendre.getEvolution() == true) {
								
								if ( Hericendre.getSens() == 0 ) { //va a gauche
									g2.drawImage(PokemonFeuEvolueMove[0],spriteLength*(i-a1) - SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								
								if ( Hericendre.getSens() == 1 ) { //va a droite
									g2.drawImage(PokemonFeuEvolueMove[1],spriteLength*(i-a1) + SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 2 ) { //va en bas
									g2.drawImage(PokemonFeuEvolueMove[2],spriteLength*(i-a1) ,spriteLength*(j-a2) + SpriteDemo.marcher,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 3 ) { //va en haut
									g2.drawImage(PokemonFeuEvolueMove[3],spriteLength*(i-a1) ,spriteLength*(j-a2) - SpriteDemo.marcher,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 4 ) { //reste sur place
									g2.drawImage(PokemonFeuEvolueMove[0],spriteLength*(i-a1) ,spriteLength*(j-a2) ,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 5 ) { //reste sur place
									g2.drawImage(PokemonFeuEvolueMove[1],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 6 ) { //reste sur place
									g2.drawImage(PokemonFeuEvolueMove[2],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 7 ) { //reste sur place
									g2.drawImage(PokemonFeuEvolueMove[3],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
							}}
							continue;
						}}catch(Exception e) {}
						try {
						if ((array_m.get(m) instanceof M2) && (((M2)array_m.get(m)).getX()==i && ((M2)array_m.get(m)).getY()==j)) {
							M2 Carapuce = (M2)(array_m.get(m));
							
							if(Carapuce.getMort()) {
								g2.drawImage(TombeRIP,spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
							}else {
							if(Carapuce.getEvolution() == false) {
								if ( Carapuce.getSens() == 0 ) { //va a gauche
									g2.drawImage(PokemonEauMove[0],spriteLength*(i-a1) - SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 1 ) { //va a droite
									g2.drawImage(PokemonEauMove[1],spriteLength*(i-a1) + SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 2 ) { //va en bas
									g2.drawImage(PokemonEauMove[2],spriteLength*(i-a1) ,spriteLength*(j-a2) + SpriteDemo.marcher,spriteLength-((int)spriteLength/5),spriteLength, frame);
								}
								if ( Carapuce.getSens() == 3 ) { //va en haut
									g2.drawImage(PokemonEauMove[3],spriteLength*(i-a1) ,spriteLength*(j-a2) - SpriteDemo.marcher,spriteLength-((int)spriteLength/5),spriteLength, frame);
								}	
								if ( Carapuce.getSens() == 4 ) { //reste sur place
									g2.drawImage(PokemonEauMove[0],spriteLength*(i-a1) ,spriteLength*(j-a2) ,spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 5 ) { //reste sur place
									g2.drawImage(PokemonEauMove[1],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 6 ) { //reste sur place
									g2.drawImage(PokemonEauMove[2],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 7 ) { //reste sur place
									g2.drawImage(PokemonEauMove[3],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
							}
							if(Carapuce.getEvolution() == true) {
								if ( Carapuce.getSens() == 0 ) { //va a gauche
									g2.drawImage(PokemonEauEvolueMove[0],spriteLength*(i-a1) - SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								
								if ( Carapuce.getSens() == 1 ) { //va a droite
									g2.drawImage(PokemonEauEvolueMove[1],spriteLength*(i-a1) + SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 2 ) { //va en bas
									g2.drawImage(PokemonEauEvolueMove[2],spriteLength*(i-a1) ,spriteLength*(j-a2) + SpriteDemo.marcher,spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 3 ) { //va en haut
									g2.drawImage(PokemonEauEvolueMove[3],spriteLength*(i-a1) ,spriteLength*(j-a2) - SpriteDemo.marcher,spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 4 ) { //reste sur place
									g2.drawImage(PokemonEauEvolueMove[0],spriteLength*(i-a1) ,spriteLength*(j-a2) ,spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 5 ) { //reste sur place
									g2.drawImage(PokemonEauEvolueMove[1],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 6 ) { //reste sur place
									g2.drawImage(PokemonEauEvolueMove[2],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 7 ) { //reste sur place
									g2.drawImage(PokemonEauEvolueMove[3],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
							}}
							continue;
						}}catch(Exception e) {}
						try {
						if (array_m.get(m) instanceof Braconnier && ((Braconnier)array_m.get(m)).getX()==i && ((Braconnier)array_m.get(m)).getY()==j) {
							Braconnier braconnier = (Braconnier)(array_m.get(m));
							if(Terrain.getTerrain()[braconnier.getY()][braconnier.getX()][1] >= Terrain.getEau()) {
							if ( braconnier.getSens() == 0 ) { //va a gauche
								g2.drawImage(Chasseur[0],spriteLength*(i-a1)-(spriteLength/5) - SpriteDemo.marcher,spriteLength*(j-a2),spriteLength+(spriteLength/5),spriteLength+(spriteLength/5), frame);
							}
							if ( braconnier.getSens() == 1 ) { //va a droite
								g2.drawImage(Chasseur[1],spriteLength*(i-a1)-(spriteLength/5) + SpriteDemo.marcher,spriteLength*(j-a2),spriteLength+(spriteLength/5),spriteLength+(spriteLength/5), frame);
							}
							if ( braconnier.getSens() == 2 ) { //va en bas
								g2.drawImage(Chasseur[3],spriteLength*(i-a1)-(spriteLength/5) ,spriteLength*(j-a2)-(spriteLength/5) + SpriteDemo.marcher,spriteLength-((int)spriteLength/5)+(spriteLength/5),spriteLength+(spriteLength/5), frame);
							}
							if ( braconnier.getSens() == 3 ) { //va en haut
								g2.drawImage(Chasseur[2],spriteLength*(i-a1)-(spriteLength/5) ,spriteLength*(j-a2)-(spriteLength/5) - SpriteDemo.marcher,spriteLength-((int)spriteLength/5)+(spriteLength/5),spriteLength+(spriteLength/5), frame);
							}
							if ( braconnier.getSens() == 4 ) { //reste sur place
								g2.drawImage(Chasseur[0],spriteLength*(i-a1)-(spriteLength/5) ,spriteLength*(j-a2),spriteLength+(spriteLength/5),spriteLength+(spriteLength/5), frame);
							}
							if ( braconnier.getSens() == 5 ) { //reste sur place
								g2.drawImage(Chasseur[1],spriteLength*(i-a1)-(spriteLength/5) ,spriteLength*(j-a2),spriteLength+(spriteLength/5),spriteLength+(spriteLength/5), frame);
							}
							if ( braconnier.getSens() == 6 ) { //reste sur place
								g2.drawImage(Chasseur[3],spriteLength*(i-a1)-(spriteLength/5) ,spriteLength*(j-a2)-(spriteLength/5),spriteLength-((int)spriteLength/5)+(spriteLength/5),spriteLength+(spriteLength/5), frame);
							}
							if ( braconnier.getSens() == 7 ) { //reste sur place
								g2.drawImage(Chasseur[2],spriteLength*(i-a1)-(spriteLength/5) ,spriteLength*(j-a2)-(spriteLength/5),spriteLength-((int)spriteLength/5)+(spriteLength/5),spriteLength+(spriteLength/5), frame);
							}
							}else {
								if ( braconnier.getSens() == 0 ) { //va a gauche
									g2.drawImage(ChasseurSurEau[0],spriteLength*(i-a1)-(spriteLength/5) - SpriteDemo.marcher,spriteLength*(j-a2),spriteLength+(spriteLength/5),spriteLength+(spriteLength/5), frame);
								}
								if ( braconnier.getSens() == 1 ) { //va a droite
									g2.drawImage(ChasseurSurEau[1],spriteLength*(i-a1)-(spriteLength/5) + SpriteDemo.marcher,spriteLength*(j-a2),spriteLength+(spriteLength/5),spriteLength+(spriteLength/5), frame);
								}
								if ( braconnier.getSens() == 2 ) { //va en bas
									g2.drawImage(ChasseurSurEau[2],spriteLength*(i-a1)-(spriteLength/5) ,spriteLength*(j-a2)-(spriteLength/5) + SpriteDemo.marcher,spriteLength-((int)spriteLength/5)+(spriteLength/5),spriteLength+(spriteLength/5), frame);
								}
								if ( braconnier.getSens() == 3 ) { //va en haut
									g2.drawImage(ChasseurSurEau[3],spriteLength*(i-a1)-(spriteLength/5) ,spriteLength*(j-a2)-(spriteLength/5) - SpriteDemo.marcher,spriteLength-((int)spriteLength/5)+(spriteLength/5),spriteLength+(spriteLength/5), frame);
								}
								if ( braconnier.getSens() == 4 ) { //reste sur place
									g2.drawImage(ChasseurSurEau[0],spriteLength*(i-a1)-(spriteLength/5) ,spriteLength*(j-a2),spriteLength+(spriteLength/5),spriteLength+(spriteLength/5), frame);
								}
								if ( braconnier.getSens() == 5 ) { //reste sur place
									g2.drawImage(ChasseurSurEau[1],spriteLength*(i-a1)-(spriteLength/5) ,spriteLength*(j-a2),spriteLength+(spriteLength/5),spriteLength+(spriteLength/5), frame);
								}
								if ( braconnier.getSens() == 6 ) { //reste sur place
									g2.drawImage(ChasseurSurEau[3],spriteLength*(i-a1)-(spriteLength/5) ,spriteLength*(j-a2)-(spriteLength/5),spriteLength-((int)spriteLength/5)+(spriteLength/5),spriteLength+(spriteLength/5), frame);
								}
								if ( braconnier.getSens() == 7 ) { //reste sur place
									g2.drawImage(ChasseurSurEau[2],spriteLength*(i-a1)-(spriteLength/5) ,spriteLength*(j-a2)-(spriteLength/5),spriteLength-((int)spriteLength/5)+(spriteLength/5),spriteLength+(spriteLength/5), frame);
								}
							}
							continue;
						}}catch(Exception e) {}
				}
			}
		}
		if(Terrain.getPluie()) {
			for ( int i = 0 ; i < wx ; i+=5) {
				for ( int j = 0 ; j < wy ; j+=5 ) {
					g2.drawImage(pluie,spriteLength*i,spriteLength*j,spriteLength*10,spriteLength*10, frame);
				}
			}
		}
		
		if (jour%12 >=3 && jour%12<6)
			g2.drawImage(soleilF,0,0,spriteLength*wx,spriteLength*wy, frame);
		
		laps= Math.abs((time_init-(System.nanoTime()/1000000000)));
		if (laps%10<5) {
			g2.drawImage(nuitF,0,0,spriteLength*wx,spriteLength*wy, frame);
			duree=true;
		}else {
			if (duree)
				jour+=1;
			duree=false;
		}
	}
	@Override
	public void keyPressed(KeyEvent evmt) {
		int source =evmt.getKeyCode();
		if (source == KeyEvent.VK_RIGHT) {
			if (vitesse == 20)
				vitesse=10;
			if (vitesse == 30)
				vitesse=20;
			
		}
		if (source == KeyEvent.VK_LEFT) {
			if (vitesse == 20)
				vitesse=30;
			if (vitesse == 10)
				vitesse=20;			
		}
		if (source == 107) {
			spriteLength+=1;

			x=dx*spriteLength;
			y=dy*spriteLength;
			frame.setSize(x,y+37);
			frame.setVisible(true);
		}
		if (source == 109) {
			spriteLength-=1;
			x=dx*spriteLength;
			y=dy*spriteLength;
			frame.setSize(x,y+37);
			frame.setVisible(true);
		}
		if (source == KeyEvent.VK_Z) {
			if (a2>0) {
				a2-=1;
				wy-=1;	
			}
		}
		if (source == KeyEvent.VK_S) {
			if ((wy-a2)*spriteLength > this.getBounds().height+(a2+1)*spriteLength) {
				a2+=1;
				wy+=1;				
			}			
		}
		if (source == KeyEvent.VK_Q) {
			if (a1>0) {
				a1-=1;
				wx-=1;
			}
		}
		if (source == KeyEvent.VK_D) {
			if ((wx-a1)*spriteLength >= this.getBounds().width+(a1+1)*spriteLength) {
				a1+=1;
				wx+=1;				
			}
		}
}						
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
             spriteLength+=1;  // scroll ver le haut 
        } else {
        	if (((wx-a1)*spriteLength >= this.getBounds().width+(a1+1)*spriteLength) && ((wy-a2)*spriteLength > this.getBounds().height+(a2+1)*spriteLength)) {
        		spriteLength-=1; // scroll ver le haut 
        	}     	
        }        
    } 
	
	public static void main(String[] args) {
		Terrain terrain= new Terrain(dx=100,dy=100);
		Monde monde = new Monde(dx,dy,0,0.1,0.3);
		SpriteDemo a =new SpriteDemo();
        a.addKeyListener(a);
        a.addMouseWheelListener(a);
        a.setFocusable(true);
		cpt_pas = 0;
		marcher = 0;
		step = 0;
		while(true) {
			if(pas < 7) {
				a.repaint();
	            pas += 1;
			}
			else {
				a.repaint();
				pas = 0;
			}
			if(cpt_pas % 8 == 0) {
				if(!Terrain.getPluie()) {
					cycle_volcan++;
				}else {
					terrain.evaporeLave(Terrain.getPluie());
				}
				monde.tree_pop(arbreEnCroissance);
				monde.arbre_prend_feu();
				monde.pomme_pop(0);
				Pomme.duree();
				Pomme.delete();
				monde.Refresh();
				cpt_pas = 0;
				marcher = 0;
				
				Monde.grandir();
				M.reproduction();
				monde.pop_Braconnier(step);
				monde.partir_braconnier(step);
				terrain.evaporeEau((jour%12 >=3 && jour%12<6) && (Terrain.getPluie() == false));
				terrain.MonteEau(Terrain.getPluie());
				terrain.herbePoussant();
				if(cycle_volcan % 100 == 0) {
					terrain.partir_lave();
				}
				if(cycle_pluie % 130 == 0 && Math.random() <= 0.8) {
					terrain.setPluie(true);;
				}
				if(cycle_pluie % 180 == 0) {
					terrain.setPluie(false);
					cycle_pluie = 1;
				}
				if(!Terrain.getPluie()) {
					terrain.propagation_lave();
					terrain.eruption();
				}
				monde.arbreMourir();
				monde.depart_feu();
				monde.propagation_F();
				monde.enfeu();
				cycle_pluie++;
			}
			marcher += (int)(spriteLength/8);
			try{
				Thread.sleep(a.vitesse); // en ms
			}catch(Exception e){
				e.printStackTrace();
			}
			cpt_pas += 1;
			step++;
		}
	}

}
