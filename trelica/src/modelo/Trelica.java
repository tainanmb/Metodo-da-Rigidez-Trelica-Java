package modelo;

import algebraLinear.Matriz;
import algebraLinear.Vetor;

/**
 * Subclasse da classe abstrata Barra. Descreve uma barra do tipo Treli�a
 * treli�a utilizada em um modelo estrutural reticulado espacial.
 * <p>
 * Dados:
 * <ul>
 * <li>id - Identificador da Barra do tipo Treli�a
 * <li>no_inicial - N� Inicial da Barra do tipo Treli�a
 * <li>no_final - N� Final da Barra do tipo Treli�a
 * <li>mat_bar - Material da Barra do tipo Treli�a
 * <li>sec_bar - Se��o Transversal da Barra do tipo Treli�a
 * <li>q_list - Lista de carregamentos distribuidos
 * <li>p_list - Lista de carregamentos pontuais
 * </ul>
 * 
 * @author Tainan Brand�o
 * @version 1.0
 */

public class Trelica extends Barra {

	/**
	 * Construtor sem par�metro. Utiliza o construtor sem par�metro da classe Barra.
	 */
	public Trelica() {
		super();
	}

	/**
	 * Construtor que recebe como argumento o identificador (id), n� inicial
	 * (no_inicial), n� final (no_final), material (mat_bar) e se��o transversal
	 * (sec_bar) e preenche todos os campos da barra do tipo P�rtico. Utiliza o
	 * construtor com par�metros da classe Barra.
	 * 
	 * @param id         identificador da barra
	 * @param no_inicial n� inicial da barra
	 * @param no_final   n� final da barra
	 * @param mat_bar    material da barra
	 * @param sec_bar    I se��o transversal da barra
	 */
	public Trelica(String id, Node no_inicial, Node no_final, Material mat_bar, SecaoTransversal sec_bar) {
		super(id, no_inicial, no_final, mat_bar, sec_bar);
	}

	/**
	 * M�todo para c�lculo da matiz de rigidez local de uma barra tipo treli�a.
	 * 
	 * @return k_local matriz de rigidez local de uma barra tipo trelica
	 */
	public Matriz k_local() {
		double E = get_mat_bar().get_E();
		double A = get_sec_bar().get_A();
		double L = calcL(get_no_inicial(), get_no_final());
		double[][] k_loc = { { E * A / L, 0, -E * A / L, 0 }, { 0, 0, 0, 0 }, { -E * A / L, 0, E * A / L, 0 },
				{ 0, 0, 0, 0 }, };
		Matriz k_local = new Matriz(k_loc);
		return k_local;
	}

	/**
	 * M�todo para c�lculo da matriz de transforma��o de coordenadas globais para
	 * locais de uma barra tipo p�rtico.
	 * 
	 * @return transf matriz de transforma��o
	 */
	public Matriz transf() {
		double L = calcL(get_no_inicial(), get_no_final());
		double deltaX = Barra.delta_x(get_no_inicial(), get_no_final());
		double deltaY = Barra.delta_y(get_no_inicial(), get_no_final());
		double cos = deltaX / L;
		double seno = deltaY / L;
		double[][] tr = { { cos, seno, 0, 0 }, { -seno, cos, 0, 0 }, { 0, 0, cos, seno }, { 0, 0, -seno, cos }, };
		Matriz transf = new Matriz(tr);
		return transf;
	}

	/**
	 * M�todo de acesso Get que retorna as for�as equivalentes nos n�s iniciais e
	 * finais, oriundas do somat�rio de cargas pontuais e distribu�das aplicadas ao
	 * longo da barra no sistema de coordenadas local.
	 * 
	 * @return EquivalentLoadLocal vetor com as for�as equivalentes total no sistema
	 *         local
	 */
	public Vetor getEquivalentLoadLocal() { // N�o tem carga na barra
		Vetor EquivalentLoadLocal = new Vetor(4);
		EquivalentLoadLocal.setVetor(0, 0);
		EquivalentLoadLocal.setVetor(1, 0);
		EquivalentLoadLocal.setVetor(2, 0);
		EquivalentLoadLocal.setVetor(3, 0);
		return EquivalentLoadLocal;
	}

	/**
	 * M�todo de acesso Get que retorna as for�as equivalentes nos n�s iniciais e
	 * finais, oriundas do somat�rio de cargas pontuais e distribu�das aplicadas ao
	 * longo da barra no sistema de coordenadas global.
	 * 
	 * @return EquivalentLoadLocal vetor com as for�as equivalentes total no sistema
	 *         global
	 */
	public Vetor getEquivalentLoadGlobal() {
		Vetor EquivalentLoadGlobal = new Vetor(4);
		EquivalentLoadGlobal.setVetor(0, 0);
		EquivalentLoadGlobal.setVetor(1, 0);
		EquivalentLoadGlobal.setVetor(2, 0);
		EquivalentLoadGlobal.setVetor(3, 0);
		return EquivalentLoadGlobal;
	}

	/**
	 * M�todo de acesso Get que retorna um vetor com os deslocamentos da barra
	 * 
	 * @return Deslocamento vetor com os deslocamentos da barra
	 */
	public Vetor get_deslocamentos() {
		Vetor Deslocamento = new Vetor(4);
		Deslocamento.setVetor(0, this.get_no_inicial().get_deslocamento(0));
		Deslocamento.setVetor(1, this.get_no_inicial().get_deslocamento(1));
		Deslocamento.setVetor(2, this.get_no_final().get_deslocamento(0));
		Deslocamento.setVetor(3, this.get_no_final().get_deslocamento(1));
		return Deslocamento;
	}

}
