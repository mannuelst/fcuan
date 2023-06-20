#include "fila.h"

Pilha *criarPilha (Partlidos_ls lista) {
    Pilha *pilha = (Pilha*) malloc (sizeof (Pilha));
    pilha->topo = lista.qtd;
    int i = 0;
    while (i < MAX_PART) {
        pilha->votos [i].partido = lista.partidos [i];
        pilha->votos [i].qtd_votos = 0;
        i++;
    }
    return pilha;
}

Urna *criarUrna (Partlidos_ls lista) {
    Urna *urna = (Urna*) malloc (sizeof (Urna));
    urna->pilha = criarPilha (lista);
    return urna;
}

int votar (Pilha *pilha, int cod) {
    if (pilha != NULL) {
        int i = 0;
        while (i < pilha->topo) {
            if (cod == pilha->votos [i].partido.codigo) {
                pilha->votos [i].qtd_votos++;
                return 1;
            }
            i++;
        }
    }
    return 0;
}

void showUrna (Urna urna) {
    //puts (urna.denominacao);
    puts ("----------------\n");
    int i = 0;
    while (i < urna.pilha->topo) {
        printf ("%d - %s\n", urna.pilha->votos [i].partido.codigo, urna.pilha->votos [i].partido.nome);
        printf ("Votos: %d\n\n", urna.pilha->votos [i].qtd_votos);
        i++;
    }
}

void menuVoto (Posto *posto, Partlidos_ls partidos) {
    int opcao = 0;
    do {
        puts ("************** EFECTUAR VOTO **************");
        int codigo, fila;
        printf ("Informe a Fila (de 1 a %d): ", MAX_FILAS);
        scanf ("%d", &fila);
        if (fila >= 1 && fila <= MAX_FILAS) {
            if (!isVaziaFila (*(posto->filas [fila-1]))) {
                puts ("...");
                showPartidos (partidos);
                printf ("\nCodigo do Partido: ");
                scanf ("%d", &codigo);
                Eleitor *e = remEleitorFila (posto->filas [fila-1]);
                e->prox = NULL;
                if (NULL != e) {
                    addEleitor (listaEleit, e);
                    //free (e);
                    if (votar (posto->urna [fila-1]->pilha, codigo))
                        puts ("Voto efectuado");
                    else
                        puts ("Falha no voto");
                }
                else
                    puts ("Falha no voto");
            }
            else
                puts ("A fila esta vazia");
        }
        else
            puts ("A fila nao existe");
        puts ("...");
        system ("pause");
        system ("cls");
        puts ("************** EFECTUAR VOTO **************");
        printf ("Continuar a votar? sim (1) ou nao (0) ");
        scanf ("%d", &opcao);
        system ("cls");
    } while (0 != opcao);
}
