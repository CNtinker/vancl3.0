package com.van.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter

public class User {
    private Integer uid;
    private String login_name;
    private String pwd;
    private String real_name;
    private String sex;
    private Date date_birth;
    private String email;
    private String tell_phone;
    private String land_phone;
    private String head_picture;
    private Date reg_date;
    private Integer user_type;
    private Integer state;
}
