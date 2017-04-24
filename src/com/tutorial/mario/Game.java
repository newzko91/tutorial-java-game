package com.tutorial.mario;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import com.tutorial.mario.entity.Player;
import com.tutorial.mario.input.KeyInput;
import com.tutorial.mario.tile.Wall;

public class Game extends Canvas implements Runnable {
	
	
	public static final int WIDTH = 250;
	public static final int HEIGHT = WIDTH/14*10;
	public static final int SCALE = 4;
	public static String TITLE = "Mario";
	private Thread thread;
	private boolean running = false; 
	public static Handler handler;
	
	//método main q "desenha" a janela principal
		public static void main(String[] args) {
			Game game = new Game(); //instancia um objeto game do tipo Game.
			JFrame frame = new JFrame(TITLE); //Instancia um obteto frame do tipo JFrame e define o titulo da janela.
			frame.add(game); //adiciona o objeto game do tipo Game ao frame
			frame.pack(); //The pack method sizes the frame so that all its contents are at or above their preferred sizes |  ocupem o menor espaço possível
			frame.setResizable(false); //não é possivel redimensionar a tela
			frame.setLocationRelativeTo(null); //centralizar a janela
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true); //frame estará visivel
			
			game.start();
		
		}
		
		//Construtor para definir os parametros de tamanho (dimensão) Altura, Largura e Escala
		public Game() {
			Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
			setPreferredSize(size);
			setMaximumSize(size);
			setMinimumSize(size);
		}
		
		private void init() {
			handler = new Handler();
			
			addKeyListener(new KeyInput());
			
			//Adicionar um jogador na tela
			handler.addEntity(new Player(300,512,64,64,true,Id.player,handler));
			
		}
	
	//para auxiliar a manter o jogo rodando 
	private synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this, "Thread");
		thread.start();
	}
	
	//para auxiliar a manter o jogo rodando 
	private synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join(); //method can be used to pause the current thread execution until unless the specified thread is dead. 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {	
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.0;
		double ns = 1000000000.0/60.0; //1 nanosegundo 
		int frames = 0;
		int ticks = 0; 
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta>=1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis()-timer>1000) { //which will occur once every second
				timer+=1000;
				System.out.println(frames + " Frames Per Second " + ticks + " Updates Per Second");
				frames = 0;
				ticks = 0;
			}	
		}
		stop();
	}
	
	//desenhar na tela
	public void render() {	
		BufferStrategy bs = getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics(); 
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0 , getWidth(), getHeight());
		handler.render(g);
		g.dispose();
		bs.show();
	}
	
	//ticks same as > update <
	public void tick() {	
		handler.tick();
	}
}
	
