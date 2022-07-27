package structureLoad;

/**
 * Classe que descreve um carregamento pontual em uma barra.
 * <p>
 * Dados:
 * <ul>
 * <li>id - Identificador
 * <li>pos - Posi��o do carregamento em rela��o ao n� inicial
 * <li>fx - Intensidade da for�a na dire��o do eixo x
 * <li>fy - Intensidade da for�a na dire��o do eixo y
 * <li>m - Intensidade do momento fletor em torno do eixo z
 * </ul>
 * 
 * @author Tainan Brand�o
 * @version 1.0
 */

public class PointLoad {

	/**
	 * Declara��o dos campos da classe.
	 */
	private String id;
	private double pos;
	private double fx;
	private double fy;
	private double m;

	/**
	 * Construtor sem par�metro.
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
	 * composta por for�as ao longo do eixo x e y e momento fletor em torno do eixo
	 * z.
	 * 
	 * @param id  id - Identificador
	 * @param pos Posi��o do carregamento em rela��o ao n� inicial
	 * @param fx  Intensidade da for�a na dire��o do eixo x
	 * @param fy  Intensidade da for�a na dire��o do eixo y
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
	 * M�todo de acesso Get que retorna o identificador.
	 * 
	 * @return Identificador
	 */
	public String get_id() {
		return this.id;
	}

	/**
	 * M�todo de acesso Get que retorna a posi��o do carregamento em rela��o ao n�
	 * inicial.
	 * 
	 * @return Posi��o do carregamento em rela��o ao n� inicial
	 */
	public double get_Position() {
		return this.pos;
	}

	/**
	 * M�todo de acesso Get que retorna a intensidade da for�a na dire��o do eixo x.
	 * 
	 * @return Intensidade da for�a na dire��o do eixo x
	 */
	public double get_fx() {
		return this.fx;
	}

	/**
	 * M�todo de acesso Get que retorna a intensidade da for�a na dire��o do eixo y.
	 * 
	 * @return Intensidade da for�a na dire��o do eixo y
	 */
	public double get_fy() {
		return this.fy;
	}

	/**
	 * M�todo de acesso Get que retorna a intensidade do momento fletor em torno do
	 * eixo z
	 * 
	 * @return Intensidade do momento fletor em torno do eixo z
	 */
	public double get_momento() {
		return this.m;
	}

	/**
	 * M�todo modificador Set do identificador (sem retorno).
	 * 
	 * @param id identificador
	 */
	public void set_id(String id) {
		this.id = id;
	}

	/**
	 * M�todo modificador Set da posi��o da carga (sem retorno).
	 * 
	 * @param position posi��o da carga
	 */
	public void set_Position(double position) {
		this.pos = position;
	}

	/**
	 * M�todo modificador Set da for�a na dire��o do eixo x (sem retorno).
	 * 
	 * @param fx for�a na dire��o do eixo x
	 */
	public void set_fx(double fx) {
		this.fx = fx;
	}

	/**
	 * M�todo modificador Set da for�a na dire��o do eixo y (sem retorno).
	 * 
	 * @param fy for�a na dire��o do eixo y
	 */
	public void set_fy(double fy) {
		this.fy = fy;
	}

	/**
	 * M�todo modificador Set do momento fletor em torno do eixo z (sem retorno).
	 * 
	 * @param momento intensidade do momento fletor em torno do eixo z
	 */
	public void set_Momento(double momento) {
		this.m = momento;
	}

	/**
	 * M�todo que retorna um texto contendo os dados da carga pontual: identificador
	 * (id), posi��o do carregamento pontual (pos), intensidade das for�as na
	 * dire��o do eixo x e y (fx e fy) e intensidade do momento fletor em torno do
	 * eizo z (m).
	 * 
	 * @return Texto contendo os dados da carga pontual
	 */
	public String toString() {
		String texto;
		texto = "\n ";
		texto += id;
		texto += "\n Posi��o do carregamento pontual: " + pos;
		texto += "\n Fx: " + fx;
		texto += "\n Fy: " + fy;
		texto += "\n Momento: " + m;
		return texto;
	}

}