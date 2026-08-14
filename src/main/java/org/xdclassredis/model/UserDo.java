package org.xdclassredis.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDo implements Serializable {
    private Long id;
    private String name;
    private String sex;

    public UserDo(Long id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public UserDo() {
    }
}
