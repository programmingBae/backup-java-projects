package com.example.teori3.DAO;

import java.util.List;

public interface daoInterface <E>{
    public int addData(E data);
    public int updateData(E data);
    public int deleteData(E data);

    public List<E> showData();
}
