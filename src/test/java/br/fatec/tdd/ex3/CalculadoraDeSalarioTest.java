package br.fatec.tdd.ex3;

import br.fatec.tdd.ex3.Funcionario.Cargo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraDeSalarioTest {

    private CalculadoraDeSalario calculadora;
    private static final double DELTA = 0.0001;

    @BeforeEach
    void setUp() {
        calculadora = new CalculadoraDeSalario();
    }

    // --- DESENVOLVEDOR ---
    @Test
    @DisplayName("DESENVOLVEDOR com salário >= 3000.00 deve ter 20% de desconto (limite exato)")
    void testDesenvolvedorSalarioLimiteExato3000() {
        Funcionario f = new Funcionario("Alice", "alice@email.com", 3000.0, Cargo.DESENVOLVEDOR);
        assertEquals(2400.0, calculadora.calcular(f), DELTA);
    }

    @Test
    @DisplayName("DESENVOLVEDOR com salário > 3000.00 deve ter 20% de desconto")
    void testDesenvolvedorSalarioMaiorQue3000() {
        Funcionario f = new Funcionario("Alice", "alice@email.com", 5000.0, Cargo.DESENVOLVEDOR);
        assertEquals(4000.0, calculadora.calcular(f), DELTA);
    }

    @Test
    @DisplayName("DESENVOLVEDOR com salário < 3000.00 deve ter 10% de desconto")
    void testDesenvolvedorSalarioMenorQue3000() {
        Funcionario f = new Funcionario("Alice", "alice@email.com", 2999.99, Cargo.DESENVOLVEDOR);
        assertEquals(2999.99 * 0.90, calculadora.calcular(f), DELTA);

        Funcionario f2 = new Funcionario("Alice", "alice@email.com", 2000.0, Cargo.DESENVOLVEDOR);
        assertEquals(1800.0, calculadora.calcular(f2), DELTA);
    }

    // --- DBA ---
    @Test
    @DisplayName("DBA com salário >= 2000.00 deve ter 25% de desconto (limite exato)")
    void testDbaSalarioLimiteExato2000() {
        Funcionario f = new Funcionario("Bob", "bob@email.com", 2000.0, Cargo.DBA);
        assertEquals(1500.0, calculadora.calcular(f), DELTA);
    }

    @Test
    @DisplayName("DBA com salário > 2000.00 deve ter 25% de desconto")
    void testDbaSalarioMaiorQue2000() {
        Funcionario f = new Funcionario("Bob", "bob@email.com", 4000.0, Cargo.DBA);
        assertEquals(3000.0, calculadora.calcular(f), DELTA);
    }

    @Test
    @DisplayName("DBA com salário < 2000.00 deve ter 15% de desconto")
    void testDbaSalarioMenorQue2000() {
        Funcionario f = new Funcionario("Bob", "bob@email.com", 1999.99, Cargo.DBA);
        assertEquals(1999.99 * 0.85, calculadora.calcular(f), DELTA);

        Funcionario f2 = new Funcionario("Bob", "bob@email.com", 1000.0, Cargo.DBA);
        assertEquals(850.0, calculadora.calcular(f2), DELTA);
    }

    // --- TESTADOR ---
    @Test
    @DisplayName("TESTADOR com salário >= 2000.00 deve ter 25% de desconto (limite exato)")
    void testTestadorSalarioLimiteExato2000() {
        Funcionario f = new Funcionario("Carol", "carol@email.com", 2000.0, Cargo.TESTADOR);
        assertEquals(1500.0, calculadora.calcular(f), DELTA);
    }

    @Test
    @DisplayName("TESTADOR com salário > 2000.00 deve ter 25% de desconto")
    void testTestadorSalarioMaiorQue2000() {
        Funcionario f = new Funcionario("Carol", "carol@email.com", 3000.0, Cargo.TESTADOR);
        assertEquals(2250.0, calculadora.calcular(f), DELTA);
    }

    @Test
    @DisplayName("TESTADOR com salário < 2000.00 deve ter 15% de desconto")
    void testTestadorSalarioMenorQue2000() {
        Funcionario f = new Funcionario("Carol", "carol@email.com", 1500.0, Cargo.TESTADOR);
        assertEquals(1275.0, calculadora.calcular(f), DELTA);
    }

    // --- GERENTE ---
    @Test
    @DisplayName("GERENTE com salário >= 5000.00 deve ter 30% de desconto (limite exato)")
    void testGerenteSalarioLimiteExato5000() {
        Funcionario f = new Funcionario("David", "david@email.com", 5000.0, Cargo.GERENTE);
        assertEquals(3500.0, calculadora.calcular(f), DELTA);
    }

    @Test
    @DisplayName("GERENTE com salário > 5000.00 deve ter 30% de desconto")
    void testGerenteSalarioMaiorQue5000() {
        Funcionario f = new Funcionario("David", "david@email.com", 10000.0, Cargo.GERENTE);
        assertEquals(7000.0, calculadora.calcular(f), DELTA);
    }

    @Test
    @DisplayName("GERENTE com salário < 5000.00 deve ter 20% de desconto")
    void testGerenteSalarioMenorQue5000() {
        Funcionario f = new Funcionario("David", "david@email.com", 4999.99, Cargo.GERENTE);
        assertEquals(4999.99 * 0.80, calculadora.calcular(f), DELTA);

        Funcionario f2 = new Funcionario("David", "david@email.com", 2500.0, Cargo.GERENTE);
        assertEquals(2000.0, calculadora.calcular(f2), DELTA);
    }

    // --- VALIDAÇÕES EXCEPCIONAIS ---
    @Test
    @DisplayName("Funcionário nulo deve lançar IllegalArgumentException")
    void testFuncionarioNulo() {
        assertThrows(IllegalArgumentException.class, () -> calculadora.calcular(null));
    }

    @Test
    @DisplayName("Funcionário sem cargo deve lançar IllegalArgumentException")
    void testFuncionarioSemCargo() {
        Funcionario f = new Funcionario("Sem Cargo", "sem@cargo.com", 3000.0, null);
        assertThrows(IllegalArgumentException.class, () -> calculadora.calcular(f));
    }

    @Test
    @DisplayName("Funcionário com salário negativo deve lançar IllegalArgumentException")
    void testFuncionarioSalarioNegativo() {
        Funcionario f = new Funcionario("Negativo", "neg@email.com", -100.0, Cargo.DESENVOLVEDOR);
        assertThrows(IllegalArgumentException.class, () -> calculadora.calcular(f));
    }
}
