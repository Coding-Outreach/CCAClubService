package com.codingoutreach.clubservice.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Email {
    private String email;
    private String subject;
    private String text;
}