package com.exchange.backend.persistence.domain;


import com.exchange.backend.enums.StatusEnum;

/**
 * Created by greenlucky on 1/31/17.
 */
public class Status {

    /**
     * Declares name with style is String.
     */
    private String name;

    /**
     * Declares description with style is String.
     */
    private String description;

    /**
     * Declares constructor with empty param.
     */
    public Status() {
    }

    public Status(StatusEnum statusEnum) {
        this.name = statusEnum.getName();
        this.description = statusEnum.getDescription();
    }


    /**
     * Declares constructor given by name and decsription.
     * @param name The variable input to the the name
     * @param description The variable input to the description
     */
    public Status(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Gets name of Status.
     * @return The name with style is string
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets variable name to name.
     * @param name The variable input
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets description of Status.
     * @return The description
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Sets description given by description.
     * @param description The variable input to description
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public final String toString() {
        return "Status{"
                + "name='" + name + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
