package com.matheus.entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.matheus.game.Jogo;
import com.matheus.mundo.Camera;
import com.matheus.mundo.Mundo;

public class Jogador extends Entidade {

	
	
	
	public Jogador(int x, int y, int width, int height, BufferedImage sprite, int velocidade) {
		super(x, y, width, height, sprite, velocidade);
		depth=1;
		
	}

	public void atualizar() {
		
		
		
	}

	public void renderizar(Graphics g) {
		g.drawImage(Jogo.spritesheet.getSprite(0, 0, 16, 16), this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

}
