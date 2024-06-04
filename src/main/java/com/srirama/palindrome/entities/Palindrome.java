package com.srirama.palindrome.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class Palindrome {
    @Id
    String name;
    boolean palindrome;
}
