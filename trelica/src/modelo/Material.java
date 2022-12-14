package modelo;

/**
 * Classe que descreve um material utilizado em barras que comp?e um modelo
 * estrutural reticulado espacial
 * <p>
 * Dados:
 * <ul>
 * <li>id - identificador do material
 * <li>E - m?dulo de elasticidade do material
 * <li>ni - coeficiente de Poisson do material
 * </ul>
 * 
 * @author Tainan Brand?o
 * @version 1.0
 */

public class Material {

	/**
	 * Declara??o dos campos da classe.
	 */
	private String id;
	private double E;
	private double ni;

	/**
	 * Construtor sem par?metro.
	 */
	public Material() {
		this.id = null;
		this.E = 0.0d;
		this.ni = 0.0d;
	}

	/**
	 * Construtor que recebe como argumento o identificador (id), o m?dulo de
	 * elasticidade (E) e o coeficiente de Poisson (ni) e preenche todos os campos
	 * do material.
	 * 
	 * @param id identificador do material
	 * @param E  m?dulo de elasticidade do material
	 * @param ni coeficiente de Poisson do material
	 */
	public Material(String id, double E, double ni) {
		this.id = id;
		this.E = E;
		this.ni = ni;
	}

	/**
	 * M?todo de acesso Get que retorna o identificador do material (id)
	 * 
	 * @return identificador do material
	 */
	public String get_id() {
		return id;
	}

	/**
	 * M?todo de acesso Get que retorna o m?dulo de elasticidade do material (E)
	 * 
	 * @return m?dulo de elasticidade do material
	 */
	public double get_E() {
		return E;
	}

	/**
	 * M?todo de acesso Get que retorna o coeficiente de Poisson do material (ni)
	 * 
	 * @return coeficiente de Poisson do material
	 */
	public double get_ni() {
		return ni;
	}

	/**
	 * M?todo modificador Set do identificador do material (id) (sem retorno)
	 * 
	 * @param id identificador do material
	 */
	public void set_id(String id) {
		this.id = id;
	}

	/**
	 * M?todo modificador Set do m?dulo de elasticidade do material (E) (sem
	 * retorno)
	 * 
	 * @param E m?dulo de elasticidade do material
	 */
	public void set_E(double E) {
		this.E = E;
	}

	/**
	 * M?todo modificador Set do coeficiente de poisson do material (ni) (sem
	 * retorno)
	 * 
	 * @param ni coeficiente de poisson do material
	 */
	public void set_ni(double ni) {
		this.ni = ni;
	}

	/**
	 * M?todo que retorna um texto contendo os dados do material: identificador
	 * (id), m?dulo de elasticidade (E) e coeficiente de Poisson (ni).
	 * 
	 * @return Texto contendo os dados do material
	 */
	public String toString() {
		String texto;
		texto = this.id;
		texto += " / E = " + E;
		texto += " / poisson = " + ni;
		return texto;
	}

}