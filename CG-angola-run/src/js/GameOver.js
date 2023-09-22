
class GameOver extends Phaser.Scene {
  constructor() {
    super("gameover");
  }
  init(data) {
    this.pontuacaoFinal = data.score;
  }

  preload() {

    this.load.image("gameOver", "./assets/img/gameover.png");

    this.load.image("restart", "./assets/img/reiniciar.png");
  }

  create() {

    const { width, height } = this.scale;
    const style = { color: "#ffffff", fontSize: 24 };


    this.add.image(width / 2, height / 2, "background");
    this.add.image(width / 2, 150, "gameOver").setScale(0.5);

    this.add
      .text(width / 2, 350, `Sua Pontuação: ${this.pontuacaoFinal}`, style)
      .setOrigin(0.5);


    const restart = this.add
      .image(width/2, 400, "restart")
      .setScale(1)
      .setInteractive();

    restart.on("pointerover", () => {
      restart.setScale(0.9);
    });
    restart.on("pointerout", () => {
      restart.setScale(0.4);
    });
    restart.on("pointerdown", () => {
      this.scene.stop("game");
      this.scene.start("game");

    });
  }
}
export default GameOver