package br.fatec.tdd.ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PersonDAO {

    // Regex para validar se cada parte do nome possui apenas letras (incluindo acentuadas)
    private static final Pattern NOME_PARTE_PATTERN = Pattern.compile("^[a-zA-ZÀ-ÿ]+$");

    // Formato "_____@____._____", sendo que cada uma das 3 partes possui ao menos um caractere
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s.]+@[^@\\s.]+\\.[^@\\s.]+$");

    public List<String> isValidToInclude(Person person) {
        List<String> errors = new ArrayList<>();

        if (person == null) {
            errors.add("O objeto Person não pode ser nulo.");
            return errors;
        }

        // Validação 1: O nome é composto por ao menos 2 partes e deve ser composto de letras
        if (person.getName() == null || person.getName().trim().isEmpty()) {
            errors.add("O nome deve ser composto por ao menos 2 partes e formado apenas por letras.");
        } else {
            String[] partes = person.getName().trim().split("\\s+");
            if (partes.length < 2) {
                errors.add("O nome deve ser composto por ao menos 2 partes.");
            } else {
                for (String parte : partes) {
                    if (!NOME_PARTE_PATTERN.matcher(parte).matches()) {
                        errors.add("O nome deve ser composto exclusivamente por letras.");
                        break;
                    }
                }
            }
        }

        // Validação 2: A idade deve estar no intervalo [1, 200]
        if (person.getAge() < 1 || person.getAge() > 200) {
            errors.add("A idade deve estar no intervalo [1, 200].");
        }

        // Validação 3: O objeto Person deve ter pelo menos um objeto da classe Email associado
        List<Email> emails = person.getEmails();
        if (emails == null || emails.isEmpty()) {
            errors.add("O objeto Person deve ter pelo menos um objeto da classe Email associado.");
        } else {
            // Validação 4: O formato do email deve ser "_____@____._____" com cada parte tendo ao menos 1 caractere
            for (Email email : emails) {
                if (email == null || email.getName() == null || !EMAIL_PATTERN.matcher(email.getName()).matches()) {
                    errors.add("O email deve estar no formato '_____@____._____', com cada parte tendo ao menos um caractere.");
                }
            }
        }

        return errors;
    }
}
