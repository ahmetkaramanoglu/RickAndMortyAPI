package com.rickandmortyapi.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.descriptor.jdbc.JsonAsStringJdbcType;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyLoggerClass {
        @Id
        @UuidGenerator
        private String id;
        //columnDefinition ile özel SQL türleri veya sütun tanımları belirtmek için kullanılır.
        @Column(columnDefinition = "LONGTEXT")
        private String request;
        @Column(columnDefinition = "LONGTEXT")
        private String response;
        private LocalDateTime createdDate;
        private String username;
        private String path;
        //headerlar, query parametreleri, path variablelar, request body gibi requestin içindeki her şeyi loglamak istiyorsak
        public MyLoggerClass(String path, String response, String request,String username,LocalDateTime createdDate) {
            this.path = path;
            this.request = request;
            this.response = response;
            this.username = username;
            this.createdDate = createdDate;
        }
}
