package by.teachmeskills.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stormtrooper implements Prototype {
    private String force;
    private String weapon;
    private String mind;

    public Stormtrooper(Stormtrooper stormtrooper) {
        this.force = stormtrooper.getForce();
        this.weapon = stormtrooper.getWeapon();
        this.mind = stormtrooper.getMind();
    }

    @Override
    public Prototype clone() {
        return new Stormtrooper(this);
    }
}
