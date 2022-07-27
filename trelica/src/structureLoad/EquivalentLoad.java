package structureLoad;

import algebraLinear.Vetor;

/**
 * Interface que caracteriza o carregamento equivalente de uma barra. descreve
 * os m�todos necess�rios em classes cuja interface � implementada.
 * 
 * @author Tainan Brand�o
 * @version 1.0
 */

public interface EquivalentLoad {

	/**
	 * M�todo abstrato de acesso Get que retorna as for�as equivalentes nos n�s
	 * iniciais e finais, oriundas do somat�rio de cargas pontuais e distribu�das
	 * aplicadas ao longo da barra no sistema de coordenadas local.
	 * 
	 * @return EquivalentLoadLocal vetor com as for�as equivalentes total no sistema
	 *         local
	 */
	public abstract Vetor getEquivalentLoadLocal();

	/**
	 * M�todo abstrato de acesso Get que retorna as for�as equivalentes nos n�s
	 * iniciais e finais, oriundas do somat�rio de cargas pontuais e distribu�das
	 * aplicadas ao longo da barra no sistema de coordenadas global.
	 * 
	 * @return EquivalentLoadLocal vetor com as for�as equivalentes total no sistema
	 *         global
	 */
	public abstract Vetor getEquivalentLoadGlobal();

}
