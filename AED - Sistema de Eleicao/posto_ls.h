#include "urna_ls.h"

Posto *criarPosto (Partlidos_ls lista) {
    Posto *posto = (Posto*) malloc (sizeof (Posto));
    strcpy (posto->denominacao, "POSTO_");
    int i = 0;
    while (i < MAX_URNAS) {
        posto->urna[i] = criarUrna (lista);
        posto->filas[i] = criarFila ();
        i++;
    }
    return posto;
}

int addEleitorPosto (Posto *posto, Eleitor *eleitor) {
    if (posto != NULL) {
        if (qtdEleitorFila (*(posto->filas[0])) <= qtdEleitorFila (*(posto->filas[1]))
         && qtdEleitorFila (*(posto->filas[1])) <= qtdEleitorFila (*(posto->filas[2])))
            return addEleitorFila (posto->filas [0], eleitor);
        if (qtdEleitorFila (*(posto->filas[1])) <= qtdEleitorFila (*(posto->filas[2])))
            return addEleitorFila (posto->filas [1], eleitor);
        return addEleitorFila (posto->filas [2], eleitor);
    }
    return 0;
}

int addEleitoresPosto (Posto *posto, Eleitores_lsl *lista) {
     if (posto != NULL && lista != NULL && !isVaziaListaEleitores (*lista)) {
        while (!isVaziaListaEleitores (*lista)) {
            addEleitorPosto (posto, remEleitor (lista));
        }
        return 1;
    }
    return 0;
}

void showPosto (Posto posto) {
    puts (posto.denominacao);
    puts ("---------------");
    puts ("FILA_1");
    showUrna (*(posto.urna [0]));
    showFila (*(posto.filas [0]));
    puts ("FILA_2");
    showUrna (*(posto.urna [1]));
    showFila (*(posto.filas [1]));
    puts ("FILA_3");
    showUrna (*(posto.urna [2]));
    showFila (*(posto.filas [2]));
    system ("pause");
    system ("cls");
}

int isVazioPosto (Posto posto) {
    return isVaziaFila (*(posto.filas [0])) && isVaziaFila (*(posto.filas [1])) && isVaziaFila (*(posto.filas [2]));
}

void menuPosto (Posto *posto, Partlidos_ls *partidos, Eleitores_lsl *listaEleitores) {
    int opcao;
    do {
        puts ("************** MENU POSTOS **************");
        puts ("1. Enfilar um");
        puts ("2. Enfilar todos");
        puts ("3. Mostrar Posto");
        puts ("0. Voltar");
        printf ("Opcao: ");
        scanf ("%d", &opcao);
        system ("cls");
        switch (opcao) {
            case 1:
                if (!isVaziaListaEleitores (*listaEleitores)) {
                    puts ("************** ENFILAMENTO UM A UM **************");
                    if (addEleitorPosto (posto, remEleitor (listaEleitores)))
                        puts ("Sucesso.");
                    else
                        puts ("Ouve falha.");
                    system ("pause");
                    system ("cls");
                }
                else {
                    puts ("************** EXCESSAO ELEITORES **************");
                    puts ("Lista de partidos ou eleitores vazio..");
                    system ("pause");
                    system ("cls");
                }
            break;
            case 2:
                if (!isVaziaListaEleitores (*listaEleitores)) {
                    puts ("************** ENFILAMENTO TOTAL **************");
                    if (addEleitoresPosto (posto, listaEleitores))
                        puts ("Sucesso.");
                    else
                        puts ("Ouve falha.");
                    system ("pause");
                    system ("cls");
                }
                else {
                    puts ("************** EXCESSAO ELEITORES **************");
                    puts ("Lista de partidos ou eleitores vazio..");
                    system ("pause");
                    system ("cls");
                }
            break;
            case 3:
                showPosto (*posto);
            break;
        }
    } while (opcao != 0);
}

