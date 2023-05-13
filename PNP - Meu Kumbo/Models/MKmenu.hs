module Models.MKmenu where

        import System.Exit
        import Data.Time
        import Data.Char
        import Models.MKopBank
        import Models.MKauxiliar
        import Models.MKconstrutores
        import Models.AuxiliarFile
        import Models.MKcliente

    -- MENU PRINCIPAL ONDE PEDE OS DADOS DOS ADMIN
        menuStart :: IO()
        menuStart = do
                loadFile
                putStr "---- BANCO MEU KUMBO | Bem-Vindo-----\n\nQual é o teu acesso?\n\t1 - Sou Cliente\n\t2 - Sou ADM\n\t0 - Sair\nPor favor, escolha uma opção:\t"
                opcao <- getLine
                menuAcesso opcao

        menuAcesso ::String-> IO()
        menuAcesso op = do
                case op of
                        "1"-> do 
                                senha <- ler__Senhas;
                                menuFilaEspera(senha)
                        "2" ->login
                        "0"-> msgEnd
                        _ ->menuStart


        login :: IO ()
        login = do
                putStr "\n----Meu Kumbo | Login administrador ----- "
                putStr "\nUsuario: "
                user_adm <- getLine
                putStr "\nSenha: "
                senha_adm <- getLine

                adminDados <- readFile acessoFile
                let admin = read adminDados :: Admin

                if(obterAdmin admin "nome" == user_adm && obterAdmin admin "senha" == senha_adm) 
                        then do
                                menuAtendimento
                        else do
                                putStrLn "\nUps! Login invalido!"
                                putStr "\nDeseja tentar fazer login como administrador novamente! (s/n): "
                                opcao <- getChar
                                if (toLower opcao == 's')
                                        then do login
                                        else menuStart
    
  {--  menuPrincipal::IO ()
    menuPrincipal = do 
                    limpaTela
                    putStr (" LOGIN \n\n\tInforme o NOME do utilizador: "); nome <- getLine
                    putStr("\tInformea SENHA do utilizador: "); senha <- getLine; putStrLn "\n"
                    if(autenticarDados nome senha) 
                        then 
                        else do 
                                putStr "Acesso Negado\n\n\tPressione ENTER para Continuar!...\n\tN - Para Terminar O Programa...\n\n"; opc <- getChar
                                if(opc=='n' || opc=='N') then exitSuccess else do menuPrincipal--}
    -- MENU DE ATENDIMENTO
        menuAtendimento::IO ()
        menuAtendimento = do 
                        limpaTela
                        putStr "MENU ATENDIMENTO \n\n\t1 - Atendimento Geral\n\t2 - Relatório Diário\n\t3 - Relatório geral\n\t4 - Sair\n\n\n\n\tPor favor, escolha uma opção: "
                        opcao <- getLine
                        case opcao of
                            "1" -> do 
                                    chamando;
                                    menuAtendimento;
                            "2" -> do 
                                    menuRelatorioDiario; 
                                    menuAtendimento; 
                            "3" -> do 
                                    menuRelatorioGeral; 
                                    menuAtendimento; 
                            "4" -> saindo;
                            _   -> menuAtendimento; 
        -- FUNÇÃO PARA CHAMAR O SERVIÇO
        atendementoSeSenha::IO ()
        atendementoSeSenha = do 
                                limpaTela
                                cli <- lerClientes
                                putStr "ATENDIMENTO RAPIDO \n\n"
                                putStrLn "\t1 - Criação de conta\n\t2 - Depósito\n\t3 - Levantamento\n\t4 - Transferência\n\t5 - Extracto\n\t6 - Consulta\n\t7 - Sair"
                                putStr "\n\n\n\tPor favor, escolha uma opção: "
                                opcao <- getLine;
                                case opcao of 
                                        "1" -> do
                                                putStrLn ("\n\tDIgite ENTER para continuar");
                                                cadastro
                                                chamando
                                        "2" -> do{
                                                if(cli/=[])
                                                then do{
                                                        putStrLn ("\n\tDIgite ENTER para continuar");
                                                        deposito;
                                                };else putStrLn "\n\tNão Existe Nenhuma Conta";
                                                chamando;};
                                        "3" ->do{
                                                if(cli/=[])
                                                then do{
                                                        putStrLn ("\n\tDIgite ENTER para continuar");
                                                        levantamento;
                                                };else putStrLn "\n\tNão Existe Nenhuma Conta";
                                                chamando;};
                                        "4" -> do{
                                                if(length cli>=2) 
                                                        then do{
                                                                putStrLn ("\n\tDIgite ENTER para continuar");
                                                                transferencia;
                                                        };else putStrLn "\tConta Que Pretende Tranferir ou receber não existe"; 
                                                chamando};
                                        "5" -> do
                                                putStrLn ("\n\tDIgite ENTER para continuar");
                                                estrato;chamando;
                                        "6"-> do 
                                                putStrLn ("\n\tDIgite ENTER para continuar");
                                                chamarConsultar; chamando;
                                        otherwise -> chamando
                                

        chamando::IO ()
        chamando = do 
                        limpaTela
                        putStr "MENU ATENDIMENTO GERAL \n\n\t1 - Chamar\n\t2 - Atender Pessoas Com Prioridade\n\t3 - Sair\n\n\n\n\tPor favor, escolha uma Serviço: "
                        opcao <- getLine; senha <-ler__Senhas; cli <- lerClientes
                        menuAtendimentoGeral cli senha opcao
        menuAtendimentoGeral ::Clientes->Senhas->String->IO ()
        menuAtendimentoGeral cli senhaList a=do case a of
                                                        "1" -> do{
                                                                if(senhaList/=[])
                                                                then do
                                                                        espera ("\tChamando .");putStrLn ("\n\n\t\tSenha: "++(mostrarChamada senhaList)++"\n\tDIgite ENTER para continuar");
                                                                        let vet= (tail senhaList);
                                                                        case fst( head senhaList) of {
                                                                                "A" -> do{
                                                                                        cadastro;
                                                                                        escreverNoArquivo (senhasFile) (upDateList2 (listarSenas vet));
                                                                                        chamando;};
                                                                                "B" -> do{
                                                                                        if(cli/=[])
                                                                                                then do{
                                                                                                        deposito;
                                                                                                        escreverNoArquivo (senhasFile) (upDateList2 (listarSenas vet));
                                                                                                };else putStrLn "\n\tNão Existe Nenhuma Conta";
                                                                                        chamando;};
                                                                                "C" -> do{
                                                                                        if(cli/=[]) 
                                                                                                then do{
                                                                                                        levantamento;
                                                                                                        escreverNoArquivo (senhasFile) (upDateList2 (listarSenas vet));
                                                                                                };else putStrLn "\n\tNão Existe Nenhuma Conta";
                                                                                        chamando};
                                                                                "D" -> do{
                                                                                        if(length cli>=2) 
                                                                                                then do{
                                                                                                        transferencia;
                                                                                                        escreverNoArquivo (senhasFile) (upDateList2 (listarSenas vet));
                                                                                                };else putStrLn "\tConta Que Pretende Tranferir ou receber não existe"; 
                                                                                        chamando};
                                                                                "E" -> do{
                                                                                        estrato;
                                                                                        escreverNoArquivo (senhasFile) (upDateList2 (listarSenas vet));
                                                                                        chamando;};
                                                                                "F" -> do{
                                                                                        chamarConsultar;
                                                                                        escreverNoArquivo (senhasFile) (upDateList2 (listarSenas vet));
                                                                                        chamando;};
                                                                                otherwise -> chamando;};
                                                                else do
                                                                        putStrLn "\tNão Existe Senha Para O Serviço Solicitado\n\tDirige A Fila De Espera Por Favor!\n\n\tDIgite Qualquer TECLA pra Continuar";
                                                                        getChar;
                                                                        chamando;};
                                                        "2" -> atendementoSeSenha
                                                        "3" ->saindo
                                                        _   -> chamando
        mostrarChamada::Senhas->String
        mostrarChamada ((letra,cod):xs) = letra++""++(show cod)
        chamarConsultar::IO ()
        chamarConsultar = do 
                                limpaTela
                                putStr "\n\n\n\tInforme O Numero da conta que deseja consultar: "
                                conta <- getLine;lista <-lerClientes;
                                if(existeCodigo lista (read conta)) 
                                then do
                                        putStrLn (" CONSULTAR \n\n\t"++(listUmCliente (lista) (read conta))++"\n")
                                else do
                                        putStr ("\tA conta: "++(conta)++" não existe!\n\tDeseja consultar Novamente?\n\t1 - SIM\t2 - NÃo: ")
                                        opcao <- getLine
                                        case opcao of 
                                                "1" -> chamarConsultar
                                                otherwise -> saindo
                                putStr "\n\tDigite ENTER para continuar\n\t"; getChar; putStrLn ""        
        -- ESTATISTICA TOTAL DOS DEPOSITOS, CADASTROS, TRANSFERENCIAS E LEVANTAMENTOS                         
        estatistica::Int->IO ()
        estatistica opcao = do 
                                limpaTela
                                trans <- lerTransfer; depos <- lerDeposito;leva <- lerLevantam;lista <- lerClientes;
                                if(opcao ==1) 
                                then do
                                        putStr (" LISTAR TODOS ESTATÍSTICAS \n\n\t"++(show (length lista)++" Clientes Cadastrados\n\t")++(show (length depos)++" Depositos Feitos\n\t")++(show (length leva)++" Levantamos Feitos\n\t")++(show (length trans)++" Transferências Feitas\n\t"));
                                else do
                                        putStr (" ESTATÍSTICAS \n\n\t1 - Hoje\t2 - Qualquer Dia\n\tEscolher a opção: ")
                                        opc <- getLine
                                        case opc of 
                                                "1" -> do
                                                        putStr (" LISTAR TODOS ESTATÍSTICAS DE HOJE \n\n");
                                                        horasSistema <- getZonedTime;
                                                        putStr ("\t"++(show (contCadastroDia lista (take 10 (show horasSistema)))++" Clientes Cadastrados\n\t")++(show (contTransferenciaDia trans (take 10 (show horasSistema)))++" Transferências Feitas\n\t")++(show (contDepositosDia depos (take 10 (show horasSistema)))++" Depositos Feitos\n\t")++(show (contLevantamentoDia leva (take 10 (show horasSistema)))++" Levantamos Feitos\n\n"));
                                                "2" -> do
                                                        putStr (" LISTAR TODOS ESTATÍSTICAS \n\n\t")
                                                        dia <- getDia
                                                        mes <- getMes
                                                        if((dia/="0") && (mes/="0")) 
                                                                then do 
                                                                        putStr (" LISTAR TODOS ESTATÍSTICAS \n\n\t")
                                                                        putStr (show (contCadastroDia lista ("2021-"++(menorQue10 mes)++"-"++(menorQue10 dia)))++" Clientes Cadastrados\n\t");
                                                                        putStr (show (contTransferenciaDia trans ("2021-"++(menorQue10 mes)++"-"++(menorQue10 dia)))++" Transferências Feitas\n\t");
                                                                        putStr ( show(contDepositosDia depos ("2021-"++(menorQue10 mes)++"-"++(menorQue10 dia)))++" Depositos Feitos\n\t");
                                                                        putStrLn ((show (contLevantamentoDia leva ("2021-"++(menorQue10 mes)++"-"++(menorQue10 dia))))++" Levantamos Feitos\n");
                                                                else estatistica 2
                                                otherwise -> estatistica 2
        -- ESTRATO                                  
        estrato::IO ()
        estrato  = do 
                        getChar 
                        limpaTela
                        putStr "\n LISTAR TODOS ESTATÍSTICAS \n\n\t1 - Mensal\t2 - Todo Estrato: "
                        opcao <- getLine; transf <- lerTransfer;deposi <- lerDeposito;levanta <- lerLevantam;cli <- lerClientes;
                        if(opcao=="1" || opcao=="2") 
                        then do
                                putStr "\n\tInforma O Numero da Conta: "
                                conta <-getLine
                                if((existeCodigo cli (read conta))) 
                                        then do
                                        if(opcao=="1") 
                                                then do
                                                        horasSistema <- getZonedTime;
                                                        putStr ("\n"++ (estratoTransferencia transf (read conta) (take 10 (show horasSistema))));
                                                        putStr ("\n"++ (estratoDeposito deposi (read conta) (take 10 (show horasSistema))));
                                                        putStr ("\n"++ (estratoLevantamento levanta (read conta) (take 10 (show horasSistema))));
                                                        putStrLn ("\n\t\t\t\tSaldo Disponível: "++show (getSaldo cli (read conta))++"\n");
                                                else do
                                                        putStrLn (todosEstratoTransferencia transf (read conta));
                                                        putStrLn (todosEstratoDeposito deposi (read conta));
                                                        putStrLn (todosEstratoLevantamento levanta (read conta));
                                                        putStrLn ("\n\t\t\t\tSaldo Disponível: "++show (getSaldo cli (read conta))++"\n");
                                        else do
                                                putStr "\n\tConta Não Existe\n\tDeseja tirar ESTRATO Novamente?\n\t1 - SIM\t2 - NÃO: "
                                                opcao <- getLine
                                                case opcao of
                                                        "1" -> estrato;
                                                        "2" -> saindo;
                                                        _ -> estrato;
                        else estrato;
                        putStr "\tDigite ENTER para continuar"; getChar; putStrLn ""
        -- MENU DO RELATORIO DIARIO
        menuRelatorioDiario::IO ()
        menuRelatorioDiario=do
                limpaTela
                putStr "MENU RELATÓRIO DIÁRIO \n\n\t1 - Criação de conta\n\t2 - Depósito\n\t3 - Levantamento\n\t4 - Transferência\n\t5 - Estatística\n\t6 - Voltar\n\n\n\n"
                putStr "\tPor favor, escolha uma opção: "
                opcao <- getLine
                case (opcao) of
                        "1" ->do
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                relactoriosCadas 2
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                menuRelatorioDiario
                        "2" ->do
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                relactorioDeposi 2
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                menuRelatorioDiario
                        "3" ->do
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                relactoriosLevan 2
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                menuRelatorioDiario
                        "4" ->do
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                relactoriosTrasf 2
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                menuRelatorioDiario
                        "5" -> do
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar;estatistica 2;putStrLn "\n\tDigite ENTER Para Continuar";getChar;
                                menuRelatorioDiario
                        "6" -> saindo
                        _  -> menuRelatorioDiario
        -- MENU DO RELATORIO GERAL
        menuRelatorioGeral::IO ()
        menuRelatorioGeral=do
                limpaTela
                putStr " MENU RELATÓRIO GERAL \n\n"
                putStrLn "\t1 - Criação de conta\n\t2 - Depósito\n\t3 - Levantamento\n\t4 - Transferência\n\t5 - Estatística\n\t6 - Voltar\n"
                putStr "\n\n"
                putStr "\tPor favor, escolha uma opção: "
                opcao <- getLine
                case (opcao) of
                        "1" ->do
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                relactoriosCadas 1
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                menuRelatorioGeral
                        "2" ->do
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                relactorioDeposi 1
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                menuRelatorioGeral
                        "3" ->do
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                relactoriosLevan 1
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                menuRelatorioGeral
                        "4" ->do
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                relactoriosTrasf 1
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                menuRelatorioGeral
                        "5" -> do
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                estatistica 1
                                putStrLn "\n\tDigite ENTER Para Continuar"
                                getChar
                                menuRelatorioGeral
                        "6" -> saindo
                        _  -> menuRelatorioGeral