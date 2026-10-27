package br.fatec.tdd.ex1;

import br.fatec.tdd.ex1.Triangulo.TipoTriangulo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrianguloTest {

    @Test
    @DisplayName("CT01: Triângulo escaleno válido")
    void testTrianguloEscalenoValido() {
        assertEquals(TipoTriangulo.ESCALENO, Triangulo.classificar(3, 4, 5));
    }

    @Test
    @DisplayName("CT02: Triângulo isósceles válido")
    void testTrianguloIsoscelesValido() {
        assertEquals(TipoTriangulo.ISOSCELES, Triangulo.classificar(5, 5, 8));
    }

    @Test
    @DisplayName("CT03: Triângulo equilátero válido")
    void testTrianguloEquilateroValido() {
        assertEquals(TipoTriangulo.EQUILATERO, Triangulo.classificar(7, 7, 7));
    }

    @Test
    @DisplayName("CT04: Triângulo isósceles válido - Permutação 1 (a == b)")
    void testTrianguloIsoscelesPermutacao1() {
        assertEquals(TipoTriangulo.ISOSCELES, Triangulo.classificar(6, 6, 4));
    }

    @Test
    @DisplayName("CT05: Triângulo isósceles válido - Permutação 2 (a == c)")
    void testTrianguloIsoscelesPermutacao2() {
        assertEquals(TipoTriangulo.ISOSCELES, Triangulo.classificar(6, 4, 6));
    }

    @Test
    @DisplayName("CT06: Triângulo isósceles válido - Permutação 3 (b == c)")
    void testTrianguloIsoscelesPermutacao3() {
        assertEquals(TipoTriangulo.ISOSCELES, Triangulo.classificar(4, 6, 6));
    }

    @Test
    @DisplayName("CT07: Um valor zero (em a, b ou c)")
    void testUmValorZero() {
        assertEquals(TipoTriangulo.INVALIDO, Triangulo.classificar(0, 4, 5));
        assertEquals(TipoTriangulo.INVALIDO, Triangulo.classificar(4, 0, 5));
        assertEquals(TipoTriangulo.INVALIDO, Triangulo.classificar(4, 5, 0));
    }

    @Test
    @DisplayName("CT08: Um valor negativo (em a, b ou c)")
    void testUmValorNegativo() {
        assertEquals(TipoTriangulo.INVALIDO, Triangulo.classificar(-3, 4, 5));
        assertEquals(TipoTriangulo.INVALIDO, Triangulo.classificar(3, -4, 5));
        assertEquals(TipoTriangulo.INVALIDO, Triangulo.classificar(3, 4, -5));
    }

    @Test
    @DisplayName("CT09: Soma de 2 lados é igual ao terceiro lado - Permutação a + b == c")
    void testSomaDoisLadosIgualTerceiroPermutacao1() {
        assertEquals(TipoTriangulo.INVALIDO, Triangulo.classificar(2, 3, 5));
    }

    @Test
    @DisplayName("CT10: Soma de 2 lados é igual ao terceiro lado - Permutação a + c == b")
    void testSomaDoisLadosIgualTerceiroPermutacao2() {
        assertEquals(TipoTriangulo.INVALIDO, Triangulo.classificar(2, 5, 3));
    }

    @Test
    @DisplayName("CT11: Soma de 2 lados é igual ao terceiro lado - Permutação b + c == a")
    void testSomaDoisLadosIgualTerceiroPermutacao3() {
        assertEquals(TipoTriangulo.INVALIDO, Triangulo.classificar(5, 2, 3));
    }

    @Test
    @DisplayName("CT12: Soma de 2 lados é menor que o terceiro lado - Permutação a + b < c")
    void testSomaDoisLadosMenorTerceiroPermutacao1() {
        assertEquals(TipoTriangulo.INVALIDO, Triangulo.classificar(2, 3, 8));
    }

    @Test
    @DisplayName("CT13: Soma de 2 lados é menor que o terceiro lado - Permutação a + c < b")
    void testSomaDoisLadosMenorTerceiroPermutacao2() {
        assertEquals(TipoTriangulo.INVALIDO, Triangulo.classificar(2, 8, 3));
    }

    @Test
    @DisplayName("CT14: Soma de 2 lados é menor que o terceiro lado - Permutação b + c < a")
    void testSomaDoisLadosMenorTerceiroPermutacao3() {
        assertEquals(TipoTriangulo.INVALIDO, Triangulo.classificar(8, 2, 3));
    }

    @Test
    @DisplayName("CT15: Os três valores iguais a zero")
    void testTresValoresIguaisAZero() {
        assertEquals(TipoTriangulo.INVALIDO, Triangulo.classificar(0, 0, 0));
    }
}
