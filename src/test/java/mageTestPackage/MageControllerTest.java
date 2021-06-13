package mageTestPackage;

import magePackage.*;
import magePackage.MageController;
import magePackage.MageRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MageControllerTest {

    @Mock
    private MageRepository mageRepository;

    @InjectMocks
    private MageController mageController;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void delete_ObjectExists_False() {
        doThrow(new IllegalArgumentException()).when(mageRepository).delete(any(String.class));

        String result = mageController.delete("Gandalf");
        assert result.equals("not found") : "should return done";
    }

    @Test
    public void delete_ObjectExists_True() {
        doNothing().when(mageRepository).delete(any(String.class));

        String result = mageController.delete("Gandalf");
        assert result.equals("done") : "should return done";
    }

    @Test
    public void find_ObjectExists_False() {
        doReturn(Optional.empty()).when(mageRepository).find(any(String.class));

        String result = mageController.find("Gandalf");
        assert result.equals("not found") : "should return not found";
    }

    @Test
    public void find_ObjectExists_True() {
        doReturn(Optional.of(Mage.class)).when(mageRepository).find(any(String.class));

        String result = mageController.find("Gandalf");
        assert result.equals("Gandalf") : "should return Gandalf";
    }

    @Test
    public void save_AlreadyExists_False() {
        //brak serwisu, więc brak wywołania metody z serwisu
        doNothing().when(mageRepository).save(any(Mage.class));

        String result = mageController.save("Gandalf", "1");
        assert result.equals("done") : "should return done";
    }

    @Test
    public void save_AlreadyExists_True() {
        doThrow(new IllegalArgumentException()).when(mageRepository).save(any(Mage.class));

        String result = mageController.save("Gandalf", "1");
        assert result.equals("bad request") : "should return bad request";
    }
}
