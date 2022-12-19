package org.zerock.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.vo.EmployeeVO;
import org.zerock.mapper.EmployeeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", " file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class EmployeeMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwencoder;

	@Setter(onMethod_ = @Autowired)
	private EmployeeMapper mapper;
	
	
	
	@Test
	public void testmatches() {
		EmployeeVO vo = mapper.read(49000L);
		log.info("=======================");
		log.info(pwencoder.matches("1234", vo.getEmp_pw()));
		log.info(vo);
	}
	
	@Test
	public void testPW() {
		log.info(pwencoder);
		log.info("=======================");
		log.info(pwencoder.matches("1234", "$2a$10$.hBisbihEUnk1DlNdDjDiuGL6k7pPK5vUB2UgWGfeLV9EPx50Wfhu"));
	}
	
	@Test
	public void testInsert() {
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 1, 1);

		EmployeeVO vo = new EmployeeVO();
		vo.setFirst_name("First");
		vo.setLast_name("Last");
		vo.setGender("M");
		vo.setTitle("Staff");
		vo.setDept_no("d001");
		vo.setBirth_date(cal.getTime());
		cal.set(2022, 11, 7);
		vo.setHire_date(cal.getTime());
		mapper.insertSelectKey(vo);

	}

	@Test
	public void testUpdate() {
		EmployeeVO vo = mapper.read(500001L);
		vo.setFirst_name("ttest");
		vo.setLast_name("ttest");
		mapper.update(vo);
	}

	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria();
		cri.setPageNum(2);
//		cri.setType("N");
//		cri.setKeyword("facello");
		log.info(mapper.getListWithPaging(cri));
	}

	@Test
	public void test() {
		Criteria cri = new Criteria();
		int i = 1;
		while (true) {
			cri.setPageNum(i);
			List<EmployeeVO> list = mapper.getListWithPaging(cri);
			if (list == null)
				break;
			list.forEach(emp -> {
				log.info(emp);
			});
			i++;
		}
		log.info("===============================");

	}

	@Test
	public void testGetTotalCount() {
		Criteria cri = new Criteria();
//		cri.setKeyword("facello");
//		cri.setType("N");
		log.info("..........total:" + mapper.getTotalCount(cri));
	}

	@Test
	public void testRead() {
		EmployeeVO vo = mapper.read(10006L);
		log.info(vo);
	}

}
