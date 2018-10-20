package com.geeny.alluidatarefresh;

import com.geeny.alluidatarefresh.model.Student;

/**
 * Created by Leslie Huang on 2018/10/14
 */
public class StudentRepository {
    private static StudentRepository instance;

    private Student mStudent;

    public static StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();

            instance.init();
        }
        return instance;
    }

    private void init() {
        mStudent = new Student("Leslie Huang", "2013314229");
    }

    public Student getStudent() {
        return mStudent;
    }

    public void setStudent(Student student) {
        this.mStudent = student;
    }
}
