package structureLoad;

/**
 * Classe que descreve um carregamento pontual em uma barra.
 * <p>
 * Dados:
 * <ul>
 * <li>id - Identificador
 * <li>pos - Posição do carregamento em relação ao nó inicial
 * <li>fx - Intensidade da força na direção do eixo x
 * <li>fy - Intensidade da força na direção do eixo y
 * <li>m - Intensidade do momento fletor em torno do eixo z
 * </ul>
 * 
 * @author Tainan Brandão
 * @version 1.0
 */

public class PointLoad {

	/**
	 * Declaração dos campos da classe.
	 */
	private String id;
	private double pos;
	private double fx;
	private double fy;
	private double m;

	/**
	 * Construtor sem parâmetro.
	 */
	public PointLoad() {
		this.id = null;
		this.pos = 0;
		this.fx = 0;
		this.fy = 0;
		this.m = 0;
	}

	/**
	 * Construtor que recebe argumentos e preenche todos os campos da carga pontual
	 * composta por forças ao longo do eixo x e y e momento fletor em torno do eixo
	 * z.
	 * 
	 * @param id  id - Identificador
	 * @param pos Posição do carregamento em relação ao nó inicial
	 * @param fx  Intensidade da força na direção do eixo x
	 * @param fy  Intensidade da força na direção do eixo y
	 * @param m   Intensidade do momento fletor em torno do eixo z
	 */
	public PointLoad(String id, double pos, double fx, double fy, double m) {
		this.id = id;
		this.pos = pos;
		this.fx = fx;
		this.fy = fy;
		this.m = m;
	}

	/**
	 * Método de acesso Get que retorna o identificador.
	 * 
	 * @return Identificador
	 */
	public String get_id() {
		return this.id;
	}

	/**
	 * Método de acesso Get que retorna a posição do carregamento em relação ao nó
	 * inicial.
	 * 
	 * @return Posição do carregamento em relação ao nó inicial
	 */
	public double get_Position() {
		return this.pos;
	}

	/**
	 * Método de acesso Get que retorna a intensidade da força na direção do eixo x.
	 * 
	 * @return Intensidade da força na direção do eixo x
	 */
	public double get_fx() {
		return this.fx;
	}

	/**
	 * Método de acesso Get que retorna a intensidade da força na direção do eixo y.
	 * 
	 * @return Intensidade da força na direção do eixo y
	 */
	public double get_fy() {
		return this.fy;
	}

	/**
	 * Método de acesso Get que retorna a intensidade do momento fletor em torno do
	 * eixo z
	 * 
	 * @return Intensidade do momento fletor em torno do eixo z
	 */
	public double get_momento() {
		return this.m;
	}

	/**
	 * Método modificador Set do identificador (sem retorno).
	 * 
	 * @param id identificador
	 */
	public void set_id(String id) {
		this.id = id;
	}

	/**
	 * Método modificador Set da posição da carga (sem retorno).
	 * 
	 * @param position posição da carga
	 */
	public void set_Position(double position) {
		this.pos = position;
	}

	/**
	 * Método modificador Set da força na direção do eixo x (sem retorno).
	 * 
	 * @param fx força na direção do eixo x
	 */
	public void set_fx(double fx) {
		this.fx = fx;
	}

	/**
	 * Método modificador Set da força na direção do eixo y (sem retorno).
	 * 
	 * @param fy força na direção do eixo y
	 */
	public void set_fy(double fy) {
		this.fy = fy;
	}

	/**
	 * Método modificador Set do momento fletor em torno do eixo z (sem retorno).
	 * 
	 * @param momento intensidade do momento fletor em torno do eixo z
	 */
	public void set_Momento(double momento) {
		this.m = momento;
	}

	/**
	 * Método que retorna um texto contendo os dados da carga pontual: identificador
	 * (id), posição do carregamento pontual (pos), intensidade das forças na
	 * direção do eixo x e y (fx e fy) e intensidade do momento fletor em torno do
	 * eizo z (m).
	 * 
	 * @return Texto contendo os dados da carga pontual
	 */
	public String toString() {
		String texto;
		texto = "\n ";
		texto += id;
		texto += "\n Posição do carregamento pontual: " + pos;
		texto += "\n Fx: " + fx;
		texto += "\n Fy: " + fy;
		texto += "\n Momento: " + m;
		return texto;
	}

}