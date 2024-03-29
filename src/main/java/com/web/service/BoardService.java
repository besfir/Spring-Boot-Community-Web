package com.web.service;

import com.web.domain.Board;
import com.web.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository repository;

    public Page<Board> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 :
                pageable.getPageNumber() - 1, pageable.getPageSize());
        return repository.findAll(pageable);
    }

    public Board findBoardByIdx(Long idx) {
        return repository.findById(idx).orElse(new Board());
    }
}
