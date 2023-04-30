module Models.MKconstrutores where


    type NumCONTA = String
    type NomeCOMPLETO = String
    type  DataNAS = String
    type NumBI = String
    type NumTLF = String 
    type Saldo = Float
    type Data = String
    type Hora = String
    type DataCadastro = String
    
    type Cliente = (NumCONTA, NomeCOMPLETO, DataNAS, NumBI, NumTLF, Saldo, DataCadastro) 
    type Clientes = [Cliente]

    --Senhas:
    type Service = String
    type NumOrdService = Int
    type Senha = (Service, NumOrdService)
    type Senhas = [Senha]

    -- DADOS DAS TRANFERÊNCIAS
    type Transferencia = (NumCONTA,NumCONTA,Saldo,Data,Hora)
    type Transferencias = [Transferencia]

    -- DADOS DO DEPOSITO
    type Deposito = (NumCONTA,Saldo,Data,Hora)
    type Depositos = [Deposito]
    --
    -- DADOS DO LEVANTAMENTO
    type Levantamento = (NumCONTA,Saldo,Data,Hora)
    type Levantamentos = [Levantamento]

    data Admin = Admin{ 
        nomeAdmin :: String,
        senhaAdmin :: String
        }deriving (Show, Read)


