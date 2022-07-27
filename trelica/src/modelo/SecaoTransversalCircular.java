package modelo;

/**
 * Classe que descreve uma sessão transversal circular
 * <p>
 * Dados:
 * <ul>
 * <li>id - Identificador da Seção Transversal
 * <li>A - Área da Seção Transversal
 * <li>I - Momento de Inércia da Seção Transversal
 * </ul>
 * 
 * @author Tainan Brandão
 * @version 1.0
 */
public class SecaoTransversalCircular extends SecaoTransversalFac implements SecaoTransversal {

	/**
	 * Declaração dos campos da classe.
	 */
	private String id;
	private double A;
	private double I;

	/**
	 * Construtor sem parâmetro.
	 */
	public SecaoTransversalCircular() {
		this.id = null;
		this.A = 0.0d;
		this.I = 0.0d;
	}

	/**
	 * Construtor que recebe como argumento o identificador (id) e o raio do círculo
	 * (r) e preenche todos os campos da seção transversal.
	 * 
	 * @param id identificador da seção transversal
	 * @param r  raio do círculo
	 */
	public SecaoTransversalCircular(String id, double r) {
		this.id = id;
		this.A = Math.PI * Math.pow(r, 2);
		this.I = (Math.PI * Math.pow(r, 4)) / 4;
	}

	/**
	 * Método de acesso Get que retorna o identificador da seção transversal (id).
	 * 
	 * @return identificador da seção transversal
	 */
	public String get_id() {
		return id;
	}

	/**
	 * Método de acesso Get que retorna a área da seção transversal (A).
	 * 
	 * @return área da seção transversal
	 */
	public double get_A() {
		return A;
	}

	/**
	 * Método de acesso Get que retorna o momento de inércia da seção transversal
	 * (I).
	 * 
	 * @return momento de inércia da seção transversal
	 */
	public double get_I() {
		return I;
	}

	/**
	 * Método modificador Set do identificador da seção transversal (id) (sem
	 * retorno).
	 * 
	 * @param id identificador da seção transversal
	 */
	public void set_id(String id) {
		this.id = id;
	}

	/**
	 * Método modificador Set da área da seção transversal (A) (sem retorno).
	 * 
	 * @param A área da seção transversal
	 */
	public void set_A(double A) {
		this.A = A;
	}

	/**
	 * Método modificador Set do momento de inércia da seção transversal (I) (sem
	 * retorno).
	 * 
	 * @param I momento de inércia da seção transversal
	 */
	public void set_I(double I) {
		this.I = I;
	}

	/**
	 * Método que retorna um texto contendo os dados da seção transversal:
	 * identificador (id), área (A) e momento de inércia (I).
	 * 
	 * @return Texto contendo os dados da seção transversal
	 */
	public String toString() {
		String texto;
		texto = this.id;
		texto += " / Área = " + A;
		texto += " / Inércia = " + I;
		return texto;
	}

}
