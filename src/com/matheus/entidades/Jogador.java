package com.matheus.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.matheus.game.Jogo;
import com.matheus.mundo.Camera;

public class Jogador extends Entidade {

	public boolean apertandoTecla=false;
	
	
	public Jogador(int x, int y, int width, int height, BufferedImage sprite, int velocidade) {
		super(x, y, width, height, sprite, velocidade);
		depth=1;
		
	}

	public void atualizar() {
		if(apertandoTecla) {
			y-=2;
		}else {
			y+=2;
		}
		for (int i = 0; i < Jogo.entidades.size(); i++) {// depois melhor criar uma lista somente para life pack
			Entidade atual = Jogo.entidades.get(i);
			if (atual instanceof Cano) {
				if (this.isColidding(this, atual)) {
					System.out.println("Morreu");
				}
			}
		}
	}

	public void renderizar(Graphics g) {
		g.drawImage(Jogo.spritesheet.getSprite(0, 0, 16, 16), this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

}
