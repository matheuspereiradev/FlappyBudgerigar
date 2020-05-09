package com.matheus.mundo;

import com.matheus.entidades.Cano;
import com.matheus.game.Jogo;

public class GerarCanos {

	public int tempo=0;
	
	public void atualizar() {
		tempo++;
		if (tempo==60) {
			tempo=0;
			int i=Jogo.rand.nextInt(140-30)+30;
			Cano canoCima=new Cano(Jogo.WIDITH, 0, 20, i ,null, 1);
			
			Jogo.entidades.add(canoCima);
			
			int tamanho=Jogo.HEIGHT-i-50;
			Cano canoBaixo=new Cano(Jogo.WIDITH, Jogo.HEIGHT-tamanho, 20, tamanho,null,1);
			
			Jogo.entidades.add(canoBaixo);
		}
	}

}
