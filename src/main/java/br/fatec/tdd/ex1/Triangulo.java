package br.fatec.tdd.ex1;

public class Triangulo {

    public enum TipoTriangulo {
        EQUILATERO,
        ISOSCELES,
        ESCALENO,
        INVALIDO
    }

    public static TipoTriangulo classificar(int a, int b, int c) {
        // Validação de lados estritamente positivos
        if (a <= 0 || b <= 0 || c <= 0) {
            return TipoTriangulo.INVALIDO;
        }

        // Conversão para long para prevenir overflow aritmético
        long ladoA = a;
        long ladoB = b;
        long ladoC = c;

        // Condição de existência do triângulo: soma de dois lados maior que o terceiro
        if (ladoA + ladoB <= ladoC || ladoA + ladoC <= ladoB || ladoB + ladoC <= ladoA) {
            return TipoTriangulo.INVALIDO;
        }

        if (ladoA == ladoB && ladoB == ladoC) {
            return TipoTriangulo.EQUILATERO;
        }

        if (ladoA == ladoB || ladoA == ladoC || ladoB == ladoC) {
            return TipoTriangulo.ISOSCELES;
        }

        return TipoTriangulo.ESCALENO;
    }
}
