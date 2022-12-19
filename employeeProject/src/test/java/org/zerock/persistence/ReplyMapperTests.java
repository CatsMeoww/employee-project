package org.zerock.persistence;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.vo.BoardVO;
import org.zerock.domain.vo.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	private Long[] bnoArr = { 218L, 217L, 216L, 213L, 214L };

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper replyMapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies = 
		replyMapper.getListWithPaging(cri, 216L);
		replies.forEach(r -> log.info(r));
	}
	
	@Test
	public void testTotal() {
		Long bno = 216L;
		log.info(replyMapper.getCountByBno(bno));
	}
	
	@Test
	public void testUpdate() {
		Long rno = 10L;
		ReplyVO reply = replyMapper.read(rno);
		reply.setReply("update reply.....");
		int count = replyMapper.update(reply);
		log.info("......update count : " + count);
	}
	
	@Test
	public void testDelete() {
		int count = replyMapper.delete(1L);
		log.info(".............. delete result : " + count);
	}
	
	@Test
	public void testRead() {
		ReplyVO reply = replyMapper.read(5L);
		log.info(reply);
	}
	
	@Test
	public void testInsert() {
		IntStream.rangeClosed(1, 100)
		.forEach(i -> {
			BoardVO board = boardMapper.read(bnoArr[i%5]);
			ReplyVO vo = new ReplyVO();
			vo.setB_no(board.getB_no());
			vo.setReply("댓글 테스트"+i);
			vo.setEmp_no(board.getEmp_no());
			replyMapper.insert(vo);
		});
	}
	
	
}
