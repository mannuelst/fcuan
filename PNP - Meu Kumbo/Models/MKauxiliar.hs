module Models.MKauxiliar where
    import Control.Concurrent
    import System.Process
    import Models.MKconstrutores
    import Data.Char
    
    
    limpaTela= system "cls"

    saindo::IO ()
    saindo = espera ("\tSAINDO .")

    espera::String->IO ()
    espera titul0 = do putStr (titul0);threadDelay 109009;putStr ".";threadDelay 1000090;putStrLn "."
    
    meses = ["janeiro", "fevereiro", "março", 
                "abril","maio", "junho",
                "julho", "agosto", "setembro",
                "outubro", "novembro", "desembro"
            ]
     
    isMes::[String]-> String->Bool
    isMes [] x = False
    isMes (m : ms) x
            | map toLower x==m = True
            | otherwise = isMes ms x

    obterAdmin :: Admin -> String -> String
    obterAdmin Admin {nomeAdmin = n, senhaAdmin = s} prop
        | prop == "nome" = n
        | prop == "senha" = s
    
    msgEnd :: IO ()
    msgEnd = putStrLn "\nSaindo, até a próxima!\n"

    msgErro :: IO ()
    msgErro =
        putStr "Ups, algo deu errado!\nQuer tentar novamente?\n\t [1] - Sim\n\nt[2] - Não\n"
    
    -----Dados Básicos!!!
    isBilhete:: String -> Bool
    isBilhete num = if(((length num)== 14) && ((all isDigit (take 9 num)) && (all isAlpha (take 2 (drop 9 num))) && (all isDigit (drop 11 num))))
                        then True 
                        else False
    
    setBilhete:: Clientes -> IO String
    setBilhete lista = do
        putStr "\n Insera a identificação: "
        bi <- getLine
        if ((isBilhete bi)) 
            then 
                if (not (existeCodigo lista bi)) 
                    then return bi
                    else do
                        msgErro
                        opcao<- getLine
                        if (opcao == "1")then do setBilhete lista else return "0"
            else do
                msgErro
                opcao<- getLine
                if (opcao == "1")then do setBilhete lista else return "0"

    setTelefone::IO String
    setTelefone = do 
        putStr "\n Insira o contacto: "
        tlf <- getLine
        if(((length tlf)== 9)&& (all isDigit tlf))
            then return tlf
            else do 
                msgErro
                opcao <- getLine 
                if (opcao == "1")then do setTelefone else return "0"

    setNumDeConta :: String ->String
    setNumDeConta numBi = (take 9 numBi)

    setNomeCompleto :: IO String
    setNomeCompleto = do 
        putStr "\nInsira o nome: "
        nome <- getLine
        putStr "\nInsira o sobrenome: "
        sobreNome <- getLine
        if (((length nome)>1 && (length sobreNome)>1)&& ((all isAlpha nome) && (all isAlpha sobreNome)))
            then return (""++nome ++" "++ sobreNome)
            else do
                msgErro
                opcao<- getLine
                if (opcao == "1")then do setNomeCompleto else return "0"
    
    
    setDataNasc :: IO String
    setDataNasc = do 
        putStr "\nData de Nascimento: \n\tDia: "
        dia <- getLine
        putStr "\n\tMêS: "
        mes <- getLine
        putStr "\n\t Ano: "
        ano <- getLine
        if((all isDigit dia)&& (all isAlpha mes)&& (all isDigit ano))
            then do ---Verificar o toLower para str!!!!
                if (((2023 - read ano)>17 && (read ano > 1943))&&(read dia<=31 && read dia >0) &&(isMes meses mes))
                    then return (" "++dia ++"/"++mes++"/"++ano)
                    else do
                        msgErro
                        opcao <- getLine
                        if (opcao == "1") then setDataNasc else return "0" 
            else do
                msgErro
                opcao <- getLine
                if (opcao == "1") then setDataNasc else return "0" 
    
    getNome::Clientes->String->NomeCOMPLETO
    getNome ((codigo, nome, dat, bi, tel, saldo, datC):xs) codi |((codigo==codi)||(bi == codi)) = nome::NomeCOMPLETO
                                                          |otherwise = getNome xs codi
   
    getCodigo::Clientes->String->NumCONTA
    getCodigo ((codigo, nome, dat, bi, tel, saldo,dataCadastro):xs) codi |((show codigo)==(show codi)|| codi == bi) = codigo::NumCONTA
                                                                            |otherwise = getCodigo xs codi
    
   
    getNumConta::Clientes->String->NumCONTA
    getNumConta ((codigo, nome, dat, bi, tel, salario,dataCadastro):xs) codi |((show codigo)==(show codi)||codi == bi) = codigo::NumCONTA
                                                                            |otherwise = getNumConta xs codi
   
   
    getBi::Clientes->String->NumBI
    getBi ((codigo, nome, dat, bi, tel, salario,dataCadastro):xs) codi |((show codigo)==(show codi)||codi == bi) = bi::NumBI
                                                                        |otherwise = getBi xs codi


    getSaldo::Clientes->String->Saldo
    getSaldo ((codigo, nome, dat, bi, tel, salario,dataCadastro):xs) codi |((show codigo)==(show codi) || (codigo==codi)||codi == bi) = salario::Saldo
                                                                            |otherwise = getSaldo xs codi

    
    
    -- VERIFICA AS SENHS DOS USUARIOS
    autenticarDados :: String -> String ->Bool
    autenticarDados nome senha = if (((nome=="jp") || (nome=="ana" || nome=="obed" || nome=="lela")) && senha=="1012") then True else False
    -- LIMPA TELA
    


    -- PEGA OS DADOS DE UM CLIENTE E RETORNA UMA STING PARA O CADASTRAMENTO DO CLIENTE
    cadastrarCliente::Clientes->NumCONTA->NomeCOMPLETO->DataNAS->NumBI->NumTLF->Saldo->DataCadastro->Clientes
    cadastrarCliente [] cod nome  dia  bi tel sal dataCadastro = [(cod,nome, dia,bi, tel,sal,dataCadastro)]
    cadastrarCliente lista cod nome  dia  bi tel sal dataCadastro=lista++[(cod,nome, dia,bi, tel,sal,dataCadastro)]

    -- ALTERA OS VALORES (SALDO) DE UMA LISTA
    inserirValor::Clientes->String->Float->Clientes
    inserirValor ((codigo, nome, dat, bi, tel, salario, dataCadastro):xs) chave valor | (codigo==chave || chave == bi) = (codigo, nome, dat, bi, tel, (salario+(valor)),dataCadastro):(inserirValor xs chave valor)
                                                                                    | (codigo/=chave || bi /= chave) = (codigo, nome, dat, bi, tel, salario, dataCadastro):(inserirValor xs chave valor)
                                                                                    | otherwise = inserirValor xs chave valor
    inserirValor [] _ _ = []
    -- ALTERA OS VALORES (SALDO) DE UMA LISTA
    inserirValorTransferencia::Clientes->String->String->Float->Clientes
    inserirValorTransferencia ((codigo, nome, dat, bi, tel, salario, dataCadastro):xs) origem destino valor | (codigo==origem || origem == bi) = (codigo, nome, dat, bi, tel, (salario - valor),dataCadastro):(inserirValorTransferencia xs origem destino valor)
                                                                                                    | (origem==codigo|| origem == bi) = (codigo, nome, dat, bi, tel, (salario + valor),dataCadastro):(inserirValorTransferencia xs origem destino valor)
                                                                                                    | (codigo/=origem || bi /= origem) = (codigo, nome, dat, bi, tel, salario, dataCadastro):(inserirValorTransferencia xs origem destino valor)
                                                                                                    | otherwise = inserirValorTransferencia xs origem destino valor
    inserirValorTransferencia [] _ _ _ = []
    -- INSERE SENHAS NO VECTOR
    insertSenha::Senhas->String->Int->Senhas
    insertSenha [] letra codi = [(letra,codi)]
    insertSenha  lista letra codi =lista++[(letra,codi)]
    -- 
    -- 
    --  VERIFICAR EXISTENCIA DE UM DADO 
    -- VERIFICA SE A SENHA EXISTE
    existeSenha::Senhas->String->Int->Bool
    existeSenha [] _ _ = False
    existeSenha ((letra,cod):xs) lett codigo | ((letra,cod)==(lett,codigo)) = True
                                            | otherwise = existeSenha xs (lett) (codigo)
    -- VERIFICA SE O CODIGO EXISTE EM UMA LISTA
    existeCodigo::Clientes->String->Bool
    existeCodigo [] codigo = False
    existeCodigo ((cod, nome, dat, bi, tel, salario,dataCad):xs) codigo | (show codigo ==  show cod) || (bi == cod) = True
                                                                        | (length xs == 0) = False
                                                                        | otherwise = existeCodigo xs codigo

 
    
    -- CONTADOR DE TODOS CADASTROS FEITO DIARIAMENTE
    contCadastroDia::Clientes->String->Int
    contCadastroDia [] dataa= 0
    contCadastroDia ((codigo, nome, dat, bi, tel, salario, dataCadastro):xs) dataa| (show dataa==show dataCadastro) || (dataa==dataCadastro) = 1+contCadastroDia xs dataa
                                                                                | otherwise = contCadastroDia xs dataa
    -- CONTADOR DE TODAS TRANSFERENCIAS FEITA DIARIAMENTE
    contTransferenciaDia::Transferencias->String->Int
    contTransferenciaDia [] dataa= 0
    contTransferenciaDia ((cont,iba,sald,dat,hora):xs) dataa | (dataa==dat) =1+contTransferenciaDia xs dataa
                                                            | otherwise = contTransferenciaDia xs dataa

    -- CONTADOR DE TODOS DEPOSITOS FEITO DIARIAMENTE
    contDepositosDia::Depositos->String->Int
    contDepositosDia [] dataa= 0
    contDepositosDia ((cont,sald,dat,hora):xs) dataa | (dataa==dat) =1+contDepositosDia xs dataa
                                                    | otherwise = contDepositosDia xs dataa
    -- CONTADOR DE TODOS LEVANTAMENTOS FEITO DIARIAMENTE
    contLevantamentoDia::Levantamentos->String->Int
    contLevantamentoDia [] dataa= 0
    contLevantamentoDia ((cont,sald,dat,hora):xs) dataa | (dataa==dat) =1+contLevantamentoDia xs dataa
                                                        | otherwise = contLevantamentoDia xs dataa

    -- ESTRATO DE TODAS TRANSFERENCIAS EM UM MES
    estratoTransferencia::Transferencias->String->String->String
    estratoTransferencia [] cod dataa= ""
    estratoTransferencia ((cont,iba,sald,dat,hora):xs) cod dataa | ((drop 5 (take 7(dataa))==  (drop 5 (take 7(dat)))) || (drop 6 (take 7(dataa))== drop 6(take 7 (dat)))) && ((show cont==show cod) || (cont==cod)) = " \t Montante: -"++(show sald)++" \t Data: "++dat++" \t Hora: "++hora++" \n"++estratoTransferencia xs cod dataa
                                                                | otherwise = estratoTransferencia xs cod dataa
    -- ESTRATO DE TODAS TRANSFERENCIAS
    todosEstratoTransferencia::Transferencias->String->String
    todosEstratoTransferencia [] cod= ""
    todosEstratoTransferencia ((cont,iba,sald,dat,hora):xs) cod |  ((show cont==show cod) || (cont==cod)) = " \t Montante: -"++(show sald)++" \t Data: "++dat++" \t Hora: "++hora++" \n"++todosEstratoTransferencia xs cod
                                                                | otherwise = todosEstratoTransferencia xs cod 
    -- ESTRATO DE TODOS DEPOSITOS EM UM MES
    estratoDeposito::Depositos->String->String->String
    estratoDeposito [] cod dataa= ""
    estratoDeposito ((cont,sald,dat,hora):xs) cod dataa | ((drop 5 (take 7(dataa))==  (drop 5 (take 7(dat)))) || (drop 6 (take 7(dataa))== drop 6(take 7 (dat)))) && ((show cont==show cod) || (cont==cod))=" \t Montante: +"++(show sald)++" \t Data: "++dat++" \t Hora: "++hora++"  \n"++estratoDeposito xs cod dataa
                                                        | otherwise = estratoDeposito xs cod dataa
    -- ESTRATO DE TODOS DEPOSITOS
    todosEstratoDeposito::Depositos->String->String
    todosEstratoDeposito [] cod = ""
    todosEstratoDeposito ((cont,sald,dat,hora):xs) cod | ((show cont==show cod) || (cont==cod))= " \t Montante: +"++(show sald)++" \t Data: "++dat++" \t Hora: "++hora++"  \n"++todosEstratoDeposito xs cod
                                                    | otherwise = todosEstratoDeposito xs cod
    -- ESTRATO DE TODOS LEVANTAMENTOS
    estratoLevantamento::Levantamentos->String->String->String
    estratoLevantamento [] cod dataa= ""
    estratoLevantamento ((cont,sald,dat,hora):xs) cod dataa | ((drop 5 (take 7(dataa))==  (drop 5 (take 7(dat)))) || (drop 6 (take 7(dataa))== drop 6(take 7 (dat)))) && ((show cont==show cod) || (cont==cod)) = " \t Montante: -"++(show sald)++" \t Data: "++dat++" \t Hora: "++hora++" \n"++estratoLevantamento xs cod dataa
                                                            | otherwise = estratoLevantamento xs cod dataa
    todosEstratoLevantamento::Levantamentos->String->String
    todosEstratoLevantamento [] cod = ""
    todosEstratoLevantamento ((cont,sald,dat,hora):xs) cod | ((show cont==show cod) || (cont==cod))= " \t Montante: +"++(show sald)++" \t Data: "++dat++" \t Hora: "++hora++"  \n"++todosEstratoLevantamento xs cod
                                                        | otherwise = todosEstratoLevantamento xs cod

    -- 
    -- 
    --  LISTAR DADOS 

    -- LISTA UM CLIENTE 
    listUmCliente::[(NumCONTA, NomeCOMPLETO, DataNAS, NumBI,NumTLF,Saldo,DataCadastro)]->String->String
    listUmCliente [] _ = ""
    listUmCliente ((codigo, nome, dat, bi, tel, salario,dataCadastro):xs) cod |((show cod)==(show codigo) || (cod)==(codigo))= "\tConta: "++show(codigo)++"\tNome: "++(upDateList2 nome)++" \tData: "++dat++"\tBI: "++bi++"\tTelefone: "++(show tel)++"\tSaldo Atual: "++(show(salario))
                                                                            | otherwise = listUmCliente xs cod

    --  LISTA UM CLIENTE RETIRANDO O VAALOR DO LEVANTAMENTO 
    tirarValorLevantar::[(NumCONTA, NomeCOMPLETO, DataNAS, NumBI,NumTLF,Saldo,DataCadastro)]->Float->Int->String
    tirarValorLevantar [] _ _ = ""
    tirarValorLevantar ((codigo, nome, dat, bi, tel, salario,dataCadastro):xs) sal cod |((show cod)==(show codigo))= "\tConta: "++show(codigo)++"    Nome: "++(upDateList2 nome)++"    Data: "++dat++"    BI: "++bi++"    Tel: "++(show tel)++"    Saldo atual: "++(show(salario-sal))++"\n"
                                                                                    | otherwise = tirarValorLevantar xs sal cod


    --  LISTA TODOS CADASTROS EXISTENTE NO FICHEIRO -------------------
    listLinhasClientes::[(NumCONTA, NomeCOMPLETO, DataNAS, NumBI,NumTLF,Saldo,DataCadastro)]->String
    listLinhasClientes []= ""
    listLinhasClientes ((codigo, nome, dat, bi, tel, salario, dataCadastro):xs) | (xs/=[] || (length xs)>=0)= show(codigo)++","++( nome)++","++dat++","++bi++","++(show tel)++","++(show(salario))++","++dataCadastro++"|"++listLinhasClientes xs
                                                                                | otherwise = listLinhasClientes xs
    --  LISTA TODOS CADASTROS EXISTENTE NO FICHEIRO -------------------
    todosClientes::[(NumCONTA, NomeCOMPLETO, DataNAS, NumBI,NumTLF,Saldo,DataCadastro)]->String
    todosClientes []= ""
    todosClientes ((codigo, nome, dat, bi, tel, salario, dataCadastro):xs) | (xs/=[] || (length xs)>=0)="\tConta: "++show(codigo)++"   Nome: "++(upDateList2 nome)++"   Data de Nascimento: "++dat++"    BI: "++bi++"    Tel: "++(show tel)++"    Saldo Atual:"++(show(salario))++"    Data de Cadastro: "++dataCadastro++"\n"++todosClientes xs
                                                                        | otherwise = todosClientes xs

    --  LISTA TODOS CADASTROS DO DIA EXISTENTE NO FICHEIRO -------------------  
    clientesDiario::Clientes->String->String                                         
    clientesDiario [] dataa= ""
    clientesDiario ((codigo, nome, dat, bi, tel, salario, dataCadastro):xs)  dataa | (dataa==dataCadastro) ="\tConta: "++show(codigo)++"   Nome: "++(upDateList2 nome)++"   Data de Nascimento: "++dat++"    BI: "++bi++"    Tel: "++(show tel)++"    Saldo Atual:"++(show(salario))++"    Data de Cadastro: "++dataCadastro++"\n"++clientesDiario xs dataa
                                                                                | otherwise = clientesDiario xs dataa

    --  LISTA TODAS SENHAS DO DIA EXISTENTE NO FICHEIRO -------------------
    listarSenas::Senhas->String
    listarSenas [] = ""
    listarSenas ((letra,codigo):xs) | (xs/=[] || (length xs)>=0) = letra++","++(show codigo)++"|"++listarSenas xs
                                    |(xs==[] || (length xs)==0) = letra++","++(show codigo)
                                    | otherwise = listarSenas xs

    --  LISTA TODAS TRANSFERÊNCIAS DO DIA EXISTENTE NO FICHEIRO -------------------
    transferenciasDiaria::Transferencias->String->String
    transferenciasDiaria [] _ = ""
    transferenciasDiaria ((cont,iba,sald,dat,hora):xs) dataa | (dataa==dat) = "\tConta: "++(show cont)++"\tIBAN: "++(show iba)++"\tValor: "++(show sald)++"\tData: "++dat++"\tHora: "++hora++"\n"++transferenciasDiaria xs dataa
                                                            | otherwise = transferenciasDiaria xs dataa
    --  LISTA TODAS TRANSFERÊNCIAS EXISTENTE NO FICHEIRO -------------------
    todasTRansferencias::[(NumCONTA,NumCONTA,Saldo,Data,Hora)]->String
    todasTRansferencias [] = ""
    todasTRansferencias ((cont,iba,sald,dat,hora):xs) | (xs/=[]) || (length xs>=0) = "\tConta: "++(show cont)++"\tIBAN: "++(show iba)++"\tValor: "++(show sald)++"\tData: "++dat++"\tHora: "++hora++"\n"++todasTRansferencias xs
                                                    | otherwise = todasTRansferencias xs

    --  LISTA TODOS DEPOSITOS DO DIA EXISTENTE NO FICHEIRO -------------------
    depositoDiaria::Depositos->String->String
    depositoDiaria [] dat = ""
    depositoDiaria ((cont,sald,dat,hora):xs) dataa | ((show dataa)==(show dat))= "\tConta: "++(show cont)++"\tValor: "++(show sald)++"\tData: "++dat++"\tHora: "++hora++"\n"++depositoDiaria xs dataa
                                                | otherwise = depositoDiaria xs dataa
    --  LISTA TODOS DEPOSITOS EXISTENTE NO FICHEIRO -------------------
    todosDositos::[(NumCONTA,Saldo,Data,Hora)]->String
    todosDositos [] = ""
    todosDositos ((cont,sald,dat,hora):xs) | (xs/=[]) || (length xs>=0) = "\tConta: "++(show cont)++"\tValor: "++(show sald)++"\tData: "++dat++"\tHora: "++hora++"\n"++todosDositos xs
                                        | otherwise = todosDositos xs
    --  LISTA TODOS LEVANTAMENTOS DO DIA EXISTENTE NO FICHEIRO -------------------
    levantamentosDiaria::Levantamentos->String->String
    levantamentosDiaria [] _ = ""
    levantamentosDiaria ((cont,sald,dat,hora):xs) dataa | (dataa==dat) = "\tConta: "++(show cont)++"\tValor: "++(show sald)++"\tData: "++dat++"\tHora: "++hora++"\n"++levantamentosDiaria xs dataa
                                                        | otherwise = levantamentosDiaria xs dataa
    --  LISTA TODOS LEVANTAMENTOS EXISTENTE NO FICHEIRO -------------------
    todosLevantamentos::[(NumCONTA,Saldo,Data,Hora)]->String
    todosLevantamentos [] = ""
    todosLevantamentos ((cont,sald,dat,hora):xs) | (xs/=[]) || (length xs>=0) = "\tConta: "++(show cont)++"\tValor: "++(show sald)++"\tData: "++dat++"\tHora: "++hora++"\n"++todosLevantamentos xs
                                                | otherwise = todosLevantamentos xs


    --  CRIAR VECTOR DE CLIENTES-------------------
    clientes::[[String]]->Clientes
    clientes [] = []
    clientes ([cod,nome,dat,bi,tel,sal,datcad]:xs) = let codigo = (read cod)::NumCONTA
                                                         nom    = (nome)::NomeCOMPLETO
                                                         dat1   = (dat)::DataNAS
                                                         bi1    = (bi)::NumBI
                                                         tel1   = (tel)::NumTLF
                                                         sal1   =( read sal)::Saldo
                                                         dataCadastro = (datcad)::DataCadastro
                                                    in (codigo,nom,dat1,bi1,tel1,sal1,dataCadastro):(clientes xs)
    --  CRIAR VECTOR DE SENHAS-------------------
    senhas :: [[String]] ->Senhas
    senhas [] = []
    senhas ([letra,cod]:xs) = let letraSenha = (letra)::Service
                                  numeroSenha = (read cod)::NumOrdService
                            in (letraSenha,numeroSenha):(senhas xs)

    --  CRIAR VECTOR DE TRANSFERÊNCIAS -------------------
    tranferencias ::[[String]]->Transferencias
    tranferencias [] = [] 
    tranferencias ([cont,iba,sald,dat,hora]:xs) = let numeroConta = (read cont)::NumCONTA
                                                      iban = (read iba)::NumCONTA
                                                      saldo = (read sald)::Saldo
                                                      dataa = (dat)::Data
                                                      horas = (hora)::Hora
                                                in (numeroConta,iban,saldo,dataa,horas):(tranferencias xs)
    --  CRIAR VECTOR DE DEPOSITOS-------------------
    depositos ::[[String]]->Depositos
    depositos [] = [] 
    depositos ([conta,sald,dat,hora]:xs) = let numeroConta = (read conta)::NumCONTA
                                               saldo = (read sald)::Saldo
                                               dataa = (dat)::Data
                                               horas = (hora)::Hora
                                            in (numeroConta,saldo,dataa,horas):(depositos xs)
    --  CRIAR VECTOR DE LEVANTAMENTOS-------------------
    levantamentos ::[[String]]->Levantamentos
    levantamentos [] = [] 
    levantamentos ([conta,sald,dat,hora]:xs) = let numeroConta = (read conta)::NumCONTA
                                                   saldo = (read sald)::Saldo
                                                   dataa = (dat)::Data
                                                   horas = (hora)::Hora
                                                in (numeroConta,saldo,dataa,horas):(levantamentos xs)
    -- 
    -- 
    --  FUÇOÕES PARA ATUALIZAR AS LISTAS 

    -- REMOVE ALGUNS CARACTERES NUMA STRING FOI USADA PRA PEGAR OS DADOS DOS FICHEIROS
    upDateList::String->String
    upDateList [] = ""
    upDateList (x:xs) |(x==',')=['\t']++upDateList xs
                    |(x=='|')=['\n']++upDateList xs
                    |(x==' ')=['_']++upDateList xs
                    |(x=='\n')=upDateList xs
                    |otherwise =[x]++upDateList xs
    -- REMOVE ALGUNS CARACTERES NUMA STRING FOI USADA PRA INSERIR OS DADOS DOS FICHEIROS
    upDateList2::String->String
    upDateList2 [] = ""
    upDateList2 (x:xs) |(x=='[' || x==']' || x=='(' || x==')' )=upDateList2 xs
                    | (x=='_') = [' ']++upDateList2 xs
                    |otherwise =[x]++upDateList2 xs 
    
    menorQue10::String->String
    menorQue10 numero = if((read numero<10) && (length numero)==1) then "0"++numero else numero

    validarDiaMesAno::String->Int->Bool
    validarDiaMesAno numero cod = if(all isDigit numero) then
                                    if((read numero)>0 && (read numero)<13 && cod==2) then True 
                                        else if((read numero)>0 && (read numero)<32 && cod==1) then True 
                                    else if((read numero)>1980 && (read numero)<2021 && cod==3 && (2021-(read numero)>17)) then True else False
                                else False
    -- FUNÇÃO QUE PEDE O DIA
    getDia::IO String
    getDia=do 
            putStr "\tInsira o Dia\n\t==> "
            dia<- getLine
            if(validarDiaMesAno dia 1) 
                then do
                        return (dia)
                else do
                        putStr " \tDIA INVALIDO!\n\tDESEJA LER NOVAMENTE?\n\t1 - Sim\t2 - Não\n\t==> "
                        opcao<-getLine
                        if(opcao=="1") then do  getDia else return "0"
    -- FUNÇÃO QUE PEDE O MES
    getMes::IO String
    getMes=do 
            putStr "\tInsira o mes\n\t==> "
            mes<- getLine
            if validarDiaMesAno mes 2
                then do
                        return mes 
                else do
                        putStr " \tMÊS INVALIDO!\n\tDESEJA LER NOVAMENTE?\n\t1 - Sim\t2 - Não\n\t==> "
                        opcao<-getLine
                        if(opcao=="1") then do  getMes else return "0"