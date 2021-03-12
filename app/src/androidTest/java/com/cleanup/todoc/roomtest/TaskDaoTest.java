package com.cleanup.todoc.roomtest;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

	// DATA SET FOR TEST

	private static final long PROJECT_ID = 1;
	private static final Project PROJECT_DEMO = new Project(PROJECT_ID, "TEST_PROJECT", 0xFF777777);
	private static final Task NEW_TASK_AAA = new Task(PROJECT_ID, "AAA", 1);
	private static final Task NEW_TASK_BBB = new Task(PROJECT_ID, "BBB", 2);
	private static final Task NEW_TASK_CCC = new Task(PROJECT_ID, "CCC", 3);

	// RULES
	@Rule
	public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

	@Before
	public void initDb() {
		this.mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getTargetContext(),
				TodocDatabase.class)
				.allowMainThreadQueries()
				.build();
		mProjectDao = mDatabase.projectDao();
		mTaskDao = mDatabase.taskDao();
	}

	@After
	public void closeDb() {
		mDatabase.close();
	}

	// TESTS

	@Test
	public void GetProjects() throws InterruptedException {
		// BEFORE : Adding a new project
		mProjectDao.insertProject(PROJECT_DEMO);
		// TEST
		Project project = LiveDataTestUtil.getValue(mProjectDao.getProject(PROJECT_ID));
		assertTrue(project.getName().equals(PROJECT_DEMO.getName()) && project.getId() == PROJECT_ID);
	}

	@Test
	public void insertAndGetTasks() throws InterruptedException {
		// BEFORE : Adding a new project
		mProjectDao.insertProject(PROJECT_DEMO);
		mTaskDao.insertTask(NEW_TASK_AAA);
		mTaskDao.insertTask(NEW_TASK_BBB);
		mTaskDao.insertTask(NEW_TASK_CCC);

		// TEST
		List<Task> tasks = LiveDataTestUtil.getValue(mTaskDao.getTasks());
		assertEquals(3, tasks.size());
	}

	@Test
	public void insertAndDeleteTask() throws InterruptedException {
		// BEFORE : Addind demo project and a demo task. Next, get the Task and delete it
		mProjectDao.insertProject(PROJECT_DEMO);
		mTaskDao.insertTask(NEW_TASK_AAA);
		List<Task> taskAdded = LiveDataTestUtil.getValue(this.mDatabase.taskDao().getTasks());
		assertEquals("AAA", taskAdded.get(0).getName());
		assertEquals(1, taskAdded.size());

		// TEST
		mTaskDao.deleteTask(taskAdded.get(0));
		List<Task> listedTasks = LiveDataTestUtil.getValue(this.mTaskDao.getTasks());
		assertEquals(0, listedTasks.size());
	}
}
