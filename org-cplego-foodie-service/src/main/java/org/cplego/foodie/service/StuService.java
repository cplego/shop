package org.cplego.foodie.service;

import org.cplego.foodie.pojo.Stu;

public interface StuService {

    public Stu getStu(int id);

    public void saveStu();

    public void updateStu(int id);

    public void deletStu(int id);

}
