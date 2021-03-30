package ssvv.barcanbatincu.repo;
import ssvv.barcanbatincu.validator.IValidator;
import ssvv.barcanbatincu.domain.TemaLab;

public class TemaLabRepo extends AbstractCrudRepo<Integer,TemaLab> {

    public TemaLabRepo(IValidator<TemaLab> v){

        super(v);
    }

    public void prelungireTermenLimita(TemaLab t,int saptCurenta){
        if(saptCurenta<=t.getSaptammanaPredarii()){
            t.setTermenLimita(t.getTermenLimita()+1);
            update(t);
        }
    }

}