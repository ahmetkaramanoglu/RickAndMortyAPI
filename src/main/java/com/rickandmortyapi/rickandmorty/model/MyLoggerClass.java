package com.rickandmortyapi.rickandmorty.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyLoggerClass {
        @Id
        @UuidGenerator
        private String id;
        private String data;
        private LocalDateTime createdDate;
        private String path;
        public MyLoggerClass(String path, String data,LocalDateTime now) {
            this.path = path;
            this.data = data;
            this.createdDate = now;
        }
}
