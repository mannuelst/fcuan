#include "eleitor_lsl.h"

Eleitores_fila *criarFila () {
    Eleitores_fila *fila = (Eleitores_fila*) malloc (sizeof (Eleitores_fila));
    fila->primeiro = fila->ultimo = NULL;
    return fila;
}

int isVaziaFila (Eleitores_fila fila) {
    return fila.primeiro == fila.ultimo && fila.primeiro == NULL;
}

int addEleitorFila (Eleitores_fila *fila, Eleitor *novo) {
    if (fila != NULL) {
            if (isVaziaFila (*fila))
                fila->primeiro = fila->ultimo = novo;
            else {
                fila->ultimo->prox = novo;
                fila->ultimo = novo;
            }
        return 1;
    }
    return 0;
}

Eleitor *remEleitorFila (Eleitores_fila *fila) {
    if (fila != NULL && !isVaziaFila (*fila)) {
            Eleitor *aux = fila->primeiro;
            fila->primeiro = fila->primeiro->prox;
            aux->prox = NULL;
            if (fila->primeiro == NULL) fila->ultimo = NULL;
        return aux;
    }
    return NULL;
}

int qtdEleitorFila (Eleitores_fila fila) {
    int i = 0;
    Eleitor *aux = fila.primeiro;
    while (aux != NULL) {
        i++;
        aux = aux->prox;
    }
    return i;
}

void showFila (Eleitores_fila fila) {
    if (isVaziaFila (fila))
        puts ("Fila vazia!!!");
    else {
        Eleitor *aux = fila.primeiro;
        while (aux != NULL) {
            printf ("Eleitor: %d - %s\n", aux->num_eleitor, aux->pessoa.nome);
            aux = aux->prox;
        }
    }
    puts ("--------------------------------\n");
}

