package de.l3s.icrawl.api.reddit;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public class ResultWrapper {
    @JsonTypeInfo(include = As.EXTERNAL_PROPERTY, property = "kind", use = Id.NAME)
    private Result data;

    public Result getData() {
        return data;
    }

    void setData(Result data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResultWrapper)) {
            return false;
        }
        return Objects.equals(data, ((ResultWrapper) obj).data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return String.format("ResultWrapper [data=%s]", data);
    }

}
