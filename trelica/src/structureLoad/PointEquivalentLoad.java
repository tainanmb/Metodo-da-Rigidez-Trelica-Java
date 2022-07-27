package structureLoad;

import algebraLinear.Vetor;

/**
 * Interface que caracteriza o carregamento equivalente de uma carga pontual de
 * uma barra. descreve os m�todos necess�rios em classes cuja interface �
 * implementada. herdando os m�todos da classe EquivalentLoad.
 * 
 * @author Tainan Brand�o
 * @version 1.0
 */

public interface PointEquivalentLoad extends EquivalentLoad {

	/**
	 * M�todo abstrato de acesso Get que retorna as for�as equivalentes nos n�s
	 * iniciais e finais, oriundas de cargas concentradas aplicadas ao longo da
	 * barra.
	 * 
	 * @return EquivalentLoadFromP vetor com as for�as equivalentes devido as cargas
	 *         P
	 */
	public abstract Vetor getEquivalentLoadFromP();

}
