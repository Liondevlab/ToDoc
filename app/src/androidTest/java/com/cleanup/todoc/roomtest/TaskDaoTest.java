package com.cleanup.todoc.roomtest;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * todoc-master
 * Created by LioNDeVLaB on 08/03/2021
 */
@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

	// FOR DATA
	private TodocDatabase mDatabase;
	private TaskDao mTaskDao;
	private ProjectDao mProjectDao;

	// RULES
	@Rule
	public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

	@Before
	public void initDb() throws Exception {
		this.mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
				TodocDatabase.class)
				.allowMainThreadQueries()
				.build();
	}

	@After
	public void closeDb() throws Exception {
		mDatabase.close();
	}

	// TESTS

}