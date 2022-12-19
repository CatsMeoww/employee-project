package org.zerock.security.domain;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.zerock.domain.vo.EmployeeVO;

import lombok.Getter;

@Getter
public class CustomUser extends User {
   private EmployeeVO employee;

   public CustomUser(EmployeeVO employee) {
      super(employee.getEmp_no().toString(), employee.getEmp_pw(), employee.getAuthList().stream()
            .map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
      this.employee = employee;
   }

}