package edu.northeastern.cs5200.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyPersonDetails extends Person implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	public MyPersonDetails(final Person users) {
        super(users);
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> out = new ArrayList<SimpleGrantedAuthority>();
		out.add(new SimpleGrantedAuthority("ROLE_" + getRole()));
		return out;
	}

	@Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
