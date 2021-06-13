package magePackage;

import java.util.Optional;

public class MageController {
    private MageRepository repository;

    public MageController(MageRepository repository) {
        this.repository = repository;
    }

    public String find(String name) {
        Optional<Mage> mage = repository.find(name);
        if (mage.isEmpty()) {
            return "not found";
        } else {
            return name;
        }
    }

    public String delete(String name) {
        try {
            repository.delete(name);
        } catch (IllegalArgumentException ex) {
            return "not found";
        }
        return "done";
    }

    public String save(String name, String level) {
        try {
            repository.save(Mage.builder().name(name).level(Integer.parseInt(level)).build());
        } catch (IllegalArgumentException ex) {
            return "bad request";
        }
        return "done";
    }
}
