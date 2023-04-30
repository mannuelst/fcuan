module Models.MKopBank where

    import Models.AuxiliarFile
    import Models.MKconstrutores
    import Models.MKauxiliar
    import Data.Char 
    import Data.Time
        -- FUNÇÃO PARA CADASTRAR UM CLIENTE
    cadastro :: IO ()
    cadastro = do 
                    getChar
                    limpaTela
                    lista <-lerClientes
                    putStrLn " CADASTRAMENTO \n"
                    nome <- setNomeCompleto; tel <- setTelefone; dia<-setDataNasc; bi <- setBilhete lista
                    let codigo = setNumDeConta bi
                    horasSistema <- getZonedTime
                    let dados=(cadastrarCliente lista (codigo) nome dia  bi  tel 0.0 (take 10 (show horasSistema)))
                    putStr ("\n\tNOME: "++nome++"      CODIGO: "++(show codigo)++"    DATA DE NASCIMENTO: "++dia++"    BI: "++bi++"    TELEFONE: "++tel++"Deseja Salvar?\n\n\t1 - SIM\t\t2 - ALTERAR\n\t\tOutra tecla para terminar...: ")
                    opc <- getLine
                    case opc of
                                "1" -> do
                                        espera "\n\t\tCadastrando ."
                                        escreverNoArquivo clientesFile (listLinhasClientes (dados)) 
                                        putStr "\tConta criada com sucesso\n"
                                "2" -> cadastro
                                _ -> saindo
                    putStr "\n\tDigite ENTER para continuar: "; getChar; putStrLn ""      
    
    -- RELATORIO DE TODO CADASTROS 
    relactoriosCadas::Int->IO ()
    relactoriosCadas opcao = do limpaTela; lista <- lerClientes;
                                if(lista/=[]) 
                                    then do
                                        if(opcao ==1) 
                                            then putStrLn (" LISTAR TODOS CADASTROS \n\n"++(todosClientes lista)++"\n");
                                            else do
                                                    putStr (" LISTAR TODOS CADASTROS \n\n\t1 - Hoje\t2 - Qualquer Dia: ")
                                                    opc <- getLine
                                                    case opc of 
                                                        "1" -> do
                                                                putStr (" LISTAR TODOS CADASTROS DE HOJE \n\n")
                                                                horasSistema <- getZonedTime;
                                                                if((contCadastroDia lista (take 10 (show horasSistema)))>0) then putStrLn (clientesDiario lista (take 10 (show horasSistema))) else putStrLn "\tLista Vazia"
                                                        "2" -> do
                                                                putStr (" LISTAR TODOS CADASTROS \n\n\t")
                                                                dia <- getDia
                                                                mes <- getMes
                                                                if((contCadastroDia lista ("2021-"++(menorQue10 mes)++"-"++(menorQue10 dia)))>0) then putStrLn (clientesDiario lista ("2021-"++(menorQue10 mes)++"-"++(menorQue10 dia))) else putStrLn "\t\tLista Vazia"
                                                        otherwise -> relactoriosCadas opcao
                                    else putStrLn " LISTAR TODOS CADASTROS \n\t\tLista Vazia\n"

    deposito :: IO ()
    deposito = do 
                getChar 
                limpaTela 
                cli<- lerClientes
                putStr "\n DEPOSÍTO \n\n\tInforme o número da conta para qual deseja depositar: "
                conta <- getLine; valor <- getValorDeposito; horasSistema <- getZonedTime --PEGAR A HORA DO SISTEMA 
                if((read (valor))>0) 
                then do
                        if(not(existeCodigo cli (read conta))) 
                            then do
                                    putStr "\n\tConta não existe!!\n\tDESEJA DEPOSITAR NOVAMENTE?\n\t1 - SIM\t2 - NÃO: "
                                    opcao <- getLine
                                    if(opcao=="1") then deposito else saindo
                            else do
                                    escreverNoArquivo clientesFile (upDateList2 (listLinhasClientes (inserirValor (cli) (read conta) (read valor))));
                                    escrever_arquivo depoFile (conta++","++valor++","++(take 10 (show horasSistema))++","++(take 8( drop 11(show horasSistema)))++"|") "";
                                    espera ("\tDepositando ."); cli2<-lerClientes;
                                    putStrLn ("\n"++(listUmCliente cli2 (read conta))++"\n\tDeposito feito com sucesso");
                else do
                        putStr "\n\tDeposito não efeituado\n\tNão é permitido o deposito de valores a baixa de 100kz\n\tDESEJA DEPOSITAR NOVAMENTE?\t1 - SIM\t2 - NÃO: "
                        opcao <- getLine
                        if (opcao=="1") then deposito else saindo
                putStr "\n\tDigite ENTER para continuar\n\t"; getChar; putStrLn ""      


    -- FUNÇÃO PARA PEDIR O VALOR DO DEPOSITO
    getValorDeposito::IO String
    getValorDeposito=do putStr "\tInforme o valor que deseja depositar: "
                        valor<- getLine
                        if((length valor)>=3 && (all isDigit valor) && (read valor)>=100) then return valor
                        else do
                                putStr "\tVALOR  INVALIDO!\n\tSó é possivel DEPOSITAR de 100kz pra cima\n\tDESEJA LER NOVAMENTE?\n\t1 - Sim\t\t2 - Não: "
                                opcao<-getLine
                                if(opcao=="1") then getValorDeposito else return ("0")                    
    -- RELATORIO OU ESTATISTICA DE TODOS DEPOSITOS
    relactorioDeposi::Int->IO ()
    relactorioDeposi opcao = do limpaTela; lista <- lerDeposito;
                                if(lista/=[]) 
                                then do
                                        if(opcao ==1) 
                                            then do
                                                    putStrLn (" LISTAR TODOS DEPOSITOS \n\n"++(todosDositos lista)++"\n");
                                            else do
                                                    putStr (" LISTAR TODOS DEPOSITOS \n\n\t1 - Hoje\t2 - Qualquer Dia: ")
                                                    opc <- getLine
                                                    case opc of 
                                                        "1" -> do
                                                                putStr (" LISTAR TODOS DEPOSITOS DE HOJE \n\n\t")
                                                                horasSistema <- getZonedTime;
                                                                if((contDepositosDia lista (take 10 (show horasSistema)))>0) then putStrLn (depositoDiaria lista (take 10 (show horasSistema))) else putStrLn "\t\tLista Vazia"
                                                        "2" -> do
                                                                putStr (" LISTAR TODOS DEPOSITOS \n\n\t")
                                                                dia <- getDia
                                                                mes <- getMes
                                                                if((contDepositosDia lista ("2021-"++(menorQue10 mes)++"-"++(menorQue10 dia)))>0) then putStrLn (depositoDiaria lista ("2021-"++(menorQue10 mes)++"-"++(menorQue10 dia))) else putStrLn "\t\tLista Vazia"
                                                        otherwise -> relactorioDeposi opcao
                                else putStrLn "\n\tLista Vazia\n"
    levantamento :: IO ()
    levantamento = do 
                    list<- lerClientes
                    getChar
                    limpaTela
                    putStr " LEVANTAMENTO \n\n\tInforme o seu número de conta: "
                    conta <- getLine
                    valor <- getValorLevantar
                    horasSistema <- getZonedTime --PEGAR A HORA DO SISTEMA 
                    if(read valor >0) 
                            then do
                                    if(existeCodigo list (read conta)) 
                                    then do
                                            if((getSaldo (list) (read conta))>=(read valor))
                                                then do
                                                        escreverNoArquivo clientesFile (upDateList2 (listLinhasClientes (inserirValor (list) (read conta) (-(read valor)))));
                                                        escrever_arquivo levantFile (conta++","++valor++","++(take 10 (show horasSistema))++","++(take 8( drop 11(show horasSistema)))++"|") "";
                                                        espera "\n\t\tLevantando .";
                                                        putStrLn ("\n\t\tlevantamento feito com sucesso!\n\n"++tirarValorLevantar (list) (read valor) (read conta));
                                                else do
                                                        putStr ("\n\tImposivel tirar essa quantia porque o teu saldo atual é de: "++(show (getSaldo (list) (read conta)))++"\nDESESJA LEVANTAR NOVAMENTE?\n\t1 - SIM\t2 - NÃO:")
                                                        opcao <- getLine
                                                        if(opcao=="1") then levantamento else saindo
                                    else do
                                            putStr ("\t\tConta não existe!!\n\tDESESJA LEVANTAR NOVAMENTE?\n\t1 - SIM\t2 - NÃO: ")
                                            opcao <- getLine
                                            if(opcao=="1") then levantamento else saindo
                            else saindo
                    putStr "\n\tDigite ENTER para continuar\n\t "; getChar; putStrLn ""  
    -- FUNÇÃO PARA PEDIR O VALOR DO LEVANTAMENTO
    getValorLevantar:: IO String
    getValorLevantar=do 
                        putStr "\tInforme o valor que deseja Levantar: "
                        valor<- getLine
                        if((length valor)>=4 && (all isDigit valor) && (read valor)>=1000) then return valor
                        else do
                                putStr "\t\tVALOR  INVALIDO!\n\t\tSó é possivel LEVANTAR  de 1000kz pra cima\n\t\tDESEJA LER NOVAMENTE?\n\t1 - Sim\t\t2 - Não\n\t\t==> "
                                opcao<-getLine
                                if(opcao=="1") then getValorLevantar else return ("0")
    -- RELATORIO OU ESTATISTICA DE TODOS LEVANTAMENTO
    relactoriosLevan::Int->IO ()
    relactoriosLevan opcao = do limpaTela
                                lista <- lerLevantam;
                                if(lista/=[]) 
                                then do
                                        if(opcao ==1) 
                                            then do
                                                    putStr (" LISTAR TODOS LEVANTAMENTOS \n\n"++(todosLevantamentos lista)++"\n");
                                            else do
                                                    putStr (" LISTAR TODOS LEVANTAMENTOS \n\n\t1 - Hoje\t2 - Qualquer Dia: ")
                                                    opc <- getLine
                                                    case opc of 
                                                        "1" -> do
                                                                putStr (" LISTAR TODOS LEVANTAMENTOS DE HOJE \n\n")
                                                                horasSistema <- getZonedTime;
                                                                if(contLevantamentoDia lista (take 10 (show horasSistema)))>0 then putStrLn (levantamentosDiaria lista (take 10 (show horasSistema))) else putStrLn "\n\t\tLista Vazia"
                                                        "2" -> do
                                                                putStr (" LISTAR TODOS LEVANTAMENTOS \n\n")
                                                                dia <- getDia
                                                                mes <- getMes
                                                                if((contLevantamentoDia lista ("2021-"++(menorQue10 mes)++"-"++(menorQue10 dia)))>0) then putStrLn (levantamentosDiaria lista ("2021-"++(menorQue10 mes)++"-"++(menorQue10 dia))) else putStrLn "\n\t\tLista Vazia"
                                                        otherwise -> relactoriosLevan 2
                                else putStrLn "Lista Vazia"

    transferencia :: IO ()
    transferencia = do 
                    getChar
                    limpaTela
                    listCli<- lerClientes
                    putStr "\n TRANSFERÊNCIA \n\n\tInforme o seu número de conta: "
                    conta <- getLine
                    putStr "\tInforme o número de conta que deseja tranferir: "
                    iban <- getLine
                    valor <- getValorTranferir
                    horasSistema <- getZonedTime
                    if(read valor>0) then tranferir listCli (read conta) (read iban) (read valor) (valor++","++(take 10 (show horasSistema))++","++(take 8( drop 11(show horasSistema)))++"|") else putStrLn ""

    -- FUNÇÃO PARA PEDIR O VALOR DO DEPOSITO
    getValorTranferir:: IO String
    getValorTranferir=do 
                        putStr "\tInforme o valor que deseja tranferir: "
                        valor<- getLine
                        if((length valor)>=3 && (all isDigit valor) && (read valor)>=100) then return valor
                            else do
                                    putStr "\t\tVALOR  INVALIDO!\n\tSó é possivel TRANSFERIR de 100kz pra cima\n\t\tDESEJA LER NOVAMENTE?\n\t\t1 - Sim\t\t2 - Não\n\t\t==> "
                                    opcao<-getLine
                                    if(opcao=="1") then do getValorTranferir else return "0"


    -- FUNCÇAO RESPONSAVEL POR FAZER O DEPOSITO DO VALOR TRANSFERIDO NO IBAN
    depositarTransferencia::Clientes->String->String->Float->String->IO ()
    depositarTransferencia listaArq conta iban valor dados = do 
                                                                if(existeCodigo listaArq conta)
                                                                    then do 
                                                                            escreverNoArquivo clientesFile (upDateList2 (listLinhasClientes (inserirValorTransferencia (listaArq) (conta) (iban) (valor))));
                                                                            escrever_arquivo depoFile ( show (iban)++","++dados) "";
                                                                            escrever_arquivo levantFile ( show (conta)++","++dados) "";
                                                                            escrever_arquivo transFile (show conta++","++(show iban)++","++dados) ""; 
                                                                            espera "\n\t\tTransferindo.";
                                                                            putStrLn "\n\tTranferência feita com sucesso";
                                                                    else putStr "\t\tConta não existe!!"
    -- FUNÇAO QUE FAZ O LEVENTAMENTO DOS VALORES PARA TRANSFER NOUTRA CONTA E USA A FUNÇÃO DEPOSITARTRANSFERENCIA PRA FAZER O DEPOSITO NO IBAN
    tranferir::Clientes->String->String->Float->String->IO ()
    tranferir listaArq conta iban valor dados = do 
                                                if(((existeCodigo listaArq conta) && (existeCodigo (listaArq) (iban))) && (conta/=iban))
                                                    then do
                                                            if((getSaldo (listaArq) (conta))>=valor) 
                                                                then do
                                                                        espera "\n\t\tVerificando O IBAN .";
                                                                        putStr (("\n\tNome: " ++(upDateList2 (getNome (listaArq) (iban))))++("    Conta: "++ show (getNumConta (listaArq) (iban)))++ ("    Valor a tranferir: "++(show valor))++"\n\n\t\tConfirmar a Transferencia?\n\t\t\t1 -SIM\n\t\tQualquer tecla para cancelar: ");
                                                                        opcao <- getLine;
                                                                        if(opcao=="1") 
                                                                        then do depositarTransferencia listaArq (conta) (iban) (valor) dados
                                                                        else putStrLn "\t\tTranferência Cancelada"
                                                                else do putStrLn ("\tImposivel tirar essa quantia porque o teu saldo atual é de: "++show(getSaldo (listaArq) (conta)))
                                                    else if(not(existeCodigo (listaArq) conta))  then do putStrLn "\t\tConta não existe!" else putStrLn "\t\tIBAN Invalido!!" 
                                                putStr "\n\tDigite ENTER para continuar\n\t"; getChar; putStrLn ""                 

    -- RELATORIO OU ESTATISTICA DE TODAS TRANSFERENCIAS
    relactoriosTrasf::Int->IO ()
    relactoriosTrasf opcao = do limpaTela;lista <- lerTransfer;
                                if(lista/=[]) 
                                then do
                                        if(opcao ==1) 
                                            then putStr (" LISTAR TODOS TRANSFERÊNCIAS \n\n"++ (todasTRansferencias lista));
                                            else do
                                                    putStr (" LISTAR TODOS TRANSFERÊNCIAS \n\n\t1 - Hoje\t2 - Qualquer Dia:")
                                                    opc <- getLine
                                                    case opc of 
                                                        "1" -> do
                                                                putStr (" LISTAR TODOS TRANSFERÊNCIAS DE HOJE \n\n")
                                                                horasSistema <- getZonedTime;
                                                                if((contTransferenciaDia lista (take 10 (show horasSistema)))>0) then putStrLn (transferenciasDiaria lista (take 10 (show horasSistema))) else putStrLn "\t\tLista Vazia"
                                                        "2" -> do
                                                                putStrLn (" LISTAR TODOS TRANSFERÊNCIAS \n")
                                                                dia <- getDia
                                                                mes <- getMes
                                                                if((contTransferenciaDia lista ("2021-"++(menorQue10 mes)++"-"++(menorQue10 dia)))>0) then putStrLn (transferenciasDiaria lista ("2021-"++(menorQue10 mes)++"-"++(menorQue10 dia))) else putStrLn "\n\t\tLista Vazia"
                                                        otherwise -> relactoriosTrasf 2
                                else putStrLn "Lista Vazia"
        