package com.producingservice.app.domain;


/**
 * This interface exists for collecting ids and names in repositories from entities
 *
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface MetadataContainer {
    Long getId();
    String getName();
}
