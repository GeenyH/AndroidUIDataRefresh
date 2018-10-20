package com.geeny.alluidatarefresh;

import com.geeny.alluidatarefresh.model.Teacher;

/**
 * Created by Leslie Huang on 2018/10/14
 */
public class TeacherRepository {
    private static TeacherRepository instance;

    private Teacher mTeacher;

    public static TeacherRepository getInstance() {
        if (instance == null) {
            instance = new TeacherRepository();

            instance.init();
        }
        return instance;
    }

    private void init() {
        mTeacher = new Teacher("Fred Chen", 30);
    }

    public Teacher getTeacher() {
        return mTeacher;
    }

    public void setTeacher(Teacher teacher) {
        this.mTeacher = teacher;
    }
}
