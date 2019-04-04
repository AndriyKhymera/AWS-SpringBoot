package com.example.demo.Model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {

    String url;
    String expiration;

}
