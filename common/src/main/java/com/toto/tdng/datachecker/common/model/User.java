package com.toto.tdng.datachecker.common.model;

import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @JsonProperty("FullName")
    public String FullName;
    @JsonProperty("FirstName")
    public String FirstName;
    public String userPrincipalName;
    public String sub;
    @JsonProperty("sAMAccountName")
    public String sAMAccountName;
    @JsonProperty("LastName")
    public String LastName;
    public String username;
    public String email;

    public User(String token) {
        User creator = new User();
        if (token != null) {
            try {
                Base64.Decoder decoder = Base64.getUrlDecoder();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(new String(decoder.decode(token)));
                creator = mapper.readValue(node.toString(), User.class);
            } catch (JsonProcessingException e) {
                log.error("Unable to parse xUser Token : " + e.getMessage());
            }
        } else {
            log.warn("xUser token is null.");
        }

        this.FullName = creator.FullName;
        this.FirstName = creator.FirstName;
        this.userPrincipalName = creator.userPrincipalName;
        this.sub =  creator.sub;
        this.sAMAccountName = creator.sAMAccountName;
        this.LastName = creator.LastName;
        this.username =  creator.username;
        this.email = creator.email;
    }
}
