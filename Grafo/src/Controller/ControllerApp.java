package Controller;

import java.util.ArrayList;

import Structure.*;
import View.ViewApp;

/**
 * Define o Controller do App
 * @author Simor / Vanessa C. / Larson R.
 *
 */
public class ControllerApp {
	Grafo grafo;
	
	/**
	 * Construtor
	 */
	public ControllerApp(){
		grafo = new Grafo();
		initApp();
	}
	
	/**
	 * Tela inicial do App
	 */
	private void initApp(){
		int op = ViewApp.mostraMain();
		
		switch (op) {
			case 1:
				dijCusto();
				break;
			case 2: 
				dijDistancia();
				break;
			case 3: 
				caixeiroObvio();
				break;
			case 4:
				caixeiroNaoObvio();
				break;
			case 5: 
				caixeiroParada();
				break;
			case 0: 
				System.exit(0);
				break;
			default:
				ViewApp.mostraErro("Op��o inv�lida.");
				initApp();
				break;
		}
	}
	
	/**
	 * Efetua uma rota com Dijkstra utilizando custo
	 */
	private void dijCusto(){
		ArrayList<Integer> lstPontos = ViewApp.opDij();
		Vertice a = null;
		Vertice b = null;
		
		// Verifica se os pontos foram digitados corretamente
		if (lstPontos.get(0) == lstPontos.get(1)) {
			ViewApp.mostraErro("Digite dois pontos diferentes!");
			dijCusto();
			return;
		}
		
		// Pega os Vertices com os "nomes" digitados
		for (Vertice item : grafo.lstVertice) {
			if (item.Nome.equals(String.valueOf(lstPontos.get(0)))){
				a = item;
			}else if(item.Nome.equals(String.valueOf(lstPontos.get(1)))) {
				b = item;
			}
			
			if (a != null && b != null) break;
		}
		
		// Cria a rota
		ArrayList<Vertice> rota = grafo.calcDij(a, b, true, false);
		
		// Mostra a rota
		ViewApp.mostraRota(rota);
		
		initApp();
	}
	
	/**
	 * Efetua uma rota com Dijkstra utilizando dist�ncia
	 */
	private void dijDistancia(){
		ArrayList<Integer> lstPontos = ViewApp.opDij();
		Vertice a = null;
		Vertice b = null;
		
		// Verifica se os pontos foram digitados corretamente
		if (lstPontos.get(0) == lstPontos.get(1)) {
			ViewApp.mostraErro("Digite dois pontos diferentes!");
			dijCusto();
			return;
		}
		
		// Pega os Vertices com os "nomes" digitados
		for (Vertice item : grafo.lstVertice) {
			if (item.Nome.equals(String.valueOf(lstPontos.get(0)))){
				a = item;
			}else if(item.Nome.equals(String.valueOf(lstPontos.get(1)))) {
				b = item;
			}
			
			if (a != null && b != null) break;
		}
		
		// Cria a rota
		ArrayList<Vertice> rota = grafo.calcDij(a, b, false, false);
		
		// Mostra a rota
		ViewApp.mostraRota(rota);
		
		initApp();
	}
	
	/**
	 * Efetua uma rota com Caixeiro Viajante utilizando custo (Modo �bvio)
	 */
	private void caixeiroObvio(){
		ArrayList<Integer> lstPontos = ViewApp.opCaixeiro();
		ArrayList<Vertice> lstVertices = new ArrayList<Vertice>();
		
		for (Integer item2 : lstPontos) {
			for (Vertice item : grafo.lstVertice) {
				if (item.Nome.equals(String.valueOf(item2))) {
					lstVertices.add(item);
					break;
				}
			}
		}
		
		// Cria a rota
		ArrayList<Vertice> rota = grafo.calcCaixeiroObvio(lstVertices);
				
		// Mostra a rota
		ViewApp.mostraRota(rota);
		
		initApp();
	}

	/**
	 * Efetua uma rota com Caixeiro Viajante utilizando custo (Modo n�o-t�o-�bvio)
	 */
	private void caixeiroNaoObvio(){
		ViewApp.mostraErro("Ainda n�o implementado (E nem ser� implementado, pois o arquivo de grafos est� totalmente zoado)");
		initApp();
	}

	/**
	 * Efetua uma rota com Caixeiro Viajante utilizando dist�ncia
	 * E levando em considera��o paradas para descanso e reabastecimento
	 */
	private void caixeiroParada(){
		ArrayList<Integer> lstPontos = ViewApp.opCaixeiro();
		ArrayList<Vertice> lstVertices = new ArrayList<Vertice>();
		
		for (Integer item2 : lstPontos) {
			for (Vertice item : grafo.lstVertice) {
				if (item.Nome.equals(String.valueOf(item2))) {
					lstVertices.add(item);
					break;
				}
			}
		}
		
		// Cria a rota
		ArrayList<VerticeParada> rota = grafo.calcCaixeiroComParadas(lstVertices);
				
		// Mostra a rota
		ViewApp.mostraRotaParada(rota);
		
		initApp();
	}
}
