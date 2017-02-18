package com.exchange.backend.persistence.domain;

/**
 * Created by greenlucky on 1/31/17.
 */
public class Category {

    /**
     * The name of type class.
     */
    private String name;

    /**
     * The description of class type.
     */
    private String description;

    private String slug;

    /**
     * Declares constructor with empty param.
     */
    public Category() {
    }

    /**
     * Declares constructor of type given by name and description.
     *
     * @param name with style of String
     * @param description with style of String
     */
    public Category(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * Gets The name of type class.
     *
     * @return name with style string
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets name given by name.
     *
     * @param name with style is string
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return A description
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description with style is string
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{"
                + "name='" + name + '\''
                + ", description='" + description + '\''
                + ", slug='" + slug + '\''
                + '}';
    }
}
