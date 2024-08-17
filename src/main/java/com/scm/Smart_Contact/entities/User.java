package com.scm.Smart_Contact.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User  implements UserDetails{
    
    private static final String FetchType = null;
    @Id
    private String userId;
    @Indexed(unique = true)
    @NotNull
    private String name;
    @Indexed(unique = true)
    @NotNull 
    private String email;
    @NotNull

   // @Getter(value=AccessLevel.NONE)
    private String password;
    
    @Size(max = 1000)
    private String about;
    @Size(max = 1000)
    private String profilePic;
    private String phoneNumber;

    //information
    @Getter(value=AccessLevel.NONE) 
    private boolean enabled=true;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    //SELF,GOOGLE,FACEBOOK,GITHUB
    
    private Providers provider=Providers.SELF;
    private String providerUserId;
   
   @DBRef 
   private List<Contact> contacts=new ArrayList<>();

  // @ElementCollection(fetch=FetchType.EAGER)
   private List<String>roleList=new ArrayList<>();

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<SimpleGrantedAuthority> roles=roleList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    return roles;
}

@Override
public String getUsername() {
   return this.email;
}

@Override
public boolean isAccountNonExpired()
{
    return true;
}

@Override
public boolean isAccountNonLocked()
{
    return true;
}

@Override
public boolean isCredentialsNonExpired() {
    return true;
}


public static String getFetchtype() {
    return FetchType;
}
@Override
public boolean isEnabled() {
    return this.enabled;
}
// @Override
// public String getPassword() {
//     return this.password;
// }
  
}
