package com.rama.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
Integer id;
String name;
String email;
String phoneNumber;
boolean isVerfied;
String password;
String country;
String state;
String city;
@CreationTimestamp
LocalDateTime createdAt;
@UpdateTimestamp
LocalDateTime updatedAt;
}
