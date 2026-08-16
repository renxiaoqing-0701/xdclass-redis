package org.xdclassredis.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserPointVO {

    private String phone;

    private String username;

    public UserPointVO(String username,String phone) {
        this.phone = phone;
        this.username = username;
    }

    public UserPointVO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPointVO that = (UserPointVO) o;
        return Objects.equals(phone, that.phone) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, username);
    }
}
