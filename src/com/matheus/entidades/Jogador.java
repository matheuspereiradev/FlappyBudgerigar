package com.matheus.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.matheus.game.Jogo;
import com.matheus.mundo.Camera;
import com.matheus.mundo.Mundo;

public class Jogador extends Entidade {

	public boolean apertandoTecla=false;
	public BufferedImage [] passaro;
	public int index=0, timeIndex=0;
	
	
	public Jogador(int x, int y, int width, int height, BufferedImage sprite, int velocidade) {
		super(x, y, width, height, sprite, velocidade);
		depth=1;
		passaro=new BufferedImage[3];
		for(int i=0; i<passaro.length;i++) {
			passaro[i]=Jogo.spritesheet.getSprite(0, 0+(16*i), 16, 16);
		}
		
		
	}

	public void atualizar() {
		if(apertandoTecla) {
			y-=2;
		}else {
			y+=2;
		};
		
		if(y>Jogo.HEIGHT || y<0) {
			Mundo.carregarFase();
		};
		
		for(int i =0; i<Jogo.entidades.size();i++) {
			Entidade e=Jogo.entidades.get(i);
			if(e != this) {
				if(e.isColidding(e, this)) {
					Jogo.statusJogo=Jogo.gameover;
				}
			}
		}
		
		timeIndex++;
		if(timeIndex==15) {
			timeIndex=0;
			index ++;
			if (index==passaro.length) {
				index=0;
			}
		}
	}

	public void renderizar(Graphics g) {
		g.drawImage(passaro[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

}
