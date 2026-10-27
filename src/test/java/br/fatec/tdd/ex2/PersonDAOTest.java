package br.fatec.tdd.ex2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonDAOTest {

    private PersonDAO personDAO;

    @BeforeEach
    void setUp() {
        personDAO = new PersonDAO();
    }

    @Test
    @DisplayName("Pessoa válida deve retornar lista de erros vazia")
    void testPersonValida() {
        Person person = new Person(1, "João Silva", 30);
        person.addEmail(new Email(1, "joao@email.com"));

        List<String> errors = personDAO.isValidToInclude(person);
        assertTrue(errors.isEmpty(), "Pessoa válida não deveria possuir erros.");
    }

    @Test
    @DisplayName("Person nula deve retornar erro")
    void testPersonNula() {
        List<String> errors = personDAO.isValidToInclude(null);
        assertFalse(errors.isEmpty());
    }

    @Test
    @DisplayName("Nome com apenas uma parte deve ser inválido")
    void testNomeUmaParte() {
        Person person = new Person(1, "João", 25);
        person.addEmail(new Email(1, "joao@dominio.com"));

        List<String> errors = personDAO.isValidToInclude(person);
        assertFalse(errors.isEmpty());
        assertTrue(errors.stream().anyMatch(e -> e.contains("ao menos 2 partes")));
    }

    @Test
    @DisplayName("Nome com caracteres não alfabéticos deve ser inválido")
    void testNomeComNumerosOuSimbolos() {
        Person person = new Person(1, "João 123", 25);
        person.addEmail(new Email(1, "joao@dominio.com"));

        List<String> errors = personDAO.isValidToInclude(person);
        assertFalse(errors.isEmpty());
        assertTrue(errors.stream().anyMatch(e -> e.contains("exclusivamente por letras")));
    }

    @Test
    @DisplayName("Nome nulo ou vazio deve ser inválido")
    void testNomeNuloOuVazio() {
        Person person1 = new Person(1, null, 25);
        person1.addEmail(new Email(1, "joao@dominio.com"));

        Person person2 = new Person(2, "   ", 25);
        person2.addEmail(new Email(2, "joao@dominio.com"));

        assertFalse(personDAO.isValidToInclude(person1).isEmpty());
        assertFalse(personDAO.isValidToInclude(person2).isEmpty());
    }

    @Test
    @DisplayName("Idade no limite inferior válido: 1")
    void testIdadeLimiteInferiorValido() {
        Person person = new Person(1, "Bebê Silva", 1);
        person.addEmail(new Email(1, "bebe@dominio.com"));

        assertTrue(personDAO.isValidToInclude(person).isEmpty());
    }

    @Test
    @DisplayName("Idade no limite superior válido: 200")
    void testIdadeLimiteSuperiorValido() {
        Person person = new Person(1, "Matusalém Silva", 200);
        person.addEmail(new Email(1, "ancião@dominio.com"));

        assertTrue(personDAO.isValidToInclude(person).isEmpty());
    }

    @Test
    @DisplayName("Idade fora dos limites (< 1 e > 200) deve ser inválida")
    void testIdadeForaDosLimites() {
        Person personZero = new Person(1, "Ana Paula", 0);
        personZero.addEmail(new Email(1, "ana@dominio.com"));

        Person personNegativa = new Person(2, "Ana Paula", -5);
        personNegativa.addEmail(new Email(2, "ana@dominio.com"));

        Person personAcima = new Person(3, "Ana Paula", 201);
        personAcima.addEmail(new Email(3, "ana@dominio.com"));

        assertFalse(personDAO.isValidToInclude(personZero).isEmpty());
        assertFalse(personDAO.isValidToInclude(personNegativa).isEmpty());
        assertFalse(personDAO.isValidToInclude(personAcima).isEmpty());
    }

    @Test
    @DisplayName("Pessoa sem email associado deve retornar erro")
    void testSemEmails() {
        Person personSemLista = new Person(1, "Carlos Drummond", 45);

        Person personListaVazia = new Person(2, "Carlos Drummond", 45, Collections.emptyList());

        assertFalse(personDAO.isValidToInclude(personSemLista).isEmpty());
        assertFalse(personDAO.isValidToInclude(personListaVazia).isEmpty());
    }

    @Test
    @DisplayName("Email com formato inválido deve retornar erro")
    void testEmailFormatoInvalido() {
        // Casos que violam _____@____._____
        String[] emailsInvalidos = {
            "invalido",
            "sem_arroba.com",
            "sem_ponto@dominio",
            "@dominio.com",
            "usuario@.com",
            "usuario@dominio.",
            "usuario@dominio..com"
        };

        for (String emailInvalido : emailsInvalidos) {
            Person person = new Person(1, "Maria Clara", 28);
            person.addEmail(new Email(1, emailInvalido));

            List<String> errors = personDAO.isValidToInclude(person);
            assertFalse(errors.isEmpty(), "Email '" + emailInvalido + "' deveria ser inválido.");
        }
    }

    @Test
    @DisplayName("Múltiplos erros simultâneos devem ser acumulados na lista")
    void testMultiplosErrosSimultaneos() {
        // Nome inválido, idade inválida e sem email
        Person person = new Person(1, "Solo", 0);

        List<String> errors = personDAO.isValidToInclude(person);
        assertTrue(errors.size() >= 3, "Deveriam existir ao menos 3 erros acumulados.");
    }
}
