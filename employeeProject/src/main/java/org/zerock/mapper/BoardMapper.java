package org.zerock.mapper;

import java.util.List;


import org.zerock.domain.Criteria;
import org.zerock.domain.vo.BoardVO;

public interface BoardMapper {

	public List<BoardVO> getListWithPaging(Criteria cri);

	public BoardVO read(Long bno);

	public void insert(BoardVO board);

	public void insertSelectKey(BoardVO board);

	public int delete(Long bno);

	public int update(BoardVO board);

	public int getTotalCount(Criteria cri);

//	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);

}
