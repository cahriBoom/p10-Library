package com.rest.libraryFront.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rest.libraryFront.beans.ClientBean;
import com.rest.libraryFront.config.BCryptManagerUtil;
import com.rest.libraryFront.proxies.LibraryProxy;
import com.rest.libraryFront.service.ClientService;

@Service("clientService")
public class ClientServiceImplementation implements ClientService{

	@Autowired 
	private LibraryProxy libraryProxy;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		ClientBean client = libraryProxy.getClientByMail(username);
		if(client==null) {
			throw new UsernameNotFoundException(username + " not found");
		}
		return client;		
	}

	@Override
	public String addClient(ClientBean client) {
		String password = client.getPassword();
		client.setPassword(BCryptManagerUtil.passwordencoder().encode(password));
		return libraryProxy.addClientSubscribe(client);
	}

	@Override
	public ClientBean getByMail(String mail) {
		return libraryProxy.getClientByMail(mail);
	}
	
}
