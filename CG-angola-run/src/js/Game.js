class Game extends Phaser.Scene {
    constructor() {
        super({ key: "game" });
    }
    init() {
        this.pontuacaoFinal = 0;
        this.bgVelocidade = 0;
        this.jogadorWidth = 120;
        this.jogadorHeight = 120;
        this.ultGerado = 0;
        this.ultMoedaGerado = 2000;
        this.timeOut = 2000; //abrandarTempo
        this.moedasTimeOut = 5000; //abrandar_moedas
        this.carros = this.physics.add.group();
        this.moedas = this.physics.add.group();
    }

    preload() {
        this.load.image("background", "./assets/img/estrada.png");
        this.load.spritesheet("jogador", "./assets/img/runner.png", {frameWidth: 120, frameHeight: 120,});
        this.load.image("moeda", "./assets/img/moeda.png");
        this.load.image("carro", "./assets/img/carro1.png");
        this.keys = this.input.keyboard.createCursorKeys();
    }

    create() {
        const { width, height } = this.scale;
        // adicionando o background
        this.background = this.add.tileSprite(
            width / 2,
            height / 2,
            width,
            height,
            "background"
        );
        // adicionando o JOGADOR setando sua posição e tamanho
        this.jogador = this.physics.add.sprite(width / 2, height - 50, "jogador");
        this.jogador.setScale(0.3);

        // adicionando o texto de pontuação
        this.scoreText = this.add.text(10, 40,
            `Pontos: ${this.pontuacaoFinal}`,
            {fontSize: 18,
            color: "#ffRepeat=ffff",
            }
        );
        //A

        this.anims.create({//
            key: "jogadorRun",
            frames: this.anims.generateFrameNumbers("jogador", {
                start: 0,
                end: 1,
            }),
            frameRate: 20,
            repeat: -1,
        });
    }

    update() {
        this.background.tilePositionY = this.bgVelocidade;
        this.move();

        if (this.time.now > this.ultGerado) {
            this.createCarro();

            this.ultGerado = this.time.now + this.timeOut;
        }

        if (this.time.now > this.ultMoedaGerado) {
            this.createmoeda();
            this.ultMoedaGerado = this.time.now + this.moedasTimeOut;
        }

        this.physics.add.overlap(
            this.jogador,
            this.carros,
            this.gameOver,
            null,
            this
        );

        this.physics.add.overlap(
            this.jogador,
            this.moedas,
            this.coletarMoedas,
            null,
            this
        );

        // Atualizando a posição do background (efeito de movimento)
        this.moverBG();
    }

    move() {
        this.jogador.play("jogadorRun", true);
        if (this.keys.left.isDown && this.jogador.x > this.jogadorWidth / 2) {
            this.jogador.setVelocityX(-200);
        } else if (
            this.keys.right.isDown &&
            this.jogador.x < this.scale.width - this.jogadorWidth / 2
        ) {
            this.jogador.setVelocityX(200);
        } else {
            this.jogador.setVelocityX(0);
        }
    }

    createCarro() {
        this.carros.create(
            Phaser.Math.Between(
                0.05 * this.scale.width,
                0.95 * this.scale.width
            ),
            -40,
            "carro"
        );
        this.carros.children.iterate((child) => {
            child.setScale(0.7);
            child.setVelocityY(500);
        });
        //Passar Tela
        this.carros.children.iterate((child) => {
            if (child && child.body.y > this.scale.height) child.destroy();
        });
    }

    createmoeda() {
        this.moedas.create(
            Phaser.Math.Between(
                0.05 * this.scale.width,
                0.95 * this.scale.width
            ),
            -40,
            "moeda"
        );
        this.moedas.children.iterate((child) => {
            child.setScale(0.2);
            child.setVelocityY(300);
        });

        this.moedas.children.iterate((child) => {
            if (child && child.body.y > this.scale.height) child.destroy();
        });
    }
    coletarMoedas(jogador, moeda) {
        this.moedas.killAndHide(moeda);
        this.moedas.remove(moeda);
        moeda.destroy();
        this.pontuacaoFinal += 100;
        this.scoreText.setText(`Pontos: ${this.pontuacaoFinal}`);
    }

    moverBG() {
        // Evitar overflow
        if (this.bgVelocidade === -10000) this.bgVelocidade = 0;
        this.bgVelocidade -= 3;
    }

    gameOver() {
        this.scene.stop();
        this.scene.start("gameover", { score: this.pontuacaoFinal });
    }
}

export default Game;
