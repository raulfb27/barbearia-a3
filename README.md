# Sistema de Gestão de Barbearia (A3)

Aplicação de console desenvolvida em Java 17 para gerenciamento completo de uma barbearia, integrando cadastro de clientes, catálogo de serviços, registro de atendimentos e relatórios financeiros com validação de regras de negócio e testes unitários automatizados.

---

## Funcionalidades

* **Gerenciamento de Clientes (`MenuClientes`):** Cadastro, listagem, busca por ID ou nome, atualização e exclusão de clientes.
* **Gerenciamento de Serviços (`MenuServicos`):** Cadastro, listagem, busca por ID, atualização de preço/descrição e remoção de serviços oferecidos.
* **Gerenciamento de Atendimentos (`MenuAtendimentos`):** Registro de atendimentos vinculando cliente e serviço, consulta por ID, filtro por cliente ou período, edição de data/valor e exclusão.
* **Relatórios Financeiros (`MenuFinanceiro` / `FinanceiroService`):**
  * Total gasto por cliente (`totalPorCliente`)
  * Faturamento consolidado por período (`faturamentoPorPeriodo`)
  * Faturamento por serviço específico (`faturamentoPorServico`)
  * Cálculo de ticket médio geral (`ticketMedio`)

---

## Arquitetura e Estrutura de Pacotes

O projeto segue a separação em camadas dentro de `src/main/java/barbearia`:

```text
barbearia/
├── excecao/        # Exceções de domínio (ValidacaoException, RecursoNaoEncontradoException)
├── modelo/         # Entidades principais (Cliente, Servico, Atendimento)
├── repositorio/    # Persistência em memória e consultas (ClienteRepositorio, ServicoRepositorio, AtendimentoRepositorio)
├── servico/        # Regras de negócio e agregações financeiras (FinanceiroService)
└── ui/             # Menus interativos e ponto de entrada (Main, MenuPrincipal, MenuClientes, MenuServicos, MenuAtendimentos, MenuFinanceiro)