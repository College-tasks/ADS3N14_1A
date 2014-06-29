package Structure;

import java.util.ArrayList;
import java.util.Collections;

import IO.Arquivo;

/**
 * Define um grafo
 * @author Simor / Vanessa C. / Larson R.
 *
 */
public class Grafo {
	public ArrayList<Vertice> lstVertice;
	
	/**
	 * Construtor - Cria o grafo a partir de um arquivo
	 */
	public Grafo(){
		Arquivo.carrega(this);
	}
	
	/**
	 * Calcula uma rota entre dois pontos (Custo)
	 * @param a Vertice A
	 * @param b Vertice B
	 * @param custo Flag para fazer a verificação pelo Custo ou Distância
	 * @return Rota de menor custo
	 */
	public ArrayList<Vertice> calcDij(Vertice a, Vertice b, boolean custo) {
		ArrayList<Vertice> visitado = new ArrayList<Vertice>();
		ArrayList<Vertice> paraVisitar = new ArrayList<Vertice>();
		ArrayList<Dji> lstDji = new ArrayList<Dji>();
		ArrayList<Vertice> rota = new ArrayList<Vertice>();
		Vertice atual = a;
		
		// Adiciona vértices na lista Dji
		for (Vertice item : lstVertice) {
			if (!item.Proximo.isEmpty()) {
				Dji dj = new Dji(item);
				// Verifica se é o Vertice inicial
				if (item == a) dj.Custo = 0;
				lstDji.add(dj);
			}
		}
		
		// Calcula os custos
		do {
			for (ProxVertice item : atual.Proximo) {
				// Verifica se o Vertice já está na lista "para visitar" e não está na lista de já visitados
				if (!visitado.contains(item.ProxVert)) {
					if (!paraVisitar.contains(item.ProxVert)) paraVisitar.add(item.ProxVert);
					
					// Recupera o Vertice atual da lista Dji
					Dji djiAtual = null;
					for (Dji item2 : lstDji) {
						if (item2.Atual == atual) {
							djiAtual = item2;
						}
					}
					
					// Verifica se existe um custo menor na lista
					for (Dji item2 : lstDji) {
						if (item2.Atual == item.ProxVert) {
							// Custo
							if (custo) {
								if (item2.Custo > item.Conex.Custo + djiAtual.Custo) {
									item2.Custo = item.Conex.Custo + djiAtual.Custo;
									item2.Anterior = atual;
								}
							} // Distância
							else {
								if (item2.Custo > item.Conex.Distancia + djiAtual.Custo) {
									item2.Custo = item.Conex.Distancia + djiAtual.Custo;
									item2.Anterior = atual;
								}
							}
							break;
						}
					}
					
					// Configura o próximo Vértice a ser verificado
					visitado.add(atual);
					if (paraVisitar.contains(atual)) paraVisitar.remove(atual);
					if (!paraVisitar.isEmpty()) {
						atual = paraVisitar.get(0);
					}
				}
			}
		} while (!paraVisitar.isEmpty());
		
		// Cria a rota
		Dji djiAtual = null;
		Dji djiAnterior = null; // Sim, deveríamos ter usado um Dji como "Anterior", mas agora f*da-se
		
		for (Dji item : lstDji) {
			if (item.Atual == b) {
				djiAtual = item;
			}
			
			for (Dji item2 : lstDji) {
				if (item2.Atual == djiAtual.Anterior) {
					djiAnterior = item2;
				}
			}
			
			do {
				rota.add(djiAtual.Atual);
				djiAtual = djiAnterior;
			} while (djiAtual.Atual != a);
		}
		
		Collections.reverse(rota);
		return rota;
	}
}