package com.bebe.persistence;


import java.util.List;

import com.bebe.domain.BoardVO;
import com.bebe.page.Criteria;
import com.bebe.page.Search;

public interface BoardDAO {
	public void create(BoardVO vo)throws Exception;
	public BoardVO read(int bno) throws Exception;
	public void update(BoardVO vo)throws Exception;
	public void delete(int bno) throws Exception;
	public List<BoardVO> listAll() throws Exception;
	public List<BoardVO> listpage(int page) throws Exception;
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public int totalCount() throws Exception;
	public List<BoardVO> listSearch(Search cri) throws Exception;
	public int searchTotal (Search cri) throws Exception;
}
