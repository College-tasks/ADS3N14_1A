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
	public ArrayList<Vertice> calcDij(Vertice a, Vertice b, boolean custo, boolean reabastecimento) {
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
				// Se for calcular o algoritmo de reabastecimento, ignora caminhos maior que 240km
				// (Para não passar de 3h de viagem)
				if (reabastecimento && item.Conex.Distancia > 240) continue;
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
					
				}
			}
			
			paraVisitar.remove(0);
			if (!paraVisitar.isEmpty()) {
				atual = paraVisitar.get(0);
			}
		} while (!paraVisitar.isEmpty());
		
		// Cria a rota
		Dji djiAtual = null;
		Dji djiAnterior = null; // Sim, deveríamos ter usado um Dji como "Anterior", mas agora f*da-se
		
		for (Dji item : lstDji) {
			if (item.Atual == b) {
				djiAtual = item;
			}
		}
			
		for (Dji item2 : lstDji) {	
			if (djiAtual.Anterior == null) continue;
			
			if (item2.Atual == djiAtual.Anterior) {
				djiAnterior = item2;
			}
		}
		
		boolean flagOk = true;
		while (flagOk) {			
			rota.add(djiAtual.Atual);
			if (djiAtual.Anterior == null) {
				flagOk = false;
				break;
			}
			djiAtual = achaDji(lstDji, djiAtual.Anterior);
		} ;
		
		
		Collections.reverse(rota);
		return rota;
	}
	
	/**
	 * Acha um vértice numa lista de Djis
	 * @param djis Lista de Djis
	 * @param v Vértice
	 * @return Dji encontrado
	 */
	private Dji achaDji(ArrayList<Dji> djis, Vertice v) {
		for (Dji item : djis) {
			if (item.Atual == v) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Calcula uma rota do tipo Caixeiro Viajante se baseando em Custo (Modo óbvio)
	 * @param caminho Pontos que a rota deve passar
	 * @return Rota calculada
	 */
	public ArrayList<Vertice> calcCaixeiroObvio(ArrayList<Vertice> caminho) {
		ArrayList<Vertice> rota = new ArrayList<Vertice>();
		
		// Adiciona caminho de ponto em ponto na rota
		for (int i = 0; i < caminho.size()-1; i++) {
			ArrayList<Vertice> caminhoTemp = calcDij(caminho.get(i), caminho.get(i+1), true, false);
			for (Vertice item : caminhoTemp) {
				rota.add(item);
			}
		}
		
		return rota;
	}
	
	/**
	 * Calcula uma rota do tipo Caixeiro Viajante se baseando em Custo (Modo não-tão-óbvio)
	 * @param caminho Pontos que a rota deve passar
	 * @return Rota calculada
	 */
	public ArrayList<Vertice> calcCaixeiroNaoObvio(ArrayList<Vertice> caminho) {
		ArrayList<Vertice> rota = new ArrayList<Vertice>();
		return rota;
	}
	
	/**
	 * Calcula uma rota do tipo Caixeiro Viajante, se baseando em distância
	 * (Com paradas para reabastecimento e descanso)
	 * @param caminho Pontos que a rota deve passar
	 * @return Rota calculada
	 */
	public ArrayList<VerticeParada> calcCaixeiroComParadas(ArrayList<Vertice> caminho) {
		ArrayList<VerticeParada> rota = new ArrayList<VerticeParada>();
		VerticeParada anterior = null;
		double tempo = 0;
		double gas = 40;
		
		// Adiciona caminho de ponto em ponto na rota
		for (int i = 0; i < caminho.size()-1; i++) {
			ArrayList<Vertice> caminhoTemp = calcDij(caminho.get(i), caminho.get(i+1), false, true);
			for (Vertice item : caminhoTemp) {
				VerticeParada vP = new VerticeParada();
				vP.Atual = item;
				vP.TemDescanso = false;
				vP.TemReabastecimento = false;
				rota.add(vP);
			}
		}

		// Verifica paradas e descanso's
		for (int i = 0; i < rota.size()-1; i++) {
			// Verifica a aresta
			Aresta aresta = null;
			for (ProxVertice item : rota.get(i).Atual.Proximo) {
				if (item.ProxVert == rota.get(i+1).Atual) {
					aresta = item.Conex;
					break;
				}
			}
			
			// Verifica se é necessário fazer uma parada no vértice anterior
			if (tempo + (aresta.Distancia / 80) > 3) {
				anterior.TemDescanso = true;
				tempo = 0;
			}
			if (gas - (aresta.Distancia / 15) <= 0) {
				anterior.TemReabastecimento = true;
				gas = 40;
			}
			
			// Seta o anterior
			anterior = rota.get(i);
		}
		
		return rota;
	}
}