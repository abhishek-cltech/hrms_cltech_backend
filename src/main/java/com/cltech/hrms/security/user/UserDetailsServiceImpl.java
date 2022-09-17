package com.cltech.hrms.security.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cltech.hrms.bean.user.User;
import com.cltech.hrms.repository.user.UserRepository;

@Service
@Qualifier("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
	private static Logger Logger=LogManager.getLogger(UserDetailsServiceImpl.class);
	
   @Autowired
   private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			 
			User userByEmail = userRepository.findUserByEmail(username);
			if(userByEmail==null) {
				 throw new UsernameNotFoundException("could not found user!!");
			}
			
			UserDetails userDetails=new CustomUserDetails(userByEmail);
			
		    return userDetails;
	    
		}catch(Exception e) {
			Logger.error(e.getMessage(),e);
			return null;

		}
		
	}

}
