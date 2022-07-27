package modelo;

/**
 * Interface que caracteriza o comportamento de uma se��o transversal qualquer,
 * descreve os m�todos necess�rios em classes cuja interface � implementada.
 * 
 * @author Tainan Brand�o
 * @version 1.0
 */

public interface SecaoTransversal { 

	/**
	 * M�todo de acesso Get que retorna o identificador da se��o transversal (id).
	 * 
	 * @return identificador da se��o transversal
	 */
	public String get_id();

	/**
	 * M�todo de acesso Get que retorna a �rea da se��o transversal (A).
	 * 
	 * @return �rea da se��o transversal
	 */
	public double get_A();

	/**
	 * M�todo de acesso Get que retorna o momento de in�rcia da se��o transversal
	 * (I).
	 * 
	 * @return momento de in�rcia da se��o transversal
	 */
	public double get_I();

	/**
	 * M�todo modificador Set do identificador da se��o transversal (id) (sem
	 * retorno).
	 * 
	 * @param id identificador da se��o transversal
	 */
	public void set_id(String id);

	/**
	 * M�todo modificador Set da �rea da se��o transversal (A) (sem retorno).
	 * 
	 * @param A �rea da se��o transversal
	 */
	public void set_A(double A);

	/**
	 * M�todo modificador Set do momento de in�rcia da se��o transversal (I) (sem
	 * retorno).
	 * 
	 * @param I momento de in�rcia da se��o transversal
	 */
	public void set_I(double I);

	/**
	 * M�todo que retorna um texto contendo os dados da se��o transversal:
	 * identificador (id), �rea (A) e momento de in�rcia (I).
	 * 
	 * @return Texto contendo os dados da se��o transversal
	 */
	public String toString();

}
