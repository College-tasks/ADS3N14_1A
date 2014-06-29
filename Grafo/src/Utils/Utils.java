package Utils;

import java.util.ArrayList;
import java.util.Scanner;

import Structure.ProxVertice;
import Structure.Vertice;
import Structure.VerticeParada;

public class Utils {
	/**
	 * Pede para digitar um n�mero at� que um n�mero seja digitado
	 * @return N�mero digitado
	 */
	public static int retInt(){
		boolean flagOk = false;
		int num = 0;
		
		// Enquanto n�o for digitado um n�mero v�lido
		do {
			try {
				 num = new Scanner(System.in).nextInt();
				 flagOk = true;
			} catch (Exception ex) {
				System.out.println("Input inv�lido. Digite novamente:");
			}
		} while (!flagOk);
		
		// Retorna
		return num;
	}
	
	/**
	 * Cria um ArrayList com dois pontos v�lidos para calcular a rota Dijkstra
	 * @return ArrayList com pontos
	 */
	public static ArrayList<Integer> retDij(){
		int a = 0, b = 0;
		ArrayList<Integer> lstInt = new ArrayList<Integer>();
		
		do {
			System.out.println("Digite o ponto A (0 ~ 49):");
			a = retInt();
		} while(a < 0 || a > 49);
		
		do {
			System.out.println("Digite o ponto B (0 ~ 49):");
			b = retInt();
		} while(b < 0 || b > 49 || b == a);
		
		lstInt.add(a);
		lstInt.add(b);
		
		return lstInt;
	}
	
	/**
	 * Cria um ArrayList com uma rota v�lida (Ou n�o, na real)
	 * @return ArrayList com pontos
	 */
	public static ArrayList<Integer> retRota() {
		int p = 0;
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		do {
			p = retInt();
			
			if (p == 0) break; // Verifica se � para parar
			else if (p < 0 || p > 49) continue; // Verifica se o n�mero � v�lido
			
			for(int item : ret) {
				if (item == p) continue;
			}
			
			ret.add(p);
		} while (p != -1);
		
		return ret;
	}
	
	/**
	 * Retorna informa��es de uma rota
	 * @param verts ArrayList de v�rtices da rota
	 * @return Informa��es da rota
	 */
	public static String retInfos(ArrayList<Vertice> verts) {
		String ret = "";
		double distancia = 0;
		double gas = 0;
		double horas = 0;
		double custo = 0;
		
		for (int i = 0; i < verts.size()-1; i++) {
			Vertice atual = verts.get(i);
			Vertice prox = verts.get(i+1);
			
			// Pega a dist�ncia e custo
			for (ProxVertice item : atual.Proximo) {
				if (item.ProxVert == prox) {
					distancia += item.Conex.Distancia;
					custo += item.Conex.Custo;
					break;
				}
			}
		}
		
		gas = distancia / 15;
		horas = distancia / 80;
		
		// Preenche retorno
		ret += "\n\n===============================\n";
		ret += "Dist�ncia total: " + distancia + "km\n";
		ret += "Custo total: " + custo + "\n";
		ret += "Gasolina total: " + gas + "l\n";
		ret += "Tempo total (Sem contar com paradas): " + horas + "h\n";
		ret += "===============================\n\n";
		
		return ret;
	}
	
	/**
	 * Retorna informa��es de uma rota (Com paradas)
	 * @param verts ArrayList de v�rtices da rota
	 * @return Informa��es da rota
	 */
	public static String retInfosParada(ArrayList<VerticeParada> verts) {
		String ret = "";
		double distancia = 0;
		double gas = 0;
		double horas = 0;
		double custo = 0;
		
		for (int i = 0; i < verts.size()-1; i++) {
			Vertice atual = verts.get(i).Atual;
			Vertice prox = verts.get(i+1).Atual;
			
			// Pega a dist�ncia e custo
			for (ProxVertice item : atual.Proximo) {
				if (item.ProxVert == prox) {
					distancia += item.Conex.Distancia;
					custo += item.Conex.Custo;
					break;
				}
			}
		}
		
		gas = distancia / 15;
		horas = distancia / 80;
		
		// Preenche retorno
		ret += "\n\n===============================\n";
		ret += "Dist�ncia total: " + distancia + "km\n";
		ret += "Custo total: " + custo + "\n";
		ret += "Gasolina total: " + gas + "l\n";
		ret += "Tempo total (Sem contar com paradas): " + horas + "h\n";
		ret += "===============================\n\n";
		
		return ret;
	}
}
