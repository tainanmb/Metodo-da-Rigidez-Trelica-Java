package modelo;

import java.util.StringTokenizer;

/**
 * Superclasse abstrata estendida para cada tipo de seção transversal:
 * Prescrita, Circular ou Retangular.
 * 
 * @author Tainan Brandão
 * @version 1.0
 */

public abstract class SecaoTransversalFac {

	/**
	 * Método estático padrão factory que instancia os tipos de seções transversais
	 * das barras.
	 * 
	 * @param id_secao  Identificador da Seção Transversal
	 * @param tipoSecao Tipo da Seção Transversal
	 * @param st        String separada em tokens que fornecem os valores
	 *                  necessários para preenchimento dos dados de cada seção
	 * @return seção transversal preenchida com construtor com argumento
	 */
	public static SecaoTransversal factory(String id_secao, String tipoSecao, StringTokenizer st) {
		// O StringTokenizer pega a linha onde tem os parametros da seção (ver
		// PreProcessadorByFile)
		// Conta os tokens existentes que devem ser coerentes com o tipo de seção
		int nt = st.countTokens();
		SecaoTransversal secao = null;

		switch (tipoSecao) {
		case ("Prescribed"):// Nesse caso só tem os parametros A e I
			if (nt == 2) {
				secao = new SecaoTransversalPrescrita(id_secao, Double.parseDouble(st.nextToken()),
						Double.parseDouble(st.nextToken()));
			} else {
				System.out.println(
						"Erro na fábrica de seções: Número de tokens inconsistente com o tipo seção prescrita");
				System.exit(1);
			}
			break;

		case ("Circular"):// Nesse caso somente o raio
			if (nt == 1) {
				secao = new SecaoTransversalCircular(id_secao, Double.parseDouble(st.nextToken()));
			} else {
				System.out
						.println("Erro na fábrica de seções: Número de tokens inconsistente com o tipo seção circular");
				System.exit(1);
			}
			break;

		case ("Rectangular"):// Nesse caso os dois lados
			if (nt == 2) {
				secao = new SecaoTransversalRetangular(id_secao, Double.parseDouble(st.nextToken()),
						Double.parseDouble(st.nextToken()));
			} else {
				System.out.println(
						"Erro na fábrica de seções: Número de tokens inconsistente com o tipo seção prescrita");
				System.exit(1);
			}
			break;
		default:
			System.out.println("Erro na fábrica de seções: Tipo de seção não prevista");
			secao = null;
		}
		return secao;
	}

}
