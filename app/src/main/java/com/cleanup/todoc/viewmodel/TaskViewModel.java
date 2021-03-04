package com.cleanup.todoc.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * todoc-master
 * Created by LioNDeVLaB on 04/03/2021
 */
public class TaskViewModel extends ViewModel {

	// REPOSITORIES
	private final TaskDataRepository mTaskDataSource;
	private final ProjectDataRepository mProjectDataSource;
	private final Executor mExecutor;

	// DATA
	@Nullable
	private LiveData<List<Project>> mProjects;

	public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
		this.mTaskDataSource = taskDataSource;
		this.mProjectDataSource = projectDataSource;
		this.mExecutor = executor;
	}

	public void init() {
		if (this.mProjects != null) {
			return;
		}
		mProjects = mProjectDataSource.getProjects();
	}

	// ------------
	// FOR PROJECT
	// ------------
	@Nullable
	public LiveData<List<Project>> getProjects() {
		return this.mProjects;
	}

	// ------------
	// FOR TASK
	// ------------

	public LiveData<List<Task>> getTasks(long projectId) {
		return mTaskDataSource.getTasks(projectId);
	}

	public void createTask(Task task) {
		mExecutor.execute(() -> {
			mTaskDataSource.createTask(task);
		});
	}

	public void deleteTask(long taskId) {
		mExecutor.execute(() -> {
			mTaskDataSource.deleteTask(taskId);
		});
	}

	public void updateTask(Task task) {
		mExecutor.execute(() -> {
			mTaskDataSource.updateTask(task);
		});
	}
}