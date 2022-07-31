package com.example.spring.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.app.dao.UserDao;
import com.example.spring.app.entity.User;

@Service
public class UserServieImpl implements UserService {
    @Autowired
    private UserDao userdao;
	@Override
	//metodo transac para q no cambie la BD
	@Transactional(readOnly=true)
	public Iterable<User> findAll() {
		
		return userdao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<User> finAll(Pageable pageable) {
	
		return userdao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<User> findById(Long id) {
		
		return userdao.findById(id);
	}

	@Override
	@Transactional
	public User save(User user) {
	
		return userdao.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		userdao.deleteById(id);
	}

}
