#include "partido_ls.h"

Eleitores_lsl *criarListaEleitores () {
    Eleitores_lsl *lista = (Eleitores_lsl*) malloc (sizeof (Eleitores_lsl));
    lista->cabeca = NULL;
    return lista;
}

int isVaziaListaEleitores (Eleitores_lsl lista) {
    return lista.cabeca == NULL;
}

Eleitor *criarEleitor (char nome [20], char genero, int idade, char bi [20]) {
    Pessoa nova;
    strcpy (nova.nome, nome);
    nova.genero = genero;
    nova.idade = idade;
    strcpy (nova.bi, bi);
    Eleitor *novo = (Eleitor*) malloc (sizeof (Eleitor));
    novo->num_eleitor = ++qtdEleitor;
    novo->pessoa = nova;
    novo->prox = NULL;
    return novo;
}

int addEleitor (Eleitores_lsl *lista, Eleitor *novo) {
    if (lista != NULL) {
            if (isVaziaListaEleitores (*lista))
                lista->cabeca = novo;
            else {
                Eleitor *aux = lista->cabeca;
                while (aux->prox != NULL)
                    aux = aux->prox;
                aux->prox = novo;
            }
        qtdEleitor++;
        return 1;
    }
    return 0;
}

int qtdEleitorLista (Eleitores_lsl lista) {
    Eleitor *aux = lista.cabeca;
    int i = 0;
    while (aux != NULL) {
        i++;
        aux = aux->prox;
    }
    return i;
}

Eleitor *remEleitor (Eleitores_lsl *lista) {
    if (lista != NULL && !isVaziaListaEleitores (*lista)) {
            Eleitor *aux = lista->cabeca;
            lista->cabeca = lista->cabeca->prox;
            aux->prox = NULL;
        return aux;
    }
    return NULL;
}

void showEleitor (Eleitor eleitor) {
    printf ("Numero: %d\n", eleitor.num_eleitor);
    printf ("Nome: %s\n", eleitor.pessoa.nome);
    printf ("Genero: %c\n", eleitor.pessoa.genero);
    printf ("Idade: %d\n", eleitor.pessoa.idade);
    printf ("BI: %s\n", eleitor.pessoa.bi);
}

void showEleitores (Eleitores_lsl lista) {
    puts ("************** LISTA DE ELEITORES **************");
    if (isVaziaListaEleitores (lista))
        puts ("Nenhum eleitor registado!!!");
    else {
        Eleitor *aux = lista.cabeca;
        while (aux != NULL) {
            showEleitor (*aux), puts ("");
            aux = aux->prox;
        }
    }
    system ("pause");
    system ("cls");
}

void menuEleitores (Eleitores_lsl *lista) {
    int opcao;
    char nome [20], genero, bi [20];
    int idade;
    do {
        puts ("************** MENU ELEITORES **************");
        puts ("1. Inserir");
        puts ("2. Listar");
        puts ("0. Voltar");
        printf ("Opcao: ");
        scanf ("%d", &opcao);
        system ("cls");
        switch (opcao) {
            case 1:
                puts ("************** INSERINDO ELEITOR **************");
                printf ("Nome: "); fflush (stdin); gets (nome);
                printf ("Genero: "); scanf ("%c", &genero);
                printf ("Idade: "); scanf ("%d", &idade);
                printf ("BI: "); fflush (stdin); gets (bi);
                if (addEleitor (lista, criarEleitor (nome, genero, idade, bi)))
                    puts ("Partido inserido.");
                else
                    puts ("Ouve falha na insercao do eleitor.");
                system ("pause");
                system ("cls");
            break;
            case 2:
                showEleitores (*lista);
            break;
        }
    } while (opcao != 0);
}
