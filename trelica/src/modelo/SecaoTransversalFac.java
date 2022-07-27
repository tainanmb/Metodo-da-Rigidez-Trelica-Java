package modelo;

import java.util.StringTokenizer;

/**
 * Superclasse abstrata estendida para cada tipo de se��o transversal:
 * Prescrita, Circular ou Retangular.
 * 
 * @author Tainan Brand�o
 * @version 1.0
 */

public abstract class SecaoTransversalFac {

	/**
	 * M�todo est�tico padr�o factory que instancia os tipos de se��es transversais
	 * das barras.
	 * 
	 * @param id_secao  Identificador da Se��o Transversal
	 * @param tipoSecao Tipo da Se��o Transversal
	 * @param st        String separada em tokens que fornecem os valores
	 *                  necess�rios para preenchimento dos dados de cada se��o
	 * @return se��o transversal preenchida com construtor com argumento
	 */
	public static SecaoTransversal factory(String id_secao, String tipoSecao, StringTokenizer st) {
		// O StringTokenizer pega a linha onde tem os parametros da se��o (ver
		// PreProcessadorByFile)
		// Conta os tokens existentes que devem ser coerentes com o tipo de se��o
		int nt = st.countTokens();
		SecaoTransversal secao = null;

		switch (tipoSecao) {
		case ("Prescribed"):// Nesse caso s� tem os parametros A e I
			if (nt == 2) {
				secao = new SecaoTransversalPrescrita(id_secao, Double.parseDouble(st.nextToken()),
						Double.parseDouble(st.nextToken()));
			} else {
				System.out.println(
						"Erro na f�brica de se��es: N�mero de tokens inconsistente com o tipo se��o prescrita");
				System.exit(1);
			}
			break;

		case ("Circular"):// Nesse caso somente o raio
			if (nt == 1) {
				secao = new SecaoTransversalCircular(id_secao, Double.parseDouble(st.nextToken()));
			} else {
				System.out
						.println("Erro na f�brica de se��es: N�mero de tokens inconsistente com o tipo se��o circular");
				System.exit(1);
			}
			break;

		case ("Rectangular"):// Nesse caso os dois lados
			if (nt == 2) {
				secao = new SecaoTransversalRetangular(id_secao, Double.parseDouble(st.nextToken()),
						Double.parseDouble(st.nextToken()));
			} else {
				System.out.println(
						"Erro na f�brica de se��es: N�mero de tokens inconsistente com o tipo se��o prescrita");
				System.exit(1);
			}
			break;
		default:
			System.out.println("Erro na f�brica de se��es: Tipo de se��o n�o prevista");
			secao = null;
		}
		return secao;
	}

}
