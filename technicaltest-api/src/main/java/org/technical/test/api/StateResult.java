package org.technical.test.api;

import net.sf.oval.constraint.NotNull;
import org.technical.test.api.common.BaseObject;

import java.util.Objects;

public class StateResult extends BaseObject {

    @NotNull
    private String state;

    @NotNull
    private String description;

    public StateResult() {
    }

    public StateResult(@NotNull String state, @NotNull String description) {
        this.state = state;
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StateResult that = (StateResult) o;
        return state.equals(that.state) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, description);
    }

}
