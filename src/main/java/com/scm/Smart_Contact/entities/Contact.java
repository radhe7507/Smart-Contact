package com.scm.Smart_Contact.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection="Contacts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {
    
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    @Size(max=1000)
    private String description;
    private boolean favorite=false;
    private String websiteLink;
    private String linkedInLink;
   
   @DBRef
   private User user; 

    @DBRef
   private List<SocialLink> links=new ArrayList<>();

}
