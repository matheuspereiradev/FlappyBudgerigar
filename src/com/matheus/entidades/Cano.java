package com.matheus.entidades;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.matheus.entidades.Entidade;
import com.matheus.game.Jogo;

public class Cano extends Entidade{

	public static BufferedImage cano=Jogo.spritesheet.getSprite(44, 16, 20, 16);
	public Cano(double x, double y, int width, int height, BufferedImage sprite, int velocidade) {
		super(x, y, width, height, sprite, velocidade);
	}

	@Override
	public void atualizar() {
		x--;
		if(x+width<=0) {
			Jogo.pontuacao++;
			Jogo.entidades.remove(this);
		}
	}

	@Override
	public void renderizar(Graphics g) {
		if(sprite!=null) {
			g.drawImage(sprite, getX(), getY(), width, height, null);
		}else {
		g.setColor(Color.green);
		g.fillRect((int)x,(int) y, width, height);
		}
	}

	

}
