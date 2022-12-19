package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.vo.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class ReplyServiceImpl implements ReplyService {

	private final ReplyMapper replyMapper;
	private final BoardMapper boardMapper;
	
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long b_no) {
		// work
		return new ReplyPageDTO(replyMapper.getCountByBno(b_no), replyMapper.getListWithPaging(cri, b_no));
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long b_no) {
		// work
		log.info("......getList Cri =" + cri);
		log.info("......getList bno =" + b_no);
		return replyMapper.getListWithPaging(cri, b_no);
	}
	
	@Transactional
	@Override
	public int register(ReplyVO reply) {
		log.info(".......register = " + reply);
//		boardMapper.updateReplyCnt(reply.getB_no(), 1);
		return replyMapper.insert(reply);
	}

	@Override
	public ReplyVO get(Long r_no) {
		log.info(".........get = " + r_no);
		return replyMapper.read(r_no);
	}

	@Override
	public int modify(ReplyVO reply) {
		log.info("........modify = " + reply);
		return replyMapper.update(reply);
	}
	
	@Transactional
	@Override
	public int remove(Long r_no) {
		log.info("..........remove = " + r_no);
//		ReplyVO reply = replyMapper.read(r_no);
//		boardMapper.updateReplyCnt(reply.getB_no(), -1);
		return replyMapper.delete(r_no);
	}
	
	
	
}




