package View;

import java.util.ArrayList;

/**
 * Define o View do App
 * @author Simor / Vanessa C. / Larson R.
 *
 */
public class ViewApp {
	/**
	 * Mostra o menu principal
	 * @return Op��o escolhida
	 */
	public int mostraMain(){
		System.out.println("1 - Dijkstra por Custo");
		System.out.println("2 - Dijkstra por Dist�ncia");
		System.out.println("3 - Caixeiro Viajante");
		System.out.println("4 - Caixeiro Viajante Com paradas");
		System.out.println("0 - Sair");
		
		return Utils.Utils.retInt();
	}
	
	/**
	 * Mostra as op��es para receber os pontos de um Dijkstra
	 * @return ArrayList com pontos de chegada e partida
	 */
	public ArrayList<Integer> opDij() {
		return Utils.Utils.retDij();
	}
	
	/**
	 * Mostra as op��es para receber os pontos de uma rota
	 * @return ArrayList com pontos da rota
	 */
	public ArrayList<Integer> opCaixeiro() {
		return Utils.Utils.retRota();
	}
}
