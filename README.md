# controle-despesas
Api para gestao de contas a pagar.

# Motivação

Eu particularmente possuo uma dificuldade enorme em organizar minhas contas.
Vencimentos no dia 1, 4, 10º dia do mês, 15 e etc.

Essa API me permitirá organizar melhor minhas despesas mensais.

# Detalhamento

O diferencial nesta aplicação é ser possível cadastrar despesas não recorrentes, por exemplo: Festa de aniversário.
E também será possível cadastrar despesas recorrentes, como conta de luz, agua etc.
A diferença é que uma conta recorrente o usuário cadastra apenas uma vez e o sistema irá automaticamente cria-las nos próximos meses.
Para os casos de contas que o valor nao se alteram, é uma opção bastante interessante.

OBS: Próximo ao vencimento das contas, um email é enviado ao usuário como forma de ajuda-lo a lembrar de liquidar aquela despesa.

Caso de uso:
Cadastro de conta de internet. Valor: 100,00. Recorrente? SIM. Data de vencimento: 03/08/2023.
Esta conta refere-se ao mes 08/2023.

No mês 09/2023 será criado automaticamente para este usuário o mesmo registro de conta com a data de vencimento para o mes corrente,
promovendo assim uma melhor experiencia de uso para o usuário(Eu mesmo rsrs).

- Processo automatizado identifica a conta de internet configurada como recorrente.
- Cria um registro identico com a data de vencimento atualizada para o mes seguinte.
- Registro disponibilizado para o usuário para consulta, atualização, exclusao.

# Tecnologias

- Java 17
- Spring boot 3.1
- Spring Data JPA
- Spring Security 6.1
- Spring Security Oauth2
- MySQL
- Flyway
- Lombok
- Modelmapper
  
# Funcionalidades implementadas

- Cadastro de usuário
- Login
- Obter dados usuário logado
- Cadastrar uma despesa
- Listar todas as despesas do usuário

# Funcionalidades pendentes

- Testes unitários/integrado
- Buscar despesa por identificador
- Atualização de despesa
- Exclusão de despesa
- Atualização dos dados de usuário
- Implementação job gerador de registros recorrentes
- Implementação job lembrete de contas a vencer.

