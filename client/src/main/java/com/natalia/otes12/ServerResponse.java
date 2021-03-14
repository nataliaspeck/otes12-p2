package com.natalia.otes12;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true) /* Ignora propriedades retornadas pelo servidor que não são necessárias */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServerResponse {

    private String name;
    private String address;

    /* CÓDIGO VULNERÁVEL
    private String id;
    private String email;
    private String phone;
    private String streetAddress;
     */

}
