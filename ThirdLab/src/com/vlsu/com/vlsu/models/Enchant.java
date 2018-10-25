package com.vlsu.com.vlsu.models;

import java.sql.Time;

public class Enchant {
    private final String name;
    private String description;
    private Time duration;

    public Enchant(String name) {
        this.name = name;
        this.description = "No description fot this enchant";
    }

    public String getDescription() {
        return description;
    }

    public Enchant setDescription(String description) {
        this.description = description;
        return this;
    }

    public Time getDuration() {
        return duration;
    }

    public Enchant setDuration(Time duration) {
        this.duration = duration;
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Enchant) {
            final Enchant enchant = (Enchant) obj;
            //Hardcoded bullshit here sorry ((((
            return this.name.equals(enchant.name) && this.description.equals(enchant.description)
                    && this.duration.getHours() == enchant.duration.getHours()
            && this.duration.getMinutes() == enchant.duration.getMinutes()
                    && this.duration.getSeconds() == enchant.duration.getSeconds();
        } else return false;
    }
}
