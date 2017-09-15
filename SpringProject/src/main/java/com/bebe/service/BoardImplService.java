package com.bebe.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bebe.domain.BoardVO;
import com.bebe.persistence.BoardDAO;

@Service
public class BoardImplService implements BoardService {
	@Inject
	private BoardDAO dao;

	@Override
	public void create(BoardVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}
}
