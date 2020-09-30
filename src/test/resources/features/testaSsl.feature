# language: pt

Funcionalidade: valida certificado ssl

Cenario: Testando um certificado valido
 Dado uma url '<url>'
 Quando a conexao retorna HTTPstatus ok para cada '<url>'
 Entao testo o certificado SSL para cada '<url>' e ele deve ter pelo menos 1 dia restante
 Exemplos:
  | url               |
  | https://github.com/                  |
  | teste1            |
  | teste2            |
  
  
