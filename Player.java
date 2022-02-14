import java.awt.*;

/**
	Esta classe representa os jogadores (players) do jogo. A classe princial do jogo (Pong)
	instancia dois objetos deste tipo quando a execução é iniciada.
*/

public class Player {

	/**
		Construtor da classe Player.
	*/
		
		private double cx; //coordenada x da posição inicial do player (centro do retangulo que o representa).
		private double cy; //coordenada y da posição inicial do player (centro do retangulo que o representa).
		private double width; //largura do retangulo que representa o player.
		private double height; //altura do retangulo que representa o player.
		private Color color; //cor do player.
		private String id; //uma string que identifica o player
		private double[] v_limit; //um array de double contendo dois valores (em pixels) que determinam os limites verticais da área útil da quadra.   
		private double speed; //velocidade do movimento vertical do player (em pixels por millisegundo).
	

	public Player(double cx, double cy, double width, double height, Color color, String id, double [] v_limit, double speed){
		this.cx = cx; 
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.id = id;
		this.v_limit = v_limit;
		this.speed = speed;
	}

	/**
		Método chamado sempre que o player precisa ser (re)desenhado.
	*/

	public void draw(){

		GameLib.setColor(this.color);
		GameLib.fillRect(this.cx, this.cy, this.width, this.height);
	}

	/**
		Método chamado quando se deseja mover o player para cima. 
		Este método é chamado sempre que a tecla associada à ação 
		de mover o player para cima estiver pressionada.

		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void moveUp(long delta){
		double distance = this.speed * delta;
		double top = this.cy - (this.height/2);

		if(top - distance <= v_limit[0]) {
			this.cy = v_limit[0] + (this.height/2);
		} else {
			this.cy = this.cy - distance;
		}
	}

	/**
		Método chamado quando se deseja mover o player para baixo. 
		Este método é chamado sempre que a tecla associada à ação 
		de mover o player para baixo estiver pressionada.

		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void moveDown(long delta){
		double distance = this.speed * delta;
		double bottom = this.cy + (this.height/2);

		if(bottom + distance >= v_limit[1]) {
			this.cy = v_limit[1] - (this.height/2);
		} else {
			this.cy = this.cy + distance;
		}
	}

	/**
		Método que devolve a string de identificação do player.
		@return a String de indentificação.
	*/

	public String getId() { 

		return this.id; 
	}

	/**
		Método que devolve a largura do retangulo que representa o player.
		@return um double com o valor da largura.
	*/

	public double getWidth() { 

		return this.width; 
	}

	/**
		Método que devolve a algura do retangulo que representa o player.
		@return um double com o valor da altura.
	*/

	public double getHeight() { 

		return this.height;
	}

	/**
		Método que devolve a coordenada x do centro do retangulo que representa o player.
		@return o valor double da coordenada x.
	*/

	public double getCx() { 
		
		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retangulo que representa o player.
		@return o valor double da coordenada y.
	*/

	public double getCy() { 
	
		return this.cy;
	}
}