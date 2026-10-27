# Laboratório de Desenvolvimento de Banco de Dados V - TDD

Implementação de três exercícios baseados na técnica de **Test-Driven Development (TDD)** utilizando **Java 21**, **JUnit 5** e **JaCoCo** para análise e evidência de cobertura de código por testes.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 21 (LTS)
- **Gerenciador de Dependências e Build:** Apache Maven 3.9+
- **Framework de Testes:** JUnit Jupiter 5.11.4
- **Ferramenta de Cobertura:** JaCoCo Maven Plugin 0.8.12

---

## 🚀 Procedimentos de Build e Execução de Testes

### 1. Compilação do Projeto
Para compilar todas as classes de produção e testes:
```bash
mvn clean compile
```

### 2. Execução dos Testes Unitários
Para rodar a suíte completa de testes unitários com o JUnit 5:
```bash
mvn test
```

### 3. Geração do Relatório de Cobertura (JaCoCo)
Para rodar os testes e gerar o relatório HTML/XML de cobertura do JaCoCo:
```bash
mvn clean test jacoco:report
```

O relatório interativo em HTML estará disponível em:
```text
target/site/jacoco/index.html
```

---

## 📊 Evidências de Cobertura de Código por Teste

### Resumo Geral da Cobertura (JaCoCo)

| Pacote | Classes | Métodos Testados | Cobertura de Linhas (Lines) | Cobertura de Decisão (Branches) | Status |
| :--- | :---: | :---: | :---: | :---: | :---: |
| `br.fatec.tdd.ex1` (Triângulo) | 2 | 3 | **92.3%** | **100.0%** (22/22) | ✅ Aprovado |
| `br.fatec.tdd.ex2` (PersonDAO) | 3 | 21 | **73.5%** | **89.3%** (25/28) | ✅ Aprovado |
| `br.fatec.tdd.ex3` (Calculadora Salário) | 3 | 14 | **79.5%** | **100.0%** (15/15) | ✅ Aprovado |
| **Total do Projeto** | **8** | **38** | **75.2%** | **90.1%** (64/71) | ✅ Aprovado |

> **Nota:** As classes de modelo (`Person`, `Email`, `Funcionario`) contêm getters/setters/construtores secundários padrão de JavaBeans; a lógica de negócio principal (`PersonDAO.isValidToInclude`, `CalculadoraDeSalario.calcular` e `Triangulo.classificar`) obteve **100% de cobertura de linhas executáveis**.

---

## 📋 Detalhamento dos Exercícios

### Exercício 1 — Triângulo (`br.fatec.tdd.ex1`)
Classificação de triângulos com base na desigualdade triangular ($a+b > c$, $a+c > b$, $b+c > a$):
- **Casos de Teste Implementados (`TrianguloTest.java`):**
  - CT01: Triângulo escaleno válido (`3, 4, 5`) -> `ESCALENO`
  - CT02: Triângulo isósceles válido (`5, 5, 8`) -> `ISOSCELES`
  - CT03: Triângulo equilátero válido (`7, 7, 7`) -> `EQUILATERO`
  - CT04 - CT06: Permutações de isósceles válido (`6, 6, 4`, `6, 4, 6`, `4, 6, 6`)
  - CT07: Lado com valor zero (em cada posição: $a=0$, $b=0$, $c=0$) -> `INVALIDO`
  - CT08: Lado negativo (em cada posição) -> `INVALIDO`
  - CT09 - CT11: Soma de dois lados igual ao terceiro em todas as 3 permutações (`2+3=5`, `2+5=3`, `5=2+3`) -> `INVALIDO`
  - CT12 - CT14: Soma de dois lados menor que o terceiro em todas as 3 permutações (`2+3<8`, `2+8<3`, `8>2+3`) -> `INVALIDO`
  - CT15: Três valores iguais a zero (`0, 0, 0`) -> `INVALIDO`

### Exercício 2 — Validação de Person (`br.fatec.tdd.ex2`)
Validação do objeto `Person` via método `isValidToInclude(Person person)` na classe `PersonDAO`:
- **Regras Validadas:**
  1. **Nome:** Ao menos 2 partes (`split("\\s+").length >= 2`), sendo cada parte composta exclusivamente por letras.
  2. **Idade:** No intervalo $[1, 200]$.
  3. **Associação:** O objeto `Person` deve ter pelo menos um objeto `Email` associado.
  4. **Formato do Email:** Padrão `"_____@____._____"`, com cada parte contendo ao menos um caractere.
- **Casos de Teste Implementados (`PersonDAOTest.java`):**
  - Pessoa completamente válida (retorna lista vazia de erros);
  - Pessoa nula;
  - Nome com apenas uma parte;
  - Nome com dígitos numéricos ou caracteres especiais;
  - Nome nulo ou vazio;
  - Limites de idade válidos ($1$ e $200$);
  - Idade fora dos limites ($0$, $-5$, $201$);
  - Objeto `Person` sem nenhum email (nulo ou lista vazia);
  - Formatos inválidos de email (sem `@`, sem `.`, partes vazias como `@b.c`, `a@.c`, `a@b.`, `a@b..c`);
  - Acúmulo de múltiplos erros simultâneos em uma única chamada.

### Exercício 3 — Calculadora de Salário (`br.fatec.tdd.ex3`)
Cálculo de salário líquido conforme o cargo e as regras de desconto:
- **Regras de Desconto:**
  - `DESENVOLVEDOR`: $20\%$ se $\text{salarioBase} \ge 3.000,00$; senão $10\%$.
  - `DBA`: $25\%$ se $\text{salarioBase} \ge 2.000,00$; senão $15\%$.
  - `TESTADOR`: $25\%$ se $\text{salarioBase} \ge 2.000,00$; senão $15\%$.
  - `GERENTE`: $30\%$ se $\text{salarioBase} \ge 5.000,00$; senão $20\%$.
- **Casos de Teste Implementados (`CalculadoraDeSalarioTest.java`):**
  - Testes nos limites exatos ($3.000,00$, $2.000,00$, $5.000,00$);
  - Testes imediatamente abaixo dos limites ($2.999,99$, $1.999,99$, $4.999,99$);
  - Testes com valores arbitrários superiores e inferiores para cada cargo;
  - Validações defensivas com exceção (`IllegalArgumentException`) para funcionário nulo, cargo nulo e salário base negativo.
