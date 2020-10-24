package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CredentialService {

    CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Create CredentialService");
    }

    public void addCredential(CredentialForm credentialForm){
        Credential credential = new Credential();

        credential.setUrl(credentialForm.getUrl());
        credential.setUserName(credentialForm.getUserName());
        credential.setPassword(credentialForm.getPassword());
        credentialMapper.insert(credential);

    }

    public int deleteCredential(CredentialForm credentialForm){
        return credentialMapper.delete(credentialForm.getCredentialId());

    }

    public List<Credential> getAllCredentials(){
        return credentialMapper.getAllCredentials();
    }

}
