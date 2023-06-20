#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_PART 3
#define MAX_FILAS 3
#define MAX_URNAS 3

typedef struct {
    int codigo;
    char nome [20];
} Partido;

typedef struct {
    Partido partidos [MAX_PART];
    int qtd;
} Partlidos_ls;

typedef struct {
    char nome [20];
    char genero;
    int idade;
    char bi [20];
} Pessoa;

typedef struct no_eleitor {
    int num_eleitor;
    Pessoa pessoa;
    struct no_eleitor *prox;
} Eleitor;

typedef struct {
    Eleitor *cabeca;
} Eleitores_lsl;

typedef struct {
    Eleitor *primeiro, *ultimo;
} Eleitores_fila;

typedef struct {
    Partido partido;
    int qtd_votos;
} Voto;

typedef struct {
    Voto votos [MAX_PART];
    int topo;
} Pilha;

typedef struct {
    char denominacao [20];
    Pilha *pilha;
} Urna;

typedef struct {
    char denominacao [20];
    Urna *urna [MAX_URNAS];
    Eleitores_fila *filas [MAX_FILAS];
} Posto;

Eleitores_lsl *listaEleit;
int qtdEleitor;
int ativo = 0;


Partlidos_ls *criarListaPartidos ();
int isVaziaListaPartidos (Partlidos_ls lista);
int isCheiaListaPartidos (Partlidos_ls lista);
int addPartido (Partlidos_ls *lista, char nome [20]);
void showPartidos (Partlidos_ls lista);
void showPartido (Partlidos_ls lista, int codigo);
void menuPartidos (Partlidos_ls *lista);

Eleitores_lsl *criarListaEleitores ();
int isVaziaListaEleitores (Eleitores_lsl lista);
int addEleitor (Eleitores_lsl *lista, Eleitor *novo);
Eleitor *remEleitor (Eleitores_lsl *lista);
void showEleitor (Eleitor eleitor);
void showEleitores (Eleitores_lsl lista);
void menuEleitores (Eleitores_lsl *lista);

Eleitores_fila *criarFila ();
int isVaziaFila (Eleitores_fila fila);
int addEleitorFila (Eleitores_fila *fila, Eleitor *novo);
Eleitor *remEleitorFila (Eleitores_fila *fila);
void showFila (Eleitores_fila fila);

Posto *criarPosto ();
void showPosto (Posto posto);
int addEleitorPosto (Posto *posto, Eleitor *eleitor);
int addEleitoresPosto (Posto *posto, Eleitores_lsl *lista);
int isVazioPosto (Posto posto);
void menuPosto (Posto *posto, Partlidos_ls *partidos, Eleitores_lsl *listaEleitores);

int votar (Pilha *pilha, int cod);
void showUrna (Urna urna);
Urna *criarUrna (Partlidos_ls lista);
void menuVoto (Posto *posto, Partlidos_ls partidos);
Pilha *criarPilha (Partlidos_ls lista);

void menuEstatistica (Posto posto, Partlidos_ls partidos, Eleitores_lsl eleitores);
void resumo (Partlidos_ls partidos, Eleitores_lsl eleitores);

void estatisticaEleitores (Eleitores_lsl eleitores);
void eleitoresPorIdade ();
void eleitoresPorGenero ();

void estatisticaPartidos (Partlidos_ls partidos);
void estatisticaVotos (Posto posto, Partlidos_ls partidos);

void menuPrincipal () {
    Partlidos_ls *lista_partidos = criarListaPartidos ();
    Eleitores_lsl *lista_eleitores = criarListaEleitores ();
    Posto *posto;
    listaEleit = criarListaEleitores ();

    int opcao;
    qtdEleitor = 0;
    do {
        puts ("************** MENU PRINCIPAL **************");
        puts ("1. Menu Partidos");
        puts ("2. Menu Eleitores");
        puts ("3. Menu Postos");
        puts ("4. Efectuar voto");
        puts ("5. Relatorio");
        puts ("6. Sobre");
        puts ("0. Sair");
        printf ("Opcao: ");
        fflush (stdin);
        scanf ("%d", &opcao);
        system ("cls");
        switch (opcao) {
            case 1:
                menuPartidos (lista_partidos);
                if (!isVaziaListaPartidos (*lista_partidos))
                    posto = criarPosto (*lista_partidos);
            break;
            case 2:
                if (!isVaziaListaPartidos (*lista_partidos))
                    menuEleitores (lista_eleitores);
                else {
                    puts ("************** EXCESSAO ELEITORES **************");
                    puts ("Registar partidos primeiro.");
                    system ("pause");
                    system ("cls");
                }
            break;
            case 3:
                if (!isVaziaListaPartidos (*lista_partidos))
                    menuPosto (posto, lista_partidos, lista_eleitores);
                else {
                    puts ("************** EXCESSAO ELEITORES **************");
                    puts ("Registar partidos primeiro.");
                    system ("pause");
                    system ("cls");
                }
            break;
            case 4:
                if (!isVaziaListaPartidos (*lista_partidos))
                    menuVoto (posto, *lista_partidos);
                else {
                    puts ("************** EXCESSAO ELEITORES **************");
                    puts ("Registar partidos primeiro.");
                    system ("pause");
                    system ("cls");
                }
            break;
            case 5:
                menuEstatistica (*posto, *lista_partidos, *lista_eleitores);
            break;
            case 6:
                //menuEstatistica ();
            break;
        }
    } while (opcao != 0);
}
