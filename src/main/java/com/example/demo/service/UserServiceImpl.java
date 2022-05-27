package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Client;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	ClientRepository clientRepository;

	@Override
	public void save(User user) {
		Role r = roleRepository.findByNom("CLIENT");
		if (r == null)
			r = new Role("CLIENT");
		user.setRoles(Arrays.asList(r));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		Client client = new Client();
		client.setPassword(user.getPassword());
		client.setUsername(client.getUsername());
		clientRepository.save(client);
	}

	@Override
	public void supprimer(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public void ajouter(User user) {
		userRepository.save(user);
		SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public void modifier(User user) {
		User u = getUser(user.getId());
		if (u != null) {
			u.setUserName(user.getUserName());
			userRepository.save(u);
		}
	}

	@Override
	public User getUser(int id) {
		if (userRepository.existsById(id))
			return userRepository.findById(id).get();
		else
			return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user == null)
			throw new UsernameNotFoundException("Nom d'utilisateur ou mot de passe erroné");
		for (Role r : user.getRoles())
			System.out.println("Role:" + r.getNom());
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				user.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getNom()))
						.collect(Collectors.toList()));
	}

	@Override
	public List<User> liste() {
		return userRepository.findAll();
	}
}