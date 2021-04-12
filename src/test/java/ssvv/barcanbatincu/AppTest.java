package ssvv.barcanbatincu;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import ssvv.barcanbatincu.domain.TemaLab;
import ssvv.barcanbatincu.exceptions.ValidatorException;
import ssvv.barcanbatincu.repo.TemaLabRepo;
import ssvv.barcanbatincu.validator.TemaLabValidator;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void addAssignmentTest1() {
        TemaLabValidator temaLabValidator = new TemaLabValidator();
        TemaLabRepo temaLabRepo = new TemaLabRepo(temaLabValidator);

        try {
            temaLabRepo.save(new TemaLab(1, "a fost si nu va mai fi", 2, 3));
            assert temaLabRepo.size() == 1;
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addAssignmentTest2() {
        TemaLabValidator temaLabValidator = new TemaLabValidator();
        TemaLabRepo temaLabRepo = new TemaLabRepo(temaLabValidator);
        try {
            temaLabRepo.save(null);
            assert temaLabRepo.size() == 0;
        } catch (Exception e) {
            assert e.toString().equals("java.lang.IllegalArgumentException: Entity can not be null!\n");
            e.printStackTrace();
        }
    }

    @Test
    public void addAssignmentTest3() {
        TemaLabValidator temaLabValidator = new TemaLabValidator();
        TemaLabRepo temaLabRepo = new TemaLabRepo(temaLabValidator);
        try {
            temaLabRepo.save(new TemaLab(1, "a fost si nu va mai fi", 16, 3));
            assert temaLabRepo.size() == 0;
        } catch (ValidatorException e) {
            assert e.toString().equals("ssvv.barcanbatincu.exceptions.ValidatorException: Termen limita invalid\n");
            e.printStackTrace();
        }
    }
}
