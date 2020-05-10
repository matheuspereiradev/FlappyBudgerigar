package com.matheus.mundo;

import com.matheus.entidades.Cano;
import com.matheus.game.Jogo;

public class GerarCanos {

	public int tempo=0;
	
	public void atualizar() {
		tempo++;
		if (tempo==60) {
			tempo=0;
			int i=Jogo.rand.nextInt(50-30)+30;
			
			Cano canoCima=new Cano(Jogo.WIDITH, 0, 20, i ,Cano.cano, 1);
			Jogo.entidades.add(canoCima);
			
			int tamanho=Jogo.HEIGHT-i-60;
			//int tamanho=Jogo.rand.nextInt(50-30)+30;
			
			Cano canoBaixo=new Cano(Jogo.WIDITH, Jogo.HEIGHT-tamanho, 20, tamanho,Cano.cano,1);
			Jogo.entidades.add(canoBaixo);
		}
	}

}
