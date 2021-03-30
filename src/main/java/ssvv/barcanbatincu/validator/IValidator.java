package ssvv.barcanbatincu.validator;
import ssvv.barcanbatincu.exceptions.ValidatorException;

public interface IValidator<E> {
    void validate(E entity) throws ValidatorException;
}