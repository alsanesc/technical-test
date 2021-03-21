package org.technical.test.api.common;

import java.io.Serializable;

public abstract class BaseObject implements Serializable {

    public abstract boolean equals(Object o);

    public abstract int hashCode();

}
