package io.mattw.youtube.datav3;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation to document which parts are accepted on each YouTube API entry point.
 *
 * @version 2018-12-08
 * @author mattwright324
 */
@Inherited
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AcceptsParts {
    Parts[] values();
}
