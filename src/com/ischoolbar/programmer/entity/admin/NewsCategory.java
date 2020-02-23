package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * ���ŷ���ʵ��
 */
@Component
public class NewsCategory {
    private long id;
    private String name;
    private int sort;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "NewsCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                '}';
    }
}
