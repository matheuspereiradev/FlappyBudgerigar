package com.matheus.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.matheus.game.Jogo;

public class UI {

	

	public void atualizar() {
		
	}
	
	public void renderizar(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD, 20));
		g.drawString("Pontos: "+Jogo.pontuacao, 25, 25);
	}

}
