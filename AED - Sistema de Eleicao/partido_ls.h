#include "estruturas.h"

Partlidos_ls *criarListaPartidos () {
    Partlidos_ls *lista = (Partlidos_ls*) malloc (sizeof (Partlidos_ls));
    lista->qtd = 0;
    return lista;
}

int isVaziaListaPartidos (Partlidos_ls lista) {
    return lista.qtd == 0;
}

int isCheiaListaPartidos (Partlidos_ls lista) {
    return lista.qtd == MAX_PART;
}

int addPartido (Partlidos_ls *lista, char nome [20]) {
    if (lista != NULL && !isCheiaListaPartidos (*lista)) {
        strcpy (lista->partidos[lista->qtd].nome, nome);
        lista->partidos[lista->qtd].codigo = lista->qtd+1;
        lista->qtd++;
        return 1;
    }
    return 0;
}

void showPartidos (Partlidos_ls lista) {
    int i = 0;
    if (isVaziaListaPartidos (lista))
        puts ("Nenhum partido registado!!!");
    else {
        while (i < lista.qtd) {
            printf ("%d - %s\n", (lista.partidos [i].codigo), (lista.partidos [i].nome));
            i++;
        }
    }
}

void showPartido (Partlidos_ls lista, int codigo) {
    int i = 0;
    puts ("************** PESQUISAR PARTIDO **************");
    if (isVaziaListaPartidos (lista))
        puts ("Nenhum partido registado!!!");
    else {
        while (i < lista.qtd) {
            if (lista.partidos [i].codigo == codigo) {
                puts ("Partido encontrado...\n");
                printf ("Codigo: %d\n", lista.partidos [i].codigo);
                printf ("Nome: %s\n", lista.partidos [i].nome);
                system ("pause");
                system ("cls");
                return;
            }
            i++;
        }
        puts ("Partido nao registado!!!");
    }
    system ("pause");
    system ("cls");
}

void menuPartidos (Partlidos_ls *lista) {
    int opcao, codigo;
    char nome [20];
    do {
        puts ("************** MENU PARTIDOS **************");
        puts ("1. Inserir");
        puts ("2. Consultar");
        puts ("3. Listar");
        puts ("0. Voltar");
        printf ("Opcao: ");
        scanf ("%d", &opcao);
        system ("cls");
        switch (opcao) {
            case 1:
                puts ("************** INSERINDO PARTIDO **************");
                printf ("Nome: ");
                fflush (stdin);
                gets (nome);
                if (addPartido (lista, nome))
                    puts ("Partido inserido.");
                else
                    puts ("Ouve falha na insercao do partido.");
                system ("pause");
                system ("cls");
            break;
            case 2:
                puts ("************** CONSULTAR PARTIDO **************");
                printf ("Codigo: ");
                scanf ("%d", &codigo);
                system ("cls");
                showPartido (*lista, codigo);
            break;
            case 3:
                puts ("************** LISTA DE PARTIDOS **************");
                showPartidos (*lista);
                system ("pause");
                system ("cls");
            break;
        }
    } while (opcao != 0);
}
