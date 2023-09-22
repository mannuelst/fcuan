class Start extends Phaser.Scene {
    constructor() {
        super({ key: "start" });
    }
    preload() {
        this.load.image("background", "./assets/img/estrada.png");
        this.load.image("play", "./assets/img/play.png");
    }

    create() {
        const { width, height } = this.scale;
        const textStyle = { color: "#fff", fontSize: 20 };

        // adicionando o plano de fundo
        this.add.image(width / 2, height / 2, "background");
        this.add.image(width / 2, 250, "play").setScale(4);

        this.add.text(20, 300, "Pressiona 'ESPAÇO' para Iniciar", textStyle);

        this.input.keyboard.once("keydown-SPACE", () => {
            this.scene.stop();
            this.scene.start("game");
        });
    }
    //  upload(){}
}

export default Start;
