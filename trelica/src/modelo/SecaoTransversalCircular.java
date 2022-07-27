package modelo;

/**
 * Classe que descreve uma sess�o transversal circular
 * <p>
 * Dados:
 * <ul>
 * <li>id - Identificador da Se��o Transversal
 * <li>A - �rea da Se��o Transversal
 * <li>I - Momento de In�rcia da Se��o Transversal
 * </ul>
 * 
 * @author Tainan Brand�o
 * @version 1.0
 */
public class SecaoTransversalCircular extends SecaoTransversalFac implements SecaoTransversal {

	/**
	 * Declara��o dos campos da classe.
	 */
	private String id;
	private double A;
	private double I;

	/**
	 * Construtor sem par�metro.
	 */
	public SecaoTransversalCircular() {
		this.id = null;
		this.A = 0.0d;
		this.I = 0.0d;
	}

	/**
	 * Construtor que recebe como argumento o identificador (id) e o raio do c�rculo
	 * (r) e preenche todos os campos da se��o transversal.
	 * 
	 * @param id identificador da se��o transversal
	 * @param r  raio do c�rculo
	 */
	public SecaoTransversalCircular(String id, double r) {
		this.id = id;
		this.A = Math.PI * Math.pow(r, 2);
		this.I = (Math.PI * Math.pow(r, 4)) / 4;
	}

	/**
	 * M�todo de acesso Get que retorna o identificador da se��o transversal (id).
	 * 
	 * @return identificador da se��o transversal
	 */
	public String get_id() {
		return id;
	}

	/**
	 * M�todo de acesso Get que retorna a �rea da se��o transversal (A).
	 * 
	 * @return �rea da se��o transversal
	 */
	public double get_A() {
		return A;
	}

	/**
	 * M�todo de acesso Get que retorna o momento de in�rcia da se��o transversal
	 * (I).
	 * 
	 * @return momento de in�rcia da se��o transversal
	 */
	public double get_I() {
		return I;
	}

	/**
	 * M�todo modificador Set do identificador da se��o transversal (id) (sem
	 * retorno).
	 * 
	 * @param id identificador da se��o transversal
	 */
	public void set_id(String id) {
		this.id = id;
	}

	/**
	 * M�todo modificador Set da �rea da se��o transversal (A) (sem retorno).
	 * 
	 * @param A �rea da se��o transversal
	 */
	public void set_A(double A) {
		this.A = A;
	}

	/**
	 * M�todo modificador Set do momento de in�rcia da se��o transversal (I) (sem
	 * retorno).
	 * 
	 * @param I momento de in�rcia da se��o transversal
	 */
	public void set_I(double I) {
		this.I = I;
	}

	/**
	 * M�todo que retorna um texto contendo os dados da se��o transversal:
	 * identificador (id), �rea (A) e momento de in�rcia (I).
	 * 
	 * @return Texto contendo os dados da se��o transversal
	 */
	public String toString() {
		String texto;
		texto = this.id;
		texto += " / �rea = " + A;
		texto += " / In�rcia = " + I;
		return texto;
	}

}
