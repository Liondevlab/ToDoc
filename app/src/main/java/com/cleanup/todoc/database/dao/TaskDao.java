package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {


        @Query("SELECT * FROM Task WHERE project_id = :projectId")
        LiveData<List<Task>> getTasks(long projectId);

        @Insert
        long insertTask(Task item);

        @Update
        int updateTask(Task item);

        @Query("DELETE FROM Task WHERE id = :taskId")
        int deleteTask(long taskId);
}
