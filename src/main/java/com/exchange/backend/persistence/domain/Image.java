package com.exchange.backend.persistence.domain;

/**
 * Created by greenlucky on 1/31/17.
 */
public class Image {

    /**
     * The url is store the address of image.
     */
    private String url;

    /**
     * The title of this image.
     */
    private String title;

    /**
     * The alternate of this image.
     */
    private String alt;

    /**
     * Declares constructor of this image with empty param.
     */
    public Image() {
    }

    /**
     * Declares constructor of image given by url, title and alt.
     *
     * @param url the variable input to the url
     * @param title the variable input to title
     * @param alt the variable input to alt
     */
    public Image(final String url, final String title, final String alt) {
        this.url = url;
        this.title = title;
        this.alt = alt;
    }

    /**
     * Gets the url of this image.
     *
     * @return The url with style is String
     */
    public final String getUrl() {
        return url;
    }

    /**
     * Sets url of this image given by url.
     *
     * @param url The variable input to url
     */
    public final void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Gets the title of this image.
     *
     * @return the title with style is String.
     */
    public final String getTitle() {
        return title;
    }

    /**
     * Sets the title given by title.
     *
     * @param title the variable input to title
     */
    public final void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Gets alt of this image.
     * @return The alt of this image.
     */
    public final String getAlt() {
        return alt;
    }

    /**
     * Sets the alt of this image given by alt.
     * @param alt the variable input
     */
    public final void setAlt(final String alt) {
        this.alt = alt;
    }

    @Override
    public final String toString() {
        return "Image{"
                + "url='" + url + '\''
                + ", title='" + title + '\''
                + ", alt='" + alt + '\''
                + '}';
    }
}
