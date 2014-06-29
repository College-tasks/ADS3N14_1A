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
	 * @return Opção escolhida
	 */
	public int mostraMain(){
		System.out.println("1 - Dijkstra por Custo");
		System.out.println("2 - Dijkstra por Distância");
		System.out.println("3 - Caixeiro Viajante (Modo óbvio)");
		System.out.println("4 - Caixeiro Viajante (Modo não-tão-óbvio)");
		System.out.println("5 - Caixeiro Viajante Com paradas");
		System.out.println("0 - Sair");
		
		return Utils.Utils.retInt();
	}
	
	/**
	 * Mostra as opções para receber os pontos de um Dijkstra
	 * @return ArrayList com pontos de chegada e partida
	 */
	public ArrayList<Integer> opDij() {
		return Utils.Utils.retDij();
	}
	
	/**
	 * Mostra as opções para receber os pontos de uma rota
	 * @return ArrayList com pontos da rota
	 */
	public ArrayList<Integer> opCaixeiro() {
		System.out.println("(Digite 0 para parar)");
		return Utils.Utils.retRota();
	}
}
