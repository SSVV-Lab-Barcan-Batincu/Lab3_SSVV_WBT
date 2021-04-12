package ssvv.barcanbatincu.repo;

import ssvv.barcanbatincu.validator.IValidator;
import ssvv.barcanbatincu.exceptions.ValidatorException;
import ssvv.barcanbatincu.domain.HasId;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCrudRepo<ID,E extends HasId<ID>> implements Repo<ID,E> {
    Map<ID,E> entities;
    IValidator<E> validator;
    public AbstractCrudRepo(IValidator v){
        entities =new HashMap<ID,E>();
        validator=v;
    }
    @Override
    public E findOne(ID id){
        if (entities.get(id)==null){
            return null;
        }else{
            if(id==null){
                throw new IllegalArgumentException();
            }else{
                return entities.get(id);
            }
        }

    }

    @Override
    public Iterable<E> findAll(){
        return entities.values();
    }
    @Override
    public E save(E entity) throws ValidatorException {
        if(entity==null){
            throw new IllegalArgumentException("Entity can not be null!\n");
        }
        try{
            validator.validate(entity);
            return entities.putIfAbsent(entity.getId(),entity);
        }catch(ValidatorException ex){
            throw new ValidatorException(ex.getMessage());
        }
    }
    @Override
    public E delete(ID id){
        return entities.remove(id);
    }
    @Override
    public E update(E entity) {
        try {
            if (entity == null) {
                throw new IllegalArgumentException("Entity can not be null!\n");
            } else {
                validator.validate(entity);
            }
        }catch(ValidatorException e){
            return null;
        }
        return entities.replace(entity.getId(), entity);
    }
    //@Override
    public long size(){
        return entities.size();
    }

}