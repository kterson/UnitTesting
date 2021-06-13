package magePackage;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Mage {
    @Getter
    private final String name;
    private final int level;
}
