package com.nztech.nzcommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


//Criando novos erros para adicionar nos erros ja existentes
public class ValidationError extends CustomError {
    private List<FieldMessage> errors = new ArrayList<>();

    // instanciar valores que estao na classe pai extendida
    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    // adicionar mensagem nos erros ja existentes
    public void  addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
