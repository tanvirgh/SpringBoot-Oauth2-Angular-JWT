package com.cmed.health.app.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @author razib
 * @date 5/6/18
 * @email fakrul@impelitsolutions.com
 */

@Service
public class ResponseMaker {

    public <T> ResponseEntity<T> responseForGet(Optional<T> optDto) {
        if (optDto.isPresent() == false)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(optDto.get());
    }

    public <T> ResponseEntity<Collection<T>> responseCollection(Optional<Collection<T>> optDtoCollection) {
        if (optDtoCollection.isPresent() == false)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        return ResponseEntity.ok(optDtoCollection.get());
    }

    public <T> ResponseEntity<T> responseForPost(Optional<T> optDto) {
        if (optDto.isPresent() == false)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(optDto.get());
    }


    public <T> ResponseEntity<T> responseForPatch(Optional<T> optDto) {
        if (optDto.isPresent() == false)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.status(HttpStatus.OK).body(optDto.get());
    }

}
