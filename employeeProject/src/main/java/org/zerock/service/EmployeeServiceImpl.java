package org.zerock.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.EmployeeDTO;
import org.zerock.domain.vo.AuthVO;
import org.zerock.domain.vo.DeptEmpVO;
import org.zerock.domain.vo.EmployeeVO;
import org.zerock.mapper.DepartmentMapper;
import org.zerock.mapper.EmployeeMapper;
import org.zerock.mapper.TitleMapper;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeMapper employeeMapper;
	private final DepartmentMapper departmentMapper;
	private final TitleMapper titleMapper;
	private final PasswordEncoder pwencoder;
	
	
	@Override
	public List<EmployeeVO> getList(Criteria cri) {
		return employeeMapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		return employeeMapper.getTotalCount(cri);
	}
	
	@Transactional
	@Override
	public int register(EmployeeDTO dto) {
		EmployeeVO vo = dto.toEmployeeVO();
		
//		String pw = pwencoder.encode("1234");
//		vo.setEmp_pw(pw);
		vo.setEmp_pw(pwencoder.encode("1234"));
		employeeMapper.insertSelectKey(vo);
		
		List<AuthVO> list = new ArrayList<AuthVO>();
		
		AuthVO auth = new AuthVO();
		auth.setAuth("ROLE_ADMIN");
	    auth.setEmp_no(vo.getEmp_no());
	    list.add(auth);
//	      
//	      AuthVO auth2 = new AuthVO();
//	      auth2.setAuth("ROLE_MANAGER");
//	      auth2.setEmp_no(vo.getEmp_no());
//	      list.add(auth2);
//	      
//	      AuthVO auth1 = new AuthVO();
//	      auth1.setAuth("ROLE_USER");
//	      auth1.setEmp_no(vo.getEmp_no());
//	      list.add(auth1);
	      vo.setAuthList(list);
		
	     employeeMapper.insertAuth(vo);
	      
		dto.setEmp_no(vo.getEmp_no());
		departmentMapper.insertDeptEmployee(dto.toDeptEmpVO());
		return titleMapper.insert(dto.toTitleVO());
	}
	
	@Transactional
	@Override
	public int modify(EmployeeDTO dto) {
		employeeMapper.update(dto.toEmployeeVO());
		DeptEmpVO deptEmpVO = dto.toDeptEmpVO();
		if(departmentMapper.eqCurrentDept(deptEmpVO) == 0) { 
//			departmentMapper.updateDeptEmployee(deptEmpVO.getEmp_no());
//			deptEmpVO.setFrom_date(new Date());
//			departmentMapper.insertDeptEmployee(deptEmpVO);
		}
		return 0;
	}
	
	@Override
	public EmployeeVO get(Long emp_no) {
		return employeeMapper.read(emp_no);
	}
	
}







