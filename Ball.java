import java.awt.*;
import java.util.Random;

/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {

	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.
	*/

		private double cx; //coordenada x da posição inicial da bola (centro do retangulo que a representa).
		private double cy; //coordenada y da posição inicial da bola (centro do retangulo que a representa).
		private double width; //largura do retangulo que representa a bola.
		private double height; //altura do retangulo que representa a bola.
		private Color color; //cor da bola.
		private double speed; //velocidade da bola (em pixels por millisegundo).
		private double directionX; //Direção inicial x
		private double directionY; //Direção inicial y
	

	public Ball(double cx, double cy, double width, double height, Color color, double speed){
		this.cx = cx; //coordenada x da posição inicial da bola (centro do retangulo que a representa).
		this.cy = cy; //coordenada y da posição inicial da bola (centro do retangulo que a representa).
		this.width = width; //largura do retangulo que representa a bola.
		this.height = height; //altura do retangulo que representa a bola.
		this.color = color; //cor da bola.
		this.speed = speed; //velocidade da bola (em pixels por millisegundo).

		Random gerador = new Random();
		this.directionX = gerador.nextInt(2);
		this.directionY = gerador.nextInt(2);

		if(this.directionX == 0) {
			this.directionX = - Math.abs(speed);
		}
		if(this.directionY == 0) {
			this.directionY = - Math.abs(speed);
		}
		if(this.directionX == 1) {
			this.directionX = Math.abs(speed);
		}
		if(this.directionY == 1) {
			this.directionY = Math.abs(speed);
		}
	}

	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/

	public void draw(){

		GameLib.setColor(this.color);
		GameLib.fillRect(this.cx, this.cy, this.width, this.height);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){
		this.cy = this.cy + (delta*this.directionY);
		this.cx = this.cx + (delta*this.directionX);
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/

	public void onPlayerCollision(String playerId){
		if(playerId == "Player 1") {
			this.directionX = Math.abs(speed);
		}
		if(playerId == "Player 2") {
			this.directionX = - Math.abs(speed);
		}
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId){
		if(wallId == "Bottom") {
			this.directionY = - Math.abs(speed);
		} else if(wallId == "Top") {
			this.directionY = Math.abs(speed);
		} else if(wallId == "Left") {
			this.directionX = Math.abs(speed);
		} else {
			this.directionX = - Math.abs(speed);
		}
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	
	public boolean checkCollision(Wall wall){
		if(wall.getId() == "Bottom") {
			if((this.cy + (this.height/2)) >= (wall.getCy() - (wall.getHeight()/2))) return true;
		} else if(wall.getId() == "Top") {
			if((this.cy - (this.height/2)) <= (wall.getCy() + (wall.getHeight()/2))) return true;
		} else if(wall.getId() == "Left") {
			if((this.cx - (this.width/2)) <= (wall.getCx() + (wall.getWidth()/2))) return true;
		} else if(wall.getId() == "Right") {
			if((this.cx + (this.width/2)) >= (wall.getCx() - (wall.getWidth()/2))) return true;
		} 

		return false;
	}

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){

		if(player.getId().equals("Player 1")) {
			if((player.getCx() + (player.getWidth()/2)) >= (this.cx - (this.width/2))) {
				if((this.cy - this.height <= player.getCy() +(player.getHeight()/2)) && (player.getCy() - (player.getHeight()/2) <= this.height + this.cy)) return true;
			}
		}
		if(player.getId().equals("Player 2")) {
			if((player.getCx() - (player.getWidth()/2)) <= (this.cx + (this.width/2))) {
				if((this.cy - this.height <= player.getCy() + (player.getHeight()/2)) && (player.getCy() - (player.getHeight()/2) <= this.height + this.cy)) return true;
			}
		}

		return false;
	}

	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){

		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/

	public double getCy(){

		return this.cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed(){

		return this.speed;
	}

}