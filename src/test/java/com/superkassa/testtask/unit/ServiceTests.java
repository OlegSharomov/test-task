package com.superkassa.testtask.unit;

import com.superkassa.testtask.dto.RequestDto;
import com.superkassa.testtask.dto.ResponseDto;
import com.superkassa.testtask.entity.EntityDB;
import com.superkassa.testtask.entity.JsonbObject;
import com.superkassa.testtask.repository.RepositoryInterface;
import com.superkassa.testtask.service.ServiceClassImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {
    @InjectMocks
    private ServiceClassImpl serviceClass;
    @Mock
    private RepositoryInterface repository;

    @Test
    public void shouldReturnCorrectResponse() {
        JsonbObject jsonbObject1 = new JsonbObject();
        jsonbObject1.setCurrent(10.0);
        JsonbObject jsonbObject2 = new JsonbObject();
        jsonbObject2.setCurrent(20.0);
        RequestDto request = new RequestDto(1L, 10.0);
        EntityDB entityDB = new EntityDB(1L, jsonbObject1);
        EntityDB entityDBAfterSave = new EntityDB(1L, jsonbObject2);
        ResponseDto responseDto = new ResponseDto(20.0);
        Mockito.when(repository.findById(eq(1L))).thenReturn(Optional.of(entityDB));
        Mockito.when(repository.save(any())).thenReturn(entityDBAfterSave);
        ResponseDto result = serviceClass.changeValue(request);
        assertEquals(responseDto, result);
    }

    @Test
    public void shouldThrowExceptionWhenIdNotFound() {
        RequestDto request = new RequestDto(999L, 10.0);
        Mockito.when(repository.findById(eq(999L))).thenReturn(Optional.empty());
        Exception re = Assertions.assertThrows(RuntimeException.class,
                () -> serviceClass.changeValue(request));
        assertEquals("Entity with id 999 not found", re.getMessage());
    }

}
