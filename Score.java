import java.awt.*;

/**
	Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
	instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
	de um player, quando a execução é iniciada.
*/

public class Score {

	/**
		Construtor da classe Score.
	*/

		private String playerId; //uma string que identifica o player ao qual este placar está associado.
		private Integer points; //um inteiro que indica quantidade de pontos (criado por mim)
	

	public Score(String playerId){
		this.playerId = playerId;
		this.points = 0;
	}

	/**
		Método de desenho do placar.
	*/

	public void draw(){
		if(this.playerId == "Player 1") {
			GameLib.drawText(this.playerId + ":" + this.points, 70, GameLib.ALIGN_LEFT);
		} else {
			GameLib.drawText(this.playerId + ":" + this.points, 70, GameLib.ALIGN_RIGHT);
		}
					
	}

	/**
		Método que incrementa em 1 unidade a contagem de pontos.
	*/

	public void inc(){
		this.points += 1;
	}

	/**
		Método que devolve a contagem de pontos mantida pelo placar.

		@return o valor inteiro referente ao total de pontos.
	*/

	public int getScore(){

		return this.points;
	}
}