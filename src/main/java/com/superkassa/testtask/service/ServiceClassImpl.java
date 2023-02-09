package com.superkassa.testtask.service;

import com.superkassa.testtask.dto.RequestDto;
import com.superkassa.testtask.dto.ResponseDto;
import com.superkassa.testtask.entity.EntityDB;
import com.superkassa.testtask.repository.RepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServiceClassImpl implements ServiceClass {
    private final RepositoryInterface repository;

    @Override
    @Transactional(readOnly = false)
    public ResponseDto changeValue(RequestDto request) {
        EntityDB entity = repository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException(String.format("Entity with id %d not found", request.getId())));
        entity.getObj().setCurrent(entity.getObj().getCurrent() + request.getAdd());
        EntityDB readyEntity = repository.save(entity);
        return new ResponseDto(readyEntity.getObj().getCurrent());
    }
}
