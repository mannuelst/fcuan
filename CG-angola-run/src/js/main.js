
import Start from "./Start.js";
import Game from "./Game.js";
import GameOver from "./GameOver.js";

let config ={
  type: Phaser.AUTO,//ca
  width: 400,
  height: 600,
  parent: 'jogo',
  pixelArt: true,
  scene: [Start, Game, GameOver],
  physics: {
    default: "arcade",
    arcade: {
      gravity: {
        y: 0,
      },
      debug: false,
    },
  },
}

let game = new Phaser.Game(config)
