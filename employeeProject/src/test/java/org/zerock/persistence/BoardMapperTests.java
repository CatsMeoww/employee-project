package org.zerock.persistence;

import java.util.Random;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.vo.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;

	@Test
	public void testTotal() {
		Criteria cri = new Criteria();
		log.info(".........................Total:" + boardMapper.getTotalCount(cri));
	}
	
	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria();
//		cri.setType("T");
//		cri.setKeyword("TEST TITLE1");
		boardMapper.getListWithPaging(cri).forEach(b -> log.info(b));
	}
	


	@Test
	public void testRead() {
		log.info(boardMapper.read(11L));
	}

	@Test
	public void testInsert() {

		log.info("----------------------------------------------");
		
		Random random = new Random(System.currentTimeMillis());
		IntStream.rangeClosed(1, 100).forEach(i -> {
			try {
				Long emp_no = Long.valueOf(random.nextInt(490001)+10001);
				BoardVO board = BoardVO.builder().title("TEST TITLE"+i).content("TEST CONTENTS"+i).emp_no(emp_no).build();
				boardMapper.insert(board);	
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		});

		log.info("----------------------------------------------");

	}

	@Test
	public void testDelete() {
		boardMapper.delete(2L);
	}

	@Test
	public void testUpdate() {
		BoardVO vo = boardMapper.read(3L);
//		BoardVO vo = BoardVO.builder().b_no(3L).title("수정!!!").content("수정입니다.").build();
		vo.setTitle("수정 타이틀 !!");
		vo.setContent("수정 내용 !! !!");
		
		int result = boardMapper.update(vo);
		log.info("===========================================================");
		log.info("count.............." + result);
		log.info("===========================================================");
	}

}
