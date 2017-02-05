package com.exchange.backend.persistence.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Creates greenlucky.
 */
public class Authority implements GrantedAuthority {

    /**
     * Declares variable authority of this Authority.
     */
    private final String authority;

    /**
     * Sets authority given by authority.
     *
     * @param authority The variable input
     */
    public Authority(final String authority) {
        this.authority = authority;
    }

    @Override
    public final String getAuthority() {
        return authority;
    }
}
