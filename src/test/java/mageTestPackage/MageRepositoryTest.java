package mageTestPackage;

import magePackage.Mage;
import magePackage.MageRepository;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import java.util.Optional;

public class MageRepositoryTest {

    @Test
    public void delete_ObjectExists_False() {
        MageRepository mageRepository = new MageRepository();

        assertThatIllegalArgumentException().isThrownBy(() -> {
            mageRepository.delete("Gandalf");
        });
    }

    @Test
    public void find_ObjectExists_False() {
        MageRepository mageRepository = new MageRepository();

        Optional<Mage> result = mageRepository.find("Gandalf");
        assert result.isEmpty() : "should be empty";
    }

    @Test
    public void find_ObjectExists_True() {
        MageRepository mageRepository = new MageRepository();
        Mage mage = Mage.builder().name("Gandalf").level(1).build();
        mageRepository.save(mage);

        Optional<Mage> result = mageRepository.find("Gandalf");
        assert result.equals(Optional.of(mage)) : "should have found";
    }

    @Test
    public void save_AlreadyExists_True() {
        MageRepository mageRepository = new MageRepository();
        Mage mage = Mage.builder().name("Gandalf").level(1).build();
        mageRepository.save(mage);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            mageRepository.save(mage);
        });
    }
}
