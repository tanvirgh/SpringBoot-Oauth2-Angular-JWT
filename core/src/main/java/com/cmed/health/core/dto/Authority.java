package com.cmed.health.core.dto;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */
public enum Authority {
    ADMIN(1L),
    MANAGER(2L),
    LEAD(3L),
    PARTICIPANTS(4L),
    OTHER(5L);

    public final Long ID;
    private Authority(Long id){
        ID = id;
    }

}
