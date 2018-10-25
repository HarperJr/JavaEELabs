package com.vlsu.com.vlsu.models;

public class Tool {
    private final String name;
    private float damage;
    private float hardness;
    private Enchant enchant;

    public Tool(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }

    public Tool setDamage(float damage) {
        this.damage = damage;
        return this;
    }

    public float getHardness() {
        return hardness;
    }

    public Tool setHardness(float hardness) {
        this.hardness = hardness;
        return this;
    }

    public Enchant getEnchant() {
        return enchant;
    }

    public Tool setEnchant(Enchant enchant) {
        this.enchant = enchant;
        return this;
    }

    @Override
    public String toString() {
        return "tool: " + name + "/hardness " + hardness + "/damage: " + damage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tool) {
            final Tool tool = (Tool) obj;
            return this.name.equals(tool.name) && this.damage == tool.damage
                    && this.hardness == tool.hardness && this.enchant == tool.enchant;
        } else return false;
    }
}
