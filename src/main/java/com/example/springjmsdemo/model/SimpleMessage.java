package com.example.springjmsdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMessage implements Serializable {

    // see Serializable docs (good practice)
    static final long serialVersionUID = 423043022374L;

    private UUID id;
    private String message;
}
