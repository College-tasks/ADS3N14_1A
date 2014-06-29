package View;

import java.util.ArrayList;

import Structure.Vertice;
import Structure.VerticeParada;

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
	public static int mostraMain(){
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
	public static ArrayList<Integer> opDij() {
		return Utils.Utils.retDij();
	}
	
	/**
	 * Mostra as opções para receber os pontos de uma rota
	 * @return ArrayList com pontos da rota
	 */
	public static ArrayList<Integer> opCaixeiro() {
		System.out.println("(Digite \"-1\" para parar)");
		return Utils.Utils.retRota();
	}
	
	/**
	 * Mostra uma mensagem de erro
	 * @param msg Mensagem a ser mostrada
	 */
	public static void mostraErro(String msg) {
		System.out.println("================");
		System.out.println(msg);
		System.out.println("================");
	}
	
	/**
	 * Mostra uma rota
	 * @param rota Rota a ser mostrada
	 */
	public static void mostraRota(ArrayList<Vertice> rota) {
		for (Vertice item : rota) {
			System.out.print(item.Nome);
			if (item != rota.get(rota.size()-1))
				System.out.print(" -> ");
		}
		
		System.out.println(Utils.Utils.retInfos(rota));
	}
	
	/**
	 * Mostra uma rota (Com paradas)
	 * @param rota Rota a ser mostrada
	 */
	public static void mostraRotaParada(ArrayList<VerticeParada> rota) {
		System.out.println("Legenda:\nD = Descanso\nR = Reabastecimento");
		for (VerticeParada item : rota) {
			if (item.TemDescanso && item.TemReabastecimento) System.out.print("(D,R)");
			else if (item.TemReabastecimento) System.out.print("(R)");
			else if (item.TemDescanso) System.out.print("(D)");
			System.out.print(item.Atual.Nome);
			if (item != rota.get(rota.size()-1))
				System.out.print(" -> ");
		}
		
		System.out.println(Utils.Utils.retInfosParada(rota));
	}
}
