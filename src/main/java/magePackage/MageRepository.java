package magePackage;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class MageRepository {
    @Getter
    private final Collection<Mage> collection;

    public MageRepository() {
        collection = new ArrayList<>();
    }

    public Optional<Mage> find(String name) {
        for (Mage mage : collection) {
            if (mage.getName().equals(name)) {
                return Optional.of(mage);
            }
        }
        return Optional.empty();
    }

    public void delete(String name) {
        Optional<Mage> mage = find(name);

        if (mage.isEmpty()) {
            throw new IllegalArgumentException();
        }

        collection.remove(mage);
    }

    public void save(Mage mage) {
        Optional<Mage> m = find(mage.getName());
        if (m.isEmpty()) {
            collection.add(mage);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
