#include "posto_ls.h"

void eleitoresPorIdade () {
    int idade, qtd = 0, opcao, idadeF;
    puts ("************** LISTA DE ELEITORES POR IDADE **************");
    printf ("Por intervalo de idade? sim (1) ou nao (2)");
    scanf ("%d", &opcao);
    if (1 != opcao) {
        printf ("Informe a idade: ");
        scanf ("%d", &idade);
        system ("cls");
        Eleitor *aux = listaEleit->cabeca;
        while (aux != NULL) {
            if (aux->pessoa.idade == idade)
                qtd++;
                aux = aux->prox;
        }
        if (qtd != 0) {
            aux = listaEleit->cabeca;
            puts ("************** LISTA DE ELEITORES POR IDADE **************");
            printf ("%d eleitores encontrados\n", qtd);
            while (aux != NULL) {
                if (aux->pessoa.idade == idade)
                    showEleitor (*aux), qtd++;
                aux = aux->prox;
            }
        }
        else {
            printf ("Nenhum resultado encontrado\n\n");
        }
    }
    else {
        printf ("Informe a idade de inicio: ");
        scanf ("%d", &idade);
        printf ("Informe a idade de fim: ");
        scanf ("%d", &idadeF);
        system ("cls");
        Eleitor *aux = listaEleit->cabeca;
        while (aux != NULL) {
            if (aux->pessoa.idade >= idade && aux->pessoa.idade <= idade)
                qtd++;
                aux = aux->prox;
            }
            if (qtd != 0) {
            aux = listaEleit->cabeca;
            puts ("************** LISTA DE ELEITORES POR IDADE **************");
            printf ("%d eleitores encontrados\n", qtd);
            while (aux != NULL) {
                if (aux->pessoa.idade >= idade && aux->pessoa.idade <= idade)
                    showEleitor (*aux), qtd++;
                aux = aux->prox;
            }
        }
        else
            printf ("Nenhum resultado encontrado\n\n");
    }
    system ("pause");
    system ("cls");
}

void eleitoresPorGenero () {
    int qtd = 0;
    char genero;
    puts ("************** LISTA DE ELEITORES POR GENERO **************");
    printf ("Informe o genero: ");
    fflush (stdin);
    scanf ("%c", &genero);
    system ("cls");
    Eleitor *aux = listaEleit->cabeca;
    while (aux != NULL) {
        if (aux->pessoa.genero == genero)
           qtd++;
        aux = aux->prox;
    }
    if (qtd != 0) {
        aux = listaEleit->cabeca;
        puts ("************** LISTA DE ELEITORES POR GENERO **************");
        printf ("%d eleitores encontrados\n", qtd);
        while (aux != NULL) {
            if (aux->pessoa.genero == genero)
                showEleitor (*aux), qtd++;
            aux = aux->prox;
        }
    }
    else
        printf ("Nenhum resultado encontrado\n\n");
    system ("pause");
    system ("cls");
}

/*void eleitoresPorLocalidade (Eleitores_lsl eleitores) {
    int qtd = 0;
    char localidade [20];
    puts ("************** LISTA DE ELEITORES POR LOCALIDADE **************");
    printf ("Informe a localidade: ");
    gets (localidade);
    system ("cls");
    Eleitor *aux = eleitores.cabeca;
    while (aux != NULL) {
        if (strcpy (aux->pessoa. == idade)
           qtd++;
    }
    if (qtd != 0) {
        aux = eleitores.cabeca;
        puts ("************** LISTA DE ELEITORES POR LOCALIDADE **************");
        printf ("%d eleitores encontrados\n", qtd);
        while (aux != NULL) {
            if (aux->pessoa.idade == idade)
                showEleitor (*aux), qtd++;
        }
    }
    else
        printf ("Nenhum resultado encontrado");
    system ("pause");
    system ("cls");
}
*/

void estatisticaEleitores (Eleitores_lsl eleitores)  {
    int opcao;
    do {
        puts ("************** ESTATISTICA ELEITORES **************");
        puts ("1. Listrar Eleitores Por Idade");
        puts ("2. Listrar Eleitores Por Genero");
        puts ("3. Listrar Eleitores Por Localidade");
        puts ("0. Sair");
        printf ("Opcao: ");
        fflush (stdin);
        scanf ("%d", &opcao);
        system ("cls");
        switch (opcao) {
            case 1:
                eleitoresPorIdade ();
            break;
            case 2:
                eleitoresPorGenero ();
            break;
            case 3:
                //eleitoresPorLocalidade ();
            break;
        }
    } while (opcao != 0);
}

void estatisticaVotos (Posto posto, Partlidos_ls partidos) {
    int i = 0, j = 0;
    Pilha pilha = *criarPilha (partidos);
    while (i < MAX_URNAS) { // percorre cada urna
        j = 0;
        while (j < posto.urna[i]->pilha->topo) { // percorre na quantidade de partidos
            pilha.votos [j].qtd_votos += posto.urna [i]->pilha->votos [j].qtd_votos;
            j++;
        }
        i++;
    }
    puts ("************** ESTATISTICA PARTIDOS **************");
    i = 0;
    while (i < pilha.topo) { // percorre na quantidade de partidos
        printf ("%d - %s\n", pilha.votos [i].partido.codigo, pilha.votos [i].partido.nome);
        printf ("Votos: %d\n\n", pilha.votos [i].qtd_votos);
        i++;
    }
    puts ("");
    //printf ("Partido com maior numero de votos: %d - %s\n", pilha.votos [i].partido.codigo, pilha.votos [i].partido.nome);
    //printf ("Votos: %d\n\n", pilha.votos [i].qtd_votos);
    system ("pause");
    system ("cls");
}

void resumo (Partlidos_ls partidos, Eleitores_lsl eleitores) {
    puts ("************** ESTATISTICA RESUMO **************");
    printf ("O partido com maior numero de votos: \n");
    printf ("Quantidade de eleitores: %d\n", qtdEleitorLista (*listaEleit));
    system ("pause");
    system ("cls");
}

void menuEstatistica (Posto posto, Partlidos_ls partidos, Eleitores_lsl eleitores) {
    int opcao;
    qtdEleitor = 0;
    do {
        puts ("************** RELATORIO **************");
        puts ("1. Resumo");
        puts ("2. Partidos");
        puts ("3. Eleitores");
        puts ("4. Voto");
        puts ("0. Sair");
        printf ("Opcao: ");
        fflush (stdin);
        scanf ("%d", &opcao);
        system ("cls");
        switch (opcao) {
            case 1:
                resumo (partidos, eleitores);
            break;
            case 2:
                //estatisticaPartidos (partidos);
            break;
            case 3:
                estatisticaEleitores (eleitores);
            break;
            case 4:
                estatisticaVotos (posto, partidos);
            break;
        }
    } while (opcao != 0);
}
