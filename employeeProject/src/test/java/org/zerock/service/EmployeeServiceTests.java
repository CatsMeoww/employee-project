package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.EmployeeDTO;
import org.zerock.domain.vo.EmployeeVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class EmployeeServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private EmployeeService employeeService;
	
	@Test
	public void getList() {
		Criteria cri = new Criteria();
		log.info(employeeService.getList(cri));
	}
	
	@Test
	public void getTotal() {
		Criteria cri = new Criteria();
		log.info("...............getTotal:"+employeeService.getTotal(cri));
	}
	
	@Test
	public void testUpdate() {
		EmployeeVO vo = employeeService.get(500001L);
		EmployeeDTO empDTO = EmployeeDTO.builder()
		.emp_no(vo.getEmp_no())
		.dept_no("d001")
		.first_name("Test")
		.last_name("Lee")
		.title("Senior Staff")
		.gender(vo.getGender())
		.birth_date(vo.getBirth_date())
		.hire_date(vo.getHire_date())
		.build();
		
		employeeService.modify(empDTO);
//		log.info(vo);
	}
	
	
}
