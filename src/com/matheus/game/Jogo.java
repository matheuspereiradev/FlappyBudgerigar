package com.matheus.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import com.matheus.entidades.*;
import com.matheus.graficos.Spritesheet;
import com.matheus.graficos.UI;
import com.matheus.mundo.GerarCanos;

public class Jogo extends Canvas implements Runnable, KeyListener, MouseListener {



	private static final long serialVersionUID = 1L;
	public static final int tamanho = 16;
	private Thread thread;
	private boolean isRunning;
	public static JFrame frame;
	public static final int WIDITH = 300, HEIGHT = 160, SCALE = 3;
	private BufferedImage background;
	public static List<Entidade> entidades;
	public static Spritesheet spritesheet;
	public static Jogador jogador;
	public static Random rand;
	public static boolean mute = true;
	public static GerarCanos geradorDeCanos;
	public UI ui;
	public int[] pixels, luzPixels;
	
	public static int pontuacao;
	
	
	public Jogo() {
		
		rand = new Random();
		addKeyListener(this);
		addMouseListener(this);
		
		setPreferredSize(new Dimension(WIDITH * SCALE, HEIGHT * SCALE));// tamanho da janela
		
		iniciarFrame();
		ui = new UI();
		background = new BufferedImage(WIDITH, HEIGHT, BufferedImage.TYPE_INT_RGB);// imagem do fundo

		

		iniciarJogo();
		
	}

	public static void main(String[] args) {
		Jogo jogo = new Jogo();
		jogo.iniciar();
	}


	public static void iniciarJogo() {

		geradorDeCanos=new GerarCanos();
		entidades = new ArrayList<Entidade>();
		spritesheet = new Spritesheet("/Spritesheet.png");
		jogador = new Jogador(0, 0, 16, 16, null,2);
		entidades.add(jogador);
		jogador.setX(WIDITH/2);
		jogador.setY(HEIGHT/2);
	}

	public void iniciarFrame() {
		frame = new JFrame("PacWoman");
		frame.add(this);
		
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void atualizar() {
	
				for (int i = 0; i < entidades.size(); i++) {
					Entidade e = entidades.get(i);
					e.atualizar();
				}

			ui.atualizar();
			geradorDeCanos.atualizar();
			
	}

	public void renderizar() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = background.getGraphics();
		g.setColor(new Color(122, 102, 255));
		g.fillRect(0, 0, WIDITH, HEIGHT);

		/* renderização do jogo */
		// Graphics2D g2 = (Graphics2D) g;

		Collections.sort(entidades, Entidade.entidadeSorter);

		for (int i = 0; i < entidades.size(); i++) {
			Entidade e = entidades.get(i);
			e.renderizar(g);
		}

		ui.renderizar(g);

		g.dispose();// limpar dados da imagem que nao foram usados
		g = bs.getDrawGraphics();
		// desenharRetangulo(40,40);

		
		g.drawImage(background, 0, 0, WIDITH * SCALE, HEIGHT * SCALE, null);
			// TELA COMUM
		
		// aqui para ficar em cima da imagem de background
		
		bs.show();
	}
	

	@Override
	public void run() {
		requestFocus();// FOCO AUTOMATICO NA JANELA
		long lastTime = System.nanoTime();// ultima vez que foi executada a atualiação
		double amountOfTicks = 60.0;// quantidade de atualizações por segundo
		double ns = 1000000000 / amountOfTicks;// "constante" do momento certo do update do jogo para ficar na
												// quantidade de fps descritas no amountOfTicks
		double delta = 0;
		
		double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();// tempo atual
			delta += (now - lastTime) / ns;
			lastTime = now;// substitui a ultima execução do while pelo tempo atual

			if (delta >= 1) {
				atualizar();
				renderizar();
				
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				
				timer = System.currentTimeMillis();// atualiza o tempo para o tempo atual
				// ou timer+=1000; para dizer que se passaram 1000 milesegundos desde o valor
				// inicial do timer

			}

		}
		parar();

	}

	public synchronized void iniciar() {
		thread = new Thread(this);
		thread.start();// chama o run da thread
		isRunning = true;

	}

	public synchronized void parar() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			jogador.apertandoTecla=true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			jogador.apertandoTecla=false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}